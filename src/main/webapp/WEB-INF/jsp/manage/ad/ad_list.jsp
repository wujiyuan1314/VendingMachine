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
      <a href="<%=basePath1%>ad/ads" class="current">广告列表</a>
    </div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="row fluid">
      <sf:form action="ads" method="post" id="Paramform" class="form-horizontal">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span12">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
                 <h5>广告列表</h5>
              </div>
              
			  <div class="widget-content">
			      <table style="margin-bottom:5px;">
			            <tr>
			              <th>广告名:</th>
			              <th><input type="text" name="adName" id="adName" placeholder="按广告名搜索"/>&nbsp;&nbsp;</th>
		                  <th><input type="submit" value="搜索" class="btn btn-info"/>&nbsp;&nbsp;</th>
		                  <shiro:hasPermission name="ad:ad">
		                  <td><a href="add" class="btn btn-success"/>添加</a>&nbsp;&nbsp;</td>
		                  </shiro:hasPermission>
		                  <shiro:hasPermission name="ad:dels">
		                  <td><input type="button" onclick="dels('ad');" value="批量删除" class="btn btn-danger"/></td>
		                  </shiro:hasPermission>
		                </tr>
			      </table>
			
			      <table class="table table-bordered table-striped with-check">
			         <thead>
			            <tr>
			              <th><input type="checkbox" onclick="selectAll('Id');" id="all" name="title-table-checkbox" /></th>
		                  <th style="width:25px;">序号</th>
		                  <th>广告名</th>
		                  <th>广告开始时间</th>
		                  <th>广告结束时间</th>
		                  <th>是否有效</th>
		                  <th>操作</th>
		                </tr>
			         </thead>
			         <tbody>
			           <c:forEach items="${vendAds}" var="vendAd" varStatus="st">
				           <tr class="gradeX">
					          <th><input type="checkbox" name="Id" id="Id" value="${vendAd.id}"/></th>
					          <td style="text-align:center;">${st.index+1}</td>
			                  <td>${vendAd.adName}</td>
			                  <td><fmt:formatDate value="${vendAd.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                  <td><fmt:formatDate value="${vendAd.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                  <td><c:choose>
			                     <c:when test="${vendAd.extend3==1}">
			                       <button class="btn btn-success btn-mini">有效</button>
			                     </c:when>
			                     <c:otherwise>
			                       <button class="btn btn-danger btn-mini">无效</button>
			                     </c:otherwise>
			                    </c:choose>
			                    </td>
			                  <td class="center">
			                    <shiro:hasPermission name="ad:edit">
			                     <a href="${vendAd.id}/edit" class="btn btn-success"/>修改</a>&nbsp;&nbsp;
			                    </shiro:hasPermission>
			                    <shiro:hasPermission name="ad:del">
			                     <a href="javascript:void(0);" onclick="delconfirm(${vendAd.id});" class="btn btn-danger"/>删除</a>
			                    </shiro:hasPermission>
			                    <shiro:hasPermission name="ad:tf">
			                    <c:choose>
			                       <c:when test="${vendAd.extend3==0}">
			                          &nbsp;&nbsp;<a href="javascript:void(0);" onclick="tfconfirm(${vendAd.id});" class="btn btn-success"/>整体投放</a>
			                       </c:when>
			                     </c:choose>
			                     <c:choose>
			                       <c:when test="${vendAd.extend3==1}">
			                       &nbsp;&nbsp; <a href="javascript:void(0);" onclick="shconfirm(${vendAd.id});" class="btn btn-success"/>回收</a>
			                       </c:when>
			                     </c:choose>
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
function delconfirm(id){
	 if(confirm("确定要删除吗?")){
		window.location.href=basePath+"ad/"+id+"/del";
	 }
}
function tfconfirm(id){
	 if(confirm("确定要投放吗?")){
		var url=basePath+"manage/"+id+"/setShAdItemList";
		 $.post(url,'',function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				alert(msg);
			}else if(success==1){
				alert(msg);
				window.location.reload();
			}
		 })
	 }
}
</script>
</body>
</html>
