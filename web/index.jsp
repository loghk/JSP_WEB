<%--
  Created by IntelliJ IDEA.
  User: 1138538400
  Date: 2019/3/22
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<center>
    <h1>登陆</h1>
    <form action="/user_login" method="post">
        ID &nbsp;&nbsp;&nbsp;<input type="text" name="id"/>
        <br>
        <br>
        密码 <input type="password" name="upwd"/>
        <br>
        <br>
        <input name="answer"/>
        <img src="/images/captcha"/>
        <br>
        <br>
        <input type="submit" value="登陆"/>
    </form>
    <br>
    <a href="regist.html">注册</a>
</center>
</body>
</html>
