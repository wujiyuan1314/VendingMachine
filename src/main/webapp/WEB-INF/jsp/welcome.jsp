<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>自动售货机管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ include file="common/common_css.jsp" %>
</head>
<body>

<!--Header-part-->
<%@ include file="common/common_top.jsp" %>
<!--Header-part-->

<!--sidebar-menu-->
<%@ include file="common/common_left.jsp" %>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="<%=basePath1%>welcome" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="row fluid">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span4">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-eye-open"></i></span>
                 <h5>每天销售情况统计</h5>
              </div>
              
			  <div class="widget-content nopadding">
			     <table class="table table-bordered">
		         <thead>
	               <tr>
	                 <th>项目</th>
	                 <th>情况</th>
	               </tr>
	             </thead>
		         <tbody>
	                <tr>
	                  <td>消费用户数</td>
	                  <td></td>
	                </tr>
	                <tr>
	                  <td>销售量</td>
	                  <td></td>
	                </tr>
	                <tr>
	                  <td>销售收入现金</td>
	                  <td></td>
	                </tr>
	                <tr>
	                  <td>免费数量</td>
	                  <td></td>
	                </tr>
	              </tbody>
			     </table>
			  </div>
		  </div>
		</div>
    </div>
  </div>
</div>
<!--end-main-container-part-->

<!--Footer-part-->

<%@ include file="common/common_bottom.jsp" %>

<!--end-Footer-part-->
<%@ include file="common/common_js.jsp" %>
</body>
</html>
