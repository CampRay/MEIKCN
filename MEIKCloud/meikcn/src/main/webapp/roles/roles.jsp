<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ taglib prefix="s" uri="http://www.springframework.org/tags"%><!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>Roles List</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge"><meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="<c:url value="/"/>assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="<c:url value="/"/>assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/><!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES --><link href="<c:url value="/"/>assets/global/css/components.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/css/plugins.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/><link id="style_color" href="<c:url value="/"/>assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/><!-- END THEME STYLES --><link rel="shortcut icon" href="<c:url value="/"/>static/images/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices --><!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default --><!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle --><!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle --><!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle --><!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar --><!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer --><!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side --><!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu --><body class="page-header-fixed">
	<!-- BEGIN HEADER -->	<c:import url="/common/header"/>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<c:import url="/common/left"/>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">				<div class="page-content">								
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				 <div class="page-bar">
					<%--<ul class="page-breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<c:url value="/"/>home"><s:message code="home"/></a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="<c:url value="/"/>roles"><s:message code="system.management.roles.title" /></a>
						</li>					</ul>--%>					
				</div> 
				<!-- END PAGE TITLE & BREADCRUMB-->
				
				<!-- BEGIN SEARCH FORM -->
				<div class="portlet-body">
					<form id="searchForm" name="searchForm" action="rolelist" class="form-horizontal" method="post">
					<div class="row">
						<div class="col-md-6">					
							<div class="form-group">
								<label class="col-md-3 control-label"><s:message code="system.management.roles.search.rolename" /></label>
								<div class="col-md-9">
									<input name="roleName" type="text" class="form-control">							
								</div>
							</div>
						</div>
						<div class="col-md-6">	
							<div class="form-group">
								<label class="col-md-3 control-label"><s:message code="system.management.user.searchform.status" /></label>
								<div class="col-md-9">
									<div class="radio-list">
										<label class="radio-inline">
										<input type="radio" name="status" value="" checked/>All </label>
										<label class="radio-inline">
										<input type="radio" name="status" value="true"/><s:message code="all.status.enable" /> </label>
										<label class="radio-inline">
										<input type="radio" name="status" value="false"/><s:message code="all.status.disable" /></label>
									</div>									
								</div>
							</div>
						</div>
					</div>					
					<div class="row">	
						<div class="col-md-6">	
							<div class="form-group">								
								<div class="col-md-offset-3 col-md-9">
									<button type="submit" class="btn blue">Search <i class="fa fa-search"></i></button>
									<button type="reset" class="btn grey-cascade">Reset <i class="fa fa-reply"></i></button>
								</div>
							</div>					
						</div>
					</div>	
					</form>
				</div>
				<!-- END SEARCH FORM -->
								<!-- BEGIN PAGE CONTENT-->				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->						<div class="portlet box green">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-edit"></i><s:message code="system.management.roles.tablename" />
								</div>
								<div class="actions">									
								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#add_role"><i class="fa fa-plus"></i><s:message code="all.table.add" /></a>								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#edit_role" id="openEditRoleModal"><i class="fa fa-pencil"></i><s:message code="all.table.edit" /></a>								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#delete_roles" id="openDeleteRoleModal"><i class="fa fa-trash-o"></i> <s:message code="all.table.delete" /></a>								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#edit_roleRights" id="openRoleRigthsModal"><i class="fa fa-cogs"></i> <s:message code="system.management.roles.assign"/></a>								    <div class="btn-group">
										<a class="btn default" href="#" data-toggle="dropdown">
										Columns <i class="fa fa-angle-down"></i>
										</a>
										<div id="column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
											<label><input type="checkbox" checked data-column="0">Checkbox</label>
											<label><input type="checkbox" checked data-column="1"><s:message code="system.management.user.searchform.id"/></label>
											<label><input type="checkbox" checked data-column="2"><s:message code="system.management.roles.search.rolename" /></label>											
											<label><input type="checkbox" checked data-column="3"><s:message code="system.management.roles.parentid" /></label>
											<label><input type="checkbox" checked data-column="4"><s:message code="system.management.user.searchform.status"/></label>
											<label><input type="checkbox" checked data-column="5"><s:message code="system.management.roles.roleright" /></label>
										</div>
									</div>								    																
								</div>
							</div>							
							<div class="portlet-body">																
								<table class="table table-striped table-hover table-bordered" id="roles_table">
									<thead>
										<tr>
											<th class="table-checkbox">
												<input type="checkbox" class="group-checkable" data-set="#roles_table .checkboxes"/>
											</th>
											<th><s:message code="system.management.user.searchform.id"/></th>											
											<th><s:message code="system.management.roles.search.rolename" /></th>											
											<th><s:message code="system.management.roles.parentid" /></th>											
											<th><s:message code="system.management.user.searchform.status"/></th>	
											<th><s:message code="system.management.roles.roleright" /></th>										
										</tr>
									</thead>
																						
								</table>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
				<!-- END PAGE CONTENT -->
								<!-- BEGIN ADD MODAL FORM-->
				<div class="modal" id="add_role" tabindex="-1" data-width="760">
					<div class="modal-header">
						<button id="closeAddModal" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title"><s:message code="system.management.addrole.title" /></h4>
					</div>
					<div id="addFormMsg"></div>
					<!-- <div class="modal-body"> -->
					<div class="portlet-body form">
						<!-- BEGIN FORM	-->					
						<form id="addRoleForm" action="addRole" method="post" name="addRoleForm" class="form-horizontal form-bordered">
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									<s:message code="system.management.user.adduser.message"/>
								</div>								
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="system.management.roles.search.rolename" /><span class="required"> * </span></label>
									<div class="col-md-9">										
										<input name="roleName" class="form-control"/>										
									</div>
								</div>								
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="system.management.roles.parentid" /><span class="required">* </span></label>
									<div class="col-md-9">
										<input name="pid" class="form-control"/>									
									</div>
								</div>								
								<%-- <div class="form-group">
									<label class="control-label col-md-3"><s:message code="system.management.user.searchform.status"/><span class="required">* </span></label>
									<div class="col-md-9">										
										<div class="radio-list">
											<label class="radio-inline">
											<input type="radio" name="status" value="true" checked/>true </label>
											<label class="radio-inline">
											<input type="radio" name="status" value="false"/>false </label>
										</div>
									</div>
								</div>	 --%>														
								
							</div>
							<div class="form-actions" style="border-top:0;">
								<div class="row">
									<div class="col-md-offset-6 col-md-6">
										<button type="submit" class="btn green" id="addFormSubmit"><i class="fa fa-check"></i> Submit</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</form>
						<!-- END FORM-->
					</div>					
				</div>				
				<!-- END ADD MODAL FORM-->								<!-- BEGIN Edit MODAL FORM-->				<div class="modal" id="edit_role" tabindex="-1" data-width="760">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title"><s:message code="system.management.editrole.title"/></h4>
					</div>
					<div id="editFormMsg"></div>
					<!-- <div class="modal-body"> -->					<div class="portlet-body form">
						<!-- BEGIN FORM-->						
						<form id="editRoleForm" action="editRole" method="post" name="editRoleForm" class="form-horizontal form-bordered">
							<input name="roleId" type="hidden" value=""/>
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									<s:message code="system.management.user.adduser.message"/>
								</div>								
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="system.management.roles.search.rolename" /><span class="required"> * </span></label>
									<div class="col-md-9">										
										<input name="roleName" class="form-control"/>										
									</div>
								</div>								
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="system.management.roles.parentid" /><span class="required"> * </span></label>
									<div class="col-md-9">
										<input name="pid" class="form-control"/>									
									</div>
								</div>								
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="system.management.user.searchform.status"/><span class="required"> * </span></label>
									<div class="col-md-9">										
										<div class="radio-list">
											<label class="radio-inline">
											<input type="radio" name="status" value="true" checked/>Active </label>
											<label class="radio-inline">
											<input type="radio" name="status" value="false"/>Inactive </label>
										</div>
									</div>
								</div>														
								
							</div>
							<div class="form-actions" style="border-top:0;">
								<div class="row">
									<div class="col-md-offset-6 col-md-6">
										<button type="submit" class="btn green" id="editFormSubmit"><i class="fa fa-check"></i> Submit</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</form>
						<!-- END FORM-->
					</div>					
				</div>				
				<!-- END EDIT MODAL FORM-->
				
				<!-- BEGIN Edit Role Rights MODAL FORM-->				<div class="modal" id="edit_roleRights" tabindex="-1" data-width="760">					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title"><s:message code="system.management.assign.title"/></h4>
					</div>
					<div id="editRoleRightsFormMsg"></div>
					<!-- <div class="modal-body"> -->					<div class="portlet-body form">
						<!-- BEGIN FORM-->						
						<form id="editRoleRightsForm" action="editRoleRights" method="post" name="editRoleRightsForm" class="form-horizontal form-bordered">
							<input name="roleId" type="hidden" value=""/>							
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									<s:message code="system.management.user.adduser.message"/>
								</div>
							<c:forEach var="rightsGroup" items="${rightsList}" varStatus="status">
								<div class="form-group">
									<label class="control-label col-md-3">${rightsGroup.key} </label>
									<div class="col-md-9">
									<c:forEach var="rights" items="${rightsGroup.value}" varStatus="status">
										<c:if test="${(status.index+1)%3==1}">																				<div class="checkbox-list">
										</c:if>																			
											<label class="checkbox-inline">
											<input type="checkbox" name="rights" value="${rights.bitFlag}"/>${rights.name}</label>
										<c:if test="${(status.index+1)%3==0||status.last}">										</div>										
										</c:if>																											    </c:forEach>
									</div>								</div>
							</c:forEach>
							</div>
							<div class="form-actions" style="border-top:0;">
								<div class="row">
									<div class="col-md-offset-6 col-md-6">
										<button type="submit" class="btn green" id="assignFormSubmit"><i class="fa fa-check"></i> Submit</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</form>
						<!-- END FORM-->
					</div>					
				</div>				                
				<!-- END EDIT Role Rights MODAL FORM-->
				
				<!-- BEGIN DELETE MODAL FORM-->
				<div class="modal" id="delete_roles" tabindex="-1" data-backdrop="static" data-keyboard="false">
					<div class="modal-body">
						<p>
							<s:message code="system.management.user.deletemessage" />
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
						<button id="deleteBtn" type="button" data-dismiss="modal" class="btn blue">Confirm</button>
					</div>					
				</div>				
				<!-- END DELETE MODAL FORM-->
			</div>		
		</div>
	</div>	
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->	<c:import url="/common/footer"/>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
	<script src="<c:url value="/"/>assets/global/plugins/respond.min.js"></script>
	<script src="<c:url value="/"/>assets/global/plugins/excanvas.min.js"></script> 
	<![endif]-->
	<script src="<c:url value="/"/>assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->	<script src="<c:url value="/"/>assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="<c:url value="/"/>assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/datatables/media/js/jquery.dataTables.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>    <script src="<c:url value="/"/>assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="<c:url value="/"/>assets/global/plugins/json/json2.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/scripts/metronic.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/admin/layout/scripts/layout.js" type="text/javascript"></script>		<script src="<c:url value="/"/>static/js/rolesTableData.js"></script>
	<script>
	jQuery(document).ready(function() {       
	   Metronic.init(); // init metronic core components	   Layout.init(); // init current layout	
	   //Demo.init(); // init demo features
	   RolesTable.init("<c:url value="/"/>");	   	});
	</script></body>
<!-- END BODY -->
</html>