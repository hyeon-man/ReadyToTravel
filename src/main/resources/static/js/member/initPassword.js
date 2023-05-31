// 버튼 클릭 이벤트 핸들러 등록
const btn = document.getElementById('btn');
btn.addEventListener('click', initPassword);

// 비밀번호 초기화 함수
function initPassword() {
    const form = document.initPassword_form;
    const url = 'initPassword';

    const formData = new FormData();
    formData.append('memberId', form.memberId.value);
    formData.append('email', form.email.value);

    fetch(url, {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            if (data === 'SUCCESS') {
                alert("이메일로 초기화된 비밀번호를 전송했습니다.")
                window.location.href = '/member/login';
            } else if (data === 'FAIL') {
                alert("아이디 또는 이메일이 일치하지 않습니다.");
            }
        })
        .catch(error => {
            console.error('에러:', error);
        });
}