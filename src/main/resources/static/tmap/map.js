window.onload = function () {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/tmap/api/map');
    xhr.onload = function () {
        if (xhr.status === 200) {
            const responseText = xhr.responseText;
            var script = document.createElement('script');
            script.innerHTML = responseText;
            document.head.appendChild(script);

            // map 생성
            // Tmapv3.Map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
            var map = new Tmapv3.Map("map_div", { // 지도가 생성될 div
                center: new Tmapv3.LatLng(37.56520450, 126.98702028),
                width: "100%",  // 지도의 넓이
                height: "400px",  // 지도의 높이
                zoom: 16  // 지도 줌레벨
            });

        }
    };
    xhr.send();
};