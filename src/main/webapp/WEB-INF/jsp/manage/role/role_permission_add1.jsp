<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>自动售货机管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ include file="../../common/common_css.jsp" %>
</head>
  <style>
	div, p, table, th, td {
		list-style:none;
		margin:0; padding:0;
		color:#333; font-size:12px;
		font-family:dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	}
	.line{
	  height:100% !important;
	}
	#testIframe {margin-left: 10px;}
  </style>
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
      <a href="<%=basePath1%>role/roles" class="current">角色列表</a>
    </div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="row fluid">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span12">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
                 <h5>角色权限添加</h5>
              </div>
			  <div class="widget-content">
			     <table class="table table-bordered table-striped with-check">
			        <input type="hidden" name="id" id="id" value="${vendRole.id }"/>
					<tr>
						<td style="width:20%">
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
						<div class="zTreeDemoBackground left">
						 <input type="button" value="添加" onclick="addpermission();" class="btn btn-success"/>
						 </div>
						</td>
						<td style="display:none;">
						  <input type="checkbox" id="py" class="checkbox first" checked />
						  <input type="checkbox" id="sy" class="checkbox first" checked />
					      <input type="checkbox" id="pn" class="checkbox first" checked />
						  <input type="checkbox" id="sn" class="checkbox first" checked />
						</td>
					</tr>
				</table>
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
var roleId=${vendRole.id};
var parentId=${vendRole.parentId};
</script>
<script src="<%=basePath2 %>resources/js/back/role_permission_add.js"></script>
</body>
</html>
