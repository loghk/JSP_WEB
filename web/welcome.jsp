<%@ page import="java.io.OutputStream" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 1138538400
  Date: 2019/3/24
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<%
    if (session.getAttribute("logined")==null){
        response.sendRedirect("index.html");
        return;
    }
    String name = (String) application.getAttribute("uname");
%>

    <center>
        <h1>Welcome,<%=name%></h1>
        <h1><%=session.getAttribute("logined")%></h1>
        <br>
        <a href="/logout">注销</a>
    </center>
</body>
</html>
