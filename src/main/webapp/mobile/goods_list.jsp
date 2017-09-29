<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>免费商品列表</title>
<%@ include file="common_top.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/TouchSlide.1.1.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/common.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/index.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/msgbox.m/msgbox.m.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/main_zhcc.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/index_zhcc.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/js/msgbox.m/msgbox.m.theme-a.min.css" />
</head>
<body>
<div class="viewport">

   <header>
       <div class="name headertop">免费商品列表</div>
   </header>
   
   <div class="zh_index">
   </div>
   
   <div class="tea_prodct" id="newSupplyList">
     <c:forEach items="${vendGoodss}" var="vendGoods" varStatus="st">
        <div class="item">
          <div class="con">
             <a href="${pageContext.request.contextPath}/mobile/${vendGoods.id}/${usercode}/detail">
                 <div class="pic">
                   <img name="newpic1" src="${vendGoods.pic}" height="174">
                 </div>
             </a>
             <div class="detail">
	             <a href="${pageContext.request.contextPath}/mobile/${vendGoods.id}/${usercode}/detail"></a>
	             <a href="#">${vendGoods.goodsName}</a>
             </div>
             <a href="${pageContext.request.contextPath}/mobile/${vendGoods.id}/${usercode}/detail">
                 <div class="sale_price">
                     <div class="price">￥${vendGoods.price}</div>
                 </div>
	         </a>
	         <div class="grade_icon">
                 <div class="star_leve">
                 </div>
             </div>
          </div>
        </div>
     </c:forEach>
   </div>
   
</div>
</body>
</html>