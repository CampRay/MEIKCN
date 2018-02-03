<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%
int roleId=((com.nuvomed.dto.TadminUser)session.getAttribute("Logined")).getAdminRole().getRoleId(); 
%>
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
<!-- BEGIN THEME STYLES -->
<link href="<c:url value="/"/>static/css/frontend.css" rel="stylesheet" type="text/css"/>
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
	<c:if test="${sessionScope.Logined!=null&&sessionScope.Logined.adminRole.roleId==4}">	
		<h3>Personal Information</a></h3>		
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Client Id:</label>
					<span>${sessionScope.Logined.adminId}</span>
				</div>
				<div class="col-6">			
					<label class="form-label">Date of birth:</label>					
					<span>${sessionScope.Logined.adminInfo.birthday}</span>										
				</div>
				
			</div>
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Last name:</label>
					<span>${sessionScope.Logined.adminInfo.lastName}</span>
				</div>
				<div class="col-6">			
					<label class="form-label">First name:</label>
					<span>${sessionScope.Logined.adminInfo.firstName}</span>
				</div>
			</div>
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Middle name:</label>
					<span></span>
				</div>
				<div class="col-6">			
					<label class="form-label">Mobile Number:</label>
					<span>${sessionScope.Logined.adminInfo.mobile}</span>
				</div>			
			</div>
			<div  class="form-row">					
				<div class="col-6">			
					<label class="form-label">Email:</label>
					<span>${sessionScope.Logined.email}</span>
				</div>
				<div class="col-6 form-center">					
					<button type="button" class="search-btn" id="editBtn" onclick="location.href='<c:url value="/"/>userinfo';">Edit</button>
					<button type="button" class="search-btn" id="regBtn">Register</button>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</c:if>
	
	<c:if test="${sessionScope.Logined!=null&&sessionScope.Logined.adminRole.roleId!=4}">
	<!-- BEGIN LOGIN FORM -->
	<form:form action="reg" method="post" commandName="searchForm">				
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Client Id:</label>
					<input name="cid" type="text" class="form-text">
				</div>
				<div class="col-6">			
					<label class="form-label">Date of birth:</label>					
					<input id="birthday" name="birthday" type="tel" data-date-format="yyyy/mm/dd" class="form-date date-picker"/>										
				</div>
				
			</div>
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Last name:</label>
					<input name="lastName" type="text" class="form-text"/>
				</div>
				<div class="col-6">			
					<label class="form-label">First name:</label>
					<input name="firstName" type="text" class="form-text"/>
				</div>
			</div>
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Middle name:</label>
					<input name="otherName" type="text" class="form-text"/>
				</div>
				<div class="col-6">			
					<label class="form-label">Mobile Number:</label>
					<input name="mobile" type="text" class="form-text"/>
				</div>			
			</div>
			<div  class="form-row">					
				<div class="col-6">			
					<label class="form-label">Email:</label>
					<input name="email" type="text" class="form-text"/>
				</div>
				<div class="col-6 form-center">					
					<button type="submit" class="search-btn" id="searchBtn">Search</button>
					<button type="reset" class="search-btn" id="resetBtn">Reset</button>
					<button type="button" class="search-btn" id="newregBtn" onclick="location.href='<c:url value="/"/>reg';">Register</button>					
				</div>
			</div>
			<div class="clear"></div>
		</div>										
	</form:form>
	<!-- END register FORM -->
	</c:if>
	
	<!-- BEGIN PAGE CONTENT-->
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet box blue" style="color:black;border: 1px solid #325963;">
				<div class="portlet-title" style="background-color: #325963;">
					<div class="caption">
						<i class="fa fa-edit"></i>
						<s:message code="client.search.tablename" />
					</div>					
				</div>
				<div class="portlet-body">
					<table class="table table-striped table-hover table-bordered" id="search_table">
						<thead>
							<tr>	
								<th><s:message code="client.search.id" /></th>																																											
								<th><s:message code="client.search.code" /></th>
								<th><s:message code="client.search.clientname" /></th>
								<th><s:message code="client.search.birthday" /></th>
								<th><s:message code="client.search.mobile" /></th>
								<th><s:message code="client.search.email" /></th>
								<th><s:message code="client.search.created.time" /></th>
								<th><s:message code="client.search.status" /></th>								
								<th><s:message code="all.table.action" /></th>																			
							</tr>
						</thead>
	
					</table>
				</div>
			</div>
			<!-- END EXAMPLE TABLE PORTLET-->
		</div>
	</div>
	<!-- END PAGE CONTENT -->
		
</div>
<!-- END LOGIN -->


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
<script src="<c:url value="/"/>assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/jquery-i18n/jquery.i18n.properties-1.0.9.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/json/json2.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="<c:url value="/"/>assets/global/plugins/datatables/media/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="<c:url value="/"/>static/js/common.js"></script>
<script src="<c:url value="/"/>static/js/regsearchTableData.js"></script>
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
	
	SearchTable.init("<c:url value="/"/>","${sessionScope.locale}");
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>