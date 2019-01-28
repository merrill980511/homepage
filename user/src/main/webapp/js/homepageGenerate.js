$(function () {
    /*
     module template html代码变量
     */
    var module = '<tr class="module">\n' +
        '                                        <td><label class="module_index">1</label></td>\n' +
        '                                        <td><input type="text" placeholder="请输入列表项内容" class="module_content"></td>\n' +
        '                                        <td>\n' +
        '                                            <input type="checkbox" class="module_isHref switch">\n' +
        '                                        </td>\n' +
        '                                        <td><input type="text" placeholder="请输入超链接" class="module_name" disabled="disabled"></td>\n' +
        '                                    <td><a href="javascript:;" class="deleteModule"><img src="/images/delete.png" title="删除"></a></td>\n' +
        '</tr>';
    var template = '<div class="template">\n' +
        '                        <div class="template_header"><input class="template_name" placeholder="请输入模块名称"><input type="hidden" class="template_id" value="-1"><button class="cancel actionButton" title="取消修改"></button><button class="delete actionButton" title="删除"></button><button class="save actionButton" title="保存"></button></div>\n' +
        '                        <div class="template_body">\n' +
        '                            <textarea type="text" placeholder="请输入内容" class="template_content" rows="5"></textarea>\n' +
        '                            <div class="list">\n' +
        '                                <table class="listTable">\n' +
        '                                    <tr class="listTitile">\n' +
        '                                        <th>序列</th>\n' +
        '                                        <th>列表项文本</th>\n' +
        '                                        <th>\n' +
        '                                            开启超链接\n' +
        '                                        </th>\n' +
        '                                        <th>超链接</th>\n' +
        '                                        <th>操作</th>\n' +
        '                                    </tr>\n' +
        '                                </table>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                        <div class="template_footer">\n' +
        '                            <a class="addModule" href="javascript:;">+ 添加列表项</a>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>';
    var sculpture_temp = 0;
    //页面显示
    showHomepage();
    //点击头像
    $("#sculpture").on("click", function () {
        cutImageInit();
        $("#lid").show();
    });
    //选择头像
    $("#cutImage").on("click", "#selectSculpture" ,function () {
        $("#changeSculpture").click();
    });
    //选择好头像
    $("#cutImage").on("change", "#changeSculpture", function () {
        changeSculpture();
    });
    //点击编辑按钮
    $("#template").on("click", ".edit", function () {
        intoTemplateEdit($(this).parents(".template"));
        setIndex();
    });
    //点击删除按钮
    $("#template").on("click", ".delete", function () {
        $(this).parent().parent().remove();
    });
    //点击滑动按钮
    $("#template").on("click", ".slideToggle", function () {
        if ($(this).hasClass("slideDown")) {
            $(this).removeClass("slideDown").addClass("slideUp");
        } else {
            $(this).removeClass("slideUp").addClass("slideDown");
        }
        $(this).parent().next().slideToggle();
    });
    //点击保存按钮
    $("#template").on("click", ".save", function () {
        templateSave($(this).parents('.template'));
    });
    //点击删除按钮
    $("#template").on("click", ".delete", function () {
        templateDelete($(this).parents('.template'));
    });
    //点击取消修改
    $("#template").on("click", ".cancel", function () {
        var templateDiv = $(this).parents(".template");
        var id = templateDiv.find(".template_id").val();
        intoTemplatePreviewAfterUpdate(templateDiv, templateGetCommit(id));
    });
    //点击超链接功能开启
    $("#template").on("click", ".module_isHref", function () {
        if ($(this).prop('checked')) {
            $(this).parent().next().children('.module_name').removeAttr("disabled");
        } else {
            $(this).parent().next().children('.module_name').val('');
            $(this).parent().next().children('.module_name').attr("disabled", "disabled");
        }
    });
    //点击添加列表项
    $("#template").on("click", ".addModule", function () {
        $(this).parent().prev().children('.list').children('.listTable').children().append(module);
        setIndex();
    });
    //点击删除列表项目
    $("#template").on("click", ".deleteModule", function () {
        $(this).parent().parent().remove();
        setIndex();
    });
    //点击添加模块
    $("#addTemplate").on("click", function () {
        $("#template").append(template);
    });
    //更换头像框关闭
    $("#cutImage").on("click","#closeAction",function () {
        cutImageInit();
        $("#lid").hide();
    });
    //确认上传
    $("#cutImage").on("click","#uploadSculpture",function () {
        uploadSculpture();
    });
    //取消上传
    $("#cutImage").on("click","#cancelUploadSculpture",function () {
        cutImageInit();
        $("#lid").hide();
    });

    //模块排序
    function setIndex() {
        $(".module_index").each(function () {
            $(this).text($(this).parents('.module').index());
        });
    };

    //模块集合获取（页面加载）
    function showHomepage() {
        $.ajax({
            "url": "/user/homepage/showHomepage",
            "method": "post",
            "headers": {
                "Content-Type": "application/json",
            },
            "data": null,
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
        cutImageInit();
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

    //模块获取（暂废）
    function templateGet(templateDiv) {
        var id = templateDiv.find(".template_id").val();
        templateGetCommit(id);
    }

    //保存大模块
    function templateSave(templateDiv) {
        templateSaveCommit(templateDiv);
    }

    //删除大模块
    function templateDelete(templateDiv) {
        templateDeleteCommit(templateDiv);
    }

    //小模块获取
    function templateGetCommit(id) {
        var templateGet = null;
        var url = "/user/template/getTemplate";
        $.ajax({
            "url": url,
            "async": false,
            "method": "post",
            "headers": {
                "Content-Type": "application/json",
            },
            "data": "{\"id\":" + id + "}",
            "dataType": "json",
            "success": function (data) {
                templateGet = data;
            },
            "fail": function () {
                alert("服务器繁忙，请稍后再试");
            },
        });
        return templateGet;
    }

    //模块保存
    function templateSaveCommit(templateDiv) {
        var templateObject = getEditTemplateObject(templateDiv);
        if (templateObject.id == "-1") {
            templateAddCommit(templateDiv);
        } else {
            templateUpdateCommit(templateDiv);
        }

    };

    //模块更新
    function templateUpdateCommit(templateDiv) {
        var id = templateDiv.find(".template_id").val();
        var templateObject = getEditTemplateObject(templateDiv);
        $.ajax({
            "url": "/user/template/updateTemplate",
            "method": "post",
            "headers": {
                "Content-Type": "application/json",
            },
            "data": JSON.stringify(templateObject),
            "dataType": "json",
            "success": function (data) {
                if (data.message == "true") {
                    intoTemplatePreviewAfterUpdate(templateDiv, templateGetCommit(id));
                    return true;
                } else {
                    alert(data.message);
                    return false;
                }
            },
            "fail": function () {
                alert("服务器繁忙，请稍后再试");
                return false;
            },
        });
    }

    //模块添加
    function templateAddCommit(templateDiv) {
        var templateObject = getEditTemplateObject(templateDiv);
        $.ajax({
            "url": "/user/template/addTemplate",
            "method": "post",
            "headers": {
                "Content-Type": "application/json",
            },
            "data": JSON.stringify(templateObject),
            "dataType": "json",
            "success": function (data) {
                if (data.message == "true") {
                    intoTemplatePreviewAfterUpdate(templateDiv, templateGetCommit(data.id));
                    return true;
                } else {
                    alert(data.message);
                    return false;
                }
            },
            "fail": function () {
                alert("服务器繁忙，请稍后再试");
                return false;
            },
        });
    }

    //模块删除
    function templateDeleteCommit(templateDiv) {
        var id = templateDiv.find(".template_id").val();
        if (id == "" || id == "undefined") {
            templateDiv.remove();
        } else {
            var url = "/user/template/deleteTemplate";
            var idObj = new Object();
            idObj.id = Number(id);
            $.ajax({
                "url": url,
                "method": "post",
                "headers": {
                    "Content-Type": "application/json",
                },
                "data": "{\"id\":\"" + id + "\"}",
                "dataType": "json",
                "success": function (data) {
                    if (data.message == "true") {
                        templateDiv.remove();
                        return true;
                    } else {
                        alert(data.message);
                        return false;
                    }
                },
                "fail": function () {
                    alert("服务器繁忙，请稍后再试");
                    return false;
                },
            });
        }
    };

    //进入预览显示
    function intoTemplatePreview(templateDiv) {
        var templateObject = getEditTemplateObject(templateDiv);
        templateDiv.html(perviewHtml(templateObject));
    }

    //进入预览显示
    function intoTemplatePreviewAfterUpdate(templateDiv, templateObject) {
        templateDiv.html(perviewHtml(templateObject));
    }

    //进入编辑显示
    function intoTemplateEdit(templateDiv) {
        var templateObject = getPreviewTemplateObject(templateDiv);
        templateDiv.html(editHtml(templateObject));
    }

    //进入编辑显示
    function intoTemplateEditAfterUpdate(templateDiv, templateObject) {
        templateDiv.html(editHtml(templateObject));
    }

    //预览显示HTML
    function perviewHtml(templateObject) {
        var templateDiv = '<div class="template_header"><label class="template_name">' + templateObject.name + '</label><input type="hidden" class="template_id" value="' + templateObject.id + '"><button class="slideToggle slideDown" title="滑动"></button><button class="edit" title="编辑"></button></div>\n' +
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
            modulePreviewHtml += '<a href="' + moduleObject.name + '" class="module_content">' + moduleObject.content + '</a><input type="hidden" class="module_isHref" value="' + moduleObject.isLink + '"><input type="hidden" class="module_name" value="' + moduleObject.name + '">';
        } else {
            modulePreviewHtml += '<label class="module_content">' + moduleObject.content + '</label><input type="hidden" class="module_isHref" value="' + moduleObject.isLink + '"><input type="hidden" class="module_name" value="' + moduleObject.name + '">';
        }
        modulePreviewHtml += '</li>'
        return modulePreviewHtml;
    }

    //编辑显示HTML
    function editHtml(templateObject) {
        var templateDiv = '<div class="template_header"><input class="template_name" placeholder="请输入模块名称" value="' + templateObject.name + '"><input type="hidden" class="template_id" value="' + templateObject.id + '"><button class="cancel" title="取消修改"></button><button class="delete" title="删除"></button><button class="save" title="保存"></button></div>\n' +
            '                        <div class="template_body">\n' +
            '                            <textarea type="text" placeholder="请输入内容" class="template_content" rows="5">' + templateObject.content + '</textarea>\n' +
            '                            <div class="list">\n' +
            '                                <table class="listTable">\n' +
            '                                    <tr class="listTitile">\n' +
            '                                        <th>序列</th>\n' +
            '                                        <th>列表项文本</th>\n' +
            '                                        <th>\n' +
            '                                            开启超链接\n' +
            '                                        </th>\n' +
            '                                        <th>超链接</th>\n' +
            '                                        <th>操作</th>\n' +
            '                                    </tr>\n'
            + modulesEditHtml(templateObject.modules) +
            '                                </table>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="template_footer">\n' +
            '                            <a class="addModule" href="javascript:;">+ 添加列表项</a>\n' +
            '                        </div>';
        return templateDiv;
    }

    //编辑小模块集合HTML
    function modulesEditHtml(modules) {
        var modulesEditHtml = '';
        var length = 0;
        if (modules != null && modules != '' && modules != 'undefined') {
            length = modules.length;
        }
        for (var i = 0; i < length; i++) {
            modulesEditHtml += moduleEditHtml(modules[i]);
        }
        return modulesEditHtml;
    }

    //编辑小模块HTML
    function moduleEditHtml(moduleObject) {
        var module_isHref_Checked = '';
        var module_Href_Abled = 'disabled="disabeled"';
        if (moduleObject.isLink == 'true') {
            module_isHref_Checked = 'checked="true"';
            module_Href_Abled = '';
        }
        var moduleEditHtml = '<tr class="module">\n' +
            '                                        <td><label class="module_index">' + moduleObject.location + '</label></td>\n' +
            '                                        <td><input type="text" placeholder="请输入列表项内容" class="module_content" value="' + moduleObject.name + '"></td>\n' +
            '                                        <td>\n' +
            '                                            <input type="checkbox" class="module_isHref switch" ' + module_isHref_Checked + '">\n' +
            '                                        </td>\n' +
            '                                        <td><input type="text" placeholder="请输入超链接" class="module_name" value="' + moduleObject.content + '" ' + module_Href_Abled + '"></td>\n' +
            '                                        <td><a href="javascript:;" class="deleteModule"><img src="/images/delete.png" title="删除"></a></td>\n' +
            '                                    </tr>';
        return moduleEditHtml;
    }

    //获取预览模块对象
    function getPreviewTemplateObject(templateDiv) {
        var templateObject = new Object();
        var id = templateDiv.find(".template_id").val();
        var location = templateDiv.index() + 1;
        var name = templateDiv.find(".template_name").text();
        var content = encodeFromLabel(templateDiv.find(".template_content").html());
        var module_Array = templateDiv.find(".module");
        var modulesArray = [];
        module_Array.each(function () {
            var module = new Object();
            module.name = $(this).find(".module_content").text();
            module.isLink = $(this).find(".module_isHref").val();
            module.content = $(this).find(".module_name").val();
            module.location = null;
            modulesArray.push(module);
        });
        templateObject.id = id;
        templateObject.name = name;
        templateObject.content = content;
        templateObject.location = location;
        templateObject.modules = modulesArray;
        return templateObject;
    }

    //获取编辑模块对象
    function getEditTemplateObject(templateDiv) {
        var templateObject = new Object();
        var id = templateDiv.find(".template_id").val();
        var location = templateDiv.index() + 1;
        var name = templateDiv.find(".template_name").val();
        var content = encodeFromTextArea(templateDiv.find(".template_content").val());
        var module_Array = templateDiv.find(".module");
        var modulesArray = [];
        module_Array.each(function () {
            var module = new Object();
            module.content = $(this).find(".module_content").val();
            module.isLink = $(this).find(".module_isHref").prop('checked');
            module.name = $(this).find(".module_name").val();
            module.location = $(this).find(".module_index").text();
            if (module.content.trim() != "") {
                modulesArray.push(module);
            }
        });
        templateObject.id = id;
        templateObject.name = name;
        templateObject.content = content;
        templateObject.location = location;
        templateObject.modules = modulesArray;
        return templateObject;
    }

    //文本转换 文本框》标签
    function encodeFromTextArea(str) {
        var reg = new RegExp("\n", "g");
        str = str.replace(reg, "<br/>");
        return str;
    }

    //文本转换 标签》文本框
    function encodeFromLabel(str) {
        var reg = new RegExp("<br/>", "g");
        str = str.replace(reg, "\n");
        reg = new RegExp("<br>", "g");
        str = str.replace(reg, "\n");
        return str;
    }

    //头像选择框格式化
    function cutImageInit(){
        $("#cutImage_left").html(' <input type="button" id="selectSculpture" value="选择图片">\n' +
            '                    <label id="sculptureTips">只支持JPG、JPEG、PNG，大小不超过10M</label>');
        $("#cutImage_right").html('<div id="preview_box" class="previewImg">\n' +
            '                        <img id="previewImg" src="/images/sculpture.jpg"/>\n' +
            '                    </div>');
        $("#previewImg").removeAttr("style")
        $("#previewImg").attr("src",$("#sculpture").attr("src"));
        var file = $("#changeSculpture")
        file.after(file.clone().val(""));
        file.remove();
    }
    //选择好头像
    function changeSculpture() {
        $("#cutImage_left").html('<div id="cutPanel">' +
            '<div class="bigImg">\n' +
            '        <img id="srcImg" src="/images/sculpture.jpg"/>\n' +
            ' </div>\n'+
            '</div>');
       loadSculpture();
    }
    //更换头像
    function uploadSculpture() {
        var files = $('#changeSculpture').prop('files');
        var data = new FormData();
        data.append('x',$("#x").val());
        data.append('y',$("#y").val());
        data.append('width',$("#w").val());
        data.append('height',$("#h").val());
        data.append('image', files[0]);
        $.ajax({
            type: 'POST',
            url: "/user/template/saveImage",
            data: data,
            cache: false,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.message == "true") {
                    sculpture_temp++;
                    $("#sculpture").attr("src", "/showImage?email=" + $("#email").text() +"&sculpture_temp="+sculpture_temp);
                    cutImageInit();
                    $("#lid").hide();
                    return true;
                } else {
                    alert(data.message);
                    return false;
                }
            },
            fail: function () {
                alert("服务器繁忙，请稍后重试！");
                return false;
            },
        });
    }
    //裁剪图像
    function cutImage(srcImg,realHeight,nowHeight){
        srcImg.Jcrop( {
            aspectRatio : 200/280,
            onChange : showCoords,
            onSelect : showCoords,
            minSize :[50,70],
        });
        //简单的事件处理程序，响应自onChange,onSelect事件，按照上面的Jcrop调用
        function showCoords(obj) {
            $("#x").val(obj.x*realHeight/nowHeight);
            $("#y").val(obj.y*realHeight/nowHeight);
            $("#w").val(obj.w*realHeight/nowHeight);
            $("#h").val(obj.h*realHeight/nowHeight);
            if (parseInt(obj.w) > 0) {
                //计算预览区域图片缩放的比例，通过计算显示区域的宽度(与高度)与剪裁的宽度(与高度)之比得到
                var rx = $("#preview_box").width() / obj.w;
                var ry = $("#preview_box").height() / obj.h;
                //通过比例值控制图片的样式与显示
                $("#previewImg").css( {
                    width : Math.round(rx * $("#srcImg").width()) + "px", //预览图片宽度为计算比例值与原图片宽度的乘积
                    height : Math.round(rx * $("#srcImg").height()) + "px", //预览图片高度为计算比例值与原图片高度的乘积
                    marginLeft : "-" + Math.round(rx * obj.x) + "px",
                    marginTop : "-" + Math.round(ry * obj.y) + "px"
                });
            }
        }
    }
    function loadSculpture(){
        var files = $('#changeSculpture').prop('files');
        var srcImg = $("#srcImg");
        $("#srcImg").attr("src",URL.createObjectURL(files[0]));
        $("#previewImg").attr("src",URL.createObjectURL(files[0]));
        var imgSrc = $("#srcImg").attr("src");
        getImageWidth(imgSrc,function(w,h){
            setSculptrueSize(srcImg,w,h);
        });
    }
    function setSculptrueSize(srcImg,w,h) {
        if(w>=h){
            srcImg.attr("width",srcImg.parents("#cutPanel").width());
            srcImg.attr("height","auto");
            srcImg.parents(".bigImg").css("width",srcImg.width());
            srcImg.parents(".bigImg").css("height",srcImg.height());
            srcImg.parents(".bigImg").css("margin","auto");
        }else{
            srcImg.attr("height",srcImg.parents("#cutPanel").height());
            srcImg.attr("width","auto");
            srcImg.parents(".bigImg").css("height",srcImg.height());
            srcImg.parents(".bigImg").css("width",srcImg.width());
            srcImg.parents(".bigImg").css("margin","auto");
        }
        cutImage(srcImg,h,srcImg.height());
    }
});