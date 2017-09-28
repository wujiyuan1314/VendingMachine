	<!-- 顶部导航栏 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path6 = request.getContextPath();
	String basePath6 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path6 + "/";
	//System.out.println(basePath);
%>
<script type="text/javascript">
var basePath6="<%=basePath6%>";
</script>