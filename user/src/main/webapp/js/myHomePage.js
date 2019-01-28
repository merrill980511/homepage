$(function () {
    var sculpture_temp = 0;
    //页面显示
    showHomepage();
    //点击滑动按钮
    $("#template").on("click", ".slideToggle", function () {
        if ($(this).hasClass("slideDown")) {
            $(this).removeClass("slideDown").addClass("slideUp");
        } else {
            $(this).removeClass("slideUp").addClass("slideDown");
        }
        $(this).parent().next().slideToggle();
    });
    //获取url
    function getUrl(){
       var totalUrl =  window.location.href;
       var url = totalUrl.substring(totalUrl.lastIndexOf("/")+1);
       return url;
    }
    //模块集合获取（页面加载）
    function showHomepage() {
        $.ajax({
            "url": "/showHomepage",
            "method": "post",
            "headers": {
                "Content-Type": "application/json",
            },
            "data": '{\"url\":\"'+getUrl()+'\"}',
            "dataType": "json",
            "success": function (data) {
                setHomepage(data.userInfo, data.templates);
            },
            "fail": function () {
                alert("服务器繁忙，请稍后再试");
                return false;
            },
        });
    };

    //页面数据置入
    function setHomepage(userInfo, templates) {
        setBasicInfo(userInfo);
        setTemplates(templates);
    }

    //基本信息置入
    function setBasicInfo(userInfo) {
        $("#name").text(userInfo.name);
        $("#title").text(userInfo.title);
        $("#department").text(userInfo.department);
        $("#nameEnglish").text(userInfo.nameEnglish);
        $("#gender").text(userInfo.gender)
        $("#birthday").text(dateLoad(userInfo.birth));
        $("#office").text(userInfo.office);
        $("#phone").text(userInfo.phone);
        $("#email").text(userInfo.email);
        sculpture_temp++;
        $("#sculpture").attr("src", "/showImage?email=" + userInfo.email + "&scuplture_temp="+sculpture_temp);
    }

    //大模块集合置入
    function setTemplates(templates) {
        var length = 0;
        if (templates != null && templates != '' && templates != 'undefined') {
            length = templates.length;
        }
        for (var i = 0; i < length; i++) {
            var templateObject = templates[i];
            var templateHtml = '<div class="template">';
            templateHtml += perviewHtml(templateObject);
            templateHtml += '</div>';
            $("#template").append(templateHtml);
        }
    }

    //预览显示HTML
    function perviewHtml(templateObject) {
        var templateDiv = '<div class="template_header"><label class="template_name">' + templateObject.name + '</label><button class="slideToggle slideDown" title="滑动"></button></div>\n' +
            '<div class="template_body"><label class="template_content">\n'
            + templateObject.content + '</label><br/><br/>\n' + modulesPreviewHtml(templateObject.modules) +
            '</div><br/>'
        return templateDiv;
    }

    //预览小模块集合HTML
    function modulesPreviewHtml(modules) {
        var modulesPreviewHtml = '<ul class="modules">';
        var length = 0;
        if (modules != null && modules != '' && modules != 'undefined') {
            length = modules.length;
        }
        for (var i = 0; i < length; i++) {
            modulesPreviewHtml += modulePreviewHtml(modules[i]);
        }
        modulesPreviewHtml += "</ul>";
        return modulesPreviewHtml;
    }

    //预览小模块HTML
    function modulePreviewHtml(moduleObject) {
        var modulePreviewHtml = '<li class="module">';
        if (moduleObject.isLink || moduleObject.isLink == 'true') {
            modulePreviewHtml += '<a href="' + moduleObject.name + '" class="module_content">' + moduleObject.content + '</a>';
        } else {
            modulePreviewHtml += '<label class="module_content">' + moduleObject.content + '</label>';
        }
        modulePreviewHtml += '</li>'
        return modulePreviewHtml;
    }
});