<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单列表</title>

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
            border: solid 2px gray;
            width: 75px;
            height: 75px;
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
<h1>我的订单</h1>


<table border="1" width="100%" cellspacing="0" background="black">
    <%--<c:set var="sum" value="0"/>--%>

    <c:forEach var="order" items="${orders}">
        <tr bgcolor="gray" bordercolor="gray">
            <td colspan="6">
                订单编号：${order.oid}　成交时间：${order.ordertime}　金额：<font color="red"><b>${order.total}元</b></font>

                <c:choose>
                    <c:when test="${order.state == 1}">
                        <a href="<c:url value='/OrderServlet?method=giveMoney&oid=${order.oid}'/>">付款</a>
                    </c:when>
                    <c:when test="${order.state == 2}">
                        等待发货
                    </c:when>
                    <c:when test="${order.state == 3}">
                        <a href="<c:url value='/OrderServlet?method=end&oid=${order.oid}'/>">确认收货</a>
                    </c:when>
                    <c:otherwise>
                        订单结束
                    </c:otherwise>
                </c:choose>


            </td>
        </tr>
        <c:set var="smallsum" value="0"/>
        <c:forEach var="bookShow" items="${bookShow}">


            <c:if test="${bookShow.oid  eq  order.oid}">
                <tr bordercolor="gray" align="center">
                    <td width="15%">
                        <div><img src="<c:url value='/${bookShow.image}'/>" height="75"/></div>
                    </td>
                    <td>书名：${bookShow.bname}</td>
                    <td>单价：${bookShow.price}元</td>
                    <td>作者：${bookShow.author}</td>
                    <td>数量：${bookShow.count}</td>
                    <td>小计：${bookShow.count * bookShow.price}元</td>
                    <c:set value="${smallsum + bookShow.count * bookShow.price}" var="smallsum"/>
                </tr>
            </c:if>


        </c:forEach>

        <%--<c:set value="${sum + smallsum}" var="sum"/>--%>
    </c:forEach>


    <%--<tr>--%>
        <%--<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">--%>
            <%--总计：${sum}元--%>
        <%--</td>--%>
    <%--</tr>--%>

</table>


<%--<table border="1" width="100%" cellspacing="0" background="black">--%>

<%--<tr bgcolor="gray" bordercolor="gray">--%>
<%--<td colspan="6">--%>
<%--订单编号：abcdefg　成交时间：2000-01-01 15:30　金额：<font color="red"><b>319.2</b></font>　--%>

<%--<a href="<c:url value='/jsps/order/desc.jsp'/>">付款</a>--%>

<%--等待发货--%>
<%--<a href="javascript:alert('已确认收货！');">确认收货</a>--%>
<%--订单结束--%>
<%--</td>--%>
<%--</tr>--%>

<%--<tr bordercolor="gray" align="center">--%>
<%--<td width="15%">--%>
<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
<%--</td>--%>
<%--<td>书名：Java详解</td>--%>
<%--<td>单价：39.9元</td>--%>
<%--<td>作者：张孝祥</td>--%>
<%--<td>数量：2</td>--%>
<%--<td>小计：79.8元</td>--%>
<%--</tr>--%>

<%--</table>--%>
</body>
</html>
