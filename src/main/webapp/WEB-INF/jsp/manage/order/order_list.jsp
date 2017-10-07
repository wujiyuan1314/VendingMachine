<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="base.util.DateUtil" %>
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
      <a href="<%=basePath1%>order/orders" class="current">订单列表</a>
    </div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="row fluid">
      <sf:form action="orders" method="post" id="Paramform" class="form-horizontal">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span12">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
                 <h5>订单列表</h5>
              </div>
              
			  <div class="widget-content">
			      <table style="margin-bottom:5px;">
			            <tr>
			              <th>用户名:</th>
			              <th><input type="text" name="usercode" id="usercode" placeholder="按用户名搜索"/>&nbsp;&nbsp;</th>
			              <th>商家名:</th>
			              <th><input type="text" name="shopusercode" id="shopusercode" placeholder="按商家名搜索"/>&nbsp;&nbsp;</th>
		                  <td>
		                  从<input type="text" name="beginTime" id="beginTime"  placeholder="开始时间" class="span2"/>
		                  </td>
		                  <td>
		                  到<input type="text" name="endTime" id="endTime"  placeholder="结束时间" class="span2"/>
		                  </td>
		                  <th><input type="submit" value="搜索" class="btn btn-info"/>&nbsp;&nbsp;</th>
		                  <!--<td><a href="add" class="btn btn-success"/>添加</a>&nbsp;&nbsp;</td>  -->
		                  <shiro:hasPermission name="order:add">
		                  <td><input type="button" onclick="dels('order');" value="批量删除" class="btn btn-danger"/></td>
		                  </shiro:hasPermission>
		                </tr>
			      </table>
			
			      <table class="table table-bordered table-striped with-check">
			         <thead>
			            <tr>
			              <th><input type="checkbox" onclick="selectAll('orderId');" id="all" name="title-table-checkbox" /></th>
		                  <th style="width:25px;">序号</th>
		                  <th>订单号</th>
		                  <th>消费用户</th>
		                  <th>商家</th>
		                  <th>商品</th>
		                  <th>金额</th>
		                  <th>下单时间</th>
		                  <th>付款时间</th>
		                  <th>操作</th>
		                </tr>
			         </thead>
			         <tbody>
			           <c:forEach items="${vendOrders}" var="vendOrder" varStatus="st">
				           <tr class="gradeX">
					          <th><input type="checkbox" name="Id" id="Id" value="${vendOrder.orderId}"/></th>
					          <td style="text-align:center;">${st.index+1}</td>
					          <td>${vendOrder.orderId}</td>
			                  <td>${vendOrder.usercode}</td>
			                  <td>${vendOrder.shopusercode}</td>
			                  <td>${vendOrder.goodsId}</td>
			                  <td>${vendOrder.amount}</td>
			                  <td><fmt:formatDate value="${vendOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                  <td><fmt:formatDate value="${vendOrder.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                  <td class="center">
			                     <shiro:hasPermission name="order:del">
			                     <a href="javascript:void(0);" onclick="delconfirm('${vendOrder.orderId}');" class="btn btn-danger  icon-trash"/></a>
			                     </shiro:hasPermission>
			                  </td>
			                </tr>
			           </c:forEach>
			         </tbody>
			      </table>
			  </div>
			  <div class="pagination alternate">
	              <ul>
	                <li><a href="javascript:changeCurrentPage('1')">首页</a></li>
	                <li><a href="javascript:changeCurrentPage('${page.currentPage -1}')">上一页</a></li>
	                <li class="active"> <a href="#">${page.currentPage}/${page.totalPage}</a> </li>
	                <li><a href="javascript:changeCurrentPage('${page.currentPage+1}')">下一页</a></li>
	                 <li><a href="javascript:changeCurrentPage('${page.totalPage}')">尾页</a></li>
	                <li>&nbsp;&nbsp;&nbsp;&nbsp;跳至第&nbsp; 
	                   <input id="currentPageText" type='text' value='${page.currentPage}' style="width:27px;height:15px;" />&nbsp;页&nbsp;
	                   <a href="javascript:changeCurrentPage2()" style="float:right;">GO</a>
	                </li>
	              </ul>
             </div>
		  </div>
		</div>
     </sf:form>
    </div>
  </div>
</div>

<!--end-main-container-part-->

<!--Footer-part-->
<%@ include file="../../common/common_bottom.jsp" %>

<!--end-Footer-part-->
<%@ include file="../../common/common_js.jsp" %>
<script type="text/javascript">
function delconfirm(orderId){
	 if(confirm("确定要删除吗?")){
		window.location.href=basePath+"order/"+orderId+"/del";
	 }
}
$('#beginTime').datetimepicker({
    format: 'yyyy-mm-dd'
});
$('#endTime').datetimepicker({
    format: 'yyyy-mm-dd'
});
</script>
</body>
</html>
