(function() {
    'use strict';

    // define variables
    var items = document.querySelectorAll('.timeline li');

    // check if an element is in viewport
    function isElementInViewport(el, offset) {
        var rect = el.getBoundingClientRect();
        return (
            rect.top + offset >= 0 &&
            rect.left >= 0 &&
            rect.bottom - offset <= (window.innerHeight || document.documentElement.clientHeight) &&
            rect.right <= (window.innerWidth || document.documentElement.clientWidth)
        );
    }

    function callbackFunc() {
        for (var i = 0; i < items.length; i++) {
            if (isElementInViewport(items[i], 3)) {
                items[i].classList.add('in-view');
            } else {
                items[i].classList.remove('in-view');
            }
        }
    }

    // listen for events
    window.addEventListener('load', callbackFunc);
    window.addEventListener('resize', callbackFunc);
    window.addEventListener('scroll', callbackFunc);
})();