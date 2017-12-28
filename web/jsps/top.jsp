<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>top</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        body {
            background: #4682B4;
        }

        a {
            text-transform: none;
            text-decoration: none;
            color: blue;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body style="background-image: url(/under_img/b2119313b07eca808de17b56982397dda044839f.jpg)">
<h1 style="text-align: center; color: blue; font-size: 30px">
    <a href="/index.jsp" target="main">动漫之家</a>
</h1>
<div style="font-size: 10pt; ">
    <h2 style="display: inline">${username}</h2>&nbsp;&nbsp;&nbsp;&nbsp;


    <c:choose>
        <c:when test="${empty username}">
            <a style="font-size: 20px;" href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a> |&nbsp;
            <a style="font-size: 20px;" href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a>
        </c:when>
        <c:otherwise>
            <a style="font-size: 20px;" href="<c:url value='/jsps/cart/list.jsp'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a style="font-size: 20px;" href="<c:url value='/OrderServlet?method=myOrder'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a style="font-size: 20px;" href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>
        </c:otherwise>
    </c:choose><br>


    <%--<a href="/index.jsp" target="main" style="padding-left: 910px; font-size: 16px">返回首页</a>--%>
    <form action="<c:url value='/BookServlet'/>" target="body">
        <input style="float: right; margin-top: 28px; margin-left: 5px" type="submit" value="提交"/>
        <input style="float: right; margin-top: 25px" type="text" name="bname"/>
        <input type="hidden" name="method" value="advanced"/>
    </form>
</div>
</body>
</html>
