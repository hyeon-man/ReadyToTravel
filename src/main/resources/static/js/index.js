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