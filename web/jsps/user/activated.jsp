<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/9/21
  Time: 上午11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>activated</title>
</head>
<body>
<%
    String code = request.getParameter("code");
    request.setAttribute("code", code);
    request.getRequestDispatcher("/UserServlet?method=activated").forward(request,response);
%>
</body>
</html>
