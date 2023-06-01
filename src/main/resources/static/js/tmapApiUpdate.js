// TODO 마커가 제대로 찍히긴하는데 newMarkers에 중첩되어 쌓이는 오류가 있음 해결해야함.

let map;
let dateButton;
let updateButton;
let marker_s;
let marker_e;
let newMarker_s;
let newMarker_e;
let marker;
let markers = [];
let newMarkers = [];
let lonLatRealList = [];
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
            let dayCount = 1;
            for (let i = 0; i < lonLatList.length; i++) {
                dateButton = document.createElement('button');
                updateButton = document.createElement('button');

                if (lonLatList[i].markerType == 'START') {
                    dateButton.textContent = lonLatList[i].calendar;
                    dateButton.classList = "planBtn";

                    updateButton.textContent = dayCount + "일차 지도 정보 수정"
                    updateButton.classList = "updateBtn";
                    dayCount++;

                    li.append(dateButton);
                    li.append(updateButton)
                }
            }

            $('.planBtn').off().on('click', function (evt) {

                const clickBtn = evt.target;
                const textContentBtn = clickBtn.textContent;

                markerDisappear();

                for (let i = 0; i < lonLatList.length; i++) {
                    if (textContentBtn == lonLatList[i].calendar)
                        lonLatRealList.push(lonLatList[i]);
                }
                createMarker(lonLatRealList);

                $('.updateBtn').off().on("click", function () {
                    newMarkers = [];
                    marker_s.setMap(null);
                    marker_e.setMap(null);
                    marker_s = null;
                    marker_e = null;
                    for (let i = 0; i < markers.length; i++) {
                        markers[i].setMap(null);
                    }
                    markers = [];

                    // 클릭 이벤트 핸들러 등록
                    map.addListener("click", function (evt) {
                        var lonlat = new Tmapv2.LatLng(evt.latLng.lat(), evt.latLng.lng());

                        if (newMarker_s == null) {
                            // 시작 마커가 없으면
                            createNewMarker('Start', lonlat);
                        } else if (newMarker_e == null) {
                            // 끝 마커가 없으면
                            createNewMarker('End', lonlat);
                        } else {
                            // 경유지 마커를 찍을 때
                            createNewMarker('경유지', lonlat);
                        }
                    });
                });
            });
        }
    })

}

$('#createBtn').off().on('click', function () {
    ajaxParams(markers, marker_s, marker_e);
});

function createMarker(lonLatList) {
    for (let i = 0; i < lonLatList.length; i++) {
        const lon = lonLatList[i].lon;
        const lat = lonLatList[i].lat;
        const markerType = lonLatList[i].markerType;

        const marker = new Tmapv2.Marker({
            position: new Tmapv2.LatLng(lat, lon),
            map: null,
            title: markerType
        });

        if (markerType === 'START') {
            marker_s = marker;
            marker_s.setMap(map);
            marker.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png");
        } else if (markerType === 'END') {
            marker_e = marker;
            marker_e.setMap(map);
            marker.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png");
        } else if (markerType === 'VIAPOINT') {
            markers.push(marker);
        }
    }

    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
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

                    let polyLine = new Tmapv2.Polyline({
                        path: drawInfoArr,
                        strokeColor: "#3399FF",
                        strokeWeight: 3,
                        map: map,
                    });

                    $('.planBtn').on("click", function () {
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

function createNewMarker(title, lonlat) {

    // 클릭된 위치에 마커 생성
    marker = new Tmapv2.Marker({
        position: lonlat,
        map: map,
        title: title
    });

    if (title === 'Start') {
        newMarker_s = marker;
        newMarker_s.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png");

        console.log("start lat : " + newMarker_s.getPosition().lat() + ", start lng : " + newMarker_s.getPosition().lng());
    } else if (title === 'End') {
        newMarker_e = marker;
        newMarker_e.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png");

        console.log("end lat : " + newMarker_e.getPosition().lat() + ", end lng : " + newMarker_e.getPosition().lng());
    } else {
        newMarkers.push(marker);
        console.log(newMarkers)
        $('#createBtn').off("click").on("click", function () {
            ajaxParams(markers, marker_s, marker_e);
            // buttonClick(markers, marker_s, marker_e);
        });
    }
}

function markerDisappear() {
    if (marker_s) {
        marker_s.setMap(null);
    }
    if (marker_e) {
        marker_e.setMap(null);
    }
    if (markers) {
        for (let i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }
    if (newMarker_s) {
        newMarker_s.setMap(null);
    }
    if (newMarker_e) {
        newMarker_e.setMap(null);
    }
    if (newMarkers) {
        for (let i = 0; i < newMarkers.length; i++) {
            newMarkers[i].setMap(null);
        }
        newMarkers = [];
    }

    lonLatRealList = [];
}