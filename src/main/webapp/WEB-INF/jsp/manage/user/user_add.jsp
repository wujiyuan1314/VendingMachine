<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
      <a href="<%=basePath1%>user/users" class="current">用户列表</a>
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
                 <h5>用户添加</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="add" enctype="multipart/form-data" commandName="vendUser" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <div class="control-group">
	                <label class="control-label">用户名</label>
	                <div class="controls">
	                  <sf:input path="username"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="username" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">密码</label>
	                <div class="controls">
	                  <sf:password path="password"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="password" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">角色</label>
	                <div class="controls">
	                  <sf:select path="roleId" items="${roles}" itemLabel="roleName" itemValue="id" onchange="shchange();">
	                  </sf:select>
	                </div>
	              </div>
	              
	              
	              <div class="control-group" id="wxgzh" style="display:none;">
	                <label class="control-label">微信公众号</label>
	                <div class="controls">
	                  <sf:input path="wechatpubNo"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="wechatpubNo" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <shiro:hasPermission name="lrbl:show">
	              <div class="control-group" id="lrbl" style="display:none;">
	                <label class="control-label">利润比例</label>
	                <div class="controls">
	                  <sf:input path="extend4"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="extend4" cssClass="errors" style="color:#b94a48;">格式(商家:代理:总后台)</sf:errors></span>
	                </div>
	              </div>
	              </shiro:hasPermission>
	         
	              
	              <div class="control-group">
	                <label class="control-label">手机号</label>
	                <div class="controls">
	                  <sf:input path="mobile"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="mobile" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">地址</label>
	                <div class="controls">
	                  <sf:input path="address"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="address" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">联系人</label>
	                <div class="controls">
	                  <sf:input path="linkman"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="linkman" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
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
$(document).ready(function(){
  var roleid=$("#roleId").val();
  if(roleid==4){
	  $("#wxgzh").css("display","block");
	  $("#lrbl").css("display","block");
  }else{
	  $("#wxgzh").css("display","none");
	  $("#lrbl").css("display","none");
  }
});
function shchange(){
	 var roleid=$("#roleId").val();
	  if(roleid==4){
		  $("#wxgzh").css("display","block");
		  $("#lrbl").css("display","block");
	  }else{
		  $("#wxgzh").css("display","none");
		  $("#lrbl").css("display","none");
	  }
}
</script>
</body>
</html>
