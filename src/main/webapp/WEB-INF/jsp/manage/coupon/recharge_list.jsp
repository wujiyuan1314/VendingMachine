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
      <a href="<%=basePath1%>coupon/chargecoupons" class="current">充值优惠活动</a>
    </div>
  </div>
<!--End-brecouponcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="row fluid">
      <sf:form action="coupons" method="post" id="Paramform" class="form-horizontal">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span12">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
                 <h5>充值优惠活动</h5>
              </div>
              
			  <div class="widget-content">
			      <table style="margin-bottom:5px;">
			            <tr>
			              <th>活动名:</th>
			              <th><input type="text" name="couponName" id="couponName" placeholder="按活动名搜索"/>&nbsp;&nbsp;</th>
		                  <th><input type="submit" value="搜索" class="btn btn-info"/>&nbsp;&nbsp;</th>
		                  <shiro:hasPermission name="coupon:add">
		                  <td><a href="rechargeadd" class="btn btn-success"/>添加</a>&nbsp;&nbsp;</td>
		                  </shiro:hasPermission>
		                  <shiro:hasPermission name="coupon:dels">
		                  <td><input type="button" onclick="dels('coupon');" value="批量删除" class="btn btn-danger"/></td>
		                  </shiro:hasPermission>
		                </tr>
			      </table>
			
			      <table class="table table-bordered table-striped with-check">
			         <thecoupon>
			            <tr>
			              <th><input type="checkbox" onclick="selectAll('Id');" id="all" name="title-table-checkbox" /></th>
		                  <th style="width:25px;">序号</th>
		                  <th>活动名</th>
		                  <th>优惠比例</th>
		                  <th>活动介绍</th>
		                  <th>活动开始日期</th>
		                  <th>活动结束日期</th>
		                  <th>是否有效</th>
		                  <th>操作</th>
		                </tr>
			         </thecoupon>
			         <tbody>
			           <c:forEach items="${vendCoupons}" var="vendCoupon" varStatus="st">
				           <tr class="grcouponeX">
					          <th><input type="checkbox" name="Id" id="Id" value="${vendCoupon.id}"/></th>
					          <td style="text-align:center;">${st.index+1}</td>
			                  <td>${vendCoupon.couponName}</td>
			                  <td>${vendCoupon.couponScale}</td>
			                  <td>${vendCoupon.couponInfo}</td>
			                  <td><fmt:formatDate value="${vendCoupon.startTime}" pattern="yyyy-MM-dd"/></td>
			                  <td><fmt:formatDate value="${vendCoupon.endTime}" pattern="yyyy-MM-dd"/></td>
			                  <td>
			                   <c:choose>
			                     <c:when test="${vendCoupon.valid==1}">
			                       <button class="btn btn-success btn-mini">有效</button>
			                     </c:when>
			                     <c:otherwise>
			                       <button class="btn btn-danger btn-mini">无效</button>
			                     </c:otherwise>
			                    </c:choose>
			                  </td>
			                  <td class="center">
			                     <shiro:hasPermission name="coupon:edit">
			                     <a href="${vendCoupon.id}/rechargeedit" class="btn btn-success btn-mini"/>修改</a>&nbsp;&nbsp;
			                     </shiro:hasPermission>
			                     <shiro:hasPermission name="coupon:del">
			                     <a href="javascript:void(0);" onclick="delconfirm(${vendCoupon.id});" class="btn btn-danger btn-mini"/>删除</a>
			                     </shiro:hasPermission>
			                     <c:choose>
			                       <c:when test="${vendCoupon.valid==0}">
			                         &nbsp;&nbsp;<a href="${vendCoupon.id}/puton" class="btn btn-success btn-mini"/>投放</a>
			                       </c:when>
			                     </c:choose>
			                     <c:choose>
			                       <c:when test="${vendCoupon.valid==1}">
			                         &nbsp;&nbsp;<a href="${vendCoupon.id}/revoke" class="btn btn-success btn-mini"/>收回</a>
			                       </c:when>
			                     </c:choose>
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
function delconfirm(id){
	 if(confirm("确定要删除吗?")){
		window.location.href=basePath+"coupon/"+id+"/del";
	 }
}
</script>
</body>
</html>
