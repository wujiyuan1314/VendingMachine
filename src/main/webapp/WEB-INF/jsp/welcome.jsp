<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="base.util.DateUtil" %>
<head>
<title>自动售货机管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ include file="common/common_css.jsp" %>
<style type="text/css">
#active{
  color: #fff;
  background-color: #337ab7;
}
</style>
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
		<div class="span8">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-eye-open"></i></span>
                 <h5>销售情况统计</h5>
              </div>
              
			  <div class="widget-content nopadding">
			      <table style="margin-bottom:5px;">
			            <tr>
		                  <td><a data-url="daysales" id="active" class="btn sales"/>每天</a>&nbsp;&nbsp;</td>
		                  <td><a data-url="weeksales" class="btn sales"/>每周</a>&nbsp;&nbsp;</td>
		                  <td><a data-url="bydatesales" class="btn sales"/>按照时间查询</a>
		                  </td>
		                </tr>
			      </table>
			      <table style="margin-bottom:5px;display:none;" class="datasearch">
			          <tr>
			              <th>
		                  <input type="text" name="beginTime" id="beginTime" value="<%=DateUtil.getCurrentDateStr()%>" placeholder="开始时间" class="span2"/>
		                  </th>
		                  <th>
		                  <input type="text" name="endTime" id="endTime" value="<%=DateUtil.getCurrentDateStr()%>" placeholder="结束时间" class="span2"/>
		                  </th>
		                  <th><a onclick="bydatesales();" class="btn-success btn"/>搜索</a>&nbsp;&nbsp;</th>
			          </tr>
			      </table>
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
		                  <td><span  class="user_num">${map.user_num}</span>/人</td>
		                </tr>
		                <tr>
		                  <td>销售量</td>
		                  <td><span  class="sell_num">${map.sell_num}</span>/瓶</td>
		                </tr>
		                <tr>
		                  <td>销售收入现金</td>
		                  <td><span  class="sell_amount">${map.sell_amount}</span>/元</td>
		                </tr>
		                <tr>
		                  <td>免费数量</td>
		                  <td><span  class="free_num">${map.free_num}</span>/人</td>
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
<script type="text/javascript">
/**
 * 每周或每天查询销售统计
 */
 $(".sales").click(function(){
	 $(".sales").attr("id","");
	 $(this).attr("id","active");
	 
	 var path=$(this).attr("data-url");
	 if(path=='bydatesales'){
		 $(".datasearch").css("display","block");
	 }else{
		 $(".datasearch").css("display","none"); 
		 var url="${pageContext.request.contextPath}/"+path;
		 $.post(url,"",function(res){
			 var data=eval("("+res+")");//转化为json串
			 $(".user_num").html(data.user_num);
			 $(".sell_num").html(data.sell_num);
			 $(".sell_amount").html(data.sell_amount);
			 $(".free_num").html(data.free_num);
		 })
	 }
 })
 /**
 * 按时间查询销售统计
 */
 function bydatesales(){
	 var url="${pageContext.request.contextPath}/bydatesales";
	 var params={
			 beginTime:$("#beginTime").val(),
			 endTime:$("#endTime").val()
	 };
	 $.post(url,params,function(res){
		 var data=eval("("+res+")");//转化为json串
		 $(".user_num").html(data.user_num);
		 $(".sell_num").html(data.sell_num);
		 $(".sell_amount").html(data.sell_amount);
		 $(".free_num").html(data.free_num);
	 })
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