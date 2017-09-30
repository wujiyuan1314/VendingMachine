<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品详情页面</title>
<%@ include file="common_top.jsp" %>
<script src="${pageContext.request.contextPath}/mobile/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/common.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/main_zhcc.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/productdetail.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/jquery-labelauty.css" />
<style>
ul { list-style-type: none;}
li { display: inline-block;}
li { margin: 10px 0;}
input.labelauty + label { font: 12px "Microsoft Yahei";}
</style>
</head>
<body>
<div class="viewport">

   <header>
       <a onclick="gotoMyPage('${usercode}');" class="go_back headeratop"></a>
       <div class="name headertop">免费商品详情</div>
   </header>
   
   <div class="product_detail">
	   <!--banner开始-->
        <div class="banner">
            <img src="${pageContext.request.contextPath}/${vendGoods.pic}" style="height:281px;"/>
        </div>
        <!--banner结束-->
	   <div class="textintor">
	   
	     <div class="title">
            <h1>
            	<div>${vendGoods.goodsName }</div>
            </h1>
         </div>
         
         <div>
	         <div class="price">
	           <label class="nowprice">￥${vendGoods.price }</label>
	         </div>
         </div>
         
          <div class="detail">
           <div class="mid_infor">
              <span>请输入机器码:</span>
              <input name="usercode"  type="hidden" value="${usercode}" class="btn_input" id="usercode"/>
              <input name="machinecode"  type="text" class="btn_input" id="machinecode"/>
           </div>
         </div>
         
          <div class="btnwrap">
            <div class="item">
            	<button class="buybtn" id="buyButtonId" onclick="quickAddorder(${vendGoods.id });">免费购买</button>
            </div>
         </div>
         <ul class="dowebok" style="padding-bottom: 55px;">
		  <li><input type="radio" name="heat" id="heat" data-labelauty="冷饮"></li>
		  <li><input type="radio" name="heat" id="heat" data-labelauty="热饮"/></li>
	   </ul>
	   </div>
   </div>
</div>
<script src="${pageContext.request.contextPath}/mobile/js/details.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/jquery-labelauty.js"></script>
<script>
$(function(){
	$(':input').labelauty();
});
</script> 
</body>
</html>