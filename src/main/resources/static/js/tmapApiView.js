let lonLatRealList = [];
let markers = [];
let marker_s;
let marker_e;
let manageMarker = [];
window.onload = function () {
    // map 생성
    // Tmapv2.Map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
    map = new Tmapv2.Map("map_div", {
        center: new Tmapv2.LatLng(36.35086524077589, 127.45422567640077), // 지도 초기 좌표
        width: "50%", // 지도의 넓이
        height: "100%", // 지도의 높이
        zoom: 12
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

            planName.textContent = response.name;
            planContents.textContent = response.contents

            const lonLatList = response.lonLatList;

            const li = document.getElementById('locationName');

            // 버튼의 textContent 값을 추출하여 배열로 저장
            const buttonTexts = lonLatList
                .filter(item => item.markerType === 'START') // markerType이 'START'인 것만 필터링
                .map(item => item.calendar); // calendar 값을 추출하여 배열로 변환

            // textContent 값을 정렬
            buttonTexts.sort();

            console.log(buttonTexts);

            // 정렬된 값들을 기반으로 버튼을 동적으로 생성하여 추가
            for (const text of buttonTexts) {
                const dateButton = document.createElement('a');
                const appendLi = document.createElement('li');
                const iconSpan= document.createElement('span');
                const titleSpan = document.createElement('span');
                iconSpan.classList = 'icon';
                appendLi.classList = 'review_page';
                titleSpan.classList.add('title');
                titleSpan.textContent = text;
                dateButton.classList.add('viewBtn');
                dateButton.appendChild(iconSpan);
                dateButton.appendChild(titleSpan);
                appendLi.appendChild(dateButton);
                li.parentNode.appendChild(appendLi);
            }

            $('.viewBtn').off().on('click', function (evt) {

                for (let i = 0; i < manageMarker.length; i++) {
                    manageMarker[i].setMap(null);
                }

                // 이전에 생성된 동적 요소들을 포함하는 부모 요소를 선택합니다.
                const parentElement = document.querySelector('.c-md-2');

                // tl-item 클래스를 가진 요소들을 선택합니다.
                const tlItems = parentElement.querySelectorAll('.tl-item-inner');

                // tl-item 클래스를 가진 요소들을 순회하며 제거합니다.
                tlItems.forEach((tlItem) => {
                    tlItem.remove();
                });

                const locationList = document.getElementsByClassName('locationList');

                while (locationList.length > 0) {
                    locationList[0].parentNode.removeChild(locationList[0]);
                }

                const clickBtn = evt.target;
                const textContentBtn = clickBtn.textContent;

                for (let i = 0; i < lonLatList.length; i++) {
                    if (textContentBtn == lonLatList[i].calendar) {
                        lonLatRealList.push(lonLatList[i]);
                    }
                }

                for (let i = 0; i < lonLatRealList.length; i++) {
                    let lon = lonLatRealList[i].lon;
                    let lat = lonLatRealList[i].lat;

                    reverseGeo(lon, lat);
                }

                for (let i = 0; i < markers.length; i++) {
                    markers[i].setMap(null);
                }
                createMarker(lonLatRealList)
                lonLatRealList = [];
            });
        }
    });
}

function reverseGeo(lon, lat) {

    var headers = {};
    headers["appKey"] = "6MTwtT0OK18O1A8FGiL349WFB2UyKhI11K5MsjXN";

    $.ajax({
        method: "GET",
        headers: headers,
        url: "https://apis.openapi.sk.com/tmap/geo/reversegeocoding?version=1&format=json&callback=result",
        async: false,
        data: {
            "coordType": "WGS84GEO",
            "addressType": "A10",
            "lon": lon,
            "lat": lat
        },
        success: function (response) {
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


            // 부모 요소를 선택합니다.
            const yearWrap = document.querySelector('.tl-year-wrap');
            const tlEnd = document.querySelector('.tl-end-div');

            // tl-item 요소를 생성하고 추가합니다.
            const tlItem = document.createElement('div');
            tlItem.classList.add('tl-item');
            tlItem.classList.add('tl-item-inner');

            // tl-date 요소를 생성합니다.
            const tlDate = document.createElement('span');
            tlDate.classList.add('tl-date');
            tlItem.appendChild(tlDate);

            // tl-event 요소를 생성합니다.
            const tlEvent = document.createElement('div');
            tlEvent.classList.add('tl-event');
            tlItem.appendChild(tlEvent);

            // h2 요소를 생성하고 내용을 설정합니다.
            const h2 = document.createElement('h2');
            h2.textContent = result;
            tlEvent.appendChild(h2);

            // tl-year-wrap와 tl-end 사이에 tl-item을 추가합니다.
            yearWrap.parentNode.insertBefore(tlItem, tlEnd);

        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n"
                + "message:" + request.responseText + "\n"
                + "error:" + error);
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
            title: markerType
        });

        if (markerType === 'START') {
            marker.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png");
        } else if (markerType === 'END') {
            marker.setIcon("http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png");
        } else if (markerType === 'VIAPOINT') {
            markers.push(marker);
        }
        manageMarker.push(marker);
    }
}