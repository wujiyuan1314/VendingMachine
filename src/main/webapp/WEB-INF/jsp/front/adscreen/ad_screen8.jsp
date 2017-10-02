<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<script src="${pageContext.request.contextPath}/resources/js/jquery1.9.0.min.js"></script> 
<script src="${pageContext.request.contextPath}/resources/js/jquery.SuperSlide.2.1.js"></script> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
<div class="content">

	<!-- 机器信息开始 -->
	<div class="use_info">
		<div class="wcode">
	    	<div class="qrcode"><img src="${pageContext.request.contextPath}/resources/img/vcode.jpg" alt="" /></div>
	        <p>扫码享受热饮</p>
	    </div>
	    <div class="consume"><h2>消费码</h2><input /></div>
	    <div class="step">
	    <i class="lt"></i>
	    <i class="rb"></i>
	    	<h2>使用步骤</h2>
	        <p>1、扫码进入小程序</p>
	        <p>2、选择喜爱的产品</p>
	        <p>3、选择机器消费码</p>
	        <p>4、等待出杯</p>
	    </div>
	</div>
	<!-- 机器信息结束 -->
	
	<!-- 图片开始 -->
	<div class="adv_pic" style="margin:0 auto">
	    <ul class="pic">
			<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/1.jpg"/></a></li>
			<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/2.jpg"/></a></li>
			<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/3.jpg"/></a></li>
			<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/4.jpg"/></a></li>
			<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/5.jpg"/></a></li>
		</ul>
		<a class="prev" href="javascript:void(0)"></a>
		<a class="next" href="javascript:void(0)"></a>
		<ul class="hd">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	<!-- 图片结束 -->
	
</div>
</body>
	<script type="text/javascript">
		/*鼠标移过，左右按钮显示*/
		jQuery(".adv_pic").hover(function(){ jQuery(this).find(".prev,.next").stop(true,true).fadeTo("show",0.2) },function(){ jQuery(this).find(".prev,.next").fadeOut() });
		/*SuperSlide图片切换*/
		jQuery(".adv_pic").slide({ mainCell:".pic",effect:"fold", autoPlay:true, delayTime:600, trigger:"click"});
	</script>
</html>
