<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'bookdesc.jsp' starting page</title>

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
            font-size: 10pt;
            background: rgb(254, 238, 189);
        }

        div {
            margin: 20px;
            border: solid 2px gray;
            width: 150px;
            height: 150px;
            text-align: center;
        }

        li {
            margin: 10px;
        }
    </style>
</head>

<body>
<div>
    <img src="<c:url value='${chooseBook.image}'/>" border="0"/>
</div>
<form style="margin:20px;" id="form" action="<c:url value='/AdminServlet?bid=${chooseBook.bid}'/>" method="post">
    图书名称：<input type="text" name="bname" value="${chooseBook.bname}"/><br/>
    图书单价：<input type="text" name="price" value="${chooseBook.price}元"/><br/>
    图书作者：<input type="text" name="author" value="${chooseBook.author}"/><br/>
    图书分类：<select name="cid" style="width: 150px; height: 20px;">
    <c:forEach var="categoryAll" items="${adminCategoryAll}">
        <option value="${categoryAll.cid}">${categoryAll.cname}</option>
    </c:forEach>
</select><br/>

    <input type="submit" name="method" value="delete" onclick="return confirm('是否真要删除该图书？');"/>
    <input type="submit" name="method" value="mod"/>
</form>

<%--<div>--%>
<%--<img src="<c:url value='${chooseBook.image}'/>" border="0"/>--%>
<%--</div>--%>
<%--<ul>--%>
<%--<li>书名：${chooseBook.bname}</li>--%>
<%--<li>作者：${chooseBook.author}</li>--%>
<%--<li>单价：${chooseBook.price}元</li>--%>
<%--<form id="form" action="<c:url value='/CartServlet?method=add&bid=${chooseBook.bid}'/>" method="post">--%>
<%--<input type="text" size="3" name="count" value="1"/>--%>
<%--</form>--%>
<%--<a href="javascript:document.getElementById('form').submit();"></a>--%>
<%--</ul>--%>


</body>
</html>
