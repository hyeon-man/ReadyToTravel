var map, marker;
var lonlat;
var markers = [];
var marker_s = null; // 시작 마커
var marker_e = null; // 끝 마커
var marker_s_lat_lng = [];
var marker_e_lat_lng = [];

//경로그림정보
var drawInfoArr = [];

// 페이지가 로딩이 된 후 호출하는 함수입니다.
window.onload = function initTmap() {
    // map 생성
    // Tmapv2.Map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
    map = new Tmapv2.Map("map_div", {
        center: new Tmapv2.LatLng(36.35086524077589, 127.45422567640077), // 지도 초기 좌표
        width: "100%", // 지도의 넓이
        height: "80%", // 지도의 높이
        zoom: 17
    });

    map.addListener("click", onClick); //map 클릭 이벤트를 등록합니다.
}


function onClick(e) {
    // if (!marker_s) { // 시작 마커가 없으면
    //     lonlat = e.latLng;
    //     // Marker 객체 생성.
    //     marker_s = new Tmapv2.Marker({
    //         position: new Tmapv2.LatLng(lonlat.lat(), lonlat.lng()), // Marker의 중심좌표 설정.
    //         map: map, // Marker가 표시될 Map 설정.
    //         title: 'Start'
    //     });
    //
    //     console.log("lat =" + marker_s.getPosition().lat())
    //     console.log("lng =" + marker_s.getPosition().lng())
    //     reverseGeo(marker_s.getPosition().lng(), marker_s.getPosition().lat())
    //
    //     marker_s_lat_lng.push({
    //         lat : marker_s.getPosition().lat(),
    //         lng : marker_s.getPosition().lng()
    //     });
    //
    //     markers = []; // 경유지 마커 배열 초기화
    //
    // } else if (!marker_e) { // 끝 마커가 없으면
    //     lonlat = e.latLng;
    //     // Marker 객체 생성.
    //     marker_e = new Tmapv2.Marker({
    //         position: new Tmapv2.LatLng(lonlat.lat(), lonlat.lng()), // Marker의 중심좌표 설정.
    //         map: map, // Marker가 표시될 Map 설정.
    //         title: 'End'
    //     });
    //
    //     console.log("lat =" + marker_e.getPosition().lat())
    //     console.log("lng =" + marker_e.getPosition().lng())
    //     reverseGeo(marker_e.getPosition().lng(), marker_e.getPosition().lat())
    //
    //     marker_e_lat_lng.push({
    //         lat : marker_e.getPosition().lat(),
    //         lng : marker_e.getPosition().lng()
    //     });
    //
    //     markers = []; // 경유지 마커 배열 초기화
    //
    // } else { // 경유지 마커를 찍을 때
    //     lonlat = e.latLng;
    //     // Marker 객체 생성.
    //     var marker = new Tmapv2.Marker({
    //         position: new Tmapv2.LatLng(lonlat.lat(), lonlat.lng()), //Marker의 중심좌표 설정.
    //         map: map, //Marker가 표시될 Map 설정.
    //         title: '경유지'
    //     });
    //
    //     console.log("lat =" + marker.getPosition().lat())
    //     console.log("lng =" + marker.getPosition().lng())
    //     reverseGeo(marker.getPosition().lng(), marker.getPosition().lat())
    //
    //     markers.push({
    //         lat : marker.getPosition().lat(),
    //         lng : marker.getPosition().lng()
    //     }); // 생성된 마커의 x,y 값을 배열에 추가
    // }

    if (!marker_s) {
        // 시작 마커가 없으면
        createMarker('Start');
        marker_s_lat_lng.push(getLatlng(marker_s));
    } else if (!marker_e) {
        // 끝 마커가 없으면
        createMarker('End');
        marker_e_lat_lng.push(getLatlng(marker_e));
    } else {
        // 경유지 마커를 찍을 때
        createMarker('경유지');
        markers.push(getLatlng(marker));
    }

    function createMarker(title) {
        const lonlat = e.latLng;
        // Marker 객체 생성.
        const marker = new Tmapv2.Marker({
            position: new Tmapv2.LatLng(lonlat.lat(), lonlat.lng()), //Marker의 중심좌표 설정.
            map: map, //Marker가 표시될 Map 설정.
            title: title
        });

        console.log(`lat = ${marker.getPosition().lat()}`);
        console.log(`lng = ${marker.getPosition().lng()}`);
        reverseGeo(marker.getPosition().lng(), marker.getPosition().lat());

        if (title === 'Start') {
            marker_s = marker;
        } else if (title === 'End') {
            marker_e = marker;
        } else {
            markers.push(marker);
        }

        markers = []; // 경유지 마커 배열 초기화
    }

    function getLatlng(marker) {
        return {
            lat: marker.getPosition().lat(),
            lng: marker.getPosition().lng()
        }
    }

    var headers = {};
    headers["appKey"]="yIMaVf12xnauu7aRo40iL6EWEJXjwVhnbBr6Lc3d";

    $.ajax({
        type:"POST",
        headers : headers,
        url:"https://apis.openapi.sk.com/tmap/routes/routeOptimization30?version=1&format=json",
        async:false,
        contentType: "application/json",
        data: JSON.stringify({
            "reqCoordType": "WGS84GEO",
            "resCoordType" : "EPSG3857",
            "startName": "출발",
            "startX": marker_s_lat_lng[0].lng,
            "startY": marker_s_lat_lng[0].lat,
            "startTime": "201711121314",
            "endName": "도착",
            "endX": marker_e_lat_lng[0].lng,
            "endY": marker_e_lat_lng[0].lat,
            "searchOption" : "0",
            "viaPoints": markers.map(function(marker, index) {
                return {
                    "viaPointId": "test" + (index + 1),
                    "viaPointName": "test" + (index + 1),
                    "viaX": marker.lng,
                    "viaY": marker.lat,
                };
            })
        }),
        success:function(response){

            console.log(response);
            var resultData = response.properties;
            var resultFeatures = response.features;

            // 결과 출력
            var tDistance = "총 거리 : " + (resultData.totalDistance/1000).toFixed(1) + "km,  ";
            var tTime = "총 시간 : " + (resultData.totalTime/60).toFixed(0) + "분,  ";
            var tFare = "총 요금 : " + resultData.totalFare + "원";

            $("#result").text(tDistance+tTime+tFare);

            for(var i in resultFeatures) {
                var geometry = resultFeatures[i].geometry;
                var properties = resultFeatures[i].properties;
                var polyline_;

                drawInfoArr = [];

                if(geometry.type == "LineString") {
                    for(var j in geometry.coordinates){
                        // 경로들의 결과값(구간)들을 포인트 객체로 변환
                        var latlng = new Tmapv2.Point(geometry.coordinates[j][0], geometry.coordinates[j][1]);
                        // 포인트 객체를 받아 좌표값으로 변환
                        var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(latlng);
                        // 포인트객체의 정보로 좌표값 변환 객체로 저장
                        var convertChange = new Tmapv2.LatLng(convertPoint._lat, convertPoint._lng);

                        drawInfoArr.push(convertChange);
                    }

                    polyline_ = new Tmapv2.Polyline({
                        path : drawInfoArr,
                        strokeColor : "#FF0000",
                        strokeWeight: 6,
                        map : map
                    });
                }
            }
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

}

// 모든 마커를 제거하는 함수입니다.
function removeMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

// x, y 좌표를 가지고 주소를 반환해줌 + 주소의 특정 이름으로 그 지역의 날씨 알려주는 기상청 api도 포함
function reverseGeo(lon, lat) {
    var headers = {};
    headers["appKey"]="yIMaVf12xnauu7aRo40iL6EWEJXjwVhnbBr6Lc3d";

    $.ajax({
        method : "GET",
        headers : headers,
        url : "https://apis.openapi.sk.com/tmap/geo/reversegeocoding?version=1&format=json&callback=result",
        async : false,
        data : {
            "coordType" : "WGS84GEO",
            "addressType" : "A10",
            "lon" : lon,
            "lat" : lat
        },
        success : function(response) {
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

            // 주소 값 스페이스바 기준으로 배열로 나눔
            const split = result;
            const splitResult = result.split(" ");

            // n번째 배열을 스페이스 기준으로 나눠진 값으로 불러옴 대전광역시 서구 ~~~ => 대전광역시
            console.log(splitResult[0]);

            fetch('weather/api/changeAddress', {
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

                    const code = data.cityCode;
                    console.log(code)
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

                            // $('#wf4AmInfo').attr("src", getWeatherImg(weather.wf3Am))

                            // console.log("wf3am", weather.wf3Am);
                        }
                    };

                    xhr.send('');

                    console.log(this.response);


                    console.log(data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });

        },
        error : function(request, status, error) {
            console.log("code:" + request.status + "\n"
                + "message:" + request.responseText + "\n"
                + "error:" + error);
        }
    });
}

// 날씨에 따른 이미지 반환하는 function
// function getWeatherImg(weather) {
//     if (weather == "맑음") {
//         return "/weatherImg/sunny.png"
//     } else if (weather == "구름많음") {
//         return "/weatherImg/sunnycloudy.png"
//     } else if (weather == "흐림") {
//         return "/weatherImg/cloudy.png"
//     } else if (weather == "비") {
//         return "/weatherImg/rainy.png"
//     } else if (weather == "눈") {
//         return "/weatherImg/snow.png"
//     } else return "/weatherImg/snow.png"
// }
