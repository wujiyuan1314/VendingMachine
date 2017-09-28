<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>登录</title>
<%@ include file="common_top.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/TouchSlide.1.1.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/common.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/login.css" />
</head>
<body>
<div class="viewport">

   <header>
       <div class="name headertop">消费用户登录</div>
   </header>
   <form id="loginform" class="form-vertical" action="login" method="post">
	   <div class="phone_frame" id="phoneDiv">
		  <div class="phone">
		     <input id="userName" name="userName" type="text"  value="" placeholder="请输入微信用户名">
		  </div>
	   </div>
	   
	   <div class="phone_frame" id="phoneDiv">
		  <div class="phone">
		     <input id="password" name="password" type="text"  value="" placeholder="请输入密码">
		  </div>
	   </div>
   </form>
   
   <div class="finish_frame">
      <div><button class="login_btn">登录</button></div>
   </div>
    
</div>
</body>
</html>