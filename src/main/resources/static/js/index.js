const videos = [
    'videos1.mp4',
    'videos2.mp4',
    'videos3.mp4',
    'videos4.mp4'
];

const video = document.getElementById('video');
const randomIndex = Math.floor(Math.random() * videos.length);
const videoSource = `/video/${videos[randomIndex]}`;
video.src = videoSource;


// 소개 버튼 클릭 시 스무스 스크롤 기능 추가
// document.querySelector('nav ul li:nth-child(1) a').addEventListener('click', function(e) {
//     e.preventDefault();
//     document.querySelector('.search').scrollIntoView({
//         behavior: 'smooth'
//     });
// });
// 스크롤 픽시드 얘도 공통요소임 css에 주석 친거랑 같이
// window.addEventListener('scroll', function() {
//     const header = document.querySelector('header');
//     if (window.pageYOffset > 0) {
//         header.classList.add('fixed-header');
//         header.style.webkitBoxShadow = '0 2px 5px 0 rgba(0, 0, 0, 0.2)';
//     } else {
//         header.classList.remove('fixed-header');
//         header.style.webkitBoxShadow = 'none';
//     }
// });