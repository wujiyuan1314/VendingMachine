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
	        <li><a href="<%=basePath3 %>goods/goodss">菜单列表</a></li>
       </ul>
    </li>
    <li class="submenu"> <a href=""><i class="icon icon-user"></i> <span>用户权限管理</span></a> 
       <ul>
	        <li><a href="<%=basePath3 %>user/users">用户管理</a></li>
	        <li><a href="<%=basePath3 %>role/roles">角色管理</a></li>
	        <li><a href="<%=basePath3 %>permission/permissions" onclick="permission();">权限设置</a></li>
       </ul>
    </li>
    <li class="submenu"> <a href=""><i class="icon icon-user"></i> <span>订单管理</span></a> 
       <ul>
	        <li><a href="<%=basePath3 %>order/orders">订单列表</a></li>
       </ul>
    </li>
    <li> <a href="charts.html"><i class="icon icon-signal"></i> <span>图表统计</span></a> </li>
    <li> <a href="widgets.html"><i class="icon icon-inbox"></i> <span>插件</span></a> </li>
    <li><a href="tables.html"><i class="icon icon-th"></i> <span>数据表格</span></a></li>
    <li><a href="grid.html"><i class="icon icon-fullscreen"></i> <span>网格布局</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>表单</span> </a>
      <ul>
        <li><a href="form-common.html">基本表单</a></li>
        <li><a href="form-validation.html">带验证的表单</a></li>
        <li><a href="form-wizard.html">带提示的表单</a></li>
      </ul>
    </li>
    <li><a href="buttons.html"><i class="icon icon-tint"></i> <span>按钮 &amp; 图标</span></a></li>
    <li><a href="interface.html"><i class="icon icon-pencil"></i> <span>组件</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-file"></i> <span>其他</span> </a>
      <ul>
        <li><a href="index2.html">首页</a></li>
        <li><a href="gallery.html">相册</a></li>
        <li><a href="calendar.html">日历</a></li>
        <li><a href="invoice.html">清单</a></li>
        <li><a href="chat.html">聊天</a></li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-info-sign"></i> <span>错误页面</span> </a>
      <ul>
        <li><a href="error403.html">403错误页面</a></li>
        <li><a href="error404.html">404错误页面</a></li>
        <li><a href="error405.html">05错误页面</a></li>
        <li><a href="error500.html">500错误页面</a></li>
      </ul>
    </li>
  </ul>
</div>