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
    </div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="row fluid">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span6">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-eye-open"></i></span>
                 <h5>个人信息</h5>
              </div>
              
			  <div class="widget-content nopadding">
			  <sf:form class="form-horizontal" method="post" action="/VendingMachine/user/editself" enctype="multipart/form-data" commandName="vendUser" name="basic_validate" id="basic_validate" novalidate="novalidate">
			     <sf:hidden path="usercode" value="${sessionScope.vendUser.usercode}"/>
			     <sf:hidden path="roleId" value="${sessionScope.vendUser.roleId}"/>
			     <sf:hidden path="password" value="${sessionScope.vendUser.password}"/>
			     <table class="table table-bordered">
		         <tbody>
	                <tr>
	                  <td>昵称</td>
	                  <td>
	                  <sf:input path="username" value="${sessionScope.vendUser.username}"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="username" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                  </td>
	                </tr>
	                <tr>
	                  <td>手机号</td>
	                  <td>
	                      <sf:input path="mobile" value="${sessionScope.vendUser.mobile}"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="mobile" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                  </td>
	                </tr>
	                <tr>
	                  <td>地址</td>
	                  <td>
	                    <sf:input path="address" value="${sessionScope.vendUser.address}"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="address" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </tr>
	                <tr>
	                  <td>联系人</td>
	                  <td>
	                      <sf:input path="linkman" value="${sessionScope.vendUser.linkman}"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="linkman" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </tr>
	                <tr>
	                  <td colspan=2><input type="submit" value="修改" class="btn btn-success"></td>
	                </tr>
	              </tbody>
			     </table>
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
function delconfirm(usercode){
	 if(confirm("确定要删除吗?")){
		window.location.href=basePath+"user/"+usercode+"/del";
	 }
}
</script>
</body>
</html>
			  