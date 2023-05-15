$("#checkId").click(function() {
    const val = $("input[name=id]").val();

    $.ajax({
        type:'POST',
        url:'/checkId/' + val,
        success:function(result){
            if(result == 'OK'){
                alert("사용가능한 아이디 입니다");

            }else{
                alert("이미 사용중입니다");
            }
        }
    })
})
/*check pw*/
$('.passwdCheck').focusout(function(){
    const pw1 = $("#passwd1").val();
    const pw2 = $("#passwd2").val();

    if(pw1 == pw2){
        const pws = 1;
        $("#same").css('display', 'inline-block');
        $("#different").css('display', 'none');
    }else{
        const pws = 0;
        $('#same').css('display', 'none');
        $('#different').css('display', 'inline-block');
    }
});