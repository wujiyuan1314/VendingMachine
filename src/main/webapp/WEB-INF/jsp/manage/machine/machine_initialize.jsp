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
		<div class="span10">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
                 <h5>机器初始化信息设置</h5>
              </div>
              
			  <div class="widget-content">
			      <table style="margin-bottom:5px;">
			            <tr>
			              <th>型号:gyp206</th>
		                </tr>
			      </table>
			
			      <table class="table table-bordered table-striped with-check">
			      <thead>
			            <tr>
		                  <th style="width:150px;">商品名</th>
		                  <th>冷热状态</th>
		                  <th>名字</th>
		                  <th>出水时间<i style="color:red;">(10~999s)</i></th>
		                  <th>出料时间<i style="color:red;">(10~999s)</i></th>
		                </tr>
			         </thead>
			         <tbody>
			           <c:forEach items="${vendMachineInts}" var="vendMachineInt" varStatus="st">
				           <tr class="gradeX">
				           <input type="hidden" name="id${st.index}" id="id${st.index}" value="${vendMachineInt.id}">
					          <td>${vendMachineInt.goodsName}</td>
			                  <td>
				                  <c:choose>
					                  <c:when test="${vendMachineInt.hotStatus==0 }">
					                      <span style="color:blue;font-weight:999;">冷</span>
					                  </c:when>
				                  </c:choose>
				                   <c:choose>
					                  <c:when test="${vendMachineInt.hotStatus==1 }">
					                      <span style="color:red;font-weight:999;">热</span>
					                  </c:when>
				                  </c:choose>
			                  </td>
			                  <th><input class="span2" name="wareName${st.index}" id="wareName${st.index}" value="${vendMachineInt.wareName}"/></th>
			                  <th><input class="span1" name="waterOutTime${st.index}" id="waterOutTime${st.index}" value="${vendMachineInt.waterOutTime}"/></th>
			                  <th><input class="span1" name="grainOutTime${st.index}" id="grainOutTime${st.index}" value="${vendMachineInt.grainOutTime}"/></th>
			                </tr>
			           </c:forEach>
			         </tbody>
			      </table>
			  </div>
			   <div class="form-actions">
	                <span style="color:#b94a48;font-size:15px;" class="errormsg"></span>
	           </div> 
			   <div class="form-actions">
	                <input type="button" value="保存" class="btn btn-success" onclick="initalize();">
	                <input type="button" value="设备参数设置" class="btn btn-success" onclick="initalize();">
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
/**
 * 提交初始化参数
 */
 function initalize(){
	var length=${vendMachineInts.size()}
	var vendMachineIntArray=new Array();
	var map={};
	for(var i=0;i<length;i++){
		map['id']=$("#id"+i).val();
		map['wareName']=$("#wareName"+i).val();
		map['waterOutTime']=$("#waterOutTime"+i).val();
		map['grainOutTime']=$("#grainOutTime"+i).val();
		var json=JSON.stringify(map);
		vendMachineIntArray.push(json);
	}
    var params={
    		vendMachineIntArray:vendMachineIntArray
    }	
	var url=basePath+"machine/initialize";
	$.ajax({url:url,
		type:'post',
		async: false,      //ajax同步
		dataType:"html",
		traditional: true,//这个设置为true，data:{"steps":["qwe","asd","zxc"]}会转换成steps=qwe&steps=asd&...
		data:params,//URL参数
		success:function(res){
			var data=eval("("+res+")");
			var success=data.success;
			var msg=data.msg;
			if(success==0){
				$(".errormsg").html(msg);
			}else{
				alert(msg,function(){
					window.location.reload();
				});
			}
		},
	    error:function(){
           alert("错误");
           }
	});
}
</script>
<script src="<%=basePath2 %>resources/js/back/machine_list.js"></script>
</body>
</html>
