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