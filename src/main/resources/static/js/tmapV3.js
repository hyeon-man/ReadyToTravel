var map, marker;
var lonlat;
var markers = [];

// 페이지가 로딩이 된 후 호출하는 함수입니다.
window.onload = function initTmap() {
    // map 생성
    // Tmapv3.Map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
    map = new Tmapv3.Map("map_div", {
        center: new Tmapv3.LatLng(37.56520450, 126.98702028),
        width: "100%",	// 지도의 넓이
        height: "80%",	// 지도의 높이
        zoom: 16	// 지도 줌레벨
    });

    map.on("Click", function (evt) {
        removeMarkers();
        lngLat = evt.data.lngLat;
        console.log(lngLat);
        var markerData = {
            lat: lngLat._lat,
            lng: lngLat._lng
        };
        fetch('/tmap/api/marker', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(markerData)
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
            })
            .catch(err => {
                console.log('fetchError', err)
            });
    });
}

// 모든 마커를 제거하는 함수입니다.
function removeMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

function myFunction1() {
    var checkBox = document.getElementById("checkbox1");
    if (checkBox.checked == true) {
        console.log("체크박스1 체크");
    } else {
        console.log("체크박스1 해제");
    }
}

function myFunction2() {
    var checkBox = document.getElementById("checkbox2");
    if (checkBox.checked == true) {
        console.log("체크박스2 체크");
    } else {
        console.log("체크박스2 해제");
    }
}