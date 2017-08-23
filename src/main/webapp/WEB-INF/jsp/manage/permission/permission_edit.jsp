<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common_css.jsp" %>
<!--Action boxes-->
<body style="background: #fff;">
  <div class="container-fluid">
    <div class="row fluid">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span12">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-info-sign"></i></span>
                 <h5>权限修改</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="/VendingMachine/permission/edit" enctype="multipart/form-data" commandName="vendPermission" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <sf:hidden path="id"/>
	               <div class="control-group">
	                <label class="control-label">权限名</label>
	                <div class="controls">
	                  <sf:input path="permissionName"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="permissionName" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">权限描述</label>
	                <div class="controls">
	                  <sf:input path="permissionDescription"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="permissionDescription" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="form-actions">
	                <input type="submit" value="修改" class="btn btn-success">&nbsp;&nbsp;
	                <a href="add" target="testIframe" class="btn btn-info"/>添加该节点的子节点</a>&nbsp;&nbsp;
	                <a href="del" class="btn btn-danger"/>删除该节点</a>&nbsp;&nbsp;
	              </div>
	            </sf:form>
			  </div>
		  </div>
		</div>
    </div>
  </div>
<%@ include file="../../common/common_js.jsp" %>
</body>
