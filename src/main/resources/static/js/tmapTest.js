let map, marker;
let lonlat;

let markers = []; // 경유지 마커들
let marker_s; // 시작
let marker_e; // 끝

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

            if (marker_s != null && marker_e != null) {
                createReq(marker_s, marker_e);
            }

            console.log("end lat : " + marker_e.getPosition().lat());
            console.log("end lng : " + marker_e.getPosition().lng());
        } else {
            markers.push(marker);
        }

        markers = []; // 경유지 마커 배열 초기화
    }
}

function createReq(marker_s, marker_e) {
    const req = {
        reqCoordType: "WGS84GEO",
        resCoordType: "WGS84GEO",
        startName: start,
        startX: marker_s.getPosition().lng(),
        startY: marker_s.getPosition().lat(),
        startTime: "202304120800",
        endName: end,
        endX: marker_e.getPosition().lng(),
        endY: marker_e.getPosition().lat(),
        searchOption: "0",
        viaPoints: [
            {
                viaPointId: "0",
                viaPointName: "경유지",
                viaX: "127.45422567640077",
                viaY: "37.56626352138058"
            }
        ]
    }
}


var headers = {};
headers["appKey"] = "6Q3ySMpue88CUObnAszWH6dpde24rGCPRqHtUYC8";

function markerAjax(reqJson) {
    console.log(reqJson);
    $.ajax({
        type: "POST",
        headers: headers,
        url: "https://apis.openapi.sk.com/tmap/routes/routeOptimization10?version=1&format=json",//
        async: false,
        contentType: "application/json",
        data: reqJson,
        success: function (response) {
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
                var polyline_;

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

                    polyline_ = new Tmapv2.Polyline({
                        path: drawInfoArr,
                        strokeColor: "#FF0000",
                        strokeWeight: 6,
                        map: map
                    });
                }
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}