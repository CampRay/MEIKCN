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
	</div>
	
</div>
<!-- END LOGO -->

<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form:form action="reg" method="post" commandName="userInfo">	
		<h3 class="form-title">1. PERSONAL DATA</h3>		
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Last name:</label>
					<input name="lastName" type="text" class="form-text">
				</div>
				<div class="col-6">			
					<label class="form-label">First name:</label>
					<input name="firstName" type="text" class="form-text"/>
				</div>
			</div>
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Date of birth:</label>					
					<input id="birthday" name="birthday" type="tel" data-date-format="yyyy/mm/dd" class="form-date date-picker"/>										
				</div>
				<div class="col-3">			
					<label class="form-label" >Height:</label>
					<input name="height" type="tel" class="form-text width-60"/>
					<span>cm</span>
				</div>
				<div class="col-3">			
					<label class="form-label">Weight:</label>
					<input name="weight" type="tel" class="form-text width-60"/>
					<span>kg</span>					
				</div>
			</div>
			<div class="form-row">	
				<div class="col-12">	
					<label class="form-label">Address:</label>
					<input name="address" type="text" class="form-text">
				</div>			
			</div>
			<div  class="form-row">	
				<div class="col-6">			
					<label class="form-label">Mobile number:</label>
					<input name="mobile" type="tel" class="form-text">
				</div>
				<div class="col-6">			
					<label class="form-label">Email:</label>
					<input name="email" type="text" class="form-text"/>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">2. PERTINENT FAMILY HISTORY (BLOOD RELATED)</h3>	
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Breast Cancer:</label>
					<select name="familyBreastCancer" class="form-select">
						<option value="0">None</option>
						<option value="1">Mother, sister</option>
						<option value="2">Aunt, cousin</option>
						<option value="3">Grand Mother, grand aunt</option>						
					</select>		
				</div>
				<div class="col-6">			
					<label class="form-label">Uterine Cancer:</label>
					<select name="familyUterineCancer" class="form-select">
						<option value="0">None</option>
						<option value="1">Mother, sister</option>
						<option value="2">Aunt, cousin</option>
						<option value="3">Grand Mother, grand aunt</option>	
					</select>
				</div>
			</div>
			<div  class="form-row">	
				<div class="col-6">			
					<label class="form-label">Cervical Cancer:</label>
					<select name="familyCervicalCancer" class="form-select">
						<option value="0">None</option>
						<option value="1">Mother, sister</option>
						<option value="2">Aunt, cousin</option>
						<option value="3">Grand Mother, grand aunt</option>	
					</select>
				</div>
				<div class="col-6">			
					<label class="form-label">Ovarian Cancer:</label>
					<select name="familyOvarianCancer" class="form-select">
						<option value="0">None</option>
						<option value="1">Mother, sister</option>
						<option value="2">Aunt, cousin</option>
						<option value="3">Grand Mother, grand aunt</option>	
					</select>	
				</div>
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">3. CURRENT BREAST HEALTH CONDITION</h3>	
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">
					<div class="col-12">			
						<input id="complaintsPalpableLumps" name="complaintsPalpableLumps" type="checkbox" class="form-checkbox"/><span> Palpayble lumps, position:</span>						
					</div>
					<div class="col-12">			
						<input name="complaintsPain" type="checkbox" class="form-checkbox"/><span> Pain/Discomfort, degree ( 1 Mild - 5Severe)</span>
						<select name="complaintsDegree" class="form-select width-50">
							<option value="0"></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</div>
					<div class="col-12">			
						<input name="complaintsColostrum" type="checkbox" class="form-checkbox"/><span> Colostrum (within 5 days after delivery)</span>
					</div>
					<div class="col-12">			
						<input name="complaintsSerous" type="checkbox" class="form-checkbox"/><span> Serous discharge (yellowish)</span>
					</div>
					<div class="col-12">			
						<input name="complaintsBlood" type="checkbox" class="form-checkbox"/><span> Blood discharge</span>
					</div>
					<br/>		
				</div>
				<div class="col-6">	
					<input id="complaintsLumpsLeftPosition" name="complaintsLumpsLeftPosition" type="hidden" value="0"/>
					<input id="complaintsLumpsRightPosition" name="complaintsLumpsRightPosition" type="hidden" value="0"/>
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
					<input name="complaintsPregnancy" type="checkbox" class="form-checkbox"/><span> Pregnancy, duration: </span>
					<select name="complaintsPregnancyTerm" class="form-select width-60">
							<option value=""></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
							<option value="32">32</option>
							<option value="33">33</option>
							<option value="34">34</option>
							<option value="35">35</option>
							<option value="36">36</option>
							<option value="37">37</option>
							<option value="38">38</option>
							<option value="39">39</option>
							<option value="40">40</option>
							<option value="41">41</option>
							<option value="42">42</option>
							<option value="43">43</option>
							<option value="44">44</option>
							<option value="45">45</option>
							<option value="46">46</option>
							<option value="47">47</option>
							<option value="48">48</option>
							<option value="49">49</option>
							<option value="50">50</option>
						</select>
					<span> weeks</span>					
				</div>				
			</div>
			<div  class="form-row">
				<div class="col-12">			
					<input name="complaintsLactation" type="checkbox" class="form-checkbox"/><span> Lactation: </span>
					<select name="complaintsLactationTerm" class="form-select width-60">
							<option value=""></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
							<option value="32">32</option>
							<option value="33">33</option>
							<option value="34">34</option>
							<option value="35">35</option>
							<option value="36">36</option>
							<option value="37">37</option>
							<option value="38">38</option>
							<option value="39">39</option>
							<option value="40">40</option>
							<option value="41">41</option>
							<option value="42">42</option>
							<option value="43">43</option>
							<option value="44">44</option>
							<option value="45">45</option>
							<option value="46">46</option>
							<option value="47">47</option>
							<option value="48">48</option>
							<option value="49">49</option>
							<option value="50">50</option>
						</select>
						<span> weeks</span>					
				</div>				
			</div>
			<div  class="form-row">
				<div class="col-2">			
					<input name="complaintsBreastImplants" type="checkbox" class="form-checkbox"/><span> Breast Implants: </span>	
				</div>	
				<div class="col-3">	
					<input name="complaintsBreastImplantsLeft" type="checkbox" class="form-checkbox"/><span> Right Breast, year</span>
					<input name="complaintsBreastImplantsLeftYear" type="text" class="form-text width-46" maxlength="4"/>
				</div>	
				<div class="col-3">	
					<input name="complaintsBreastImplantsRight" type="checkbox" class="form-checkbox"/><span> Left Breast, year</span>	
					<input name="complaintsBreastImplantsRightYear" type="text" class="form-text width-46" maxlength="4"/>
				</div>	
				<div class="col-4">	
					<span>Materials:</span>
					<select name="complaintsBreastImplantsMaterials" class="form-select width-200">
						<option value=""></option>
						<option value="1">Cohesive Silicon Gel</option>
						<option value="2">Autologous fat transfer</option>
						<option value="3">Other</option>						
					</select>			
				</div>				
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">4. MENSTRUAL CONDITION</h3>	
		<div class="form-group">
			<div class="form-row">	
				<div class="col-full">			
					<span>First day of last mensruation period:</span>
					<select name="mensesLastMenstruationDay" class="form-select width-60">
						<option value=""></option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
					</select>	
					<select name="mensesLastMenstruationMonth" class="form-select width-100">
						<option value=""></option>
						<option value="1">JAN</option>
						<option value="2">FEB</option>
						<option value="3">MAR</option>
						<option value="4">APR</option>
						<option value="5">MAY</option>
						<option value="6">JUN</option>
						<option value="7">JUL</option>
						<option value="8">AUG</option>
						<option value="9">SEP</option>
						<option value="10">OCT</option>
						<option value="11">NOV</option>
						<option value="12">DEC</option>						
					</select>	
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">			
					<input name="mensesCycleDisorder" type="checkbox" class="form-checkbox"/><span> Menstrual cycle disorder</span>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">			
					<input name="mensesPostmenopause" type="checkbox" class="form-checkbox"/><span> Postmenopause: </span>
					<input name="mensesPostmenopauseYear" type="text"  class="form-text width-50"/><span> years</span>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-8">			
					<input name="mensesHormonalContraceptives" type="checkbox" class="form-checkbox"/><span> Hormonal contraceptives, brand name </span>
					<input name="mensesHormonalContraceptivesName" type="text"  class="form-text width-240"/>
				</div>	
				<div class="col-4">						
					<span> Administration period: </span>
					<input name="mensesHormonalContraceptivesPeriod" type="text"  class="form-text width-50"/>
					<span>months</span>
				</div>				
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">5. OBSTETRIC</h3>	
		<div class="form-group">			
			<div  class="form-row">	
				<div class="col-full">			
					<input name="obstetricInfertility" type="checkbox" class="form-checkbox"/><span> Infertility</span>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">			
					<input name="obstetricAbortions" type="checkbox" class="form-checkbox"/><span> Abortion / Miscarriage(s) </span>
					<input name="obstetricAbortionsTimes" type="text"  class="form-text width-50"/><span> times</span>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-4">			
					<input name="obstetricBirths" type="checkbox" class="form-checkbox"/><span> Birth(s) </span>
					<input name="obstetricBirthsTimes" type="text"  class="form-text width-50"/><span> times</span>
				</div>
				<div class="col-8">
					<span>Lactation(Past History) </span>
					<select name="obstetricLactation" class="form-select width-200">
						<option value="0"></option>
						<option value="1">Under 1 month</option>
						<option value="2">Under 1 year</option>
						<option value="3">Over 1 year</option>										
					</select>
				</div>				
			</div>
			
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">6. ANAMNESIS</h3>	
		<div class="form-group">
			<div class="form-row">	
				<div class="col-6">			
					<label class="form-label">Breast diseases:</label>
					<select name="anamnesisBreastDiseases" class="form-select">
						<option value="0"></option>
						<option value="1">Trauma</option>
						<option value="2">Mastitis</option>
						<option value="3">Fibrous-cystic mastopathy</option>
						<option value="4">cysts</option>
						<option value="5">Cancer</option>
					</select>		
				</div>
				<div class="col-6">			
					<label class="form-label">Uterus diseases:</label>
					<select name="anamnesisUterusDiseases" class="form-select">
						<option value="0"></option>
						<option value="1">Inflammation</option>						
						<option value="2">Myoma</option>
						<option value="3">Cancer</option>
						<option value="4">Endometriosis</option>						
					</select>
				</div>
			</div>
			<div  class="form-row">	
				<div class="col-6">			
					<label class="form-label">Ovary diseases:</label>
					<select name="anamnesisOvaryDiseases" class="form-select">
						<option value="0"></option>
						<option value="1">Inflammation</option>						
						<option value="2">cysts</option>
						<option value="3">Cancer</option>
						<option value="4">Endometriosis</option>
					</select>
				</div>
				<div class="col-6">			
					<label class="form-label">Somatic diseases:</label>
					<select name="anamnesisSomaticDiseases" class="form-select">
						<option value="0"></option>
						<option value="1">Adiposity</option>						
						<option value="2">Essential hypertension</option>
						<option value="3">Diabetes</option>
						<option value="4">Thyroid gland diseases</option>
					</select>	
				</div>
			</div>
			<div class="clear"></div>
		</div>
		
		<h3 class="form-title">7. LAST BREAST EXAMINATION</h3>	
		<div class="form-group">			
			<div  class="form-row">	
				<div class="col-full">		
					<input name="examinationsPalpation" type="checkbox" class="form-checkbox"/>
					<span style="display:inline-block;width:120px;"> Palpation</span>
					<span>year</span>
					<input name="examinationsPalpationYear" type="text"  class="form-text width-60"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<span>Results:</span>
					<select name="examinationsPalpationStatus" class="form-select width-150">
						<option value="0"></option>
						<option value="1">Normal</option>						
						<option value="2">Diffuse</option>
						<option value="3">Focal</option>
					</select>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">			
					<input name="examinationsUltrasound" type="checkbox" class="form-checkbox"/>
					<span style="display:inline-block;width:120px;"> Ultrasound</span>
					<span>year</span>
					<input name="examinationsUltrasoundYear" type="text"  class="form-text width-60"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<span>Results:</span>
					<select name="examinationsUltrasoundStatus" class="form-select width-150">
						<option value="0"></option>
						<option value="1">Normal</option>						
						<option value="2">Diffuse</option>
						<option value="3">Focal</option>
					</select>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">			
					<input name="examinationsMammography" type="checkbox" class="form-checkbox"/>
					<span style="display:inline-block;width:120px;"> Mammography</span>
					<span>year</span>
					<input name="examinationsMammographyYear" type="text"  class="form-text width-60"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<span>Results:</span>
					<select name="examinationsMammographyStatus" class="form-select width-150">
						<option value="0"></option>
						<option value="1">Normal</option>						
						<option value="2">Diffuse</option>
						<option value="3">Focal</option>
					</select>
				</div>				
			</div>
			<div  class="form-row">	
				<div class="col-full">			
					<input name="examinationsBiopsy" type="checkbox" class="form-checkbox"/>
					<span style="display:inline-block;width:120px;"> Biopsy</span>
					<span>year</span>
					<input name="examinationsBiopsyYear" type="text"  class="form-text width-60"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<span>Results:</span>
					<select name="examinationsBiopsyStatus" class="form-select width-200">
						<option value="0"></option>
						<option value="1">Normal</option>						
						<option value="2">Cancer</option>
						<option value="3">Proliferation</option>
						<option value="4">Dysplasia</option>
						<option value="5">Intraductal papilloma</option>
						<option value="6">Fibroadenoma</option>
					</select>
				</div>				
			</div>
			<div class="clear"></div>
		</div>
		<div class="form-agree">
			<input id="regAgree" name="regAgree" type="checkbox" class="form-checkbox"/>
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
	$("#backBtn").click( function () { location.href="http://www.meikasia.com";});
	register.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>