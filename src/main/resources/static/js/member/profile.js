// add hovered class to selected list item
let list = document.querySelectorAll(".navigation li");

function activeLink() {
    list.forEach((item) => {
        item.classList.remove("hovered");
    });
    this.classList.add("hovered");
}

list.forEach((item) => item.addEventListener("click", activeLink));

// Menu Toggle
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function () {
    navigation.classList.toggle("active");
    main.classList.toggle("active");
};



document.addEventListener('DOMContentLoaded', function() {
    // li class="my_page" 클릭 시 이벤트 핸들러
    document.querySelector('.my_page').addEventListener('click', function() {
        // id=my_page 요소로 스크롤 이동
        document.getElementById('my_page').scrollIntoView({ behavior: 'smooth' });
    });
    // // li class="group_page" 클릭 시 이벤트 핸들러
    document.querySelector('.group_page').addEventListener('click', function() {
        // id=group_card 요소로 스크롤 이동
        document.getElementById('group_card').scrollIntoView({ behavior: 'smooth' });
    });

    // // li class="plan_page" 클릭 시 이벤트 핸들러
    document.querySelector('.plan_page').addEventListener('click', function() {
        // id=plan_card 요소로 스크롤 이동
        document.getElementById('plan_card').scrollIntoView({ behavior: 'smooth' });
    });

    // // li class="help_page" 클릭 시 이벤트 핸들러
    document.querySelector('.help_page').addEventListener('click', function() {
        // id=help_card 요소로 스크롤 이동
        document.getElementById('help_card').scrollIntoView({ behavior: 'smooth' });
    });
});

//모달
document.addEventListener("DOMContentLoaded", function() {
    var card = document.querySelector(".my_card");
    var modal = document.getElementById("modal");
    var closeButton = document.querySelector(".close-button");

    card.addEventListener("click", function() {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // Disable scrolling on the body
    });

    closeButton.addEventListener("click", function() {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // Enable scrolling on the body
    });
});
