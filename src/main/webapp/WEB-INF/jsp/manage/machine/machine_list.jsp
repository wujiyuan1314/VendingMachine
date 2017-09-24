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
                 <h5>机器列表</h5>
              </div>
              
			  <div class="widget-content">
			      <table style="margin-bottom:5px;">
			            <tr>
			              <th>机器名:</th>
			              <th><input type="text" name="machineName" id="machineName" placeholder="按机器名搜索"/>&nbsp;&nbsp;</th>
		                  <th>机器码:</th>
			              <th><input type="text" name="machineCode" id="machineCode" placeholder="按机器名搜索"/>&nbsp;&nbsp;</th>
		                  <th><input type="submit" value="搜索" class="btn btn-info"/>&nbsp;&nbsp;</th>
		                  <td><a href="add" class="btn btn-success"/>新增分机</a>&nbsp;&nbsp;</td>
		                  <td><a class="btn btn-danger" data-toggle="modal" data-target="#myModal"/>解绑</a>&nbsp;&nbsp;</td>
		                  
		                  <!-- 模态框（Modal）开始 -->
		                  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
							  <div class="modal-content">
							  
							    <div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" 
											aria-hidden="true">×
									</button>
									<h4 class="modal-title" id="myModalLabel">
										解除绑定
									</h4>
								</div>
								<div class="modal-body">
									<dl>
										<dd>要转移的商户名:
										<input type="input" id="transusercode" name="transusercode"/></dd>
										<dd class="binderror" style="color:#b94a48;"></dd>
									</dl>
                                </div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭
									</button>
									<button type="button" class="btn btn-primary" onclick="transmachine();">
										提交
									</button>
								</div>
								
							  </div>
							</div>
					      </div>
					      <!-- 模态框（Modal）结束 -->
					
		                  <!-- <td><input type="button" onclick="dels('machine');" value="批量删除" class="btn btn-danger"/></td> -->
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
		                  <th>使用状态</th>
		                  <th>咖啡机状态</th>
		                  <th>创建时间</th>
		                  <th>操作</th>
		                </tr>
			         </thead>
			         <tbody>
			           <c:forEach items="${vendMachines}" var="vendMachine" varStatus="st">
				           <tr class="gradeX">
					          <th><input type="checkbox" name="Id" id="Id" value="${vendMachine.id}"/></th>
			                  <td>${vendMachine.machineName}</td>
			                  <td>${vendMachine.machineId}</td>
			                  <td>${vendMachine.machineCode}</td>
			                  <td>${vendMachine.usercode}</td>
			                  <td><a href="${vendMachine.id}/initialize" class="btn btn-primary btn-mini">gyp206</a></td>
			                  <td><code:itemname codeno="USESTATUS" itemno="${vendMachine.useStatus}"></code:itemname></td>
			                  <td><a href="javascript:detail('${vendMachine.id}');" class="btn btn-success btn-mini">详情</a></td>
			                  <td><fmt:formatDate value="${vendMachine.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                  <td class="center">
			                     <c:choose>
			                       <c:when test="${vendMachine.useStatus==2}">
			                         <a href="javascript:open('${vendMachine.id}');" class="btn btn-primary btn-mini"/>开机</a>&nbsp;&nbsp;
			                       </c:when>
			                     </c:choose>
			                      <c:choose>
			                       <c:when test="${vendMachine.useStatus==1||vendMachine.useStatus==3}">
			                         <a href="javascript:shutdown('${vendMachine.id}');" class="btn btn-danger btn-mini"/>关机</a>&nbsp;&nbsp;
			                         <a href="javascript:reboot('${vendMachine.id}');" class="btn btn-primary btn-mini"/>重启</a>&nbsp;&nbsp;
		                             <a href="javascript:autoClean('${vendMachine.id}');" class="btn btn-primary btn-mini"/>清洗</a>&nbsp;&nbsp;
			                         <a href="javascript:selfCheck('${vendMachine.id}');" class="btn btn-primary btn-mini"/>自检</a>&nbsp;&nbsp;
			                       </c:when>
			                     </c:choose>
			                     <c:choose>
			                       <c:when test="${vendMachine.useStatus==2||vendMachine.useStatus==3}">
			                         <a href="${vendMachine.id}/edit" class="btn btn-success btn-mini"/>修改</a>&nbsp;&nbsp;
			                         <!--<a href="javascript:void(0);" onclick="delconfirm('${vendMachine.id}');" class="btn btn-danger btn-mini"/>删除</a>  -->
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
	                <li><a href="javascript:changeCurrentPage('${page.totalPage}">尾页</a></li>
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
