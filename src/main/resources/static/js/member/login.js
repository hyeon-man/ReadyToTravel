function handleRedirect() {
    // 리디렉션 이벤트에 대한 처리를 여기에 작성하세요.
    alert("다시 로그인을 시도해주세요");
    // 추가적인 작업을 수행할 수 있습니다.
}

// 페이지 리디렉션 이벤트를 감지하는 함수를 등록합니다.
window.addEventListener("load", function() {
    // 네트워크 엔트리에서 리디렉션 이벤트를 검색합니다.
    const entries = performance.getEntriesByType("navigation");
    const navigationEntry = entries[0];

    if (navigationEntry.type === "reload" || navigationEntry.type === "back_forward") {
        // 페이지가 리로드되거나 뒤로/앞으로 이동되었을 때 handleRedirect 함수를 호출합니다.
        handleRedirect();
    }
});
