<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>登录</title>
<%@ include file="common_top.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/common.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/details.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/main_zhcc.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/productdetail.css" />
</head>
<body>
<div class="viewport">

   <header>
       <a onclick="gotoMyPage();" class="go_back headeratop"></a>
       <div class="name headertop">免费商品详情</div>
   </header>
   
   <div class="product_detail">
	   <!--banner开始-->
        <div class="banner">
            <img src="${vendGoods.pic }" />
        </div>
        <!--banner结束-->
	   <div class="textintor">
	   
	     <div class="title">
            <h1>
            	<div>${vendGoods.goodsName }</div>
            </h1>
         </div>
         
         <div class="cb"><div>
         <div class="price">
           <label class="nowprice">￥${vendGoods.price }</label>
         </div>
         
         <div class="detail">
           <span>请输入机器码:</span>
           <input name="usercode" style="width:60px;" type="hidden" value="${usercode }" class="btn_input" id="usercode">
           <input name="machinecode" style="width:60px;" type="text" class="btn_input" id="machinecode">
         </div>
         
         <div class="btnwrap">
            <div class="item">
            	<button class="buybtn" id="buyButtonId" onclick="quickAddorder(${vendGoods.id });">免费购买</button>
            </div>
         </div>
	   </div>
	   
   </div>
   
</div>
</body>
</html>