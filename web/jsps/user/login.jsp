<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>登录</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="../../menu/jquery-3.2.1.js"></script>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        #div1 {

            color: #ffffff;
            height: 300px;
            width: 400px;
            background-color: rgba(68, 63, 63, 0.52);
            position: fixed;
            top: 230px;
            left: 480px;
        }

        input {
            width: 300px;
            height: 30px;
            padding-left: 0;
            color: #1c1c1c;
            border: none;
            font-size: 16px;
            font-weight: bold;
            line-height: 30px;
        }
        .inp {
            border: 1px solid #cccccc;
            border-radius: 2px;
            padding: 0 10px;
            width: 278px;
            height: 40px;
            font-size: 18px;
        }
        .btn {
            border: 1px solid #cccccc;
            border-radius: 2px;
            width: 100px;
            height: 40px;
            font-size: 16px;
            color: #666;
            cursor: pointer;
            background: white linear-gradient(180deg, #ffffff 0%, #f3f3f3 100%);
        }
        .btn:hover {
            background: white linear-gradient(0deg, #ffffff 0%, #f3f3f3 100%)
        }
        #captcha1,
        #captcha2 {
            width: 300px;
            display: inline-block;
        }
        .show {
            display: block;
        }
        .hide {
            display: none;
        }
        #notice1,
        #notice2 {
            color: red;
        }
        label {
            vertical-align: top;
            display: inline-block;
            width: 80px;
            text-align: right;
        }
        #wait1, #wait2 {
            text-align: left;
            color: #666;
            margin: 0;
        }
    </style>

</head>

<%
    request.getSession().setAttribute("msg", "");
%>

<body style="background-size: cover; background-image: url(/under_img/Bg_15.png)">

<div style="margin-top: 250px"></div>

<%--<div id="div1"></div>--%>

<h1 style="margin-left: 580px">登录</h1>
<p style="color: red; font-weight: 900; margin-left: 580px">${msg}</p>

<form action="<c:url value="/UserServlet"/>" method="post" style="margin-left: 500px">
    <input type="hidden" name="method" value="login"/><br/>
    用户名：　　<input type="text" name="username"  placeholder="请输入用户名"
               onfocus="if(placeholder=='请输入用户名') {value=''}" onblur="if (value=='')
        {value='请输入用户名'}" maxlength="30"><br/><br/>
    密　码：　　<input type="password" name="password" placeholder="请输入密码"  maxlength="15"/><br/><br/>
    <br>


    <div>
        <label>完成验证：</label>
        <div id="captcha1">
            <p id="wait1" class="show">正在加载验证码......</p>
        </div>
    </div>

    <br><br>

    <%--<input style="margin-left: 50px" type="submit" value="登录"/>--%>

    <p id="notice1" class="hide">请先完成验证</p>
    <input style="margin-left: 50px" class="btn" id="submit1" type="submit" value="提交">

</form>
</body>

<script src="gt.js"></script>

<script>
    var handler1 = function (captchaObj) {
        $("#submit1").click(function (e) {
            var result = captchaObj.getValidate();
            if (!result) {
                $("#notice1").show();
                setTimeout(function () {
                    $("#notice1").hide();
                }, 2000);
                e.preventDefault();
            }
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
        captchaObj.appendTo("#captcha1");
        captchaObj.onReady(function () {
            $("#wait1").hide();
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    $.ajax({
        url: "/startCaptcha?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            // 调用 initGeetest 初始化参数
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
                offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                product: "float", // 产品形式，包括：float，popup
                width: "100%"
                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
            }, handler1);
        }
    });
</script>

</html>
