
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
