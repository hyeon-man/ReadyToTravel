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

// 드래그막기
function disableTextSelection() {
    return false;
}

// 모달안에있는 비밀번호보이기 토글버튼
const togglePassword = (event) => {
    const passwordInput = event.target.parentNode.parentNode.querySelector('input[name="password"]');
    const icon = event.target;

    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        icon.setAttribute('name', 'eye-off-outline');
    } else {
        passwordInput.type = 'password';
        icon.setAttribute('name', 'eye-outline');
    }
};

const eyeIcons = document.querySelectorAll('.eyes ion-icon');
eyeIcons.forEach(icon => {
    icon.addEventListener('click', togglePassword);
});

// ------------------------------------이미지 비동기 변경 출처G쌤 매핑해서해보셈-----------------------

// 파일 선택 이벤트 리스너 등록
// const fileInput = document.getElementById('fileUpload');
// fileInput.addEventListener('change', async (event) => {
//     const file = event.target.files[0];
//
//     try {
//         const imageURL = await uploadImage(file); // 이미지 비동기 업로드 함수 호출
//
//         // 페이지에 업로드된 이미지 표시
//         const previewImage = document.getElementById('previewImage');
//         previewImage.src = imageURL;
//
//         console.log('이미지가 성공적으로 변경되었습니다.');
//     } catch (error) {
//         console.error('이미지 변경에 실패했습니다:', error);
//     }
// });

// 이미지 비동기 업로드 함수
// function uploadImage(file) {
//     return new Promise((resolve, reject) => {
//         const formData = new FormData();
//         formData.append('image', file);
//
//         const xhr = new XMLHttpRequest();
//         xhr.open('POST', '/member/myPage/profileIMGUpdate'); // 이미지 업로드를 처리하는 서버 URL로 변경해야 함
//
//         xhr.onload = function () {
//             if (xhr.status === 200) {
//                 const response = JSON.parse(xhr.responseText);
//                 const imageURL = response.url; // 업로드된 이미지 URL
//                 resolve(imageURL);
//             } else {
//                 reject(xhr.statusText);
//             }
//         };
//
//         xhr.onerror = function () {
//             reject('Network error');
//         };
//
//         xhr.send(formData);
//     });
// }