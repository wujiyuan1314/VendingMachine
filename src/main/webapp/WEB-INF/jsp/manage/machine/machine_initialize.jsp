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
      <sf:form action="machines" method="post" id="Paramform" class="form-horizontal">
      <input type="hidden" name="currentPage" id="currentPage" value="1" />
		<div class="span12">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
                 <h5>机器初始化信息设置</h5>
              </div>
              
			  <div class="widget-content">
			      <table style="margin-bottom:5px;">
			            <tr>
			              <th>机器名:</th>
		                </tr>
			      </table>
			
			      <table class="table table-bordered table-striped with-check">
			      <thead>
			            <tr>
			              <th><input type="checkbox" onclick="selectAll('machinecode');" id="all" name="title-table-checkbox" /></th>
		                  <th>机器名</th>
		                  <th>机器ID</th>
		                  <th>机器码</th>
		                  <th>登录账号</th>
		                  <th>咖啡机型号(可选择)</th>
		                </tr>
			         </thead>
			         <tbody>
			           <c:forEach items="${list}" var="map" varStatus="st">
				           <tr class="gradeX">
					          <th><input type="checkbox" name="Id" id="Id" value="${vendMachine.id}"/></th>
			                  <td>${vendMachine.machineName}</td>
			                  <td>${vendMachine.machineId}</td>
			                  <td>${vendMachine.machineCode}</td>
			                  <td>${vendMachine.usercode}</td>
			                   <td>${vendMachine.usercode}</td>
			                </tr>
			           </c:forEach>
			         </tbody>
			      </table>
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
		window.location.href=basePath+"machine/"+id+"/del";
	 }
}
/**
 * 转移一个机器给另外一个商家
 */
 function transmachine(){
	var machineId=new Array();
	var length=0;
	$("input[id='Id']:checked").each(function(){
		length=machineId.push($(this).val());
	})
	if(length==0){
		$(".binderror").html("请选择一条记录");
		return;
	}
	if(length>1){
		$(".binderror").html("只能选择一条记录");
		return;
	}
	var id=machineId[0];
	var transusercode=$("#transusercode").val();
	if(transusercode==''){
		$(".binderror").html("请输入要转移的用户名");
		return;
	}
	if(confirm("确定要解绑吗?")){
		var url=basePath+"machine/"+id+"/"+transusercode+"/unbind";
		$.post(url,'',function(res){
			var data=eval("("+res+")");
			if(data.success==1){
				window.location.reload();
			}else if(data.success==0){
				$(".binderror").html(data.msg);
			}
		})
	}
}
</script>
<script src="<%=basePath2 %>resources/js/back/machine_list.js"></script>
</body>
</html>
