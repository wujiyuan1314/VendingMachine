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
                 <h5>菜单修改</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/menuitem/edit" enctype="multipart/form-data" commandName="menuitem" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <sf:hidden path="id"/>
	              <sf:hidden path="parentId"/>
	              <div class="control-group">
	                <label class="control-label">菜单名</label>
	                <div class="controls">
	                  <sf:input path="menuName"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="menuName" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">菜单链接</label>
	                <div class="controls">
	                  <sf:input path="uri"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="uri" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">菜单图标</label>
	                <div class="controls">
	                  <sf:input path="icon"/>
	                 <span for="required" generated="true" class="help-inline"> <sf:errors path="icon" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="form-actions">
	                <shiro:hasPermission name="menuitem:edit">
	                <input type="submit" value="修改" class="btn btn-success">
	                </shiro:hasPermission>
	                <shiro:hasPermission name="menuitem:add">
	                <a href="add" target="testIframe" class="btn btn-info"/>添加该节点的子节点</a>&nbsp;&nbsp;
	                </shiro:hasPermission>
	                <shiro:hasPermission name="menuitem:del">
	                <a href="del" class="btn btn-danger"/>删除该节点</a>&nbsp;&nbsp;
	                </shiro:hasPermission>
	              </div>
	            </sf:form>
			  </div>
		  </div>
		</div>
    </div>
  </div>
<%@ include file="../../common/common_js.jsp" %>
</body>