const form = document.initPassword_form;

form.addEventListener("submit", function(event) {
    event.preventDefault(); // 기본 폼 제출 동작 방지

    const formData = new FormData();
    formData.append('memberId', form.memberId.value);
    formData.append('email', form.email.value);

    fetch('initPassword', {
        method: "POST",
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            if (data === "SUCCESS") {
                // 성공적으로 처리된 경우
                console.log("Password initialization successful");
                window.location.href = "http://localhost:9070/member/login";

            } else {
                console.log("Password initialization failed");
                }
        })
        .catch(error => {
            console.error("An error occurred during password initialization:", error);
            // 에러 처리 로직 수행
        });
});
