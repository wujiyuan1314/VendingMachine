<!--1，项目根目录-->
<%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path1 + "/";
%>
<!--2，引用标签  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!--3，页面元信息 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!--4，公共css文件 -->
<link rel="stylesheet" href="<%=basePath1 %>resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath1 %>resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath1 %>resources/css/fullcalendar.css" />
<link rel="stylesheet" href="<%=basePath1 %>resources/css/matrix-style.css" />
<link rel="stylesheet" href="<%=basePath1 %>resources/css/matrix-media.css" />
<link rel="stylesheet" href="<%=basePath1 %>resources/font-awesome/css/font-awesome.css"/>
<link rel="stylesheet" href="<%=basePath1 %>resources/css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>