// TODO 마커가 제대로 찍히긴하는데 newMarkers에 중첩되어 쌓이는 오류가 있음 해결해야함.
let markerData = [];
let manageNewMarker = [];
let manageMarker = [];
let map;
let dateButton;
let marker_s;
let marker_e;
let newMarker;
let polyLine;
let markers = [];
let lonLatRealList = [];
let calVal;

window.onload = function initTmap() {
    // map 생성
    // Tmapv2.Map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
    map = new Tmapv2.Map("map_div", {
        center: new Tmapv2.LatLng(36.35086524077589, 127.45422567640077), // 지도 초기 좌표
        width: "100%", // 지도의 넓이
        height: "100%", // 지도의 높이
        zoom: 15
    });

// 현재 페이지의 URL에서 planNum 추출
    const url = window.location.href;
    const planNum = url.split("/").pop(); // URL에서 마지막 부분 추출

    $.ajax({
        type: "GET",
        url: `/plan/getMarker/${planNum}`,//
        async: false,
        contentType: "application/json",
        success: function (response) {
            const planName = document.getElementById('planName');
            const planContents = document.getElementById('planContents');

            planName.value = response.name;
            planContents.value = response.contents

            const lonLatList = response.lonLatList;

            const li = document.getElementById('dateLi');

            // 버튼의 textContent 값을 추출하여 배열로 저장
            const buttonTexts = lonLatList
                .filter(item => item.markerType === 'START') // markerType이 'START'인 것만 필터링
                .map(item => item.calendar); // calendar 값을 추출하여 배열로 변환

            // textContent 값을 정렬
            buttonTexts.sort();

            // 정렬된 값들을 기반으로 버튼을 동적으로 생성하여 추가
            for (const text of buttonTexts) {
                const dateButton = document.createElement('button');
                dateButton.textContent = text;
                dateButton.classList.add('planBtn');
                li.appendChild(dateButton);
            }


            $('.planBtn').off().on('click', function (evt) {
                const clickBtn = evt.target;
                const textContentBtn = clickBtn.textContent;

                for (let i = 0; i < lonLatList.length; i++) {
                    if (textContentBtn == lonLatList[i].calendar) {
                        lonLatRealList.push(lonLatList[i]);
                    }
                }

                marker_s = null;
                marker_e = null;
                markers = [];
                for (let i = 0; i < manageMarker.length; i++) {
                    manageMarker[i].setMap(null);
                }
                for (let i = 0; i < manageNewMarker.length; i++) {
                    manageNewMarker[i].setMap(null);
                }
                manageNewMarker = [];
                manageMarker = [];

                createMarker(lonLatRealList);
                lonLatRealList = [];

                $('#removeMarker').off().on('click', function () {
                    marker_s = null;
                    marker_e = null;
                    markers = [];
                    for (let i = 0; i < manageMarker.length; i++) {
                        manageMarker[i].setMap(null);
                    }
                    for (let i = 0; i < manageNewMarker.length; i++) {
                        manageNewMarker[i].setMap(null);
                    }
                    manageNewMarker = [];
                    manageMarker = [];
                    lonLatRealList = [];
                });

                // 클릭 이벤트 핸들러 등록
                map.addListener("click", function (evt) {

                    var lonlat = new Tmapv2.LatLng(evt.latLng.lat(), evt.latLng.lng());
                    // 경유지 마커를 찍을 때
                    var lon = evt.latLng.lng();
                    var lat = evt.latLng.lat();
                    if (marker_s == null) {
                        newMarker = createNewMarker('START', lon, lat);
                    } else if (marker_e == null) {
                        newMarker = createNewMarker('END', lon, lat);
                    } else {
                        newMarker = createNewMarker('VIAPOINT', lon, lat);
                    }
                    markers.push(newMarker);
                    manageNewMarker.push(newMarker);
                });

                $('#updatePlanBtn').off().on('click', function () {
                    markerData = {
                        markers: markers,
                        marker_s: marker_s,
                        marker_e: marker_e,
                        calendar: textContentBtn
                    };

                    serverFetch(markerData);

                    markerData = [];
                });
            });
        }
    });
}

function createMarker(lonLatList) {

    for (let i = 0; i < lonLatList.length; i++) {
        const lon = lonLatList[i].lon;
        const lat = lonLatList[i].lat;
        const markerType = lonLatList[i].markerType;

        const marker = new Tmapv2.Marker({
            position: new Tmapv2.LatLng(lat, lon),
            map: map,
            draggable: true,
        });

        if (markerType === 'START') {
            marker_s = marker;
            marker_s.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png");
            marker_s.setTitle(markerType);
        } else if (markerType === 'END') {
            marker_e = marker;
            marker_e.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png");
            marker_e.setTitle(markerType);
        } else if (markerType === 'VIAPOINT') {
            marker.setTitle(markerType);
            markers.push(marker);
        }

        manageMarker.push(marker);

        // 드래그 완료 이벤트 리스너 등록
        marker.addListener('dragend', function () {
            console.log(markerType + " lat : " + marker.getPosition().lat() + ", lng : " + marker.getPosition().lng());
        });
    }

    $('#createBtn').off("click").on("click", function () {
        ajaxParams(markers, marker_s, marker_e);
    });
}


function createNewMarker(title, lon, lat) {
    const newMarker = new Tmapv2.Marker({
        position: new Tmapv2.LatLng(lat, lon),
        map: map,
        draggable: true,
        title: title
    })

    if (title == 'START') {
        marker_s = newMarker
        marker_s.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png");
    }
    if (title == 'END') {
        marker_e = newMarker
        marker_e.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png");
    }

    // 새로운 'dragend' 이벤트 리스너 등록
    newMarker.addListener('dragend', function () {
        console.log(title + "의 위도: " + newMarker.getPosition().lat() + ", 경도: " + newMarker.getPosition().lng());
    });

    return newMarker;
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
            var tDistance = (resultData.totalDistance / 1000).toFixed(1) + "km";
            // var tTime = "총 시간 : " + (resultData.totalTime / 60).toFixed(0) + "분";

            const resultDistance = document.getElementById('result');
            resultDistance.textContent = '총 거리 : ' + tDistance;
            $('.planBtn').off('click').on('click', function () {
                resultDistance.textContent = '총 거리 : ';
            });

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

                    polyLine = new Tmapv2.Polyline({
                        path: drawInfoArr,
                        strokeColor: "#3399FF",
                        strokeWeight: 3,
                        map: map,
                    });
                    $('#removeBtn').on("click", function () {
                        polyLine.setPath(0);
                    });
                }
            }
        },
        error: function (request, status, error) {
            // console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function makeViaPoints(markers) {
    var viaPoints = [];
    for (let i = 0; i < markers.length; i++) {
        var viaPoint = {};
        viaPoint.viaPointId = "Id";
        viaPoint.viaPointName = "경유지";
        data_via = markers[i].getPosition()
        viaPoint.viaX = data_via.lng().toString();
        viaPoint.viaY = data_via.lat().toString();
        viaPoints.push(viaPoint);
    }
    return viaPoints;
}

function serverFetch(markerData) {
    var radioVal = $('input[name="planType"]:checked').val();

    console.log(markerData);
    var markerPoint = (createPoints(markerData));

    var planDTO = {
        "name": $('#planName').val(),
        "contents": $('#planContents').val(),
        "lonLatList": markerPoint,
        "type": radioVal
    }

    const url = window.location.href;
    const planNum = url.split("/").pop(); // URL에서 마지막 부분 추출

    fetch(`/plan/updatePlan/${planNum}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(planDTO)
    }).then(response => {
        // console.log(response);
        return response.json();
    }).then(data => {
        window.location.href = "/plan/viewPlan/" + planNum;
    }).catch(error => {
        // console.log(error);
    });
}

function createPoints(markerData) {

    var lonLatList = [];

    for (let j = 0; j < markerData.markers.length; j++) {
        let data_via = markerData.markers[j].getPosition();
        var viaMarkerInfo = {
            "lon": data_via.lng().toString(),
            "lat": data_via.lat().toString(),
            "calendar": markerData.calendar,
            "markerType": 1
        };
        lonLatList.push(viaMarkerInfo);
    }

    let data_start = markerData.marker_s.getPosition();
    var startMarkerInfo = {
        "lon": data_start.lng().toString(),
        "lat": data_start.lat().toString(),
        "calendar": markerData.calendar,
        "markerType": 0
    };
    lonLatList.push(startMarkerInfo);

    let data_end = markerData.marker_e.getPosition();
    var endMarkerInfo = {
        "lon": data_end.lng().toString(),
        "lat": data_end.lat().toString(),
        "calendar": markerData.calendar,
        "markerType": 2
    };
    lonLatList.push(endMarkerInfo);
    console.log(lonLatList);
    return lonLatList;
}

// 모달 열기
document.getElementById("openModal").addEventListener("click", function() {
    document.getElementById("myModal").style.display = "block";
});

// 모달 닫기
document.getElementsByClassName("close")[0].addEventListener("click", function() {
    document.getElementById("myModal").style.display = "none";
});