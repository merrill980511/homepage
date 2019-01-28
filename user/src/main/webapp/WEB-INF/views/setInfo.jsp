<%--
  Created by IntelliJ IDEA.
  User: 程鹏
  Date: 2018/12/23
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息填写</title>
    <link rel="stylesheet" href="/css/setInfo.css" type="text/css">
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/setInfo.js"></script>
</head>
<body>
<div id="frame">
    <div id="logo">请填写个人信息</div>
    <div id="form">
        <div class="info"><label><span>*</span>姓&emsp;&emsp;名：</label><input class="infoInput" type="text" placeholder="请输入姓名" id="name" maxlength="20"/>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><label><span>&nbsp;</span>英文姓名：</label><input class="infoInput" type="text" placeholder="请输入英文姓名" id="nameEnglish" maxlength="20"/>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><label><span>*</span>性&emsp;&emsp;别：</label><select class="infoInput" id="gender"><option value="男">男</option><option value="女">女</option></select>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><label><span>*</span>生&emsp;&emsp;日：</label><input class="infoInput" type="text" placeholder="请选择您的生日"  id="birthday"/>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><label><span>*</span>联系方式：</label><input class="infoInput" type="text" placeholder="请输入手机号" id="phone" maxlength="11"/></button>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><label><span>*</span>学&emsp;&emsp;院：</label><input class="infoInput" type="text" placeholder="请输入学院" id="department" maxlength="30"/></button>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><label><span>*</span>职&emsp;&emsp;称：</label><input class="infoInput" type="text" placeholder="请输入职称" id="title" maxlength="30"/></button>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><label><span>*</span>办公地点：</label><input class="infoInput" type="text" placeholder="请输入办公地点" id="office" maxlength="100"/></button>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <button id="setInfoAction">确认</button>
        <div class="errorMessage"><img src="/images/error.png"><label></label></div>
    </div>
</div>
</body>
</html>
