<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理员登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background-size: cover; background-image: url(/under_img/Bg_03.png)">

  <div style="margin-top: 250px"></div>

  <%--<div style="background-color: #6bff96; width: 500px; height: 500px">--%>

<h1 style="margin-left: 500px">管理员登录页面</h1>
  <p style="font-weight: 900; color: red">${msg }</p>
<form action="<c:url value='/AdminServlet?method=login'/>" method="post"  style="margin-left: 500px">
	管理员账户：<input type="text" name="username"  placeholder="请输入管理员账户\(0w0)/"
				 onfocus="if(placeholder=='请输入用户名') {value=''}" onblur="if (value=='')
        {value='请输入用户名'}" maxlength="30"><br/><br/>
	密　　　码：<input type="password" name="password" placeholder="<--你的密码"  maxlength="15"/><br/>
	<input type="submit" value="进入后台"/>
</form>

  <%--</div>--%>
  </body>
</html>

<%--<form action="<c:url value="/UserServlet"/>" method="post" style="margin-left: 500px">--%>
	<%--<input type="hidden" name="method" value="login"/><br/>--%>
	<%--用户名：<input type="text" name="username"  placeholder="请输入用户名"--%>
			   <%--onfocus="if(placeholder=='请输入用户名') {value=''}" onblur="if (value=='')--%>
        <%--{value='请输入用户名'}" maxlength="30"><br/><br/>--%>
	<%--密　码：<input type="password" name="password" placeholder="请输入密码"  maxlength="15"/><br/><br/>--%>
	<%--<input style="margin-left: 90px" type="submit" value="登录"/>--%>
<%--</form>--%>
