let isCheck;

function checkId(mode) {
    if(document.signup_form.memberId.value == "") {
        alert("중복검사 전에 아이디를 입력 해 주세요");
        return;
    }

    if(mode)
        checkId_Async();
}

function checkId_Async() {
    const form = document.signup_form;

    const xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        console.log(xhr.readyState);

        if(xhr.readyState == XMLHttpRequest.DONE) {
            if(xhr.status == 200) {
                const result = xhr.responseText;

                if(result == "OK") {
                    isCheck = form.memberId.value;
                    alert("사용 가능한 아이디 입니다");
                    if (confirm("'" + isCheck + "' 이 아이디를 사용하시겠습니까?")){
                        const inputElement = document.getElementById("id");
                        inputElement.readOnly = true;
                    } else {
                        console.log("No Use");
                    }
                } else
                    alert("이미 사용중인 아이디 입니다");
            }
        }
    };

    xhr.open("GET", "checkId/" + form.memberId.value, true);

    xhr.send();
}

function signup() {
    const form = document.signup_form;

    if(isCheck != form.memberId.value) {
        alert("아이디 중복 검사를 하셔야 합니다");
        return;
    }

    if(form.memberId.value == "") {
        alert("아이디를 입력 해 주세요");
        form.memberId.focus();
        return;
    }
    if(form.email.value == "") {
        alert("이메일을 입력 해 주세요");
        form.email.focus();
        return;
    }

    if(form.password.value == "") {
        alert("비밀번호를 입력 해 주세요");
        form.password.focus();
        return;
    }

    if(form.passwd_valid.value == "") {
        alert("비밀번호 확인을 입력 해 주세요");
        form.passwd_valid.focus();
        return;
    }

    if(form.password.value != form.passwd_valid.value) {
        alert("비밀번호가 일치하지 않습니다");
        form.password.focus();
        return;
    }



    form.submit();
}