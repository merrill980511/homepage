$(function(){
	var name = $("#name");
	var nameEnglish = $("#nameEnglish");
	var gender = $("#gender");
	var birthday = $("#birthday");
	var phone = $("#phone");
	var office = $("#office");
	var department = $("#department");
	var title = $("#title");
	var setInfoAction = $("#setInfoAction");
    //回车
    $(document).on("keydown",function(event){
        if(event.keyCode ==13){
            $("#setInfoAction").click();
        }
    });
	//姓名失去焦点
	name.on("blur",function(){
		if(checkIsNull(name)){
            name.next().children("label").text("姓名不能为空");
            name.next().show();
		}else{
            name.next().hide();
		}
	});
    //选择生日
    birthday.on("click",function(){
        birthday.attr("type","date");
    });
	//生日失去焦点
    birthday.on("blur",function(){
        if(checkIsNull(birthday)){
            birthday.next().children("label").text("生日不能为空");
            birthday.next().show();
        }else{
            birthday.next().hide();
        }
    });
    //手机号失去焦点
    phone.on("blur",function(){
    	if(checkIsNull(phone)){
            phone.next().children("label").text("手机号码不能为空");
            phone.next().show();
		} else if(!checkPhone(phone)){
            phone.next().children("label").text("手机号码格式不正确");
            phone.next().show();
        }else{
            phone.next().hide();
        }
    });
    //部门失去焦点
    department.on("blur",function(){
        if(checkIsNull(department)){
            department.next().children("label").text("学院不能为空");
            department.next().show();
        } else{
            department.next().hide();
        }
    });
    //职称失去焦点
    title.on("blur",function(){
        if(checkIsNull(title)){
            title.next().children("label").text("职称不能为空");
            title.next().show();
        } else{
            title.next().hide();
        }
    });
    //办公地址失去焦点
    office.on("blur",function(){
        if(checkIsNull(office)){
            office.next().children("label").text("办公地址不能为空");
            office.next().show();
        } else{
            office.next().hide();
        }
    });
	//点击确认
	setInfoAction.on("click",function(){
        setInfoAction.next().hide();
        if(!checkErrorMessage(name,birthday,phone)){
            setInfoAction.next().children("label").text("请检查信息是否填写正确！");
            setInfoAction.next().show();
		}else{
            sendInfo(name,nameEnglish,gender,birthday,phone);
		}
	});
	//检测手机号码格式
	function checkPhone(phone){
        var reg = /^1[3,5,8]\d{9}$/;
        if(reg.test(phone.val()))
        {
            return true;
        }
        else
        {
            return false;
        }
	};
	//检测是否为空
	function checkIsNull(input) {
		if(input.val() == ""){
			return true;
		}else{
			return false;
		}
    }
    //检测是否有错误信息
	function checkErrorMessage(name,birthday,phone){
		if(checkIsNull(name)||checkIsNull(birthday)||checkIsNull(phone)||checkIsNull(title)||checkIsNull(department)||checkIsNull(office)||!checkPhone(phone)){
			return false;
		}else{
            return true;
		}
	}
	//注册动作
	function sendInfo(name,nameEnglish,gender,birthday,phone){
		$.ajax({
			"url":"/user/info/addCommit",
			"method":"post",
            "headers": {
                "Content-Type": "application/json",
            },
			"data":"{\"name\":\""+name.val()+"\",\"nameEnglish\":\""+nameEnglish.val()+"\",\"gender\":\""+gender.val()+"\",\"birth\":\""+birthday.val()+"\",\"phone\":\""+phone.val()+"\",\"office\":\""+office.val()+"\",\"department\":\""+department.val()+"\",\"title\":\""+title.val()+"\"}",
			"dataType":"json",
			"success":function(data){
				if(data.message == "true"){
                    location.href="/user/homepage/homepageGenerate";
					return true;
				}else{
                    setInfoAction.next().children("label").text(data.message);
                    setInfoAction.next().show();
					return false;
				}
			},
			"fail":function(data){
                setInfoAction.next().children("label").text("服务器繁忙，请稍后再试");
                setInfoAction.next().show();
				return false;
			},
		});
	}
});