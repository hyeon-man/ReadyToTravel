 // 스크롤 픽시드 얘도 공통요소임 css에 주석 친거랑 같이
 //    window.addEventListener('scroll', function() {
 //        const header = document.querySelector('header');
 //        if (window.pageYOffset > 0) {
 //            header.classList.add('fixed-header');
 //            header.style.webkitBoxShadow = '0 2px 5px 0 rgba(0, 0, 0, 0.2)';
 //        } else {
 //            header.classList.remove('fixed-header');
 //            header.style.webkitBoxShadow = 'none';
 //        }
 //    });

(function () {
    "use strict";

    // define variables
    var items = document.querySelectorAll(".timeline li");

    // check if an element is in viewport
    // http://stackoverflow.com/questions/123999/how-to-tell-if-a-dom-element-is-visible-in-the-current-viewport
    function isElementInViewport(el) {
        var rect = el.getBoundingClientRect();
        return (
            rect.top >= 0 &&
            rect.left >= 0 &&
            rect.bottom <=
            (window.innerHeight || document.documentElement.clientHeight) &&
            rect.right <= (window.innerWidth || document.documentElement.clientWidth)
        );
    }

    function callbackFunc() {
        for (var i = 0; i < items.length; i++) {
            if (isElementInViewport(items[i])) {
                items[i].classList.add("in-view");
            }
        }

    }


    // listen for events
    window.addEventListener("load", callbackFunc);
    window.addEventListener("resize", callbackFunc);
    window.addEventListener("scroll", callbackFunc);
})();
