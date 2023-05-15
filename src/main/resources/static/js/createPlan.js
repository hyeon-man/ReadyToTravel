// date range picker 한글 설정 및 Calendar 불러오기
$(function () {
    $('input[name="daterangepicker"]').daterangepicker({
        "locale": {
            "format": "YYYY-MM-DD",
            "separator": " ~ ",
            "applyLabel": "확인",
            "cancelLabel": "취소",
            "fromLabel": "From",
            "toLabel": "To",
            "customRangeLabel": "Custom",
            "weekLabel": "W",
            "daysOfWeek": ["월", "화", "수", "목", "금", "토", "일"],
            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
            "firstDay": 1
        },
        opens: 'left'
    }, function (start, end, label) {
        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
        let sDate = new Date(start);
        let eDate = new Date(end);

        // 몇일 선택했는지 표시
        $('#calendarsDate').text(getDateRangeData(sDate, eDate).length + "Day")
        console.log(getDateRangeData(sDate, eDate));

        const ul = document.getElementById("dateByPlan")
        ul.replaceChildren();

        // Drop Down Menu 날짜 표시
        for (let i = 0; i < getDateRangeData(sDate, eDate).length; i++) {
            const input = document.createElement("input");
            var listDate = [];

            listDate = getDateRangeData(sDate, eDate);

            input.value = listDate[i];
            input.type = "text";
            input.name = "calendars";
            input.style = "border: none; outline: none; font-size: 15px";
            input.setAttribute("readonly", true);

            $('#dateByPlan').append(input);
        }
    });
});

// 날짜 형식 맞춰주는 function
function getDateRangeData(param1, param2) {  //param1은 시작일, param2는 종료일이다.
    var res_day = [];
    var ss_day = new Date(param1);
    var ee_day = new Date(param2);
    while (ss_day.getTime() <= ee_day.getTime()) {
        var _mon_ = (ss_day.getMonth() + 1);
        _mon_ = _mon_ < 10 ? '0' + _mon_ : _mon_;
        var _day_ = ss_day.getDate();
        _day_ = _day_ < 10 ? '0' + _day_ : _day_;
        res_day.push(ss_day.getFullYear() + '-' + _mon_ + '-' + _day_);
        ss_day.setDate(ss_day.getDate() + 1);
    }
    return res_day;
}

// 드롭 다운 메뉴
function dp_menu() {
    let click = document.getElementById("drop-content");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";
    }
}