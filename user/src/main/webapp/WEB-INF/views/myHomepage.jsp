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
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/util.js"></script>
    <script type="text/javascript" src="/js/myHomePage.js"></script>
</head>
<body>
<div id="header"><a href="javascript:;">English</a></div>
<div id="body">
    <div id="content">
        <div id="info">
            <div id="mainInfo">
                <div id="mainInfo_left"><img src="" id="sculpture"></div>
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
    </div>
</div>
<div id="footer"></div>
</body>
</html>
