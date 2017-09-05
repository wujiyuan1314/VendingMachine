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
		<div class="span12">
		  <div class="widget-box">
	          <div class="widget-title"> <span class="icon"> <i class="icon-file"></i> </span>
	            <h5>广告屏幕样式类别列表</h5>
	            <a href="/VendingMachine/codeLibrary/${codeno}/add" class="btn btn-success"/>添加参数类别</a>
	          </div>
	          <div class="widget-content nopadding">
	            <table class="table table-bordered">
	              <thead>
	                <tr>
	                  <th>类别编号</th>
	                  <th>类别值</th>
	                  <th>详细描述</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
		              <c:forEach items="${codeLibrarys}" var="codeLibrary" varStatus="st">
		                <tr>
		                  <td>${codeLibrary.itemno}</td>
		                  <td>${codeLibrary.itemname}</td>
		                  <td>${codeLibrary.extend1}</td>
		                  <td>
		                      <a href="/VendingMachine/codeLibrary/${codeLibrary.id}/edit" class="btn btn-success icon-edit"/></a>&nbsp;&nbsp;
			                  <a href="javascript:void(0);" onclick="delconfirm('${codeLibrary.id}','${codeno}');" class="btn btn-danger  icon-trash"/></a>
		                  </td>
		                </tr>
		              </c:forEach>
	              </tbody>
	            </table>
	          </div>
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
function delconfirm(id,codeno){
	 if(confirm("确定要删除吗?")){
		window.location.href=basePath+"codeLibrary/"+codeno+"/"+id+"/del";
	 }
}
</script>
</body>
</html>
