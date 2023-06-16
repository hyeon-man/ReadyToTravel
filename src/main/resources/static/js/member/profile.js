// add hovered class to selected list item
let list = document.querySelectorAll(".navigation li");
let groupNum;
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
});

//모달 그룹페이지
document.addEventListener("DOMContentLoaded", function() {
    var liElement = document.querySelector(".group_page");
    var modal = document.getElementById("modal3");
    var closeButton = document.querySelector(".close-button-group");

    liElement.addEventListener("click", function() {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // Disable scrolling on the body

        // 초기화 함수
        function resetData() {
            const membersContainer = document.querySelector('.members');
            membersContainer.innerHTML = ''; // 기존 데이터 초기화

            const leaderContents = document.querySelector('#leaderContents');
            leaderContents.innerHTML = '';
        }

// 데이터 처리 함수
        function processData(data) {
            // memberInfo
            const titleElement = document.querySelector('.groupName');
            const contentsElement = document.querySelector('.modal3-1-text');
            const membersContainer = document.querySelector('.members');
            const imgElement = document.createElement('img');
            membersContainer.appendChild(imgElement);
            // memberUpdate(Group)
            const leaderContents = document.querySelector('#leaderContents');

            // 제목 태그에 데이터 추가
            titleElement.textContent = data.name;
            // 컨텐츠에 데이터 추가
            contentsElement.textContent = data.contents;

            // 구성원 정보 추가
            data.memberDTO.forEach(member => {


                imgElement.src = "/img/" + member.profileIMG;
                imgElement.alt = 'Profile Image';

                const memberElement = document.createElement('div');
                memberElement.className = 'member';
                // memberElement.style.display = 'flex';

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


                //leader contents
                const memberIdTd = document.createElement('td');
                memberIdTd.textContent = member.memberId;

                const nameTd = document.createElement('td');
                nameTd.textContent = member.name;

                const emailTd = document.createElement('td');
                emailTd.textContent = member.email;

                const phoneNumTd = document.createElement('td');
                phoneNumTd.textContent = member.phoneNum;

                const deleteA = document.createElement('button');

                var memberSession = '<%=(String)session.getAttribute("uid")%>';
                if (memberSession.num != member.num){
                    deleteA.textContent = "삭제";
                } else {

                }
                deleteA.addEventListener('click', function() {


                    fetch('/member/profile/removeMemberInGroup/' + groupNum + '?memberNum=' + member.num, {
                        method: 'GET',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                        .then(response => response.text())
                        .then(data => {
                            // 요청이 성공적으로 처리되었을 때의 동작
                            location.reload();
                            console.log(data);
                            console.log("멤버 넘버 success" + member.num + "GroupNum Success: " + groupNum);
                        })
                        .catch(error => {
                            // 요청이 실패했을 때의 동작
                            console.log("멤버 넘버 error" + member.num + "GroupNum Success" + groupNum);
                            console.error('Error:', error);
                        });
                });



                deleteTd.appendChild(deleteA);


                leaderContents.appendChild(memberIdTd);
                leaderContents.appendChild(nameTd);
                leaderContents.appendChild(emailTd);
                leaderContents.appendChild(phoneNumTd);
                leaderContents.appendChild(deleteTd);


            });

        }


// 초기화 후 데이터 처리
        fetch('/member/profile/groupList')
            .then(response => response.json())
            .then(data => {
                if (data.num != null){
                    groupNum = data.num;
                    resetData(); // 데이터 초기화
                    processData(data); // 데이터 처리
                } else{
                    const membersContainer = document.querySelector('.members');
                    membersContainer.textContent = "가입된 그룹이 없습니다.";
                    const groupEditButton = document.querySelector('.more-button');
                    groupEditButton.style.display = "none";
                    const inviteButton = document.querySelector("#copyButton");
                    inviteButton.style.display="none";
                }

            })
            .catch(error => {
                // fetch 요청 중에 발생한 오류를 처리합니다
                console.log('오류 발생:', error);
            });
    });

    closeButton.addEventListener("click", function() {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // Enable scrolling on the body
    });
});

//모달 그룹페이지 안에 more button
const moreButton = document.querySelector('.more-button');
const modal = document.getElementById('modal3-1');

moreButton.addEventListener('click', function() {
    modal.style.display = 'block';
});

modal.addEventListener('click', function(e) {
    if (e.target === modal) {
        modal.style.display = 'none';
    }
});
//모달 계획페이지
document.addEventListener("DOMContentLoaded", function() {
    var liElement = document.querySelector(".plan_page");
    var modal = document.getElementById("modal4");
    var closeButton = document.querySelector(".close-button-plan");

    liElement.addEventListener("click", function() {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // Disable scrolling on the body
    });

    closeButton.addEventListener("click", function() {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // Enable scrolling on the body
    });
});
//모달 나의 여행 후기 페이지
document.addEventListener("DOMContentLoaded", function() {
    var liElement = document.querySelector(".review_page");
    var modal = document.getElementById("modal5");
    var closeButton = document.querySelector(".close-button-review");

    liElement.addEventListener("click", function() {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // Disable scrolling on the body
    });

    closeButton.addEventListener("click", function() {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // Enable scrolling on the body
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


//초대코드 버튼
const copyButton = document.getElementById('copyButton');
const urlInput = document.getElementById('urlInput');

copyButton.addEventListener('click', function() {
    const url = 'https://example.com'; // 복사할 URL을 여기에 입력하세요
    copyToClipboard(url);
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


//////