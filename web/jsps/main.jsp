<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>main</title>
	  <embed src="/under_img/11111.mp3" hidden="true" autostart="true" loop="true"/>
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
		*{
			font-size:10pt;
		}
		body{
			text-align:center;
			background-image: url(/under_img/main.jpg);
			background-size: cover;
		}
		.table{
			width:1024px;
			height:100%;
			border:1px solid gray;/*固定边框,1像素*/
		    border-collapse: collapse;/*单线的列表边框*/
		}
		.table td{
			border:1px solid gray;/*固定边框,1像素*/
		}
		iframe {
			width: 100%;
			height: 100%;
		}
	</style>

	  <style type="text/css">
		  button {
			  cursor: pointer;
			  width: 200px;
			  height: 30px;
			  margin-top: 25px;
			  padding: 0;
			  background: #ef4300;
			  -moz-border-radius: 6px;
			  -webkit-border-radius: 6px;
			  border-radius: 6px;
			  border: 1px solid #ff730e;
			  -moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset,
			  0 2px 7px 0 rgba(0, 0, 0, .2);
			  -webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset,
			  0 2px 7px 0 rgba(0, 0, 0, .2);
			  box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset,
			  0 2px 7px 0 rgba(0, 0, 0, .2);
			  font-family: 'PT Sans', Helvetica, Arial, sans-serif;
			  font-size: 14px;
			  font-weight: 700;
			  color: #fff;
			  text-shadow: 0 1px 2px rgba(0, 0, 0, .1);
			  -o-transition: all .2s;
			  -moz-transition: all .2s;
			  -webkit-transition: all .2s;
			  -ms-transition: all .2s;
		  }
		  button:hover {
			  -moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
			  0 2px 7px 0 rgba(0, 0, 0, .2);
			  -webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
			  0 2px 7px 0 rgba(0, 0, 0, .2);
			  box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
			  0 2px 7px 0 rgba(0, 0, 0, .2);
		  }
		  a:hover {
			  text-decoration: none;
		  }
		  button:active {
			  -moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
			  0 2px 7px 0 rgba(0, 0, 0, .2);
			  -webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
			  0 2px 7px 0 rgba(0, 0, 0, .2);
			  box-shadow: 0 5px 8px 0 rgba(0, 0, 0, .1) inset,
			  0 1px 4px 0 rgba(0, 0, 0, .1);
			  border: 0px solid #ef4300;
		  }
	  </style>
  </head>
  
  <body>
<table class="table" align="center">
	<tr style="background: #4682B4; height: 120px; ">
		<td colspan="2" align="center">
			<iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
		</td>
	</tr>
	<tr>
		<td width="120" style="padding:5px;" align="center" valign="top">
			<iframe frameborder="0" width="120" src="<c:url value='/jsps/left.jsp'/>" name="left"></iframe>
		</td>
		<td>
			<iframe frameborder="0" src="<c:url value='/jsps/body.jsp'/>" name="body"></iframe>
		</td>
	</tr>
</table>
  </body>
</html>
