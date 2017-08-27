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
      <a href="<%=basePath1%>ad/ads" class="current">广告列表</a>
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
	                <label class="control-label">广告图片轮播时间</label>
	                <div class="controls">
	                  <sf:input path="picInterval"/>&nbsp;秒
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="picInterval" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">上传广告图片</label>
	                <div class="controls">
	                                              图片1： <input type="file" name="file"/>
	                        <input type="button" value="上传" onclick="doUpload('pic1')">      
	                  <sf:hidden path="pic1" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic1" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
                    </div>
                    <div class="controls">                          
	                                              图片2： <input type="file" name="file"/>
	                         <input type="button" value="上传" onclick="doUpload('pic2')" class="btn btn-success">                     
	                  <sf:hidden path="pic2" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic2" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	                 <div class="controls">
	                                              图片3：<input type="file" name="file"/>
	                  <input type="button" value="上传" onclick="doUpload('pic3')" class="btn btn-success">
	                  <sf:hidden path="pic3" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic3" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
                    <div class="controls">                           
	                                            图片4：<input type="file" name="file"/>
	                   <input type="button" value="上传" onclick="doUpload('pic4')" class="btn btn-success">
	                  <sf:hidden path="pic4" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic4" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	                 <div class="controls">
	                                            图片5：<input type="file" name="file"/>
	                  <input type="button" value="上传" onclick="doUpload('pic5')" class="btn btn-success">
	                  <sf:hidden path="pic5" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic5" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
                    <div class="controls">                           
	                                            图片6：<input type="file" name="file"/>
	                 <input type="button" value="上传" onclick="doUpload('pic6')" class="btn btn-success">
	                  <sf:hidden path="pic6" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic6" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">视频名</label>
	                <div class="controls">
	                  <sf:input path="extend1"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="extend1" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">上传视频</label>
	                <div class="controls">
	                  <input type="file" name="file"/>
	                  <input type="button" value="上传" onclick="doUpload('video')" class="btn btn-success">
	                  <sf:hidden path="video" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="video" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">图片或视频宽高设置</label>
	                <div class="controls">
	                                             宽度：<sf:input path="width"/>&nbsp;px
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="width" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                  </div>
	                  <div class="controls">                         
	                                             高度：<sf:input path="height"/>&nbsp;px
	                                             
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="height" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
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