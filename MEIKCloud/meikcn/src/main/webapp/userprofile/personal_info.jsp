<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="s" uri="http://www.springframework.org/tags"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]--><!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]--><!-- BEGIN HEAD --><head><meta charset="utf-8"/><title>User Profile</title><meta http-equiv="X-UA-Compatible" content="IE=edge"><meta content="width=device-width, initial-scale=1.0" name="viewport"/><meta content="" name="description"/><meta content="" name="author"/><!-- BEGIN GLOBAL MANDATORY STYLES --><link href="<c:url value="/"/>assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/><!-- END GLOBAL MANDATORY STYLES --><!-- BEGIN PAGE LEVEL STYLES --><link href="<c:url value="/"/>assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/admin/pages/css/profile.css" rel="stylesheet" type="text/css"/><link rel="stylesheet" type="text/css" href="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/><!-- END PAGE LEVEL STYLES --><!-- BEGIN THEME STYLES --><link href="<c:url value="/"/>assets/global/css/components.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/global/css/plugins.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/><link id="style_color" href="<c:url value="/"/>assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/><link href="<c:url value="/"/>assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/><!-- END THEME STYLES --><link rel="shortcut icon" href="<c:url value="/"/>static/images/favicon.ico"/></head><!-- END HEAD --><!-- BEGIN BODY --><!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices --><!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default --><!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle --><!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle --><!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle --><!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar --><!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer --><!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side --><!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu --><body class="page-header-fixed">	<!-- BEGIN HEADER -->	<c:import url="/common/header"/>	<!-- END HEADER -->	<!-- BEGIN CONTAINER -->	<div class="page-container">		<!-- BEGIN SIDEBAR -->		<c:import url="/common/left"/>		<!-- END SIDEBAR -->		<!-- BEGIN CONTENT -->		<div class="page-content-wrapper">	
			<div class="page-content">												<!-- BEGIN PAGE TITLE & BREADCRUMB-->				<h3 class="page-title">			        <s:message code="userprofile.UserProfile"/>			     </h3>				<div class="page-bar">					<ul class="page-breadcrumb">						<li>							<i class="fa fa-home"></i>							<a href="<c:url value="/"/>home"><s:message code="home"/></a>							<i class="fa fa-angle-right"></i>						</li>						<li>							<a href="<c:url value="/"/>userprofile"><s:message code="userprofile.UserProfile"/></a>													</li>											</ul>									</div>				<!-- END PAGE TITLE & BREADCRUMB-->								<!-- BEGIN PAGE CONTENT-->				<div class="row profile">				  <div class="col-md-12">					<div class="portlet light">					   <div class="col-md-11">					     <div class="portlet-title tabbable-line">						     <ul class="nav nav-tabs" style="text-align: center;">						        <li class="active">								   <a href="#tab_1_1" data-toggle="tab">								   <s:message code="userprofile.PersonalInfo"/></a>							    </li>							   <li>								 <a href="#tab_1_2" data-toggle="tab">								  <s:message code="userprofile.ChangeAvatar"/> </a>							   </li>							   <li>								  <a href="#tab_1_3" data-toggle="tab">								   <s:message code="userprofile.ChangePassword"/></a>							   </li>							   </ul>						  </div>						</div>						<div class="portlet-body">						  <div class="tab-content">							<div class="tab-pane active" id="tab_1_1">								<div class="row">								    									<div class="col-md-3">										<ul class="list-unstyled profile-nav">											<li>											    <img alt="100%x250" class="img-responsive" src="<%=request.getContextPath()%>/userprofile/getAvatar" style="height: 250px; width: 200px;">												<!--<img alt="" class="img-responsive" src="<c:url value="/"/>assets/admin/pages/media/profile/profile-img.png">-->												  <!--<a class="profile-edit" href="#tab_1_2" data-toggle="tab">												edit </a>-->											</li>																					</ul>									</div>									<div class="col-md-8">										<div id="tab_1-1" class="tab-pane active portlet-body form">																						 <form:form  role="form" action="" commandName="adminInfo" id="editUserProfile">												    <div class="alert alert-danger display-hide">									                <button class="close" data-close="alert"></button>									                <s:message code="system.management.user.adduser.message"/>								                    </div>								                    <div id="editFormMsg"></div>													<div class="form-group">														<label class="control-label"><s:message code="userprofile.FirstName"/></label>														<form:input type="text" placeholder="Enter your First Name" class="form-control" path="firstName"/>													</div>													<div class="form-group">														<label class="control-label"><s:message code="userprofile.LastName"/></label>														<form:input type="text" placeholder="Enter your Last Name" class="form-control" path="lastName"/>													</div>													<div class="form-group">														<label class="control-label"><s:message code="userprofile.Gender"/></label>														<div class="radio-inline">											                <label><form:radiobutton  path="gender" value="true" /><s:message code="userprofile.male"/> </label>											                <label><form:radiobutton  path="gender" value="false" /><s:message code="userprofile.female"/> </label>										                </div>													</div>																										<div class="form-group">														<label class="control-label"><s:message code="userprofile.MobileNumber"/></label>														<form:input type="text" placeholder="Mobile Number" class="form-control" path="mobile"/>													</div>													<div class="form-group">														<label class="control-label"><s:message code="system.management.user.searchform.email"/></label>														<form:input type="text" placeholder="Email" class="form-control" path="email"/>													</div>													<div class="form-group">														<label class="control-label"><s:message code="userprofile.Birthday"/></label>                                                        <div data-date-format="yyyy/mm/dd" class="input-group date date-picker">												           <form:input type="text" path="birthday" readonly="true" class="form-control"/>												           <span class="input-group-btn">												           <button type="button" class="btn default"><i class="fa fa-calendar"></i></button>												           </span>											            </div>											        </div>																						<div class="form-group">														<label class="control-label"><s:message code="userprofile.Position"/></label>														<form:input type="text" placeholder="Your Position" class="form-control" path="position"/>													</div>													 <div class="form-group">														<label class="control-label"><s:message code="userprofile.About"/></label>														<form:textarea class="form-control" rows="3" placeholder="About you !!!!!" path="about"></form:textarea>													 </div>													<div class="form-group">														<label class="control-label"><s:message code="userprofile.notify"/></label>														<form:checkbox class="form-control" path="notify"/>													</div>													<div class="margiv-top-10">														<input type="submit" class="btn green" value="Save Changes" >														<input type="button" class="btn green" value="Cancel " onclick="location.href='javascript:history.go(-1);'">																		                                    </div>												</form:form>																								</div>									</div>								</div>							</div>							<!--tab_1_2-->							<div class="tab-pane" id="tab_1_2">								<div class="row profile-account">								          <div class="col-sm-2">								          </div>								          <div class="col-sm-9">								                 												<form action="chageAvatar" role="form" enctype="multipart/form-data" method="post">													<div class="form-group">														<div class="fileinput fileinput-new" data-provides="fileinput">															<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">																<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt=""/>															</div>															<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">															</div>															<div>																<span class="btn default btn-file">																<span class="fileinput-new">																Select image </span>																<span class="fileinput-exists">																Change </span>																<input type="file" name="avatar" accept="image/*">																</span>																<a href="#" class="btn default fileinput-exists" data-dismiss="fileinput">																Remove </a>																																<div class="clearfix margin-top-10">															       <span class="label label-danger">															       NOTE! </span>															     <span>															     <s:message code="userprofile.changeavate.note"/> </span>														        </div>														        																<div class="margin-top-10">														          <input type="submit" class="btn green fileinput-exists" value="Change Avatar" class="form-control"/>													            </div>															</div>														</div>																																								</div>												</form>											</div>										</div>									</div>							<!--end tab-1-2-->														<!-- tab-1-3 -->							<div class="tab-pane" id="tab_1_3">								<div class="row">								    <div class="col-sm-2">								    								    </div>									<div class="col-sm-4">									        <div class="portlet-body form">									            <div id="changePasswordMsg"></div>												<form action="" id="changePasswordForm" method="post" name="changePasswordForm">												    <div class="form-group">														<label class="control-label"><s:message code="userprofile.CurrentPassword"/></label>														<input type="password" class="form-control" name="oldpassword"/>													</div>													<div class="form-group">														<label class="control-label"><s:message code="userprofile.NewPassword"/></label>														<input type="password" class="form-control" name="newpassword" id="newpassword"/>													</div>													<div class="form-group">														<label class="control-label"><s:message code="userprofile.Re-typeNewPassword"/></label>														<input type="password" class="form-control" name="renewpassword"/>													</div>													<div class="margin-top-10">														<input type="submit" class="btn green" value="Change Password" class="form-control"/>														<input type="reset" class="btn green" value="Reset" class="form-control"/>													</div>												</form>												</div>									</div>								</div>							</div>							<!--end tab-1-3 -->						</div>						</div>						</div>					</div>				</div>				<!-- END PAGE CONTENT -->								
			</div>		
		</div>	</div>	
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<c:import url="/common/footer"/>
	<!-- END FOOTER -->	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->	<!-- BEGIN CORE PLUGINS -->	<!--[if lt IE 9]>	<script src="<c:url value="/"/>assets/global/plugins/respond.min.js"></script>	<script src="<c:url value="/"/>assets/global/plugins/excanvas.min.js"></script> 	<![endif]-->	<script src="<c:url value="/"/>assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->	<script src="<c:url value="/"/>assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>	<!-- END CORE PLUGINS -->	<!-- BEGIN PAGE LEVEL PLUGINS -->	<script src="<c:url value="/"/>assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/datatables/media/js/jquery.dataTables.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>    <script src="<c:url value="/"/>assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>    <script src="<c:url value="/"/>assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>    <script type="text/javascript" src="<c:url value="/"/>assets/global/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js"></script>    <script src="<c:url value="/"/>assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script>    <script src="<c:url value="/"/>assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script>    <script src="<c:url value="/"/>assets/global/plugins/typeahead/handlebars.min.js" type="text/javascript"></script>    <script src="<c:url value="/"/>assets/global/plugins/typeahead/typeahead.bundle.min.js" type="text/javascript"></script>    <script src="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>       <!-- END PAGE LEVEL PLUGINS -->	<!-- BEGIN PAGE LEVEL SCRIPTS -->	<script src="<c:url value="/"/>assets/admin/pages/scripts/components-form-tools.js"></script>    <script src="<c:url value="/"/>assets/global/plugins/json/json2.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/global/scripts/metronic.js" type="text/javascript"></script>	<script src="<c:url value="/"/>assets/admin/layout/scripts/layout.js" type="text/javascript"></script>		<script src="<c:url value="/"/>static/js/UserProfile.js"></script>	<script>	   jQuery(document).ready(function() {       	   Metronic.init(); // init metronic core components	   Layout.init(); // init current layout		   //Demo.init(); // init demo features	   UserProfile.init("<c:url value="/"/>");	 	   //ComponentsFormTools.init();	});	</script>	</body>
<!-- END BODY -->
</html>