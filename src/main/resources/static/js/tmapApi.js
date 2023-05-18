// TODO 1 취소가 2번 초과 할 경우 MapLayer 오류 발생함
// TODO 2 보도로 변경 할 것도 고려해야함

let markers = []; // 경유지 마커들
let marker_s; // 시작
let marker_e; // 끝
let calVal = '';
let viaPointName = '';
let mapDiv = "";

// 페이지가 로딩이 된 후 호출하는 함수입니다.
window.onload = function initTmap() {
    // map 생성
    // Tmapv2.Map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
    map = new Tmapv2.Map("map_div", {
        center: new Tmapv2.LatLng(36.35086524077589, 127.45422567640077), // 지도 초기 좌표
        width: "100%", // 지도의 넓이
        height: "100%", // 지도의 높이
        zoom: 15
    });
    map.addListener("click", onClick); //map 클릭 이벤트를 등록합니다.
}

function onClick(e) {
    // //
    if (!marker_s) {
        // 시작 마커가 없으면
        createMarker('Start');
    } else if (!marker_e) {
        // 끝 마커가 없으면
        createMarker('End');
    } else {
        // 경유지 마커를 찍을 때
        createMarker(viaPointName);
    }

    function createMarker(title) {
        const lonlat = e.latLng;
        // Marker 객체 생성.
        const marker = new Tmapv2.Marker({
            position: new Tmapv2.LatLng(lonlat.lat(), lonlat.lng()), //Marker의 중심좌표 설정.
            map: map, //Marker가 표시될 Map 설정.
            title: title
        });

        $('#removeBtn').on("click", function () {
            marker.setMap(null);
            marker_s = null;
            marker_e = null;
            markers = [];
        });

        reverseGeo(marker.getPosition().lng(), marker.getPosition().lat());

        if (title === 'Start') {
            marker_s = marker;
            marker_s.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png");

            console.log("start lat : " + marker_s.getPosition().lat() + ", start lng : " + marker_s.getPosition().lng());
            console.log();
        } else if (title === 'End') {
            marker_e = marker;
            marker_e.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png");

            console.log("end lat : " + marker_e.getPosition().lat() + ", end lng : " + marker_e.getPosition().lng());
        } else {
            markers.push(marker);

            $('#createBtn').off().on("click", function () {
                ajaxParams(markers, marker_s, marker_e);
            });

            $('#createPlanBtn').off("click").on("click", function () {
                serverFetch(markers, marker_s, marker_e);
            });
        }
    }
}

function ajaxParams(markers, marker_s, marker_e) {
    if (marker_s != null && marker_e != null) {

        var viaPoints = makeViaPoints(markers);
        const data_s = marker_s.getPosition();
        const data_e = marker_e.getPosition();

        const params = {
            reqCoordType: "WGS84GEO",
            resCoordType: "EPSG3857",
            startName: "출발",
            startX: data_s.lng().toString(),
            startY: data_s.lat().toString(),
            startTime: "202304120800",
            endName: "도착",
            endX: data_e.lng().toString(),
            endY: data_e.lat().toString(),
            viaPoints
        };
        ajaxReq(params);
    }
}

function serverFetch(markers, marker_s, marker_e) {

    if (marker_s != null && marker_e != null) {
        var markerPoint = (createPlanViaPoints(markers));

        const data_s = marker_s.getPosition();
        const data_e = marker_e.getPosition();

        const smarker = {
            "lon": data_s.lng().toString(),
            "lat": data_s.lat().toString(),
            "calendar": calVal,
            "markerType": 0
        };
        markerPoint.push(smarker);

        const emarker = {
            "lon": data_e.lng().toString(),
            "lat": data_e.lat().toString(),
            "calendar": calVal,
            "markerType": 2
        }
        markerPoint.push(emarker);

        var planDTO = {
            "name": $('#planName').val(),
            "contents": $('#planContents').val(),
            "lonLatList": markerPoint
        }

        console.log(planDTO);
        fetch('/plan/createPlan', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(planDTO)
        }).then(response => {
            return response.json();
        }).then(data => {
            console.log(data);
        }).catch(error => {
            console.error(error);
        });
    }
}

function ajaxReq(req) {
    var headers = {};
    headers["appKey"] = "6MTwtT0OK18O1A8FGiL349WFB2UyKhI11K5MsjXN";

    $.ajax({
        type: "POST",
        headers: headers,
        url: "https://apis.openapi.sk.com/tmap/routes/routeOptimization10?version=1&format=json",//
        async: false,
        contentType: "application/json",
        data: JSON.stringify(req),
        success: function (response) {

            var resultData = response.properties;
            var resultFeatures = response.features;

            // 결과 출력
            var tDistance = "총 거리 : " + (resultData.totalDistance / 1000).toFixed(1) + "km,  ";
            var tTime = "총 시간 : " + (resultData.totalTime / 60).toFixed(0) + "분";

            $("#result").text(tDistance + tTime);

            for (var i in resultFeatures) {
                var geometry = resultFeatures[i].geometry;
                var properties = resultFeatures[i].properties;

                const drawInfoArr = [];
                if (geometry.type == "LineString") {
                    for (var j in geometry.coordinates) {
                        // 경로들의 결과값(구간)들을 포인트 객체로 변환
                        var latlng = new Tmapv2.Point(geometry.coordinates[j][0], geometry.coordinates[j][1]);
                        // 포인트 객체를 받아 좌표값으로 변환
                        var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(latlng);
                        // 포인트객체의 정보로 좌표값 변환 객체로 저장
                        var convertChange = new Tmapv2.LatLng(convertPoint._lat, convertPoint._lng);

                        drawInfoArr.push(convertChange);
                    }

                    let polyline = new Tmapv2.Polyline({
                        path: drawInfoArr,
                        strokeColor: "#3399FF",
                        strokeWeight: 3,
                        map: map,
                    });

                    $('#removeBtn').on("click", function () {
                        polyline.setPath(0);
                    });

                }
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

// x, y 좌표를 가지고 주소를 반환해줌 + 주소의 특정 이름으로 그 지역의 날씨 알려주는 기상청 api도 포함
function reverseGeo(lon, lat) {
    var headers = {};
    headers["appKey"] = "6MTwtT0OK18O1A8FGiL349WFB2UyKhI11K5MsjXN";

    $.ajax({
        method: "GET",
        headers: headers,
        url: "https://apis.openapi.sk.com/tmap/geo/reversegeocoding?version=1&format=json&callback=result",
        async: false,
        data: {
            "coordType": "WGS84GEO",
            "addressType": "A10",
            "lon": lon,
            "lat": lat
        },
        success: function (response) {
            // 3. json에서 주소 파싱
            var arrResult = response.addressInfo;

            //법정동 마지막 문자
            var lastLegal = arrResult.legalDong
                .charAt(arrResult.legalDong.length - 1);

            // 새주소
            newRoadAddr = arrResult.city_do + ' '
                + arrResult.gu_gun + ' ';

            if (arrResult.eup_myun == ''
                && (lastLegal == "읍" || lastLegal == "면")) {//읍면
                newRoadAddr += arrResult.legalDong;
            } else {
                newRoadAddr += arrResult.eup_myun;
            }
            newRoadAddr += ' ' + arrResult.roadName + ' '
                + arrResult.buildingIndex;

            // 새주소 법정동& 건물명 체크
            if (arrResult.legalDong != ''
                && (lastLegal != "읍" && lastLegal != "면")) {//법정동과 읍면이 같은 경우

                if (arrResult.buildingName != '') {//빌딩명 존재하는 경우
                    newRoadAddr += (' (' + arrResult.legalDong
                        + ', ' + arrResult.buildingName + ') ');
                } else {
                    newRoadAddr += (' (' + arrResult.legalDong + ')');
                }
            } else if (arrResult.buildingName != '') {//빌딩명만 존재하는 경우
                newRoadAddr += (' (' + arrResult.buildingName + ') ');
            }

            // 구주소
            jibunAddr = arrResult.city_do + ' '
                + arrResult.gu_gun + ' '
                + arrResult.legalDong + ' ' + arrResult.ri
                + ' ' + arrResult.bunji;
            //구주소 빌딩명 존재
            if (arrResult.buildingName != '') {//빌딩명만 존재하는 경우
                jibunAddr += (' ' + arrResult.buildingName);
            }

            result = "새dAddr주소 : " + newRoadAddr + "</br>";
            result += "지번주소 : " + jibunAddr + "</br>";
            result += "위경도좌표 : " + lat + ", " + lon;

            var resultDiv = document.getElementById("result");
            // 받아온 주소값
            result = newRoadAddr;
            viaPointName = result;

            // 주소 값 스페이스바 기준으로 배열로 나눔
            const split = result;
            const splitResult = result.split(" ");

            // n번째 배열을 스페이스 기준으로 나눠진 값으로 불러옴 대전광역시 서구 ~~~ => 대전광역시
            // console.log(splitResult[0]);
            $('#location').text(splitResult[0]);

            fetch('../weather/api/changeAddress', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(splitResult[0]),
            })
                .then(response => response.json())
                .then(data => {

                    var today = new Date();

                    var year = today.getFullYear();
                    var month = ('0' + (today.getMonth() + 1)).slice(-2);
                    var day = ('0' + today.getDate()).slice(-2);

                    var toDate = year + month + day;
                    // console.log(toDate);

                    const code = data.cityCode;
                    var xhr = new XMLHttpRequest();
                    var url = 'http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst'; /*URL*/
                    var queryParams = '?' + encodeURIComponent('serviceKey') + '=' + 'gKP4gLj7CA1GF0xfchCjLXWeYf9lD6II81CJhYLb%2BLLxxT%2Fz8rVgvR0wwC4us75JxkrgSX7oQoiku8468mW1cg%3D%3D'; /*Service Key*/
                    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
                    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
                    queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('json'); /**/
                    // queryParams += '&' + encodeURIComponent('stnId') + '=' + encodeURIComponent('133'); /**/
                    queryParams += '&' + encodeURIComponent('regId') + '=' + encodeURIComponent(code); /**/
                    queryParams += '&' + encodeURIComponent('tmFc') + '=' + encodeURIComponent(toDate + '0600'); /**/
                    xhr.open('GET', url + queryParams);
                    xhr.onreadystatechange = function () {
                        if (this.readyState == 4) {
                            // console.log('Status: ' + this.status + 'nHeaders: ' + JSON.stringify(this.getAllResponseHeaders()) + 'nBody: ' + this.responseText);
                            const jsonResponse = JSON.parse(this.responseText)

                            /*
                            weather.wf3AM , wf3Pm 흐림 맑음 등등
                            weather.rnSt3Am, rnSt3Pm  강수확률
                             */
                            const weather = jsonResponse.response.body.items.item[0];

                            $('#weather').text("해당 지역의 날씨는 " + weather.wf10 + "이고, ");
                            $('#rain').text("강수량은 " + weather.rnSt10 + "% 입니다.");
                        }
                    };

                    xhr.send('');

                })
                .catch((error) => {
                    console.error('Error:', error);
                });

        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n"
                + "message:" + request.responseText + "\n"
                + "error:" + error);
        }
    });
}

$(function () {
    $('input[name="daterange"]').daterangepicker({
        opens: 'left'
    }, function (start, end, label) {
        console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
    });
});

function makeViaPoints(markers) {
    var viaPoints = [];
    for (let i = 0; i < markers.length; i++) {
        var viaPoint = {};
        viaPoint.viaPointId = "Id" + i;
        viaPoint.viaPointName = "경유지" + i;
        data_via = markers[i].getPosition()
        viaPoint.viaX = data_via.lng().toString();
        viaPoint.viaY = data_via.lat().toString();
        viaPoints.push(viaPoint);
    }
    return viaPoints;
}

function createPlanViaPoints(markers) {
    var lonLatList = [];
    for (var i = 0; i < markers.length; i++) {
        data_via = markers[i].getPosition()
        var markerInfo = {
            "lon": data_via.lng().toString(),
            "lat": data_via.lat().toString(),
            "calendar": calVal,
            "markerType": 1
        };
        lonLatList.push(markerInfo);
    }
    return lonLatList
}

// date range picker 한글 설정 및 Calendar 불러오기
$(function () {
    $('input[name="daterangepicker"]').daterangepicker({
        "locale": {
            "format": "YYYY-MM-DD",
            "separator": " ~ ",
            "applyLabel": "확인",
            "cancelLabel": "취소",
            "fromLabel": "From",
            "toLabel": "To",
            "customRangeLabel": "Custom",
            "weekLabel": "W",
            "daysOfWeek": ["월", "화", "수", "목", "금", "토", "일"],
            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
            "firstDay": 1
        },
        opens: 'left'
    }, function (start, end, label) {
        let sDate = new Date(start);
        let eDate = new Date(end);

        // 몇일 선택했는지 표시
        $('#calendarsDate').text(getDateRangeData(sDate, eDate).length + "Day")
        // console.log(getDateRangeData(sDate, eDate));

        const ul = document.getElementById("dateByPlan")
        ul.replaceChildren();

        // Drop Down Menu 날짜 표시
        for (let i = 0; i < getDateRangeData(sDate, eDate).length; i++) {
            const div = document.createElement("div");
            const button = document.createElement("button");
            const input = document.createElement("input")
            var listDate = [];

            listDate = getDateRangeData(sDate, eDate);

            div.classList.add("calendarParents")

            button.type = "button";
            button.classList.add("calendar");
            button.setAttribute("data-btn", i);

            input.value = listDate[i];
            input.type = "hidden";
            input.name = "calendar";
            input.classList.add("calendar" + i);

            div.append(input)
            div.append(button)

            $('#dateByPlan').append(div);

            // 버튼에 클릭 이벤트 리스너 추가
            const buttons = document.querySelectorAll(".calendar");
            buttons.forEach((button) => {
                button.addEventListener("click", handleClick);
            });
        }
    });
});

// 날짜 형식 맞춰주는 function
function getDateRangeData(param1, param2) {  //param1은 시작일, param2는 종료일이다.
    var res_day = [];
    var ss_day = new Date(param1);
    var ee_day = new Date(param2);
    while (ss_day.getTime() <= ee_day.getTime()) {
        var _mon_ = (ss_day.getMonth() + 1);
        _mon_ = _mon_ < 10 ? '0' + _mon_ : _mon_;
        var _day_ = ss_day.getDate();
        _day_ = _day_ < 10 ? '0' + _day_ : _day_;
        res_day.push(ss_day.getFullYear() + '-' + _mon_ + '-' + _day_);
        ss_day.setDate(ss_day.getDate() + 1);
    }
    return res_day;
}

// 드롭 다운 메뉴
function dp_menu() {
    let click = document.getElementById("drop-content");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";
    }
}

// 버튼 클릭 이벤트 핸들러 함수
function handleClick(event) {
    // 클릭된 버튼 요소 가져오기
    const button = event.target;

    // data-btn 속성을 통해 해당 버튼의 인덱스 가져오기
    const index = button.getAttribute("data-btn");

    // 해당 인덱스의 input 요소 가져오기
    const input = document.querySelector(".calendar" + index);

    // input 요소의 값 가져오기
    const value = input.value;

    calVal = value;

    // 가져온 값 확인
    console.log(calVal);
}
