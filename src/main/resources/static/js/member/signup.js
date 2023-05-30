let isCheck;
let emailValidate;
let ValidateCodeCheck;
const form = document.signup_form;

function checkId(mode) {
    if (document.signup_form.memberId.value == "") {
        alert("중복검사 전에 아이디를 입력해 주세요");
        return;
    }

    if (mode) {
        checkIdAsync();
    }
}

function checkEmailButton(emailCheckButton) {
    if (document.signup_form.email.value == "") {
        alert("메일 인증을 진행 하려면 메일을 입력해주세요");
        form.email.focus();
        return;
    }

    if (emailCheckButton) {
        checkEmailAsync();
    }
}
function validationCodeButton(validationCodeButton){
    if (document.signup_form.mailValidateCode.value == "") {
        alert("인증 코드 확인을 위해 인증코드를 입력해주세요")
        form.mailValidateCode.focus();
        return;
    }
    if (validationCodeButton){
        validationCodeAsync();
    }

}
function checkIdAsync() {
    const checkIdForm = document.signup_form;
    const url = "checkId/" + checkIdForm.memberId.value;

    fetch(url)
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error("Network response is not OK");
            }
        })
        .then(result => {
            if (result === "OK") {
                isCheck = checkIdForm.memberId.value;
                alert("사용 가능한 아이디입니다");
                if (confirm("'" + isCheck + "' 이 아이디를 사용하시겠습니까?")) {
                    const inputElement = document.getElementById("id");
                    inputElement.readOnly = true;
                } else {
                    console.log("No Use");
                }
            } else {
                alert("이미 사용중인 아이디입니다");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

function checkEmailAsync() {
    const checkEmailForm = document.signup_form;
    const urlEmail = "checkEmail/" + checkEmailForm.email.value;

    fetch(urlEmail)
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error("Network response is not OK");
            }
        })
        .then(result => {
            if (result === "sendMailOK") {
                emailValidate = checkEmailForm.email.value;
                alert("이메일로 보안코드를 전송하였습니다.");
                const elementButton = document.getElementById("mailValidationButton");
                elementButton.style.display = "block";

                const elementInput = document.getElementById("mailValidationCodeInput");
                elementInput.style.display = "block";

                const elementLabel = document.getElementById("mailValidationCodeLabel");
                elementLabel.style.display = "block";
            }else
            {
                alert("이미 존재하는 이메일 입니다.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

function validationCodeAsync() {
    const validateForm = document.signup_form;
    const url = 'validateCode';

    const formData = new FormData();
    formData.append('email', validateForm.email.value);
    formData.append('mailValidateCode', validateForm.mailValidateCode.value);

    fetch(url, {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            if (data === 'emailValidOK') {
                alert("이메일 인증 성공!")
                const inputElementEmail = document.getElementById("email");
                inputElementEmail.readOnly = true;

                const elementButton = document.getElementById("mailValidationButton");
                elementButton.style.display = "none";

                const elementInput = document.getElementById("mailValidationCodeInput");
                elementInput.style.display = "none";

                const elementLabel = document.getElementById("mailValidationCodeLabel");
                elementLabel.style.display = "none";
            } else if (data === 'emailValidFAIL') {
                alert("유효하지 않은 보안코드 입니다. 보안코드가 오지 않았다면 다시 시도하거나 이메일을 확인해주세요");
            }
        })
        .catch(error => {
            console.error('에러:', error);
        });
}


function signup() {
    const form = document.signup_form;

    if (isCheck != form.memberId.value) {
        alert("아이디 중복 검사를 하셔야 합니다");
        return;
    }

    if (emailValidate != form.email.value){
        alert("이메일 인증을 진행하십시오");
        return;
    }

    if (form.memberId.value == "") {
        alert("아이디를 입력해 주세요");
        form.memberId.focus();
        return;
    }
    if (form.email.value == "") {
        alert("이메일을 입력해 주세요");
        form.email.focus();
        return;
    }

    if (form.name.value == "") {
        alert("이름을 입력해 주세요");
        form.name.focus();
        return;
    }

    if (form.password.value == "") {
        alert("비밀번호를 입력해 주세요");
        form.password.focus();
        return;
    }

    if (form.passwd_valid.value == "") {
        alert("비밀번호 확인을 입력해 주세요");
        form.passwd_valid.focus();
        return;
    }

    if (form.password.value != form.passwd_valid.value) {
        alert("비밀번호가 일치하지 않습니다");
        form.password.focus();
        return;
    }

    form.submit();
    alert("회원가입 완료! 로그인 페이지로 이동합니다.")
}
