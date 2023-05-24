// 공통적인 상단헤드 고정
// window.addEventListener('scroll', function () {
//     const header = document.querySelector('header');
//     if (window.pageYOffset > 0) {
//         header.classList.add('fixed-header');
//         header.style.boxShadow = '0 2px 5px 0 rgba(0, 0, 0, 0.2)';
//     } else {
//         header.classList.remove('fixed-header');
//         header.style.boxShadow = 'none';
//         header.style.transition = 'box-shadow 0.3s ease-in-out';
//     }
// });

const cards = document.querySelectorAll('.card');
cards.forEach((card) => {
    card.addEventListener('click', function () {
        const inputVal = this.querySelector('input').value;
        const img = this.querySelector('img').getAttribute("src");
        const title = this.querySelector('h4').textContent;

        $("#cardComment").text(inputVal);
        document.querySelector("#cardImg").setAttribute("src", img);
        $("#cardTitle").text(title);
    });
});