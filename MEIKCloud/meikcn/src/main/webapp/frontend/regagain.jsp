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
<title>Registration Form</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="<c:url value="/"/>assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" type="text/css"/>

<!-- BEGIN THEME STYLES -->
<link href="<c:url value="/"/>static/css/registration.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="<c:url value="/"/>static/images/favicon.ico"/>
</head>
<body class="registration">
<!-- BEGIN LOGO -->
<div class="logo">	
	<div>	
		<div class="col-6">			
			<h1>Registration Form</h1>
		</div>
		<div class="col-6">			
			<a href="http://www.meikasia.com"><img src="<c:url value="/"/>static/images/MEIKlogo.png" alt=""/></a>
			<h3 style="float:right;clear:both;">Rapid Breast Health Screening</h3>	
		</div>
		<div class="clear"></div>
	</div>	
	
</div>
<!-- END LOGO -->

<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form:form action="reg" method="post" commandName="userInfo">			
		<h3 class="form-title">Session Number: ${userInfo.infoId}</h3>
		<br/>		
		<h3 class="form-title">1. PERSONAL DATA</h3>		
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Last name:</label>
					<form:input path="lastName" cssClass="form-text" placeholder="" autocomplete="off"/>					
				</div>
				<div class="col-6">			
					<label class="form-label">First name:</label>
					<form:input path="firstName" cssClass="form-text" placeholder="" autocomplete="off"/>					
				</div>
			</div>
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Date of birth:</label>
					<form:input path="birthday" cssClass="form-date date-picker" placeholder="" autocomplete="off"  data-date-format="yyyy/mm/dd" id="birthday"/>																				
				</div>
				<div class="col-3">			
					<label class="form-label" >Height:</label>
					<form:input path="height" cssClass="form-text width-60" placeholder="" autocomplete="off" type="tel"/>					
					<span>cm</span>
				</div>
				<div class="col-3">			
					<label class="form-label">Weight:</label>
					<form:input path="weight" cssClass="form-text width-60" placeholder="" autocomplete="off" type="tel"/>					
					<span>kg</span>					
				</div>
			</div>
			<div class="form-row">	
				<div class="col-12">	
					<label class="form-label">Address:</label>
					<form:input path="address" cssClass="form-text" placeholder="" autocomplete="off"/>					
				</div>			
			</div>
			<div  class="form-row">	
				<div class="col-6">			
					<label class="form-label">Mobile number:</label>
					<form:input path="mobile" cssClass="form-text" placeholder="" autocomplete="off" type="tel"/>					
				</div>
				<div class="col-6">			
					<label class="form-label">Email:</label>
					<form:input path="email" cssClass="form-text" placeholder="" autocomplete="off"/>					
				</div>
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">2. PERTINENT FAMILY HISTORY (BLOOD RELATED)</h3>	
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Breast Cancer:</label>
					<form:select path="familyBreastCancer" cssClass="form-select" items="${familyMap}" />										
				</div>
				<div class="col-6">			
					<label class="form-label">Uterine Cancer:</label>
					<form:select path="familyUterineCancer" cssClass="form-select" items="${familyMap}" />
				</div>
			</div>
			<div  class="form-row">	
				<div class="col-6">			
					<label class="form-label">Cervical Cancer:</label>
					<form:select path="familyCervicalCancer" cssClass="form-select" items="${familyMap}" />					
				</div>
				<div class="col-6">			
					<label class="form-label">Ovarian Cancer:</label>
					<form:select path="familyOvarianCancer" cssClass="form-select" items="${familyMap}" />					
				</div>
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">3. CURRENT BREAST HEALTH CONDITION</h3>	
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">
					<div class="col-12">
						<form:checkbox path="complaintsPalpableLumps"  id="complaintsPalpableLumps" cssClass="form-checkbox"/>	
						<span> Palpayble lumps, position:</span>		
												
					</div>
					<div class="col-12">
						<form:checkbox path="complaintsPain" cssClass="form-checkbox"/>			
						<span> Pain/Discomfort, degree ( 1 Mild - 5Severe)</span>
						<form:select path="complaintsDegree" cssClass="form-select width-50" items="${degreeMap}">
							
						</form:select>						
					</div>
					<div class="col-12">
						<form:checkbox path="complaintsColostrum" cssClass="form-checkbox"/>			
						<span> Colostrum (within 5 days after delivery)</span>
					</div>
					<div class="col-12">	
						<form:checkbox path="complaintsSerous" cssClass="form-checkbox"/>		
						<span> Serous discharge (yellowish)</span>
					</div>
					<div class="col-12">	
						<form:checkbox path="complaintsBlood" cssClass="form-checkbox"/>		
						<span> Blood discharge</span>
					</div>
					<br/>		
				</div>
				<div class="col-6">	
					<form:hidden path="complaintsLumpsLeftPosition" id="complaintsLumpsLeftPosition"/>
					<form:hidden path="complaintsLumpsRightPosition" id="complaintsLumpsRightPosition"/>					
					<table style="width:450px;border:0;">
						<tr>
							<td width="50%" align="center">Right Breast</td>
							<td width="50%" align="center">Left Breast</td>
						</tr>
						<tr>
							<td height="190" align="center" class="breast-icon">															
								<div id="leftBreastIcon1" data="1" class="breast-icon_1 leftBreastDiv"></div>
								<div id="leftBreastIcon2" data="2" class="breast-icon_2 leftBreastDiv"></div>
								<div id="leftBreastIcon3" data="4" class="breast-icon_3 leftBreastDiv"></div>
								<div id="leftBreastIcon4" data="8" class="breast-icon_4 leftBreastDiv"></div>
								<div id="leftBreastIcon5" data="16" class="breast-icon_5 leftBreastDiv"></div>
								<div id="leftBreastIcon6" data="32" class="breast-icon_6 leftBreastDiv"></div>
								<div id="leftBreastIcon7" data="64" class="breast-icon_7 leftBreastDiv"></div>
								<div id="leftBreastIcon8" data="128" class="breast-icon_8 leftBreastDiv"></div>
								<div id="leftBreastIcon9" data="256" class="breast-icon_9 leftBreastDiv"></div>
								<div id="leftBreastIcon10" data="512" class="breast-icon_10 leftBreastDiv"></div>
								<div id="leftBreastIcon11" data="1024" class="breast-icon_11 leftBreastDiv"></div>
								<div id="leftBreastIcon12" data="2048" class="breast-icon_12 leftBreastDiv"></div>
								<img src="<c:url value="/"/>static/images/empty.png" width="180px" border="0" height="180px" usemap="#leftbreastmap" style="position:absolute;top:3px;left:23px;"/>	
								<map name="leftbreastmap" id="leftbreastmap">								  
								  <area shape="poly" coords="113,16,147,35,94,90" data="1" alt="1" status="0"/>
								  <area shape="poly" coords="147,35,167,69,94,91" data="2" alt="2" status="0"/>
								  <area shape="poly" coords="167,70,167,110,94,91" data="4" alt="3" status="0"/>
								  <area shape="poly" coords="167,110,147,144,94,92" data="8" alt="4" status="0"/>
								  <area shape="poly" coords="147,144,113,163,94,92" data="16" alt="5" status="0"/>
								  <area shape="poly" coords="113,163,73,163,93,90" data="32" alt="6" status="0"/>
								  <area shape="poly" coords="73,163,39,144,93,91" data="64" alt="7" status="0"/>
								  <area shape="poly" coords="39,144,19,110,93,90" data="128" alt="8" status="0"/>
								  <area shape="poly" coords="19,110,19,70,92,90" data="256" alt="9" status="0"/>
								  <area shape="poly" coords="19,70,39,35,92,90" data="512" alt="10" status="0"/>
								  <area shape="poly" coords="39,35,73,16,93,90" data="1024" alt="11" status="0"/>
								  <area shape="poly" coords="73,16,113,16,93,90" data="2048" alt="12" status="0"/>
								</map>
							</td>
							<td height="190" align="center" class="breast-icon">
								<div id="rightBreastIcon1" data="1" class="breast-icon_1"></div>
								<div id="rightBreastIcon2" data="2" class="breast-icon_2"></div>
								<div id="rightBreastIcon3" data="4" class="breast-icon_3"></div>
								<div id="rightBreastIcon4" data="8" class="breast-icon_4"></div>
								<div id="rightBreastIcon5" data="16" class="breast-icon_5"></div>
								<div id="rightBreastIcon6" data="32" class="breast-icon_6"></div>
								<div id="rightBreastIcon7" data="64" class="breast-icon_7"></div>
								<div id="rightBreastIcon8" data="128" class="breast-icon_8"></div>
								<div id="rightBreastIcon9" data="256" class="breast-icon_9"></div>
								<div id="rightBreastIcon10" data="512" class="breast-icon_10"></div>
								<div id="rightBreastIcon11" data="1024" class="breast-icon_11"></div>
								<div id="rightBreastIcon12" data="2048" class="breast-icon_12"></div>
								<img src="<c:url value="/"/>static/images/empty.png" width="180px" border="0" height="180px" usemap="#rightbreastmap" style="position:absolute;top:3px;left:23px;"/>	
								<map name="rightbreastmap" id="rightbreastmap">
								  <area shape="poly" coords="113,16,147,35,94,90" data="1" alt="1" status="0"/>
								  <area shape="poly" coords="147,35,167,69,94,91" data="2" alt="2" status="0"/>
								  <area shape="poly" coords="167,70,167,110,94,91" data="4" alt="3" status="0"/>
								  <area shape="poly" coords="167,110,147,144,94,92" data="8" alt="4" status="0"/>
								  <area shape="poly" coords="147,144,113,163,94,92" data="16" alt="5" status="0"/>
								  <area shape="poly" coords="113,163,73,163,93,90" data="32" alt="6" status="0"/>
								  <area shape="poly" coords="73,163,39,144,93,91" data="64" alt="7" status="0"/>
								  <area shape="poly" coords="39,144,19,110,93,90" data="128" alt="8" status="0"/>
								  <area shape="poly" coords="19,110,19,70,92,90" data="256" alt="9" status="0"/>
								  <area shape="poly" coords="19,70,39,35,92,90" data="512" alt="10" status="0"/>
								  <area shape="poly" coords="39,35,73,16,93,90" data="1024" alt="11" status="0"/>
								  <area shape="poly" coords="73,16,113,16,93,90" data="2048" alt="12" status="0"/>
								</map>
							</td>
						</tr>												
					</table>										
				</div>				
			</div>
			
			<div  class="form-row">
				<div class="col-12" style="border-bottom:1px solid white;">			
					Special Conditions					
				</div>				
			</div>
			<div  class="form-row">
				<div class="col-12">
					<form:checkbox path="complaintsPregnancy" cssClass="form-checkbox"/>			
					<span> Pregnancy, duration: </span>
					<form:select path="complaintsPregnancyTerm" cssClass="form-select width-60" items="${termMap}" />
					<span> weeks</span>					
				</div>				
			</div>
			<div  class="form-row">
				<div class="col-12">
					<form:checkbox path="complaintsLactation" cssClass="form-checkbox"/>			
					<span> Lactation: </span>
					<form:select path="complaintsLactationTerm" cssClass="form-select width-60" items="${termMap}" />					
					<span> weeks</span>					
				</div>				
			</div>
			<div  class="form-row">
				<div class="col-2">		
					<form:checkbox path="complaintsBreastImplants" cssClass="form-checkbox"/>	
					<span> Breast Implants: </span>	
				</div>	
				<div class="col-3">	
					<form:checkbox path="complaintsBreastImplantsLeft" cssClass="form-checkbox"/><span> Right Breast, year</span>
					<form:input path="complaintsBreastImplantsLeftYear" cssClass="form-text width-46" maxlength="4"/>
					
				</div>	
				<div class="col-3">
					<form:checkbox path="complaintsBreastImplantsRight" cssClass="form-checkbox"/><span> Left Breast, year</span>
					<form:input path="complaintsBreastImplantsRightYear" cssClass="form-text width-46" maxlength="4"/>	
					
				</div>	
				<div class="col-4">	
					<span>Materials:</span>
					<form:select path="complaintsBreastImplantsMaterials" cssClass="form-select width-200" items="${materialsMap}">					
										
					</form:select>			
				</div>				
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">4. MENSTRUAL CONDITION</h3>	
		<div class="form-group">
			<div class="form-row">	
				<div class="col-full">			
					<span>First day of last mensruation period:</span>
					<form:select path="mensesLastMenstruationDay" cssClass="form-select width-60" items="${dayMap}" />					
					<form:select path="mensesLastMenstruationMonth" cssClass="form-select width-100" items="${monthMap}" />					
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">
					<form:checkbox path="mensesCycleDisorder" cssClass="form-checkbox"/>			
					<span> Menstrual cycle disorder</span>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">
					<form:checkbox path="mensesPostmenopause" cssClass="form-checkbox"/><span> Postmenopause:</span>
					<form:input path="mensesPostmenopauseYear" cssClass="form-text width-50"/><span> years</span>			
					
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-8">	
					<form:checkbox path="mensesHormonalContraceptives" cssClass="form-checkbox"/><span> Hormonal contraceptives, brand name </span>
					<form:input path="mensesHormonalContraceptivesName" cssClass="form-text width-240"/>		
												
				</div>	
				<div class="col-4">						
					<span> Administration period: </span>
					<form:input path="mensesHormonalContraceptivesPeriod" cssClass="form-text width-50"/>					
					<span>months</span>
				</div>				
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">5. OBSTETRIC</h3>	
		<div class="form-group">			
			<div  class="form-row">	
				<div class="col-full">	
					<form:checkbox path="obstetricInfertility" cssClass="form-checkbox"/>		
					<span> Infertility</span>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">	
					<form:checkbox path="obstetricAbortions" cssClass="form-checkbox"/><span> Abortion / Miscarriage(s) </span>
					<form:input path="obstetricAbortionsTimes" cssClass="form-text width-50"/><span> times</span>								
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-4">		
					<form:checkbox path="obstetricBirths" cssClass="form-checkbox"/><span> Birth(s) </span>
					<form:input path="obstetricBirthsTimes" cssClass="form-text width-50"/><span> times</span>	
					
				</div>
				<div class="col-8">
					<span>Lactation(Past History) </span>
					<form:select path="obstetricLactation" cssClass="form-select width-200"  items="${lactationMap}"></form:select>
				</div>				
			</div>
			
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">6. ANAMNESIS</h3>	
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Breast diseases:</label>
					<form:select path="anamnesisBreastDiseases" cssClass="form-select" items="${breastMap}">
						
					</form:select>		
				</div>
				<div class="col-6">			
					<label class="form-label">Uterus diseases:</label>
					<form:select path="anamnesisUterusDiseases" cssClass="form-select" items="${uterusMap}">
											
					</form:select>
				</div>
			</div>
			<div  class="form-row">	
				<div class="col-6">			
					<label class="form-label">Ovary diseases:</label>
					<form:select path="anamnesisOvaryDiseases" cssClass="form-select" items="${ovaryMap}">
						
					</form:select>
				</div>
				<div class="col-6">			
					<label class="form-label">Somatic diseases:</label>
					<form:select path="anamnesisSomaticDiseases" cssClass="form-select" items="${somaticMap}">
						
					</form:select>	
				</div>
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">7. LAST BREAST EXAMINATION</h3>	
		<div class="form-group">			
			<div  class="form-row">	
				<div class="col-full">	
					<form:checkbox path="examinationsPalpation" cssClass="form-checkbox"/>						
					<span style="display:inline-block;width:120px;"> Palpation</span>
					<span>year</span>
					<form:input path="examinationsPalpationYear" cssClass="form-text width-60" placeholder="" autocomplete="off"/>					
					&nbsp;&nbsp;&nbsp;&nbsp;
					<span>Results:</span>
					<form:select path="examinationsPalpationStatus" cssClass="form-select width-150" items="${examinationsMap}">
						
					</form:select>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">	
					<form:checkbox path="examinationsUltrasound" cssClass="form-checkbox"/>								
					<span style="display:inline-block;width:120px;"> Ultrasound</span>
					<span>year</span>
					<form:input path="examinationsUltrasoundYear" cssClass="form-text width-60" placeholder="" autocomplete="off"/>					
					&nbsp;&nbsp;&nbsp;&nbsp;
					<span>Results:</span>
					<form:select path="examinationsUltrasoundStatus" cssClass="form-select width-150" items="${examinationsMap}">
						
					</form:select>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">	
					<form:checkbox path="examinationsMammography" cssClass="form-checkbox"/>							
					<span style="display:inline-block;width:120px;"> Mammography</span>
					<span>year</span>
					<form:input path="examinationsMammographyYear" cssClass="form-text width-60" placeholder="" autocomplete="off"/>					
					&nbsp;&nbsp;&nbsp;&nbsp;
					<span>Results:</span>
					<form:select path="examinationsMammographyStatus" cssClass="form-select width-150" items="${examinationsMap}">
						
					</form:select>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">			
					<form:checkbox path="examinationsBiopsy" cssClass="form-checkbox"/>							
					<span style="display:inline-block;width:120px;"> Biopsy</span>
					<span>year</span>
					<form:input path="examinationsBiopsyYear" cssClass="form-text width-60" placeholder="" autocomplete="off"/>					
					&nbsp;&nbsp;&nbsp;&nbsp;
					<span>Results:</span>
					<form:select path="examinationsBiopsyStatus" class="form-select width-200" items="${biopsyMap}">
						
					</form:select>
				</div>				
			</div>
			<div class="clear"></div>
		</div>
		<div class="form-agree">
			<form:checkbox path="regAgree" cssClass="form-checkbox" id="regAgree"/>			
			<span> I am fully aware that the results from  this screening are merely intended for use by trained 
			healthcare providers to assist evaluation. I further understand that the results are not intended as 
			confirmation of the current status of my health condition. I hereby agree not to hold the company or its agent liable for any loss, damages, or injury arising from any inaccuracy or loss of data/report or for any other reason whatsoever. 
			</span>
		</div>
		<div class="form-actions">			
			<button type="button" class="form-btn" id="backBtn">Back</button>
			<button type="submit" class="form-btn">Submit</button>
		</div>
		<div class="form-mark">
			*Information provided will be kept strictly confidential
		</div>		
						
	</form:form>
	<!-- END register FORM -->
		
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
<script src="<c:url value="/"/>assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="<c:url value="/"/>assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="<c:url value="/"/>assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<c:url value="/"/>assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<c:url value="/"/>assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<c:url value="/"/>static/js/registration.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {  
		
	if($("#complaintsPalpableLumps").is(":checked"))
    {
		var leftPosition=parseInt($("#complaintsLumpsLeftPosition").val());
		var rightPosition=parseInt($("#complaintsLumpsRightPosition").val());
		$.each($("#leftbreastmap area[status='0']"), function(i, n){
			if((leftPosition&parseInt($(n).attr("data")))>0){
				$(n).trigger("click");
			}
		});
		$.each($("#rightbreastmap area[status='0']"), function(i, n){
			if((rightPosition&parseInt($(n).attr("data")))>0){
				$(n).trigger("click");
			}			
		});				        
    }	
	$("#backBtn").click( function () { location.href="<c:url value="/"/>regSearch";});
		
	register.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>