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
                 <h5>二维码投放</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/qrcode/edit" enctype="multipart/form-data" commandName="vendShopQrcode" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <sf:hidden path="id"/>
	              <sf:hidden path="usercode"/>
	              <sf:hidden path="extend1"/>
	              <sf:hidden path="extend2"/>
	              <sf:hidden path="extend4"/>
	              <sf:hidden path="machineId"/>
	              <sf:hidden path="createTime"/>
	              <sf:hidden path="extend5" value="${id }"/><!-- 机器在数据库的主键 -->
	              <div class="control-group">
	                <label class="control-label">上传二维码
	                (可上传类型:<c:forEach items="${uppictypes}" var="uppictype">
	                  ${uppictype.itemname},
	                  </c:forEach>)</label>
	                <div class="controls">
	                  <input type="file" name="file" id="file_qrcode"/>
	                  <a href="javascript:doUpload('qrcode')" class="btn btn-success">上传</a>  
	                  <span class="infoqrcode" style="color:#b94a48;"></span> 
	                  <sf:hidden path="qrcode" class="filepath"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="qrcode" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                  <span><c:choose>
                         <c:when test="${vendShopQrcode.qrcode!=''}">
                           <img src="<%=basePath1%>${vendShopQrcode.qrcode}" style="width:70px;height:50px;" class="imgqrcode"/>
                         </c:when>
                         <c:otherwise>
                           <img src="" class="imgqrcode"/>
                         </c:otherwise>
                        </c:choose></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">广告屏文字</label>
	                <div class="controls">
	                <sf:textarea path="intro" style="width:360px;height:320px;"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="intro" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="form-actions">
	                <input type="submit" value="保存" class="btn btn-success">
	                <a href="javascript:qrcodePuton('${id}');" class="btn btn-success btn-mini">投放</a>
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
/** 初始化编辑器*/
KindEditor.ready(function(K) {
			var uploadJson = '${pageContext.request.contextPath}/resources/kindeditor/upload?a='+Math.random();
			var editor1 = K.create('textarea[name="intro"]', {
				cssPath : '${pageContext.request.contextPath}/resources/kindeditor/plugins/code/prettify.css',
				uploadJson : uploadJson,
				fileManagerJson : '${pageContext.request.contextPath}/resources/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager :false,
				allowUpload : false, 
				fillDescAfterUploadImage :false,//上传图片后跳转到图片编辑页面
				//pagebreakHtml : '$$$$$$', //自定义分页符
				afterCreate : function() {
							var self = this;
							self.sync();
						},
				afterChange : function() {
							var self = this;
							self.sync();
						},
				afterBlur : function() {
							var self = this;
							self.sync();
						}
				
			});
			
		});
</script>
</body>
</html>
