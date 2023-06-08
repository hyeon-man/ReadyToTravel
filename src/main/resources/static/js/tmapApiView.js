let lonLatRealList = [];

window.onload = function () {
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

            const li = document.getElementById('locationName');

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
                dateButton.classList.add('viewBtn');
                li.appendChild(dateButton);
            }
            $('.viewBtn').off().on('click', function (evt) {
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
            const li = document.getElementById('locationName');
            const p = document.createElement('p');
            p.classList = "locationList";

            p.textContent = result;
            li.append(p);
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n"
                + "message:" + request.responseText + "\n"
                + "error:" + error);
        }
    });
}