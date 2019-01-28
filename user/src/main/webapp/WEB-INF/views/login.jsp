<%--
  Created by IntelliJ IDEA.
  User: 程鹏
  Date: 2018/12/22
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" href="/css/login.css" type="text/css">
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/md5.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>
</head>
<body>
<div id="frame">
    <div id="logo">HomePage</div>
    <div id="form">
        <div class="info"><input type="text" placeholder="请输入登录邮箱" id="email" autofocus/>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><input type="password" placeholder="请输入密码" id="pwd" maxlength="16"/>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div>
            <a href="/user/register" id="register">注册账号</a>
        </div>
        <button id="loginAction">确认登录</button>
        <div class="errorMessage"><img src="/images/error.png"><label></label>
        </div>
    </div>
</div>
</body>
</html>
