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
    <div class="row clearfix">
		<div class="col-md-12 column">
			<div class="alert alert-success alert-dismissable">
				 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				<h4>
					欢迎进入自动售货机管理系统!
				</h4> <strong>简介!</strong> 自动售货机（Vending Machine，VEM）是能根据投入的钱币自动付货的机器。自动售货机是商业自动化的常用设备，它不受时间、地点的限制，能节省人力、方便交易。是一种全新的商业零售形式，又被称为24小时营业的微型超市。目前国内常见的自动售卖机共分为四种：饮料自动售货机、食品自动售货机、综合自动售货机、化妆品自动售卖机。</a>
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
