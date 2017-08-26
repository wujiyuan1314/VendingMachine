<!-- 中心管理员左侧导航栏 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path3 = request.getContextPath();
	String basePath3 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path3 + "/";
%>
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> 控制台</a>
  <ul>
    <li class="active"><a href="<%=basePath3 %>welcome"><i class="icon icon-home"></i> <span>首页</span></a></li>
    <li class="submenu"> <a href=""><i class="icon icon-briefcase"></i> <span>商品管理</span></a> 
       <ul>
	        <li><a href="<%=basePath3 %>goods/goodss">商品列表</a></li>
       </ul>
    </li>
     <li class="submenu"> <a href=""><i class="icon icon-briefcase"></i> <span>广告管理</span></a> 
       <ul>
	        <li><a href="<%=basePath3 %>ad/ads">广告列表</a></li>
       </ul>
    </li>
    <li class="submenu"> <a href=""><i class="icon icon-align-justify"></i> <span>菜单管理</span></a> 
       <ul>
	        <li><a href="<%=basePath3 %>menuitem/menuitems">菜单列表</a></li>
       </ul>
    </li>
    <li class="submenu"> <a href=""><i class="icon icon-user"></i> <span>用户权限管理</span></a> 
       <ul>
	        <li><a href="<%=basePath3 %>user/users">用户管理</a></li>
	        <li><a href="<%=basePath3 %>role/roles">角色管理</a></li>
	        <li><a href="<%=basePath3 %>permission/permissions">权限设置</a></li>
       </ul>
    </li>
    <li class="submenu"> <a href=""><i class="icon icon-file-alt"></i> <span>订单管理</span></a> 
       <ul>
	        <li><a href="<%=basePath3 %>order/orders">订单列表</a></li>
       </ul>
    </li>
     <li class="submenu"> <a href=""><i class="icon icon-th"></i> <span>分类管理</span></a> 
       <ul>
	        <li><a href="<%=basePath3 %>codeCatalog/codeCatalogs">参数列表</a></li>
       </ul>
    </li>
  </ul>
</div>