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
		<div class="span12">
		  <div class="widget-box">
		      <div class="widget-title"> <span class="icon"><i class="icon-info-sign"></i></span>
                 <h5>机器修改</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/machine/edit" enctype="multipart/form-data" commandName="vendMachine" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <sf:hidden path="id"/>
	              <div class="control-group">
	                <label class="control-label">机器名</label>
	                <div class="controls">
	                  <sf:input path="machineName" readonly="true"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="machineName" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              
	              <div class="control-group">
	                <label class="control-label">选择广告</label>
	                <div class="controls">
	                  <div class="input-append date datepicker">
	                  <sf:hidden path="extend2"/>
	                    <input type="hidden" id="adId"/>
	                   <input type="text" id="adName" readonly="true" data-toggle="modal" data-target="#myModal"/>
	                    <span class="add-on" data-toggle="modal" data-target="#myModal"><i class="icon-caret-down"></i></span>
	                 <a href="javascript:adPuton('${vendMachine.id}');" class="btn btn-success btn-mini">投放</a>
	                  </div>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="extend2" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	               
	                <!-- 模态框（Modal）开始 -->
					 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" 
											aria-hidden="true">×
									</button>
									<h4 class="modal-title" id="myModalLabel">
										广告样式选择
									</h4>
								</div>
								<div class="modal-body">
									<div class="container-fluid">
										<div class="row-fluid">
											<div class="span12">
											    <ul class="thumbnails">
											      <c:forEach items="${ads}" var="ad" varStatus="st">
													<li class="span2">
														<div class="thumbnail">
															<img src="${pageContext.request.contextPath}/resources/img/adscreen/fg${ad.extend2}.jpg" style="width:120px;height:150px;">
															<div class="caption">
																<p>
																    ${ad.adName}
																</p>
																<p>
																  <div class="radio" id="uniform-adtype">
																   <span data-id="${ad.id}" data-adname="${ad.adName}" id="thumbBox${st.index}" onclick="checkid(${st.index});"></span>
																  </div>
																</p>
															</div>
														</div>
													</li>
												  </c:forEach>
												</ul>
							                </div>
										</div>
									</div>
									
                                </div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="selectad();">
										提交
									</button>
								</div>
							</div>
						</div> 
					</div>
					<!-- 模态框（Modal）jieshu-->
					 
	              </div>
	              
	            </sf:form>
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
<script src="<%=basePath2 %>resources/js/back/machine_list.js"></script>
<script type="text/javascript">
//模拟框正中
$("#adName").click(function(){
	$(".modal").css("margin-left","-450px");
})
$(".add-on").click(function(){
	$(".modal").css("margin-left","-450px");
})
//选择广告屏
var length=${ads.size()};
function checkid(j){
	for(var i=0;i<length;i++){
		$("#thumbBox"+i).removeClass("checked");
	}
	$("#thumbBox"+j).addClass("checked");
	
}
function selectad(){
	var adId=$(".checked").attr("data-id");
	if(typeof(adId)=="undefined"){ 
		alert("请选择一个"); 
        return;
	} 
	var adName=$(".checked").attr("data-adName");
	$("#adId").val(adId);
	$("#adName").val(adName);
}
</script>
</body>
</html>
