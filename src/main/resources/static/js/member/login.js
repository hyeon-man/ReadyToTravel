function handleRedirect() {
    // 리디렉션 이벤트에 대한 처리를 여기에 작성하세요.
    alert("다시 로그인을 시도해주세요");
    // 추가적인 작업을 수행할 수 있습니다.
}

// 페이지 리디렉션 이벤트를 감지하는 함수를 등록합니다.
window.onload = function() {
    // window.performance 객체를 사용하여 페이지 리디렉션 이벤트를 확인합니다.
    if (window.performance && window.performance.navigation.type === window.performance.navigation.TYPE_REDIRECT) {
        // 페이지가 리디렉션되었을 때 handleRedirect 함수를 호출합니다.
        handleRedirect();
    }
};
