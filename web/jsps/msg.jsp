<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'msg.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>

<body>
<tr>
    <td valign="middle"></td>
</tr>


<div style="margin-top: 220px">
<center>
<h1>${msg }</h1>
<h2 id="time"></h2>
    <%--<img src="under_img/213.jpeg" alt="跪下" width="200px" height="200px" >--%>
</center>
</div>
<%
    response.setHeader("Refresh", "6;http://localhost:8080");
%>
</body>
<script>

    var i = 6;

    function showMun() {

        i = i - 1;

        document.getElementById("time").innerHTML = "还有" + i + "秒跳转页面";

    }

    setInterval(showMun, 1000);

</script>
</html>
