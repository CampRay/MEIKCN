<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>Client Search</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="<c:url value="/"/>assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/"/>assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/"/>assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/"/>assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/"/>assets/global/css/components.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/"/>assets/global/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/"/>assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css" />
<link id="style_color" href="<c:url value="/"/>assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/"/>assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/"/>assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css"/>
<!-- BEGIN THEME STYLES -->
<link href="<c:url value="/"/>static/css/frontend.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/admin/pages/css/profile.css" rel="stylesheet" type="text/css"/>

<!-- END THEME STYLES -->
<link rel="shortcut icon" href="<c:url value="/"/>static/images/favicon.ico"/>
</head>
<body>
<div class="header">
	<div class="container">
		<div class="dropdown_box">
			<ul>
			<li><a href="<c:url value="/"/>settings/locale?locale=en_US">English</a></li>
			<li><a href="<c:url value="/"/>settings/locale?locale=zh_CN">繁體中文</a></li>
			</ul>
		</div>
		<div class="reg_box">			
			<c:if test="${sessionScope.Logined==null}">
			<a href="<c:url value="/"/>regSearch">Login</a>			
			</c:if>
			<c:if test="${sessionScope.Logined!=null}">
			<!-- <a href="<c:url value="/"/>regSearch">Re-registration</a> -->			
			<a href="<c:url value="/"/>regLogout">Logout</a>
			</c:if>
		</div>
		<div class="share-icons">
			<a class="share-facebook" href="https://www.facebook.com/MEIK-1636495473315579/"></a>
			<a class="share-twitter" href="https://twitter.com/MEIK_Asia"></a>
			<a class="share-google" href="https://plus.google.com/collection/4jbkoB"></a>
			<a class="share-instagram" href="https://www.instagram.com/meik_asia/"></a>
		</div>
		<div class="clearfix"></div>
		<div class="header_top">
			<div class="logo">
				<a href="http://www.meikasia.com/"><img src="<c:url value="/"/>static/images/frontend_logo.png" alt=""></a>
			</div>
			<!-- 
			<div class="search">
				<form class="form-inline" name="searchFormHeader" action="<cms:link>/en/search/</cms:link>" method="post">
					<div class="input-group animated fadeInDown" id="searchContentHeader">
						<input type="text" name="q" class="form-control search-input" placeholder="Search" id="searchWidgetAutoCompleteHeader" />
						<span class="input-group-btn">
							<button class="btn-u" type="button" onclick="this.form.submit(); return false;">&nbsp;&nbsp;</button>
						</span>
					</div>
				</form>
			</div>
			 -->			
			<div class="clearfix"> </div>
			<!----//End-top-nav---->
		</div>
		<!-- 
		<div class="menu">
			<a class="toggleMenu" href="#"><img src="<c:url value="/"/>static/images/nav_icon.png" alt=""> </a>
			<ul class="nav" id="navigation">		
				<li>
					<a href="http://www.meikasia.com/"><span>Home</span></a>
					</li><li class="drop">
					<a href="#"><span>Solutions</span></a>
					<ul><li>
					<a href="http://www.meikasia.com/site/en/solutions/overview/"><span>Overview</span></a>
					</li><li>
					<a href="http://www.meikasia.com//site/en/solutions/highlights/"><span>Highlights</span></a>
					</li><li>
					<a href="http://www.meikasia.com//site/en/solutions/theory-of-operation/"><span>Theory of Operation</span></a>
					</li><li>
					<a href="http://www.meikasia.com//site/en/solutions/screening-process/"><span>Screening Process</span></a>
					</li><li>
					<a href="http://www.meikasia.com//site/en/solutions/interpretation-analysis/"><span>Interpretation &amp; Analysis</span></a>
					</li><li>
					<a href="http://www.meikasia.com//site/en/solutions/device-chracteristics/"><span>Device Chracteristics</span></a>
					</li><li>
					<a href="http://www.meikasia.com//site/en/solutions/the-future/"><span>The Future</span></a>
					</li></ul></li><li>
					<a href="http://www.meikasia.com//site/en/health-info/"><span>Health Info</span></a>
					</li><li class="drop">
					<a href="#"><span>Knowledge</span></a>
					<ul><li>
					<a href="http://www.meikasia.com//site/en/knowledge/index.html"><span>F.A.Q</span></a>
					</li><li>
					<a href="http://www.meikasia.com//site/en/knowledge/download.html"><span>Download</span></a>
					</li></ul></li><li>
					<a href="http://www.meikasia.com//site/en/contact-us/"><span>Contact Us</span></a>
				</li>
			</ul>
		</div>
		 -->
	</div>
</div>

<div class="registration">


	<div class="content">
				
		<!-- BEGIN PAGE CONTENT-->
		<div class="row profile">
		  <div class="col-md-12">
			<div class="portlet light">
			   <div class="col-md-11">
			     <div class="portlet-title tabbable-line">
				     <ul class="nav nav-tabs" style="text-align: center;">
				        <li class="active">
						   <a href="#tab_1_1" data-toggle="tab">
						   <s:message code="userprofile.PersonalInfo"/></a>
					    </li>
					   <li>
						 <a href="#tab_1_2" data-toggle="tab">
						  <s:message code="userprofile.ChangeAvatar"/> </a>
					   </li>
					   <li>
						  <a href="#tab_1_3" data-toggle="tab">
						   <s:message code="userprofile.ChangePassword"/></a>
					   </li>
					   </ul>
				  </div>
				</div>
				<div class="portlet-body">
				  <div class="tab-content">
					<div class="tab-pane active" id="tab_1_1">
						<div class="row">
						    
							<div class="col-md-3">
								<ul class="list-unstyled profile-nav">
									<li>
									    <img alt="100%x250" class="img-responsive"  src="<%=request.getContextPath()%>/userprofile/getAvatar" style="max-height: 250px; max-width: 250px;">
										<!--<img alt="" class="img-responsive" src="<c:url value="/"/>assets/admin/pages/media/profile/profile-img.png">-->
										  <!--<a class="profile-edit" href="#tab_1_2" data-toggle="tab">
										edit </a>-->
									</li>
									
								</ul>
							</div>
							<div class="col-md-8">
								<div id="tab_1-1" class="tab-pane active portlet-body form">
								
										 <form:form  role="form" action="" commandName="adminInfo" id="editUserProfile">
										    <div class="alert alert-danger display-hide">
							                <button class="close" data-close="alert"></button>
							                <s:message code="system.management.user.adduser.message"/>
						                    </div>
						                    <div id="editFormMsg"></div>
											<div class="form-group">
												<label class="control-label"><s:message code="userprofile.FirstName"/></label>
												<form:input type="text" placeholder="Enter your First Name" class="form-control" path="firstName"/>
											</div>
											<div class="form-group">
												<label class="control-label"><s:message code="userprofile.LastName"/></label>
												<form:input type="text" placeholder="Enter your Last Name" class="form-control" path="lastName"/>
											</div>
											<div class="form-group">
												<label class="control-label"><s:message code="userprofile.Gender"/></label>
												<div style="display:inline-block;">
									                <form:radiobutton  path="gender" value="true" /><s:message code="userprofile.male"/>
									                <form:radiobutton  path="gender" value="false" /><s:message code="userprofile.female"/>
								                </div>
											</div>
											
											<div class="form-group">
												<label class="control-label"><s:message code="userprofile.MobileNumber"/></label>
												<form:input type="text" placeholder="Mobile Number" class="form-control" path="mobile"/>
											</div>
											<div class="form-group">
												<label class="control-label"><s:message code="system.management.user.searchform.email"/></label>
												<form:input type="text" placeholder="Email" class="form-control" path="email"/>
											</div>
											<div class="form-group">
												<label class="control-label"><s:message code="userprofile.Birthday"/></label>
	                                                     <div data-date-format="yyyy-mm-dd" class="input-group date date-picker">
										           <form:input type="text" path="birthday" readonly="true" class="form-control"/>
										           <span class="input-group-btn">
										           <button type="button" class="btn default"><i class="fa fa-calendar"></i></button>
										           </span>
									            </div>
									        </div>
							
											
											 <div class="form-group">
												<label class="control-label"><s:message code="userprofile.About"/></label>
												<form:textarea class="form-control" rows="3" placeholder="About you !!!!!" path="about"></form:textarea>
											 </div>
											<div class="form-group">
												<label class="control-label"><s:message code="userprofile.notify"/></label>
												
												<form:checkbox path="notify"/>
											</div>
											<div class="margiv-top-10">
												<input type="submit" class="btn green" value="Save Changes" >
												<input type="button" class="btn green" value="Cancel " onclick="location.href='<c:url value="/"/>regSearch';">												
		                                    </div>
										</form:form>
											
									</div>
							</div>
						</div>
					</div>
					<!--tab_1_2-->
					<div class="tab-pane" id="tab_1_2">
						<div class="row profile-account">
						          <div class="col-sm-2">
						          </div>
						          <div class="col-sm-9">						                 
										<form action="chageAvatar" role="form" enctype="multipart/form-data" method="post">
											<div class="form-group">
												<div class="fileinput fileinput-new" data-provides="fileinput">
													<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
														<img src="<c:url value="/"/>static/images/noimage.png" alt=""/>
													</div>
													<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
													</div>
													<div>
														<span class="btn default btn-file">
														<span class="fileinput-new">
														Select image </span>
														<span class="fileinput-exists">
														Change </span>
														<input type="file" name="avatar" accept="image/*">
														</span>
														<a href="#" class="btn default fileinput-exists" data-dismiss="fileinput">
														Remove </a>
														
														<div class="clearfix margin-top-10">
													       <span class="label label-danger">
													       NOTE! </span>
													     <span>
													     <s:message code="userprofile.changeavate.note"/> </span>
												        </div>
												        
														<div class="margin-top-10">
												          <input type="submit" class="btn green fileinput-exists" value="Change Avatar" class="form-control"/>
											            </div>
													</div>
												</div>
												
											
											</div>
										</form>
									</div>
								</div>
							</div>
					<!--end tab-1-2-->
					
					<!-- tab-1-3 -->
					<div class="tab-pane" id="tab_1_3">
						<div class="row">
						    <div class="col-sm-2">
						    
						    </div>
							<div class="col-sm-4">
							        <div class="portlet-body form">
							            <div id="changePasswordMsg"></div>
										<form action="" id="changePasswordForm" method="post" name="changePasswordForm">
										    <div class="form-group">
												<label class="control-label"><s:message code="userprofile.CurrentPassword"/></label>
												<input type="password" class="form-control" name="oldpassword"/>
											</div>
											<div class="form-group">
												<label class="control-label"><s:message code="userprofile.NewPassword"/></label>
												<input type="password" class="form-control" name="newpassword" id="newpassword"/>
											</div>
											<div class="form-group">
												<label class="control-label"><s:message code="userprofile.Re-typeNewPassword"/></label>
												<input type="password" class="form-control" name="renewpassword"/>
											</div>
											<div class="margin-top-10">
												<input type="submit" class="btn green" value="Change Password" class="form-control"/>
												<input type="reset" class="btn green" value="Reset" class="form-control"/>
											</div>
										</form>
										</div>
							</div>
						</div>
					</div>
					<!--end tab-1-3 -->
				</div>
				</div>
				</div>
			</div>
		</div>
		<!-- END PAGE CONTENT -->
		
	</div>
</div>

<div class="footer text-center">
   	<div class="container">
   		<span>Copyright &copy; 2017 MEIK Asia. All rights reserved. </span>
   	</div>
</div>

<!--[if lt IE 9]>
<script src="assets/global/plugins/respond.min.js"></script>
<script src="assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="<c:url value="/"/>assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="<c:url value="/"/>assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<script src="<c:url value="/"/>assets/global/plugins/jquery-i18n/jquery.i18n.properties-1.0.9.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/json/json2.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="<c:url value="/"/>assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script>

<script src="<c:url value="/"/>assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<c:url value="/"/>static/js/UserProfile.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->

<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {   
	var isShowMenu=false;
	$(".toggleMenu").click( function () { 
		if(isShowMenu){
			$("#navigation").hide();
			isShowMenu=false;
		}
		else{
			$("#navigation").show();
			isShowMenu=true;
		}
	});
	UserProfile.init("<c:url value="/"/>");
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>