<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

  <%
	  request.getSession().setAttribute("msg", "");
  %>

  <body style="background-image:url('/under_img/regist.jpg'); background-size:cover">
  <div style="padding-top: 70px; padding-left: 30px">
  <h1>注册</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value="/UserServlet"/>" method="post">
	<input type="hidden" name="method" value="add"/>
	用户名：<input type="text" name="username" value=""/><br/><br/>
	密　码：<input type="password" name="password"/><br/><br/>
	邮　箱：<input type="text" name="email" value=""/><br/><br/>
	验证码：<input style="width: 50px" type="text" name="code" id="code">
	<img src="/VerifyServlet" onclick="javascript:this.src = '/VerifyServlet?tm='+Math.random()" id="codesuper">
	<span id="sc" style="color: red"></span><br><br>
	<input type="submit" value="注册"/>
</form>
  </div>

  <a href="../../小游戏.jsp">不如打把游戏??</a>

  </body>
</html>
