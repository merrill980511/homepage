$(function(){
    var email = $("#email");
    var pwd = $("#pwd");
    var loginAction = $("#loginAction");
    //回车
    $(document).on("keydown",function(event){
        if(event.keyCode ==13){
            $("#loginAction").click();
        }
    });
    //邮箱获得焦点
    email.on("focus",function(){
        email.next().hide();
    });
    //邮箱失去焦点
    email.on("blur",function(){
        if(!checkEmail(email)){
            email.next().children("label").text("邮箱格式不正确");
            email.next().show();
        }else{
            email.next().hide();
        }
    });
    //密码获得焦点
    pwd.on("focus",function() {
        pwd.next().hide();
    });
    //密码失去焦点
    pwd.on("blur",function(){
        if(pwd.val().length<8||pwd.val().length>16){
            pwd.next().children("label").text("密码长度8~16");
            pwd.next().show();
        }else{
            pwd.next().hide();
        }
    });
    //点击登录
    loginAction.on("click",function(){
        if(checkEmail(email)&&pwd.val().length>=8&&pwd.val().length<=16){
            loginAction.next().hide();
            sendLogin(email,pwd);
        }
    });
    //检测邮箱格式
    function checkEmail(email){
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if(reg.test(email.val())){
            return true;
        }else{
            return false;
        }
    };
    //登录动作
    function sendLogin(email,pwd){
        $.ajax({
            "url":"/user/login/commit",
            "method":"post",
            "headers": {
                "Content-Type": "application/json",
            },
            "data":"{\"email\":\""+email.val()+"\",\"password\":\""+hex_md5(pwd.val())+"\"}",
            "dataType":"json",
            "success":function(data){
                if(data.message == "true"){
                    location.href="/user/homepage/homepageGenerate";
                    return true;
                }else{
                    loginAction.next().children("label").text(data.message);
                    loginAction.next().show();
                    return false;
                }
            },
            "fail":function(data){
                loginAction.next().children("label").text("服务器繁忙，请稍后再试");
                loginAction.next().show();
                return false;
            },
        });
    }
});