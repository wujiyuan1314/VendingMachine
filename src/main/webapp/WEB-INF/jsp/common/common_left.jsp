<!-- 中心管理员左侧导航栏 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="menu" uri="/menu-tags"%>
<%
	String path3 = request.getContextPath();
	String basePath3 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path3 + "/";
%>
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> 控制台</a>
   <ul>
   <menu:rolemenu parentId="1" roleid="${sessionScope.vendUser.roleId}">
     <c:forEach items="${rolemenus}" var="rolemenu1" varStatus="status">
       <li class="submenu">
          <a href="<%=basePath3 %>${rolemenu1.uri}">
              <i class="icon ${rolemenu1.icon}"></i> <span>${rolemenu1.menuName}</span>
          </a>
          <ul>
            <menu:rolemenu parentId="${rolemenu1.id }" roleid="${sessionScope.vendUser.roleId}">
	           <c:forEach items="${rolemenus}" var="rolemenu2" varStatus="status">
	              <li><a href="<%=basePath3 %>${rolemenu2.uri}">${rolemenu2.menuName}</a></li>
	           </c:forEach>
			</menu:rolemenu>
          </ul>
       </li>
     </c:forEach>
   </menu:rolemenu>
   </ul>
</div>