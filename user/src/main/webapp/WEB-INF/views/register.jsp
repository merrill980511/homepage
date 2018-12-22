<%--
  Created by IntelliJ IDEA.
  User: 梅峰鑫
  Date: 2018/12/22
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="/css/register.css" type="text/css">
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/register.js"></script>
</head>
<body>
<div id="frame">
    <div id="logo">欢迎注册HomePage</div>
    <div id="form">
        <div class="info"><input type="text" placeholder="请输入邮箱" id="email"/>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><input type="password" placeholder="请输入密码" id="pwd" maxlength="16"/>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><input type="password" placeholder="请再次输入你的密码" id="re_pwd"/>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <div class="info"><input type="text" placeholder="请输入验证码" id="code" maxlength="8"/><button id="getCode">获取验证码</button>
            <div class="errorMessage"><img src="/images/error.png"><label></label></div>
        </div>
        <button id="registerAction">确认注册</button>
        <div class="errorMessage"><img src="/images/error.png"><label></label>
        </div>
    </div>
</div>
</body>
</html>
