<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../../common/common_css.jsp" %>
<body style="background: #fff">
  <div class="container-fluid">
    <div class="row fluid">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span12">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-info-sign"></i></span>
                 <h5>角色修改</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="/VendingMachine/role/edit" enctype="multipart/form-data" commandName="vendRole" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <sf:hidden path="id"/>
	               <div class="control-group">
	                <label class="control-label">角色名</label>
	                <div class="controls">
	                  <sf:input path="roleName"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="roleName" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">角色描述</label>
	                <div class="controls">
	                  <sf:input path="roleDescription"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="roleDescription" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="form-actions">
	                <input type="submit" value="修改" class="btn btn-success">
	                <a href="${vendRole.parentId}/addpermission" target="testIframe" class="btn btn-warning"/>分配权限</a>&nbsp;&nbsp;
	                <a href="${vendRole.parentId}/addmenuitem" class="btn btn-primary"/>分配菜单</a>&nbsp;&nbsp;
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
</html>
