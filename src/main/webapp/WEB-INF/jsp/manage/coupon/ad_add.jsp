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
      <a href="<%=basePath1%>goods/goodss" class="current">广告列表</a>
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
                 <h5>广告添加</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="add" enctype="multipart/form-data" commandName="vendAd" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <div class="control-group">
	                <label class="control-label">广告名</label>
	                <div class="controls">
	                  <sf:input path="adName"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="adName" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">广告类型</label>
	                <div class="controls">
	                 <sf:select path="adName" items="${adtypes}" itemLabel="itemname" itemValue="itemno"></sf:select>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="adName" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">上传广告</label>
	                <div class="controls">
	                  <input type="file" name="file"/>
	                  <sf:hidden path="extend1" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="extend1" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">广告投放区域</label>
	                <div class="controls">
	                  <sf:input path="adArealist"/>
	                 <span for="required" generated="true" class="help-inline"> <sf:errors path="adArealist" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">广告链接</label>
	                <div class="controls">
	                  <sf:input path="adUrl"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="adUrl" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">广告屏选择</label>
	                <div class="controls">
	                  <sf:input path="adScreen"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="adScreen" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">广告开始时间</label>
	                <div class="controls">
	                  <sf:input path="startTime" value="<%=DateUtil.getCurrentDateTimeStr()%>" readonly="true"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="startTime" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">广告结束时间</label>
	                <div class="controls">
	                  <sf:input path="endTime" value="<%=DateUtil.getCurrentDateTimeStr()%>" readonly="true"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="endTime" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="form-actions">
	                <input type="submit" value="添加" class="btn btn-success">
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
<script type="text/javascript">
$('#startTime').datetimepicker({
    format: 'yyyy-mm-dd hh:mm:ss'
});
$('#endTime').datetimepicker({
    format: 'yyyy-mm-dd hh:mm:ss'
});
</script>
</body>
</html>