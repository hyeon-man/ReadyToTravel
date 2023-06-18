function getGroupList() {
    return new Promise((resolve, reject) => {
        fetch('/member/profile/groupList')
            .then(response => response.json())
            .then(data => {
                resolve(data);
            })
            .catch(error => {
                console.log('오류 발생:', error);
                reject(error);
            });
    });
}

function getBoardList() {
    return new Promise((resolve, reject) => {
        fetch('/member/profile/boardList')
            .then(response => response.json())
            .then(data => {
                resolve(data);
            })
            .catch(error => {
                console.log('오류 발생:', error);
                reject(error);
            });
    });
}

// add hovered class to selected list item
let list = document.querySelectorAll(".navigation li");

function activeLink() {
    list.forEach((item) => {
        item.classList.remove("hovered");
    });
    this.classList.add("hovered");
}
list.forEach((item) => item.addEventListener("click", activeLink));

// Menu Toggle
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function () {
    navigation.classList.toggle("active");
    main.classList.toggle("active");
};

//모달 내정보
document.addEventListener("DOMContentLoaded", function() {
    var card = document.querySelector(".my_card");
    var modal = document.getElementById("modal");
    var closeButton = document.querySelector(".close-button-my");

    card.addEventListener("click", function() {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // Disable scrolling on the body
    });

    closeButton.addEventListener("click", function() {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // Enable scrolling on the body
    });
});
/*
//모달 여행지
document.addEventListener("DOMContentLoaded", function() {
    var card = document.querySelector(".my_card2");
    var modal = document.getElementById("modal2");
    var closeButton = document.querySelector(".close-button-place");

    card.addEventListener("click", function() {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // Disable scrolling on the body
    });

    closeButton.addEventListener("click", function() {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // Enable scrolling on the body
    });
});*/

//모달 그룹페이지
document.addEventListener("DOMContentLoaded", function() {
    var liElement = document.querySelector(".group_page");
    var modal = document.getElementById("modal3");
    var closeButton = document.querySelector(".close-button-group");

    liElement.addEventListener("click", function() {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // Disable scrolling on the body

        // 초기화 함수

            const membersContainer = document.querySelector('.members');
            membersContainer.innerHTML = ''; // 기존 데이터 초기화

            // 데이터 처리 함수
        getGroupList().then(data => {
            // memberInfo
            const titleElement = document.querySelector('.groupName');
            const titleLinkElement = document.createElement('a');
            const contentsElement = document.querySelector('.modal3-1-text');
            const membersContainer = document.querySelector('.members');


            // 제목 태그에 데이터 추가
            titleLinkElement.textContent = data.name;
            titleLinkElement.href = "http://localhost:9060/plan/viewPlan/" + data.planNum;
            titleElement.appendChild(titleLinkElement);
            // 컨텐츠에 데이터 추가

            contentsElement.textContent = data.contents;
            data.memberDTO.forEach(member => {
                const imgElement = document.createElement('img');
                membersContainer.appendChild(imgElement);

                imgElement.src = "/img/" + member.profileIMG;
                imgElement.alt = 'Profile Image';

                const memberElement = document.createElement('div');
                memberElement.className = 'member';

                const memberIdElement = document.createElement('p');
                memberIdElement.textContent = member.memberId;

                const emailElement = document.createElement('p');
                emailElement.textContent = member.email;

                const nameElement = document.createElement('p');
                nameElement.textContent = member.name;

                const phoneNumElement = document.createElement('p');
                phoneNumElement.textContent = member.phoneNum;

                memberElement.appendChild(memberIdElement);
                memberElement.appendChild(emailElement);
                memberElement.appendChild(nameElement);
                memberElement.appendChild(phoneNumElement);

                membersContainer.appendChild(memberElement);
            });

        });


    });

    closeButton.addEventListener("click", function() {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // Enable scrolling on the body
    });
});


// 모달 그룹페이지 안에 more button
const moreButton = document.querySelector('.more-button');
const loginMemberNum = moreButton.dataset.value;
const modal = document.getElementById('modal3-1');
getGroupList().then(data => {
    if (data.groupLeader == loginMemberNum) {
        moreButton.addEventListener('click', function() {
            modal.style.display = 'block';

            // 데이터 초기화
            const groupEditTbody = document.querySelector('#groupEditTbody');
            groupEditTbody.innerHTML = '';

            // 구성원 정보 추가
            data.memberDTO.forEach(member => {
                const groupEditTr = document.createElement('tr');

                const groupEditId = document.createElement('td');
                groupEditId.textContent = member.memberId;
                groupEditTr.appendChild(groupEditId);

                const groupEditName = document.createElement('td');
                groupEditName.textContent = member.name;
                groupEditTr.appendChild(groupEditName);

                const groupEditEmail = document.createElement('td');
                groupEditEmail.textContent = member.email;
                groupEditTr.appendChild(groupEditEmail);

                const groupEditPhoneNum = document.createElement('td');
                groupEditPhoneNum.textContent = member.phoneNum;
                groupEditTr.appendChild(groupEditPhoneNum);

                const groupEditDeleteTd = document.createElement('td');
                if (member.num == data.groupLeader) {
                    groupEditDeleteTd.textContent = "";
                } else {
                    const groupEditDeleteButton = document.createElement('button');
                    groupEditDeleteButton.textContent = "삭제";
                    groupEditDeleteButton.addEventListener('click', function() {
                        fetch('/member/profile/removeMemberInGroup/' + data.num + '?memberNum=' + member.num, {
                            method: 'GET',
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        })
                            .then(response => response.text())
                            .then(groupEditDataResponse => {
                                // 요청이 성공적으로 처리되었을 때의 동작
                                location.reload();
                                alert("삭제 완료");
                            })
                            .catch(error => {
                                // 요청이 실패했을 때의 동작
                                console.error('Error:', error);
                            });
                    });

                    groupEditDeleteTd.appendChild(groupEditDeleteButton);
                }
                groupEditTr.appendChild(groupEditDeleteTd);

                groupEditTbody.appendChild(groupEditTr);
            });
        });
    }else {
        moreButton.style.display="none";
    }
});

modal.addEventListener('click', function(e) {
    if (e.target === modal) {
        modal.style.display = 'none';
    }
});


//모달 계획페이지
document.addEventListener("DOMContentLoaded", function() {
    var liElement = document.querySelector(".review_page");
    var modal = document.getElementById("modal5");
    var closeButton = document.querySelector(".close-button-review");

    liElement.addEventListener("click", function () {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // Disable scrolling on the body

        const boardListTbody = document.querySelector('.boardListTbody');
        boardListTbody.innerHTML = "";

        getBoardList()
            .then(data => {
                // 구성원 정보 추가
                data.forEach(boardList => {
                    const boardTr = document.createElement('tr');

                    const boardTitle = document.createElement('td');
                    const baordInfoLink = document.createElement('a');
                    boardTitle.appendChild(baordInfoLink);
                    baordInfoLink.textContent = boardList.boardName;
                    baordInfoLink.href = "/board/info/" + boardList.boardNum;

                    const boardCreateDate = document.createElement('td');
                    const createDate = new Date(boardList.boardDateCreate);
                    const formattedDate = `${createDate.getFullYear()}/${createDate.getMonth() + 1}/${createDate.getDate()}`;
                    boardCreateDate.textContent = formattedDate;

                    boardTr.appendChild(boardTitle);
                    boardTr.appendChild(boardCreateDate);
                    boardListTbody.appendChild(boardTr);
                });
            });

        closeButton.addEventListener("click", function () {
            modal.style.display = "none";
            document.body.style.overflow = "auto"; // Enable scrolling on the body
        });
    });
});


// 모달안에있는 비밀번호보이기 토글버튼
const togglePassword = (event) => {
    const passwordTop = event.target.closest('.password-box');
    const passwordMiddle = passwordTop.querySelector('.modal_password');
    const passwordBot = passwordMiddle.querySelector('input');
    const passwordIcon = passwordMiddle.querySelector('.eyes');
    const iconBox = passwordIcon.querySelector('ion-icon');

    const confirmMiddle = passwordTop.querySelector('.modal_password_confirm');
    const confirmBot = confirmMiddle.querySelector('input');

    if(passwordBot.type == 'password') {
        passwordBot.type = 'text';
        iconBox.setAttribute('name', 'eye-off-outline');
    } else if(passwordBot.type == 'text') {
        passwordBot.type = 'password';
        iconBox.setAttribute('name', 'eye-outline');
    }
    if(confirmBot.type == 'password') {
        confirmBot.type = 'text';
    } else if(confirmBot.type == 'text') {
        confirmBot.type = 'password';
    }
};

const eyeIcons = document.querySelectorAll('.eyes ion-icon');
eyeIcons.forEach(icon => {
    icon.addEventListener('click', togglePassword);
});

// 초대코드 버튼
const copyButton = document.getElementById('copyButton');
const urlInput = document.getElementById('urlInput');
copyButton.addEventListener('click', function() {
    getGroupList().then(data => {
            fetch('/group/generateInviteCode/' + data.num, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => response.text())
                .then(inviteData => {
                    // 요청이 성공적으로 처리되었을 때의 동작
                    const url = "http://localhost:9060/group/joinGroup/" + inviteData; // 복사할 URL을 여기에 입력하세요

                    copyToClipboard(url);
                })
                .catch(error => {
                    // 요청이 실패했을 때의 동작
                    console.error('Error:', error);
                });
        });
});

function copyToClipboard(text) {
    const input = document.createElement('input');
    input.style.position = 'fixed';
    input.style.opacity = 0;
    input.value = text;
    document.body.appendChild(input);
    input.select();
    document.execCommand('copy');
    document.body.removeChild(input);

    urlInput.value = text;
    urlInput.select();
    urlInput.setSelectionRange(0, 99999);
    document.execCommand('copy');

    setTimeout(function(){
        alert("복사되었습니다");
    }, 300);
}

