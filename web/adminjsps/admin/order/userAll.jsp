<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/9/26
  Time: 下午4:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
    <style>
        body{
            margin: 0 auto;
        }
    </style>
</head>
<body>
<h1>用户信息</h1>


<table border="1" width="100%" cellspacing="0"">

                <tr bordercolor="gray" align="center">
                    <td>用户名：${user.username}</td><br>
                    <td>用户邮箱：${user.email}</td><br>
                    <td>用户地址：${address}</td>
                </tr>

</table>
</body>
</html>
