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
document.querySelector('nav ul li:nth-child(1) a').addEventListener('click', function(e) {
    e.preventDefault();
    document.querySelector('#parallax1').scrollIntoView({
        behavior: 'smooth'
    });
});