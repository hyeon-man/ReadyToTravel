let isCheck;
let emailValidate;
let emailValidationCheck;
const form = document.signup_form;

const checkIdButton = document.getElementById("idButton");
checkIdButton.addEventListener("click", function() {
    const checkIdForm = document.signup_form;
    const checkIdValue = checkIdForm.memberId.value;


    if (checkIdValue.trim() === "") {
        alert("아이디 중복 검사 전 아이디를 입력 해주세요");
        checkIdForm.memberId.focus();
        return;
    }
    const url = "checkId/" + checkIdValue;

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
                    const elementIdButton = document.getElementById("idButton");
                    elementIdButton.style.display = "none";
                } else {
                    isCheck = "";
                    console.log("No Use");
                }
            } else {
                alert("이미 사용중인 아이디입니다");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
});

const emailCheck = document.getElementById("emailButton");

emailCheck.addEventListener("click", function() {
    const checkEmailForm = document.signup_form;
    const checkEmailValue = checkEmailForm.email.value;
    if (checkEmailValue.trim() === "") {
        alert("이메일 중복 검사 전 아이디를 입력 해주세요");
        checkEmailForm.email.focus();
        return;
    }
    const urlEmail = "/member/checkEmail/" + checkEmailForm.email.value;

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
    });

const validationButton = document.getElementById("mailValidationButton");
validationButton.addEventListener("click", function() {
    const validateForm = document.signup_form;
    const validateValue = validateForm.mailValidateCode.value;

    if (validateValue.trim() === ""){
        alert("보안코드 검사 전 보안코드를 입력해주세요");
        validateForm.mailValidateCode.focus();
    }

    const url = '/member/validateCode';
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
                alert("이메일 인증이 완료 되었습니다!");
                const inputElementEmail = document.getElementById("email");
                inputElementEmail.readOnly = true;

                const elementButton = document.getElementById("mailValidationButton");
                elementButton.style.display = "none";

                const elementInput = document.getElementById("mailValidationCodeInput");
                elementInput.style.display = "none";

                const elementLabel = document.getElementById("mailValidationCodeLabel");
                elementLabel.style.display = "none";

                const elementEmailButton = document.getElementById("emailButton");
                elementEmailButton.style.display = "none";

                emailValidationCheck = 1;
            } else if (data === 'emailValidFAIL') {
                alert("유효하지 않은 보안 코드입니다. 보안 코드를 받지 못한 경우 다시 시도하거나 이메일을 확인해주세요.");
                emailValidationCheck = 2;
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});


function signup() {
    const form = document.signup_form;

    if (isCheck != form.memberId.value) {
        alert("아이디 중복 검사를 하셔야 합니다");
        form.memberId.focus();
        return;
    }

    if (emailValidate != form.email.value){
        alert("이메일 인증을 진행하십시오");
        form.email.focus();
        return;
    }

    if (emailValidationCheck != 1){
        alert("이메일 인증이 완료되지 않았습니다!");
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
    if (form.phoneNum.value == "") {
        alert("전화 번호를 입력해 주세요");
        form.phoneNum.focus();
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
        form.passwd_valid.focus();
        return;
    }

    form.submit();
    alert("회원가입 완료! 로그인 페이지로 이동합니다.");
}
