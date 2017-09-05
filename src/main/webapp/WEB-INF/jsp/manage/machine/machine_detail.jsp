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
      <a href="<%=basePath1%>machine/machines" class="current">机器列表</a>
    </div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="row fluid">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span4">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-eye-open"></i></span>
                 <h5>咖啡机状态</h5>
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
	                  <td>储水情况</td>
	                  <td>
	                    <c:choose>
	                     <c:when test="${vendMachine.waterStatus==1}">
	                       
	                     </c:when>
	                     <c:otherwise>
	                     </c:otherwise>
	                    </c:choose>
	                  </td>
	                </tr>
	                <tr>
	                  <td>储杯情况</td>
	                  <td>5670</td>
	                </tr>
	                <tr>
	                  <td>加热状态</td>
	                  <td>4130</td>
	                </tr>
	                <tr>
	                  <td>开机状态</td>
	                  <td>1574</td>
	                </tr>
	                <tr>
	                  <td>自动清洗</td>
	                  <td>1044</td>
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

<%@ include file="../../common/common_bottom.jsp" %>

<!--end-Footer-part-->
<%@ include file="../../common/common_js.jsp" %>
</body>
</html>
