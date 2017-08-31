<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>自动售货机管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ include file="common/common_css.jsp" %>
</head>
<body>

<!--Header-part-->
<%@ include file="common/common_top.jsp" %>
<!--Header-part-->

<!--sidebar-menu-->
<%@ include file="common/common_left.jsp" %>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> 
      <a href="<%=basePath1%>welcome" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a>
      <a class="current">无权限页面</a>
    </div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
            <h5>无权限页面</h5>
          </div>
          <div class="widget-content">
            <div class="error_ex">
              <h1>无权限</h1>
              <p>对不起，您没有权限!!!</p>
              <a class="btn btn-warning btn-big"  href="../welcome">回到首页</a> </div>
          </div>
        </div>
      </div>
    </div>
</div>

<!--end-main-container-part-->

<!--Footer-part-->

<%@ include file="common/common_bottom.jsp" %>

<!--end-Footer-part-->
<%@ include file="common/common_js.jsp" %>
</body>
</html>
