$(function () {
    var applayFrame = $("#applyFrame");
    var urlApplyFor = $("#urlApplyFor");
    var applyAction = $("#applyAction");
    //回车
    $(document).on("keydown",function(event){
        if(event.keyCode ==13){
            $("#applyAction").click();
        }
    });
    //点击申请按钮
    applyAction.on("click",function () {
        sendApply(urlApplyFor);
    });
    //用户申请域名
    function sendApply(urlApplyFor){
        $.ajax({
            "url":"/user/url/commit",
            "method":"post",
            "headers": {
                "Content-Type": "application/json",
            },
            "data":"{\"url\":\""+urlApplyFor.val()+"\"}",
            "dataType":"json",
            "success":function(data){
                if(data.message == "true"){
                    location.href="/user/info";
                    return true;
                }else{
                    applayFrame.next().children("label").text(data.message);
                    applayFrame.next().show();
                    return false;
                }
            },
            "fail":function(data){
                applayFrame.next().children("label").text("服务器繁忙，请稍后再试");
                applayFrame.next().show();
                return false;
            },
        });
    };
});