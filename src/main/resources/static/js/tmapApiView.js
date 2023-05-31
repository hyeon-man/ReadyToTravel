let map;
let button;
let markers = [];
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
            console.log(response);
            const lonLatList = response.lonLatList;

            const li = document.getElementById('dateButton');

            for (let i = 0; i < lonLatList.length; i++) {
                button = document.createElement('button');

                if (lonLatList[i].markerType == 'START') {
                    button.textContent = lonLatList[i].calendar;
                    button.classList = "planBtn";
                    li.append(button);
                }
            }

            $('.planBtn').off().on('click', function (evt) {
                const clickBtn = evt.target;
                const textContentBtn = clickBtn.textContent;

                for (let i = 0; i < lonLatList.length; i++) {
                    if (textContentBtn == lonLatList[i].calendar)
                        lonLatRealList.push(lonLatList[i]);
                }
                clearMarker();
                createMarker(lonLatRealList);
            });
        }
    })
}

function createMarker(lonLatList) {

    for (let i = 0; i < lonLatList.length; i++) {
        const lon = lonLatList[i].lon;

        const lat = lonLatList[i].lat;

        //Marker 객체 생성.
        marker = new Tmapv2.Marker({
            position: new Tmapv2.LatLng(lat, lon), //Marker의 중심좌표 설정.
            map: map, //Marker가 표시될 Map 설정..
            title: lonLatList[i].markerType
        });

        markers.push(marker);

        if (lonLatList[i].markerType == 'START') {
            marker.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png");
        }
        if (lonLatList[i].markerType == 'END') {
            marker.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png");
        }
    }
}

function clearMarker() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
        console.log(markers[i]);
    }
}