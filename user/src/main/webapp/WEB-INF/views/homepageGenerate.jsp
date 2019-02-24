<%--
  Created by IntelliJ IDEA.
  User: 程鹏
  Date: 2019/1/11
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页编辑</title>
    <link rel="stylesheet" href="/css/homepageGenerate.css">
    <link rel="stylesheet" href="/css/jquery.Jcrop.css">
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="/js/util.js"></script>
    <script type="text/javascript" src="/js/homepageGenerate.js"></script>
</head>
<body>
    <div id="header"><a href="javascript:;">English</a></div>
    <div id="body">
        <div id="content">
            <div id="info">
                <div id="mainInfo">
                    <div id="mainInfo_left"><a href="javascript:;" title="点击上传图片"><img src="" id="sculpture"></a></div>
                    <div id="mainInfo_right">
                        <div id="mainInfo_right_top">
                            <div id="name" class="basicInfo"></div>
                            <div id="title" class="basicInfo"></div>
                            <div id="department" class="basicInfo"></div>
                        </div>
                        <div id="mainInfo_right_content">
                            <span class="otherInfo">英文名称：<label id="nameEnglish"></label></span>
                            <span class="otherInfo">性&emsp;&emsp;别：<label id="gender"></label></span><br/>
                            <span class="otherInfo">出生日期：<label id="birthday"></label></span>
                            <span class="otherInfo">办公地点：<label id="office"></label></span><br/>
                            <span class="otherInfo">联系电话：<label id="phone"></label></span>
                            <span class="otherInfo">电子邮箱：<label id="email"></label></span>
                        </div>
                    </div>
                </div>
                <div id="template">

                </div>
            </div>
            <div id="action">
                <div id="templateAction"><button id="addTemplate">添加模块</button></div>
            </div>
        </div>
    </div>
    <div id="footer"></div>
    <div id="lid">
        <div id="operationPanel">
            <div id="cutImage">
                <div id="cutImage_top">
                    <label>上传照片</label><a id="closeAction" href="javascript:;"><img src="/images/close.png" title="关闭"/></a>
                </div>
                <div id="cutImage_left">
                    <input type="button" id="selectSculpture" value="选择图片">
                    <label id="sculptureTips">只支持JPG、JPEG、PNG，大小不超过10M</label>
                </div>
                <div id="cutImage_right">
                    <div id="preview_box" class="previewImg">
                        <img id="previewImg" src=""/>
                    </div>
                </div>
                <div id="cutImage_bottom">
                    <input type="hidden" id="x" name="x" />
                    <input type="hidden" id="y" name="y" />
                    <input type="hidden" id="w" name="w" />
                    <input type="hidden" id="h" name="h" />
                    <input type="file" id="changeSculpture">
                    <button id="uploadSculpture">确认上传</button>
                    <button id="cancelUploadSculpture">取消上传</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
