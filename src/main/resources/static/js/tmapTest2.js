let map, marker;

let markers = []; // 경유지 마커들
let marker_s; // 시작
let marker_e; // 끝

var prevPolyline_;
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
    // //
    if (!marker_s) {
        // 시작 마커가 없으면
        createMarker('Start');
    } else if (!marker_e) {
        // 끝 마커가 없으면
        createMarker('End');
    } else {
        // 경유지 마커를 찍을 때
        createMarker('경유지');
    }

    function createMarker(title) {
        const lonlat = e.latLng;
        // Marker 객체 생성.
        const marker = new Tmapv2.Marker({
            position: new Tmapv2.LatLng(lonlat.lat(), lonlat.lng()), //Marker의 중심좌표 설정.
            map: map, //Marker가 표시될 Map 설정.
            title: title
        });

        // reverseGeo(marker.getPosition().lng(), marker.getPosition().lat());

        if (title === 'Start') {
            marker_s = marker;
            marker_s.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png");

            console.log("start lat : " + marker_s.getPosition().lat());
            console.log("start lng : " + marker_s.getPosition().lng());
        } else if (title === 'End') {
            marker_e = marker;
            marker_e.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png");

            console.log("end lat : " + marker_e.getPosition().lat());
            console.log("end lng : " + marker_e.getPosition().lng());
        } else {
            markers.push(marker);
            ajaxParams(markers, marker_s, marker_e);
        }
    }
}

// TODO 1 경유지 최적화하고 여러 경유지 마커 생성시 기존의 polyline 삭제 하는 작업 해야함
// TODO 2 보도로 변경 할 것도 고려해야함
function ajaxParams(markers, marker_s, marker_e) {
    if (marker_s != null && marker_e != null) {
        var viaPoints = makeViaPoints(markers);
        const data1 = marker_s.getPosition();
        const data2 = marker_e.getPosition();

        const params = {
            reqCoordType: "WGS84GEO",
            resCoordType: "EPSG3857",
            startName: "출발",
            startX: data1.lng().toString(),
            startY: data1.lat().toString(),
            startTime: "202304120800",
            endName: "도착",
            endX: data2.lng().toString(),
            endY: data2.lat().toString(),
            viaPoints
        };
        ajaxReq(params);
    }
}

function makeViaPoints(markers) {
    var viaPoints = [];

    for (let i = 0; i < markers.length; i++) {
        var viaPoint = {};
        viaPoint.viaPointId = "Id" + i;
        viaPoint.viaPointName = "경유지" + i;
        data3 = markers[i].getPosition()
        viaPoint.viaX = data3.lng().toString();
        viaPoint.viaY = data3.lat().toString();
        viaPoints.push(viaPoint);
    }
    return viaPoints;
}

function ajaxReq(req) {

    console.log(req);
    var headers = {};
    headers["appKey"] = "yIMaVf12xnauu7aRo40iL6EWEJXjwVhnbBr6Lc3d";

    $.ajax({
        type: "POST",
        headers: headers,
        url: "https://apis.openapi.sk.com/tmap/routes/routeOptimization10?version=1&format=json",//
        async: false,
        contentType: "application/json",
        data: JSON.stringify(req),
        success: function (response) {

            if (prevPolyline_) {
                console.log("들어왔다")
                prevPolyline_.setMap(null);
            }

            var resultData = response.properties;
            var resultFeatures = response.features;

            // 결과 출력
            var tDistance = "총 거리 : " + (resultData.totalDistance / 1000).toFixed(1) + "km,  ";
            var tTime = "총 시간 : " + (resultData.totalTime / 60).toFixed(0) + "분,  ";
            var tFare = "총 요금 : " + resultData.totalFare + "원";

            $("#result").text(tDistance + tTime + tFare);

            for (var i in resultFeatures) {
                var geometry = resultFeatures[i].geometry;
                var properties = resultFeatures[i].properties;

                drawInfoArr = [];

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

                    prevPolyline_ = new Tmapv2.Polyline({
                        path: drawInfoArr,
                        strokeColor: "#FF0000",
                        strokeWeight: 6,
                        map: map,
                    });
                }
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}