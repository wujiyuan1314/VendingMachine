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
                 <h5>广告投放</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/ad/edit" enctype="multipart/form-data" commandName="vendAd" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <sf:hidden path="id"/>
	              <sf:hidden path="type"/>
	              <sf:hidden path="machineId"/>
	              <sf:hidden path="usercode"/>
	               <sf:hidden path="extend4" value="${id }"/><!-- 机器在数据库的主键 -->
	              <sf:hidden path="adName" value="${vendAd.machineId }"/>
	              <div class="control-group">
	                <label class="control-label">广告图片轮播时间</label>
	                <div class="controls">
	                  <sf:input path="picInterval"/>&nbsp;秒
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="picInterval" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">上传广告图片
	              (可上传类型:<c:forEach items="${uppictypes}" var="uppictype">
	                  ${uppictype.itemname},
	                  </c:forEach>)
	                </label>
	                <div class="controls">
	                                              图片1： <input type="file" name="file" id="file_pic1" value="${vendAd.pic1 }"/>
	                  <a href="javascript:doUpload('pic1')" class="btn btn-success">上传</a>  
	                  <span class="infopic1" style="color:#b94a48;"></span>
	                  <a href="<%=basePath1%>${vendAd.pic1}" target="_blank" class="icon-picture" style="color:green;">查看</a>       
	                  <sf:hidden path="pic1" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic1" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
                    </div>
                    <div class="controls">                          
	                                              图片2： <input type="file" name="file" id="file_pic2" value="${vendAd.pic2 }"/>
	                  <a href="javascript:doUpload('pic2')" class="btn btn-success">上传</a>            
	                  <span class="infopic2" style="color:#b94a48;"></span>
	                  <a href="<%=basePath1%>${vendAd.pic2}" target="_blank" class="icon-picture" style="color:green;">查看</a>       
	                  <sf:hidden path="pic2" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic2" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	                 <div class="controls">
	                                              图片3：<input type="file" name="file" id="file_pic3" value="${vendAd.pic3 }"/>
	                  <a href="javascript:doUpload('pic3')" class="btn btn-success">上传</a>  
	                  <span class="infopic3" style="color:#b94a48;"></span>
	                  <a href="<%=basePath1%>${vendAd.pic3}" target="_blank" class="icon-picture" style="color:green;">查看</a>       
	                  <sf:hidden path="pic3" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic3" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
                    <div class="controls">                           
	                                            图片4：<input type="file" name="file" id="file_pic4" value="${vendAd.pic4}"/>
	                  <a href="javascript:doUpload('pic4')" class="btn btn-success">上传</a>  
	                  <span class="infopic4" style="color:#b94a48;"></span>
	                  <a href="<%=basePath1%>${vendAd.pic4}" target="_blank" class="icon-picture" style="color:green;">查看</a>       
	                  <sf:hidden path="pic4" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic4" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	                 <div class="controls">
	                                            图片5：<input type="file" name="file" id="file_pic5" value="${vendAd.pic5 }"/>
	                  <a href="javascript:doUpload('pic5')" class="btn btn-success">上传</a>  
	                  <span class="infopic5" style="color:#b94a48;"></span>
	                  <a href="<%=basePath1%>${vendAd.pic5}" target="_blank" class="icon-picture" style="color:green;">查看</a>       
	                  <sf:hidden path="pic5" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic5" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
                    <div class="controls">                           
	                                            图片6：<input type="file" name="file" id="file_pic6" value="${vendAd.pic6 }"/>
	                  <a href="javascript:doUpload('pic6')" class="btn btn-success">上传</a>  
	                  <span class="infopic6" style="color:#b94a48;"></span>
	                  <a href="<%=basePath1%>${vendAd.pic6}" target="_blank" class="icon-picture" style="color:green;">查看</a>       
	                  <sf:hidden path="pic6" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic6" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">视频名</label>
	                <div class="controls">
	                  <sf:input path="extend1"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="extend1" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">上传视频
	                     (可上传类型:<c:forEach items="${upvideotypes}" var="upvideotype">
	                  ${upvideotype.itemname},
	                  </c:forEach>)
	                  </label>
	                <div class="controls">
	                  <input type="file" name="file" id="file_video" value="${vendAd.video }"/>
	                  <a href="javascript:doUploadVideo('video')" class="btn btn-success">上传</a>  
	                  <span class="infovideo" style="color:#b94a48;"></span>
	                  <sf:hidden path="video" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="video" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">图片或视频宽高设置</label>
	                <div class="controls">
	                                             宽度：<sf:input path="width"/>&nbsp;px
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="width" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                  </div>
	                  <div class="controls">                         
	                                             高度：<sf:input path="height"/>&nbsp;px
	                                             
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="height" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              
	              <div class="control-group">
	                <label class="control-label">广告屏样式选择</label>
	                <div class="controls">
	                  <div class="input-append date datepicker">
	                  <sf:hidden path="extend2"/>
	                    <input type="text" id="adtypename" value="${adtypename}" readonly="true" data-toggle="modal" data-target="#myModal"/>
	                    <span class="add-on" data-toggle="modal" data-target="#myModal"><i class="icon-caret-down"></i></span>
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
											      <c:forEach items="${adscreens}" var="adscreen" varStatus="st">
													<li class="span2">
														<div class="thumbnail">
															<img src="${pageContext.request.contextPath}/resources/img/adscreen/fg${adscreen.itemno}.jpg" style="width:120px;height:150px;">
															<div class="caption">
																<p>
																    ${adscreen.itemname}
																</p>
																<p>
																  <div class="radio" id="uniform-adtype">
																   <span data-itemname="${adscreen.itemname}" data-itemno="${adscreen.itemno}" id="thumbBox${st.index}" onclick="checkitemno(${st.index});"></span>
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
									<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="adscreen();">
										提交
									</button>
								</div>
							</div>
						</div> 
					</div>
					<!-- 模态框（Modal）jieshu-->
					 
	              </div>
	              
	              <div class="control-group">
	                <label class="control-label">广告开始时间</label>
	                <div class="controls">
	                  <div class="input-append date datepicker">
	                      <sf:input path="startTime" class="Wdate span2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="<%=DateUtil.getCurrentDateTimeStr()%>" readonly="true"/>
		                  
		              </div>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="startTime" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">广告结束时间</label>
	                <div class="controls">
	                  <div class="input-append date datepicker">
	                      <sf:input path="endTime" class="Wdate span2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="<%=DateUtil.getCurrentDateTimeStr()%>" readonly="true"/>
		               
		              </div>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="endTime" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="form-actions">
	                <input type="submit" value="保存" class="btn btn-success">
	                 <a href="javascript:adPuton('${id}');" class="btn btn-success btn-mini">投放</a>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
//模拟框正中
$("#adtypename").click(function(){
	$(".modal").css("margin-left","-450px");
})
$(".add-on").click(function(){
	$(".modal").css("margin-left","-450px");
})
//选择广告屏
var length=${adscreens.size()};
function checkitemno(j){
	for(var i=1;i<length+1;i++){
		$("#thumbBox"+i).removeClass("checked");
	}
	$("#thumbBox"+j).addClass("checked");
	
}

function adscreen(){
	var adtype=$(".checked").attr("data-itemno");
	if(typeof(adtype)=="undefined"){ 
		alert("请选择一个"); 
        return;
	} 
	var adtypename=$(".checked").attr("data-itemname");
	$("#extend2").val(adtype);
	$("#adtypename").val(adtypename);
}
</script>
</body>
</html>