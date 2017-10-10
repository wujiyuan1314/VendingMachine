<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="base.util.DateUtil" %>
<head>
<title>自动售货机管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ include file="../../common/common_css.jsp" %>
</head>
<body>

<!--Header-part-->
<%@ include file="../../common/common_top.jsp" %>
<!--Header-part-->

<!--sidebar-menu-->
<%@ include file="../../common/common_left.jsp" %>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> 
      <a href="<%=basePath1%>welcome" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a>
      <a href="<%=basePath1%>coupon/chargecoupons" class="current">充值优惠活动</a>
    </div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="row fluid">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span12">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-info-sign"></i></span>
                 <h5>充值优惠活动添加</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/coupon/rechargeedit" enctype="multipart/form-data" commandName="vendCoupon" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <sf:hidden path="id"/>
	              <div class="control-group">
	                    <label class="control-label">活动名</label>
	                <div class="controls">
	                  <sf:input path="couponName"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="couponName" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">充</label>
	                <div class="controls">
	                   <div class="input-append">
	                     <sf:input path="extend3" class="span2"/><span class="add-on">$</span>
	                  </div>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="extend3" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	                <label class="control-label">送</label>
	                <div class="controls">
	                   <div class="input-append">
	                     <sf:input path="extend4" class="span2"/><span class="add-on">$</span>
	                  </div>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="extend4" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <sf:hidden path="couponScale" value="1"/>
	              <sf:hidden path="startTime" value="<%=DateUtil.getCurrentDateStr()%>" readonly="true" class="span2" />
	              <sf:hidden path="endTime" value="<%=DateUtil.getCurrentDateStr()%>" readonly="true" class="span2" />
	              <div class="form-actions">
	                <input type="submit" value="修改" class="btn btn-success">
	              </div>
	            </sf:form>
			  </div>
		  </div>
		</div>
    </div>
  </div>
</div>
<!--end-main-container-part-->

<!--Footer-part-->

<%@ include file="../../common/common_bottom.jsp" %>

<!--end-Footer-part-->
<%@ include file="../../common/common_js.jsp" %>
</body>
</html>