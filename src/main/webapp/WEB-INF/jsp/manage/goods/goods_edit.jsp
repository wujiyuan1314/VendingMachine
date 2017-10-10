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
      <a href="<%=basePath1%>goods/goodss" class="current">商品列表</a>
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
                 <h5>商品修改</h5>
              </div>
              
			  <div class="widget-content nopadding">
			    <sf:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/goods/edit" enctype="multipart/form-data" commandName="vendGoods" name="basic_validate" id="basic_validate" novalidate="novalidate">
	              <sf:hidden path="id"/>
	              <div class="control-group">
	                <label class="control-label">商品名</label>
	                <div class="controls">
	                  <sf:input path="goodsName"/>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="goodsName" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="control-group">
	                <label class="control-label">商品图片</label>
	                <div class="controls">
	                  <input type="file" name="file" id="file_pic"/>
	                   <a href="javascript:doUpload('pic')" class="btn btn-success">上传</a>
	                   <span class="infopic" style="color:#b94a48;"></span>
	                  <sf:hidden path="pic" class="filepath"/>
	                    <a href="<%=basePath1%>${vendGoods.pic}" target="_blank" class="icon-picture" style="color:green;">查看</a>
	                  <span for="required" generated="true" class="help-inline"> <sf:errors path="pic" cssClass="errors"  style="color:#b94a48;"></sf:errors></span>
	                <span><c:choose>
                         <c:when test="${vendGoods.pic!=''}">
                           <img src="<%=basePath1%>${vendGoods.pic}" style="width:70px;height:50px;" class="imgpic"/>
                         </c:when>
                         <c:otherwise>
                           <img src="" class="imgpic"/>
                         </c:otherwise>
                        </c:choose></span>
	                </div>
	              </div>
	             
	              <div class="control-group">
	                <label class="control-label">价格</label>
	                <div class="controls">
	                  <sf:input path="price"/>
	                 <span for="required" generated="true" class="help-inline"> <sf:errors path="price" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              
	              <div class="control-group">
	                <label class="control-label">热饮通道号</label>
	                <div class="controls">
	                 <sf:input path="heatChno"/>
	                 <span for="required" generated="true" class="help-inline"><sf:errors path="heatChno" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	               </div>
	                
	              <div class="control-group">
	                <label class="control-label">冷饮通道号</label>
	                <div class="controls">
	                  <sf:input path="coldChno"/>
	                 <span for="required" generated="true" class="help-inline"><sf:errors path="coldChno" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              
	              <div class="control-group">
	                <label class="control-label">描述</label>
	                <div class="controls">
	                  <sf:textarea path="goodsInfo"/>
	                    <span for="required" generated="true" class="help-inline"> <sf:errors path="goodsInfo" cssClass="errors" style="color:#b94a48;"></sf:errors></span>
	                </div>
	              </div>
	              <div class="form-actions">
	                <input type="submit" value="修改" class="btn btn-success">
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
</body>
</html>
