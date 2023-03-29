var map, marker;
var lonlat;
var markers = [];

// 페이지가 로딩이 된 후 호출하는 함수입니다.
window.onload = function initTmap() {
    // map 생성
    // Tmapv2.Map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
    map = new Tmapv2.Map("map_div", {
        center: new Tmapv2.LatLng(37.56520450, 126.98702028), // 지도 초기 좌표
        width: "100%", // 지도의 넓이
        height: "80%", // 지도의 높이
        zoom: 17
    });

    map.addListener("click", onClick); //map 클릭 이벤트를 등록합니다.
}


function onClick(e) {
    // 클릭한 위치에 새로 마커를 찍기 위해 이전에 있던 마커들을 제거
    removeMarkers();

    lonlat = e.latLng;
    //Marker 객체 생성.
    marker = new Tmapv2.Marker({
        position: new Tmapv2.LatLng(lonlat.lat(), lonlat.lng()), //Marker의 중심좌표 설정.
        map: map, //Marker가 표시될 Map 설정.
        animation: Tmapv2.MarkerOptions.ANIMATE_FLICKER,
        title: '찍혀욧',
        draggable: true

    });

    console.log("lat =" + marker.getPosition().lat())
    console.log("lng =" + marker.getPosition().lng())
    markers.push(marker);
    reverseGeo(marker.getPosition().lng(), marker.getPosition().lat())

    marker.addListener("dragend", function (evt) {
        console.log("마커 드래그 이벤트")
        console.log("lat =" + marker.getPosition().lat())
        console.log("lng =" + marker.getPosition().lng())

    })

}

// 모든 마커를 제거하는 함수입니다.
function removeMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

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

            result = "새주소 : " + newRoadAddr + "</br>";
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

            fetch('../weather/changeAddress', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(splitResult[0]),
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);

                    let today = new Date();

                    let year = today.getFullYear(); // 년도
                    let month = today.getMonth() + 1;  // 월
                    let date = today.getDate();  // 날짜

                    const code = data.cityCode;

                    console.log(code);

                    var xhr = new XMLHttpRequest();
                    var url = 'http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst'; /*URL*/
                    var queryParams = '?' + encodeURIComponent('serviceKey') + '=' + 'gKP4gLj7CA1GF0xfchCjLXWeYf9lD6II81CJhYLb%2BLLxxT%2Fz8rVgvR0wwC4us75JxkrgSX7oQoiku8468mW1cg%3D%3D'; /*Service Key*/
                    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
                    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
                    queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('json'); /**/
                    queryParams += '&' + encodeURIComponent('regId') + '=' + encodeURIComponent(code); /**/
                    queryParams += '&' + encodeURIComponent('tmFc') + '=' + encodeURIComponent(year + '0' + month + date + '0600'); /**/
                    xhr.open('GET', url + queryParams);
                    xhr.onreadystatechange = function () {
                        if (this.readyState == 4) {
                            console.log('Status: ' + this.status + 'nHeaders: ' + JSON.stringify(this.getAllResponseHeaders()) + 'nBody: ' + this.responseText);

                            // Json 값을 가져옴
                            const response = JSON.parse(this.responseText);

                            // JSON 안에 있는 값들 중 필요한 값이 있는 곳을 고른다
                            const items = response.response.body.items.item;

                            // 반복문으로 하나씩 item에 넣는다
                            for (let i = 0; i < items.length; i++) {
                                const item = items[i];

                                // 필요한 정보가 들어있는 것을 html id가 부여된 곳에 값을 넣어준다.
                                $('#wf3PmInfo').attr("src", getWeatherImageUrl(item.wf3Pm));
                                $('#wf4PmInfo').attr("src", getWeatherImageUrl(item.wf4Pm));
                                $('#wf5PmInfo').attr("src", getWeatherImageUrl(item.wf5Pm));
                                $('#wf6PmInfo').attr("src", getWeatherImageUrl(item.wf6Pm));
                                $('#wf6PmInfo').attr("src", getWeatherImageUrl(item.wf7Pm));
                                $('#wf6PmInfo').attr("src", getWeatherImageUrl(item.wf8));

                            }
                        }
                    };

                    xhr.send('');

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

// todo 날씨 이미지 반환하는 것 이미지 퍼오고 다른 날씨 추가하면 끝
function getWeatherImageUrl(weatherStatus) {
    let imageUrl = ''; // 이미지 URL 초기화

    if (weatherStatus === '맑음') {
        imageUrl = '/weatherImg/sunny.png'; // 맑음
    } else if (weatherStatus === '구름많음') {
        imageUrl = '/weatherImg/cloudy.png'; // 구름많음
    } else {
        imageUrl = '/weatherImg/rainy.png'; // 그 외의 날씨
    }

    return imageUrl;
}