let isCheck;
let emailValidate;
function checkId(mode) {
    if (document.signup_form.memberId.value == "") {
        alert("중복검사 전에 아이디를 입력해 주세요");
        return;
    }

    if (mode) {
        checkIdAsync();
    }
}

function checkEmail(emailCheckButton) {
    if (document.signup_form.email.value == "") {
        alert("메일 인증을 진행 하려면 메일을 입력해주세요");
        return;
    }

    if (emailCheckButton) {
        checkEmailAsync();
    }
}
function checkIdAsync() {
    const form = document.signup_form;
    const url = "checkId/" + form.memberId.value;

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
                isCheck = form.memberId.value;
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
    const form = document.signup_form;
    const urlEmail = "checkEmail/" + form.email.value;

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
                emailValidate = form.email.value;
                alert("이메일로 보안코드를 전송하였습니다.");
                const elementButton = document.getElementById("hiddenButton");
                elementButton.removeAttribute("id");

                const elementInput = document.getElementById("hiddenInput");
                elementInput.removeAttribute("id");

            }else
            {
                alert("이미 존재하는 이메일 입니다.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
}
function signup() {
    const form = document.signup_form;

    if (isCheck != form.memberId.value) {
        alert("아이디 중복 검사를 하셔야 합니다");
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
}