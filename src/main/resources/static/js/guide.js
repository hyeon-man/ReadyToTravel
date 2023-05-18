
    function Splitting() {

}

    Splitting();

    setTimeout(function() {
    document.querySelector('.first').classList.add('active');
}, 300);

    const sections = document.querySelectorAll('.triggerOnScroll');

    // Using Intersection Observer ↓

    const observerConfig = {
    root: null,
    rootMargin: '600px 0px 0px',
    threshold: 0.1
};

    const observer = new IntersectionObserver((entries, observer) => {
    entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('active');
            } else {
                entry.target.classList.remove('active');
            }
        }
    );
}, observerConfig);

    sections.forEach(section => {
    observer.observe(section);
});
    // 스크롤 픽시드 얘도 공통요소임 css에 주석 친거랑 같이
    window.addEventListener('scroll', function() {
        const header = document.querySelector('header');
        if (window.pageYOffset > 0) {
            header.classList.add('fixed-header');
            header.style.webkitBoxShadow = '0 2px 5px 0 rgba(0, 0, 0, 0.2)';
        } else {
            header.classList.remove('fixed-header');
            header.style.webkitBoxShadow = 'none';
        }
    });