<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>购物车列表</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
  <%--<script>--%>
	  <%--window.onload = function () {--%>
		  <%--var topInp = document.getElementById("theadInp");--%>
		  <%--var tbody = document.getElementById("tbody");--%>
		  <%--var botInpArr = tbody.getElementsByTagName("input");--%>
		  <%--//绑定事件--%>
		  <%--topInp.onclick = function () {--%>
			  <%--//优化版（被点击的input按钮的checked属性值，应该直接作为下面的所有的input按钮的checked属性值）--%>
			  <%--for (var i = 0; i < botInpArr.length; i++) {--%>
				  <%--botInpArr[i].checked = this.checked;--%>
			  <%--}--%>
		  <%--};--%>

		  <%--for (var i = 0; i < botInpArr.length; i++) {--%>
			  <%--botInpArr[i].onclick = function () {--%>
				  <%--//定义一个标识是true还是false的变量--%>
				  <%--//默认它为true--%>
				  <%--var bool = true;--%>
				  <%--//检测每一个input的checked属性值。--%>
				  <%--for (var j = 0; j < botInpArr.length; j++) {--%>
					  <%--if (botInpArr[j].checked === false) {--%>
						  <%--bool = false;--%>
					  <%--}--%>
				  <%--}--%>
				  <%--topInp.checked = bool;--%>
			  <%--}--%>
		  <%--}--%>
	  <%--}--%>
  <%--</script>--%>


<h1>购物车</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			<a href="<c:url value='/CartServlet?method=clear&'/>">清空购物车</a>
		</td>
	</tr>
	<tr>
		<th>图片</th>
		<th>书名</th>
		<th>作者</th>
		<th>单价</th>
		<th>数量</th>
		<th>小计</th>
		<th>操作</th>
	</tr>

	<%
		request.getSession().getAttribute("username");
		int allPrice = 0;
	%>

	<c:set var="username" value="${username}"/>
	<c:set var="sum" value="0"/>
	<c:forEach var="cartMap" items="${sessionScope[username].cartMap}">
		<tr>
			<td><div><img src="<c:url value='/${cartMap.value.book.image}'/>"/></div></td>
			<td>${cartMap.value.book.bname}</td>
			<td>${cartMap.value.book.author}</td>
			<td>${cartMap.value.book.price}元</td>
			<td>${cartMap.value.count}</td>
			<td>${cartMap.value.book.price * cartMap.value.count}元</td>
			<td><a href="<c:url value='/CartServlet?method=delete&bid=${cartMap.value.book.bid}'/>">删除</a></td>
			<c:set value="${sum + cartMap.value.book.price * cartMap.value.count}" var="sum"/>

		</tr>
	</c:forEach>


	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			合计：${sum}元
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			<a id="buy" href="<c:url value='/OrderServlet?method=add'/>"></a>
		</td>
	</tr>
</table>
  </body>

</html>
