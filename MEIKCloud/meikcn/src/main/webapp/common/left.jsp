<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
    <!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse">
		<!-- BEGIN SIDEBAR MENU -->        
		<ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
			<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
			<li class="sidebar-toggler-wrapper">
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				<div class="sidebar-toggler">
				</div>
				<!-- END SIDEBAR TOGGLER BUTTON -->
			</li>
			<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
			<li class="sidebar-search-wrapper">&nbsp;</li>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <c:set var="cureReqUri" value="${pageContext.request.requestURI}"/>	        
        <%-- 循环所有一级菜单 --%>						
		<c:forEach var="menu" items="${menus}" varStatus="status">	
			<%-- 设置hasSubMenu变量，判断当前一级菜单是否有子菜单 --%>	
			<c:set var="hasSubMenu" value="${menu.value!=null&&menu.value.size()>0}"/>		
			<%-- 如果一级菜单没有子菜单 --%>
			<c:if test="${menu.value==null||menu.value.size()==0}">
				<c:if test="${menu.key.uri.startsWith('/')}">
					<c:set var="menuUri" value="${contextPath}${menu.key.uri}"/>
				</c:if>
				<c:if test="${!menu.key.uri.startsWith('/')}">
					<c:set var="menuUri" value="${contextPath}/${menu.key.uri}"/>
				</c:if>
				<%-- 如果当前请求路径与此菜单路径一致，设置selectedNode变量为当前节点 --%>
				<c:if test="${cureReqUri.startsWith(menuUri)}">						
						<c:set var="selectedNode" value="${menu.key}"/>			
				</c:if>	
			</c:if>
			<%-- 如果一级菜单有子菜单 --%>
			<c:if test="${menu.value!=null&&menu.value.size()>0}">	
				<%-- 循环所有子菜单路径 --%>
				<c:forEach var="tempmenu" items="${menu.value}">
					<c:set var="tempMenuUri" value="${tempmenu.uri}"/>
					<c:if test="${tempMenuUri.startsWith('/')}">						
						<c:set var="reqUri" value="${contextPath}${tempMenuUri}"/>
					</c:if>
					<c:if test="${!tempMenuUri.startsWith('/')}">						
						<c:set var="reqUri" value="${contextPath}/${tempMenuUri}"/>
					</c:if>						
					<%-- 如果当前请求路径与此菜单路径一致，设置selectedNode变量为当前子菜单节点 --%>			
					<c:if test="${cureReqUri.startsWith(reqUri)}">										
						<c:set var="selectedNode" value="${tempmenu}"/>			
					</c:if>
				</c:forEach>
								
			</c:if>	
			<%-- 设置菜单显示样式 --%>	
			<c:set var="classStr" value=""/>	
			<%-- 如果当前菜单是第一个菜单 --%>						
			<c:if test="${status.first}">	
			    <!-- 如果selectedNode变量有值，表示当前菜单或其子菜单是访问的菜单 ,设置菜单样式-->				
				<c:if test="${selectedNode!=null&&selectedNode.nodeId==menu.key.nodeId}">		
					<c:set var="classStr" value="start active"/>									
				</c:if>
				<c:if test="${selectedNode!=null&&selectedNode.nodeId!=menu.key.nodeId&&selectedNode.pid==menu.key.nodeId}">		
					<c:set var="classStr" value="start active open"/>			
				</c:if>
				<c:if test="${selectedNode.nodeId!=menu.key.nodeId&&selectedNode.pid!=menu.key.nodeId}">		
					<c:set var="classStr" value="start"/>		
				</c:if>
			</c:if>			
			<%-- 如果当前菜单不是第一个菜单 --%>		
			<c:if test="${!status.first}">		
				<c:if test="${selectedNode!=null&&selectedNode.nodeId==menu.key.nodeId}">
					<c:set var="classStr" value="active"/>					
				</c:if>
				<c:if test="${selectedNode!=null&&selectedNode.nodeId!=menu.key.nodeId&&selectedNode.pid==menu.key.nodeId}">		
					<c:set var="classStr" value="active open"/>			
				</c:if>
				<c:if test="${selectedNode!=null&&selectedNode.nodeId!=menu.key.nodeId&&selectedNode.pid!=menu.key.nodeId}">		
					<c:set var="classStr" value=""/>			
				</c:if>
			</c:if>				
						
			<jsp:useBean id="menuBean" class="com.nuvomed.dto.TadminNodes"></jsp:useBean>	
			<jsp:setProperty name="menuBean" property="bitFlag" value="${menu.key.bitFlag}"></jsp:setProperty>	
			<%
			boolean isSupAdmin=((com.nuvomed.dto.TadminUser)session.getAttribute("Logined")).getAdminId().equals("campray");
			Long userRights=null;
			if(session.getAttribute("rights")!=null){
				userRights=(Long)session.getAttribute("rights");
			}	
			
			if(isSupAdmin||(userRights&menuBean.getBitFlag())>0){				
			%>	
			<li class="${classStr}">															
				<a href="${hasSubMenu?'javascript;':menuUri}">
				<i class="<c:out value="${menu.key.menuIcon}"/>"></i> 
				<span class="title"><s:message code="${menu.key.name}"/></span>
				<c:if test="${selectedNode!=null&&selectedNode.nodeId==menu.key.nodeId}">				
					<span class="selected"></span>								
				</c:if>	
				<c:if test="${hasSubMenu}">
					<c:if test="${selectedNode==null}">
						<span class="arrow"></span>
					</c:if>
					<c:if test="${selectedNode!=null&&selectedNode.pid!=0&&selectedNode.pid==menu.key.nodeId}">
						<span class="arrow open"></span>
					</c:if>						
				</c:if>	
				</a>
				<c:if test="${hasSubMenu}">
					<ul class="sub-menu">
					<c:forEach var="subMenu" items="${menu.value}" varStatus="substatus">						
						<jsp:useBean id="subMenuBean" class="com.nuvomed.dto.TadminNodes"></jsp:useBean>	
						<jsp:setProperty name="subMenuBean" property="bitFlag" value="${subMenu.bitFlag}"></jsp:setProperty>
						<%if(isSupAdmin||(userRights&subMenuBean.getBitFlag())>0){%>
					   <li class="${(selectedNode!=null&&subMenu.nodeId==selectedNode.nodeId)?'active':''}">
							<a href="${contextPath}${subMenu.uri}">
							<s:message code="${subMenu.name}"/>							
							</a>
						</li>
						<%} %>
					</c:forEach>											
					</ul>
				</c:if>	
			</li>
			<%} %>			    
		</c:forEach>									
			
		</ul>
		<!-- END SIDEBAR MENU -->
	   </div>
    </div>    
   <!-- END SIDEBAR -->