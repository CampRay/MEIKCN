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
<title>Registration Result</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="<c:url value="/"/>assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" type="text/css"/>

<!-- BEGIN THEME STYLES -->
<link href="static/css/registration.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="static/images/favicon.ico"/>
</head>
<body class="registration">
<!-- BEGIN LOGO -->
<div class="logo">	
	<div>	
		<div class="col-6">			
			<h1>Registration Result</h1>
		</div>
		<div class="col-6">			
			<a href="http://www.meikasia.com"><img src="<c:url value="/"/>static/images/MEIKlogo.png" alt=""/></a>
			<h3 style="float:right;clear:both;">Rapid Breast Health Screening</h3>	
		</div>
	</div>
	
</div>
<!-- END LOGO -->

<div class="content">		
	<h3 class="form-title">Screening Reservation Order:</h3>		
	<div class="print-group" id="printArea">
		<div class="form-row">	
			<div class="col-full">			
				<h1 class="form-center">Welcome to MEIK Asia</h1>					
			</div>				
		</div>
		<div class="form-row">	
			<div class="col-full">			
				<label class="print-label">Your session number:</label>	
				<span class="print-value">${userInfo['infoId']}</span>																		
			</div>													
		</div>
		<div class="form-row">	
			<div class="col-full">	
				<h4 class="form-center">Please give this session number to technician when you begin to do screening.</h4>					
			</div>			
		</div>
		<div  class="form-row">	
			<div class="col-12">	
				<h4 class="form-center">${userInfo['createdTimeStr']}</h4>					
			</div>	
		</div>
		<div class="clear"></div>
	</div>
					
	<div class="form-actions">			
		<button type="button" class="form-btn" id="backBtn" onclick="location.href='<c:url value="/"/>reg';">Back</button>
		<button type="button" class="form-btn" onclick="printPage();">Print</button>
	</div>
	<div class="form-mark">
		*Please print or remember your session number that is a reservation order for MEIK Screening. 
	</div>										
		
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
	 Copyright &copy; 2017 MEIK Asia. All rights reserved.
</div>
<!-- END COPYRIGHT -->

<!--[if lt IE 9]>
<script src="assets/global/plugins/respond.min.js"></script>
<script src="assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="assets/admin/layout/scripts/layout.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>
	function printPage()
	{
	document.body.innerHTML=document.getElementById('printArea').outerHTML;
	window.print();
	}
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>