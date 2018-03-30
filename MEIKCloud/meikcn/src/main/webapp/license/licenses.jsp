<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%
String adminId=((com.nuvomed.dto.TadminUser)session.getAttribute("Logined")).getAdminId();
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

<meta charset="utf-8"/>

<title>License List</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>

<meta content="" name="description"/>

<meta content="" name="author"/>

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<c:url value="/"/>assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

<link href="<c:url value="/"/>assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>

<link href="<c:url value="/"/>assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

<link href="<c:url value="/"/>assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>

<link href="<c:url value="/"/>assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="<c:url value="/"/>assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>

<link href="<c:url value="/"/>assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL STYLES -->

<!-- BEGIN THEME STYLES -->
<link href="<c:url value="/"/>assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="<c:url value="/"/>assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="<c:url value="/"/>static/images/favicon.ico"/>

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">

	<!-- BEGIN HEADER -->
	<c:import url="/common/header"/>
	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->
	<div class="page-container">

		<!-- BEGIN SIDEBAR -->
		<c:import url="/common/left"/>
		<!-- END SIDEBAR -->

		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">	
			<div class="page-content">											
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<div id="msg"></div>
				<!-- END PAGE TITLE & BREADCRUMB-->
					
				<!-- BEGIN SEARCH FORM -->

				<div class="portlet-body">
					<form id="searchForm" name="searchForm" action="licenseslist" class="form-horizontal" method="post">
					<div class="row">
						<div class="col-md-6">					
							<div class="form-group">
								<label class="col-md-3 control-label"><s:message code="license.key" /></label>
								<div class="col-md-9">
									<input name="license" type="text" class="form-control">							
								</div>
							</div>
						</div>
						<div class="col-md-6">	
							<div class="form-group">
								<label class="col-md-3 control-label"><s:message code="system.management.user.searchform.status" /></label>
								<div class="col-md-9">
									<div class="radio-list">
										<label class="radio-inline">
										<input type="radio" name="status" value="" checked/><s:message code="all.status.all"/> </label>
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
									<button type="submit" class="btn blue"><s:message code="all.table.search"/> <i class="fa fa-search"></i></button>
									<button type="reset" class="btn grey-cascade"><s:message code="all.table.reset"/> <i class="fa fa-reply"></i></button>
								</div>
							</div>					
						</div>
					</div>	
					</form>
				</div>

				<!-- END SEARCH FORM -->
				
				<!-- END PAGE CONTENT -->									 
				<div class="row" >
					<div class="col-md-12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet  box green">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-edit"></i><s:message code="license.title"></s:message>
								</div>
								<div class="actions">
									<%if("campray".equals(adminId)){ %>									
								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#add_license"><i class="fa fa-plus"></i> <s:message code="all.table.add" /></a>
								    <%} %>								    
								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#edit_license" id="openEditModal"><i class="fa fa-plus"></i> <s:message code="all.table.edit" /></a>
								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#delete_licenses" id="deleteLicenseModal"><i class="fa fa-key"></i> <s:message code="all.table.delete"/></a>
								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#active_licenses" id="openActiveLicenseModal"><i class="fa fa-key"></i> <s:message code="all.table.activate"/></a>
								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#deactive_licenses" id="openDeactiveLicenseModal"><i class="fa fa-trash-o"></i> <s:message code="all.table.deactivate" /></a>
								    <div class="btn-group">
										<a class="btn default" href="#" data-toggle="dropdown">
										<s:message code="all.table.column" /> <i class="fa fa-angle-down"></i>
										</a>
										<div id="column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
											<label><input type="checkbox" checked data-column="0">Checkbox</label>
											<label><input type="checkbox" checked data-column="1"><s:message code="license.key"/></label>
											<label><input type="checkbox" checked data-column="2"><s:message code="license.type"/></label>
											<label><input type="checkbox" checked data-column="3"><s:message code="license.username"/></label>
											<label><input type="checkbox" checked data-column="4"><s:message code="license.deadline"/></label>
											<label><input type="checkbox" checked data-column="5"><s:message code="license.cpu"/></label>
											<label><input type="checkbox" checked data-column="6"><s:message code="license.created.time"/></label>
											<label><input type="checkbox" checked data-column="7"><s:message code="license.active.time"/></label>											
											<label><input type="checkbox" checked data-column="8"><s:message code="license.status"/></label>											
										</div>
									</div>								    																
								</div>
							</div>							
							<div class="portlet-body">																
								<table class="table table-striped table-hover table-bordered" id="licenses_table">
									<thead>
										<tr>
											<th class="table-checkbox">
												<input type="checkbox" class="group-checkable" data-set="#licenses_table .checkboxes"/>
											</th>
											<th><s:message code="license.key"/></th>
											<th><s:message code="license.type"/></th>
											<th><s:message code="license.username"/></th>
											<th><s:message code="license.deadline"/></th>
											<th><s:message code="license.cpu"/></th>
											<th><s:message code="license.created.time"/></th>
											<th><s:message code="license.active.time"/></th>											
											<th><s:message code="license.status"/></th>											
										</tr>
										</thead>																	
								</table>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>				
				
				<!-- BEGIN ADD MODAL FORM-->
				<div class="modal" id="add_license" tabindex="-1" data-width="760">
					<div class="modal-header">
						<button id="closeAddModal" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title"><s:message code="system.management.license.addlicense"/></h4>
					</div>
					<div id="addFormMsg"></div>
					<!-- <div class="modal-body"> -->
					<div class="portlet-body form">
						<!-- BEGIN FORM	-->					
						<form id="addLicenseForm" action="add" method="post" name="addLicenseForm" class="form-horizontal form-bordered">
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									<s:message code="system.management.license.addlicense.message"/>
								</div>	
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="license.type"/></label>
									<div class="col-md-9">
										<select name="type" class="form-control">											
											<option value="0" selected="selected"><s:message code="license.type.screen"/></option>														
											<option value="1"><s:message code="license.type.report"/></option>
										</select>										
									</div>
								</div>							
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="license.username"/></label>
									<div class="col-md-9">										
										<input name="deviceId" class="form-control"/>										
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="license.deadline"/></label>
									<div class="col-md-5">
										<div data-date-format="mm/dd/yyyy" class="input-group date date-picker">
											<input type="text" name="deadlineStr" readonly="true" class="form-control" /> <span class="input-group-btn">
												<button type="button" class="btn default">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
									</div>
								</div>								
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
				<!-- END ADD MODAL FORM-->
				
				<!-- BEGIN Edit MODAL FORM-->
				<div class="modal" id="edit_license" tabindex="-1" data-width="760">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title"><s:message code="system.management.license.editlicense"/></h4>
					</div>
					<div id="editFormMsg"></div>
					<!-- <div class="modal-body"> -->
					<div class="portlet-body form">
						<!-- BEGIN FORM-->						
						<form id="editLicenseForm" action="edit" method="post" name="editLicenseForm" class="form-horizontal form-bordered">
						    <input name="cpuId" type="hidden" value=""/>
						    <input name="activeTime" type="hidden" value=""/>
						    <input name="status" type="hidden" value=""/>	
						    <input name="deadline" type="hidden" value=""/>	
						    <input name="createdTime" type="hidden" value=""/>				    
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									<s:message code="system.management.license.editlicense.message"/>
								</div>	
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="license.key"/></label>
									<div class="col-md-9">										
										<input name="license" class="form-control" readonly="true"/>										
									</div>
								</div>														
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="license.username"/></label>
									<div class="col-md-9">										
										<input name="deviceId" class="form-control"/>										
									</div>
								</div>
								<%if("campray".equals(adminId)){ %>	
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="license.type"/></label>
									<div class="col-md-9">
										<select name="type" class="form-control">											
											<option value="0"><s:message code="license.type.screen"/></option>														
											<option value="1"><s:message code="license.type.report"/></option>
										</select>										
									</div>
								</div>			
								<div class="form-group">
									<label class="control-label col-md-3"><s:message code="license.deadline"/></label>
									<div class="col-md-5">
										<div data-date-format="mm/dd/yyyy" class="input-group date date-picker">
											<input type="text" name="deadlineStr" readonly="true" class="form-control" /> <span class="input-group-btn">
												<button type="button" class="btn default">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
									</div>
								</div>					
								<%} %>			
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
								
				<!-- BEGIN DELETE MODAL FORM-->
				<div class="modal" id="delete_licenses" tabindex="-1" data-backdrop="static" data-keyboard="false">
					<div class="modal-body">
						<p>
							 <s:message code="license.delete.message" />
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-default"><s:message code="all.table.concel" /></button>
						<button id="deleteBtn" type="button" data-dismiss="modal" class="btn blue"><s:message code="all.table.confirm" /></button>
					</div>					
				</div>				
				<!-- END DELETE MODAL FORM-->	
				
				<!-- BEGIN ACTIVE MODAL FORM-->
				<div class="modal" id="active_licenses" tabindex="-1" data-backdrop="static" data-keyboard="false">
					<div class="modal-body">
						<p>
							 <s:message code="license.active.message" />
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-default"><s:message code="all.table.concel" /></button>
						<button id="activeBtn" type="button" data-dismiss="modal" class="btn blue"><s:message code="all.table.confirm" /></button>
					</div>					
				</div>				
				<!-- END ACTIVE MODAL FORM-->		
				
				<!-- BEGIN DEActivate MODAL FORM-->
				<div class="modal" id="deactive_licenses" tabindex="-1" data-backdrop="static" data-keyboard="false">
					<div class="modal-body">
						<p>
							<s:message code="license.deactive.message" />
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-default"><s:message code="all.table.concel" /></button>
						<button id="deactivateBtn" type="button" data-dismiss="modal" class="btn blue"><s:message code="all.table.confirm" /></button>
					</div>					
				</div>				
				<!-- END DELETE MODAL FORM-->		   						
			</div>							
		</div>		
	</div>
		
	
	
	
	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->
	<c:import url="/common/footer"/>
	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<!--[if lt IE 9]>

	<script src="<c:url value="/"/>assets/global/plugins/respond.min.js"></script>

	<script src="<c:url value="/"/>assets/global/plugins/excanvas.min.js"></script> 

	<![endif]-->

	<script src="<c:url value="/"/>assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="<c:url value="/"/>assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>

	<script src="<c:url value="/"/>assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>

	<script src="<c:url value="/"/>assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>

	<script src="<c:url value="/"/>assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<script type="text/javascript" src="<c:url value="/"/>assets/global/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="<c:url value="/"/>assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/"/>assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
	<script src="<c:url value="/"/>assets/global/plugins/datatables/media/js/jquery.dataTables.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
    <script src="<c:url value="/"/>assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
    <script src="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
    <script src="<c:url value="/"/>assets/global/plugins/jquery-i18n/jquery.i18n.properties-1.0.9.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	
	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="<c:url value="/"/>assets/global/plugins/json/json2.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/global/scripts/metronic.js" type="text/javascript"></script>
	<script src="<c:url value="/"/>assets/admin/pages/scripts/form-wizard.js"></script>
	<script src="<c:url value="/"/>assets/admin/layout/scripts/layout.js" type="text/javascript"></script>	
	<script src="<c:url value="/"/>static/js/common.js"></script>
	<script src="<c:url value="/"/>static/js/LicenseTableData.js"></script>
	<script>

	jQuery(document).ready(function() {       

	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout	

	   //Demo.init(); // init demo features
		FormWizard.init();
		LicensesTable.init("<c:url value="/"/>","${sessionScope.locale}");	   
	});

	</script>
</body>

<!-- END BODY -->


</html>