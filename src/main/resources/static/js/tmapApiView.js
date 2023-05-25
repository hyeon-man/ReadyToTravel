let markers = []; // 경유지 마커들
let marker_s; // 시작
let marker_e; // 끝
let viaPointName = '';
let lon;
let lat;

window.onload = function initTmap() {
    map = new Tmapv2.Map("map_div", {
        center: new Tmapv2.LatLng(36.35086524077589, 127.45422567640077), // 지도 초기 좌표
        width: "100%", // 지도의 넓이
        height: "100%", // 지도의 높이
        zoom: 15
    });
// //
    if (marker_type == 0) {
        // 시작 마커가 없으면
        setMapMarker('Start');
    } else if (marker_type == 2) {
        // 끝 마커가 없으면
        setMapMarker('End');
    } else {
        // 경유지 마커를 찍을 때
        setMapMarker(viaPointName);
    }

    function setMapMarker(title) {
        let markerLon = lon;
        let markerLat = lat;

        // Marker 객체 생성.
        const marker = new Tmapv2.Marker({
            position: new Tmapv2.LatLng(markerLat.lat(), markerLon.lng()), //Marker의 중심좌표 설정.
            map: map, //Marker가 표시될 Map 설정.
            title: title
        });

        if (title === 'Start') {
            marker_s = marker;
            marker_s.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png");

            // console.log("start lat : " + marker_s.getPosition().lat() + ", start lng : " + marker_s.getPosition().lng());
        } else if (title === 'End') {
            marker_e = marker;
            marker_e.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png");

            // console.log("end lat : " + marker_e.getPosition().lat() + ", end lng : " + marker_e.getPosition().lng());
        } else {
            markers.push(marker);
        }
    }
}