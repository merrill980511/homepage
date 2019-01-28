$(function(){
	var email = $("#email");
	var pwd = $("#pwd");
	var re_pwd = $("#re_pwd");
	var code = $("#code");
	var getCode = $("#getCode");
	var registerAction = $("#registerAction");
    //回车
    $(document).on("keydown",function(event){
        if(event.keyCode ==13){
            $("#registerAction").click();
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
		}else if(checkEmailNotRepeat(email)){
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
	//重输密码获得焦点
    re_pwd.on("focus",function() {
        re_pwd.next().hide();
    });
	//重输密码失去焦点
	re_pwd.on("blur",function(){
		if(checkPwd(pwd,re_pwd)){
			re_pwd.next().children("label").text("两次密码输入不一致");
			re_pwd.next().show();
		}else{
			re_pwd.next().hide();
		}
	});
	//点击获取验证码
	getCode.on("click",function(){
        getCode.next().hide();
	    setMessage();
	    checkCodeSend(email);
    });
	//点击注册
	registerAction.on("click",function(){
        registerAction.next().hide();
        sendRegister(email,pwd,code);
	});
	//获取验证码倒计时
	function setMessage(){
	   var time = 60;
	   var count = setInterval(() => {
		   if (time == 0) {
			   getCode.text('重新发送').removeAttr('disabled');
			   getCode.css({
				   background: 'rgb(0, 183, 211)',
				   color:'#fff',
				   });
			   clearInterval(count);
			   }else {
				   getCode.attr('disabled', true);
				   getCode.css({
					   background: '#d8d8d8',
					   color: '#707070',
			       });
				   getCode.text(time + '秒后可重新获取');
				   }
		   time--;
		   },1000);
	};
	//检测邮箱格式
	function checkEmail(email){
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(reg.test(email.val())){
			return true;
		}else{
			return false;
		}
	};
	//检测两次密码是否一致
	function checkPwd(pwd,re_pwd){
		if(pwd.val()!=re_pwd.val()){
			return true;
		}else{
			return false;
		}
	};
	//检测邮箱是否重复
	function checkEmailNotRepeat(email){
		$.ajax({
            "url": "/user/register/emailCommit",
            "method": "POST",
            "headers": {
                "Content-Type": "application/json",
            },
            "data": "{\"email\":\""+email.val()+"\"}",
            "dataType":"json",
			"success":function(data){
				if(data.message=="true"){
					return true;
				}else{
					email.next().children("label").text(data.message);
					email.next().show();
					return false;
				}
			},
			"fail":function(data){
				email.next().children("label").text("服务器繁忙，请稍后再试");
				email.next().show();
				return false;
			}
		});
	};
	//检测验证码发送
	function checkCodeSend(email){
		$.ajax({
			"url":"/user/register/verificationCodeCommit",
			"method":"post",
            "headers": {
                "Content-Type": "application/json",
            },
            "data": "{\"email\":\""+email.val()+"\"}",
			"dataType":"json",
			"success":function(data){
				if(data.message == "true"){
					return true;
				}else{
					code.next().children("label").text(data.message);
					code.next().show();
					return false;
				}
			},
			"fail":function(data){
				code.next().children("label").text(data.message+"，请稍后再试");
				code.next().show();
				return false;
			}
		});
	};
	//注册动作
	function sendRegister(email,pwd,code){
		$.ajax({
			"url":"/user/register/commit",
			"method":"post",
            "headers": {
                "Content-Type": "application/json",
            },
			"data":"{\"email\":\""+email.val()+"\",\"password\":\""+hex_md5(pwd.val())+"\",\"code\":\""+code.val()+"\"}",
			"dataType":"json",
			"success":function(data){
				if(data.message == "true"){
                    location.href="/user/url";
					return true;
				}else{
					registerAction.next().children("label").text(data.message);
					registerAction.next().show();
					return false;
				}
			},
			"fail":function(data){
				registerAction.next().children("label").text("服务器繁忙，请稍后再试");
				registerAction.next().show();
				return false;
			},
		});
	}
});