/**   
* @Title: RegistrationController.java 
* @Package com.uswop.action 
*
* @Description: TODO
* 
* @date Sep 10, 2014 3:06:32 PM
* @version V1.0   
*/ 
package com.nuvomed.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.nuvomed.commons.ConvertTools;
import com.nuvomed.commons.MyException;
import com.nuvomed.commons.SystemConstants;
import com.nuvomed.core.SameUrlData;
import com.nuvomed.dto.TadminInfo;
import com.nuvomed.dto.TadminUser;
import com.nuvomed.dto.TuserInfo;
import com.nuvomed.model.DataTableParamter;
import com.nuvomed.model.PagingData;
import com.nuvomed.service.AdminInfoService;
import com.nuvomed.service.AdminJobService;
import com.nuvomed.service.UserInfoService;

/** 
 * @ClassName: RegistrationController 
 * @Description: 
 * @author Phills Li
 * @date Sep 10, 2014 3:06:32 PM 
 *  
 */
@Controller
public class RegistrationController extends BaseController {	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AdminJobService adminJobService;
	@Autowired
	private AdminInfoService adminInfoService;
	   
	/** 
	 * <p>Open the register page</p>
	 * @Title: reg 
	 * @return String
	 * @throws 
	 */     
    @RequestMapping(value="/reg",method=RequestMethod.GET)
	public ModelAndView reg() {
		ModelAndView mav=new ModelAndView();
		TuserInfo userInfo=new TuserInfo();		
		mav.addObject("userInfo", userInfo);
		mav.setViewName("frontend/registration");
		return mav;
	}
		
	
	/** 
	 * <p>User register</p>
	 * @Title: reg 
	 * @param request
	 * @param userInfo
	 * @return ModelAndView
	 * @throws 
	 */ 
	@RequestMapping(value="/reg",method=RequestMethod.POST)
	@SameUrlData
	public ModelAndView reg(HttpServletRequest request,TuserInfo userInfo){	
		ModelAndView mav=new ModelAndView();
		TadminUser tUser=getSessionUser(request);
		if(tUser!=null){			
			userInfo.setCreatedBy(tUser.getAdminId());			
		}
		
		if(userInfo.getFamilyBreastCancer().equals("1")){
			userInfo.setFamilyBreastCancer1(true);
		}
		else if(userInfo.getFamilyBreastCancer().equals("2")){
			userInfo.setFamilyBreastCancer2(true);
		}
		else if(userInfo.getFamilyBreastCancer().equals("3")){
			userInfo.setFamilyBreastCancer3(true);
		}
		
		if(userInfo.getFamilyUterineCancer().equals("1")){
			userInfo.setFamilyUterineCancer1(true);
		}
		else if(userInfo.getFamilyUterineCancer().equals("2")){
			userInfo.setFamilyUterineCancer2(true);
		}
		else if(userInfo.getFamilyUterineCancer().equals("3")){
			userInfo.setFamilyUterineCancer3(true);
		}
		
		if(userInfo.getFamilyCervicalCancer().equals("1")){
			userInfo.setFamilyCervicalCancer1(true);
		}
		else if(userInfo.getFamilyCervicalCancer().equals("2")){
			userInfo.setFamilyCervicalCancer2(true);
		}
		else if(userInfo.getFamilyCervicalCancer().equals("3")){
			userInfo.setFamilyCervicalCancer3(true);
		}
		
		if(userInfo.getFamilyOvarianCancer().equals("1")){
			userInfo.setFamilyOvarianCancer1(true);
		}
		else if(userInfo.getFamilyOvarianCancer().equals("2")){
			userInfo.setFamilyOvarianCancer2(true);
		}
		else if(userInfo.getFamilyOvarianCancer().equals("3")){
			userInfo.setFamilyOvarianCancer3(true);
		}
		
		if(userInfo.getComplaintsBreastImplantsMaterials().equals("1")){
			userInfo.setComplaintsBreastImplantsGel(true);
		}
		else if(userInfo.getComplaintsBreastImplantsMaterials().equals("2")){
			userInfo.setComplaintsBreastImplantsFat(true);
		}
		else if(userInfo.getComplaintsBreastImplantsMaterials().equals("3")){
			userInfo.setComplaintsBreastImplantsOthers(true);
		}
		
		if(!userInfo.getMensesLastMenstruationDay().isEmpty()&&!userInfo.getMensesLastMenstruationMonth().isEmpty()){
			userInfo.setMensesLastMenstruation(userInfo.getMensesLastMenstruationDay()+"/"+userInfo.getMensesLastMenstruationMonth());
		}
		
		if(userInfo.getObstetricLactation().equals("1")){
			userInfo.setObstetricLactationUnderMonth(true);
		}
		else if(userInfo.getObstetricLactation().equals("2")){
			userInfo.setObstetricLactationUnderYear(true);
		}
		else if(userInfo.getObstetricLactation().equals("3")){
			userInfo.setObstetricLactationOverYear(true);
		}
		
		if(userInfo.getAnamnesisBreastDiseases().equals("1")){
			userInfo.setAnamnesisBreastDiseasesTrauma(true);
		}
		else if(userInfo.getAnamnesisBreastDiseases().equals("2")){
			userInfo.setAnamnesisBreastDiseasesMastitis(true);
		}
		else if(userInfo.getAnamnesisBreastDiseases().equals("3")){
			userInfo.setAnamnesisBreastDiseasesFibrous(true);
		}
		else if(userInfo.getAnamnesisBreastDiseases().equals("4")){
			userInfo.setAnamnesisBreastDiseasesCysts(true);
		}
		else if(userInfo.getAnamnesisBreastDiseases().equals("5")){
			userInfo.setAnamnesisBreastDiseasesCancer(true);
		}
		
		if(userInfo.getAnamnesisUterusDiseases().equals("1")){
			userInfo.setAnamnesisUterusDiseasesInflammation(true);
		}
		else if(userInfo.getAnamnesisUterusDiseases().equals("2")){
			userInfo.setAnamnesisUterusDiseasesMyoma(true);
		}
		else if(userInfo.getAnamnesisUterusDiseases().equals("3")){
			userInfo.setAnamnesisUterusDiseasesCancer(true);
		}
		else if(userInfo.getAnamnesisUterusDiseases().equals("4")){
			userInfo.setAnamnesisUterusDiseasesEndometriosis(true);
		}
		
		if(userInfo.getAnamnesisOvaryDiseases().equals("1")){
			userInfo.setAnamnesisOvaryDiseasesInflammation(true);
		}
		else if(userInfo.getAnamnesisOvaryDiseases().equals("2")){
			userInfo.setAnamnesisOvaryDiseasesCyst(true);
		}
		else if(userInfo.getAnamnesisOvaryDiseases().equals("3")){
			userInfo.setAnamnesisOvaryDiseasesCancer(true);
		}
		else if(userInfo.getAnamnesisOvaryDiseases().equals("4")){
			userInfo.setAnamnesisOvaryDiseasesEndometriosis(true);
		}
		
		if(userInfo.getAnamnesisSomaticDiseases().equals("1")){
			userInfo.setAnamnesisSomaticDiseasesAdiposity(true);
		}
		else if(userInfo.getAnamnesisSomaticDiseases().equals("2")){
			userInfo.setAnamnesisSomaticDiseasesHypertension(true);
		}
		else if(userInfo.getAnamnesisSomaticDiseases().equals("3")){
			userInfo.setAnamnesisSomaticDiseasesDiabetes(true);
		}
		else if(userInfo.getAnamnesisSomaticDiseases().equals("4")){
			userInfo.setAnamnesisSomaticDiseasesThyroid(true);
		}
		
		if(userInfo.getExaminationsPalpationStatus().equals("1")){
			userInfo.setExaminationsPalpationNormal(true);
		}
		else if(userInfo.getExaminationsPalpationStatus().equals("2")){
			userInfo.setExaminationsPalpationAbnormal(true);
			userInfo.setExaminationsPalpationDiffuse(true);
		}
		else if(userInfo.getExaminationsPalpationStatus().equals("3")){
			userInfo.setExaminationsPalpationAbnormal(true);
			userInfo.setExaminationsPalpationFocal(true);
		}
		
		if(userInfo.getExaminationsUltrasoundStatus().equals("1")){
			userInfo.setExaminationsUltrasoundNormal(true);
		}
		else if(userInfo.getExaminationsUltrasoundStatus().equals("2")){
			userInfo.setExaminationsUltrasoundAbnormal(true);
			userInfo.setExaminationsUltrasoundDiffuse(true);
		}
		else if(userInfo.getExaminationsUltrasoundStatus().equals("3")){
			userInfo.setExaminationsUltrasoundAbnormal(true);
			userInfo.setExaminationsUltrasoundFocal(true);
		}
		
		if(userInfo.getExaminationsMammographyStatus().equals("1")){
			userInfo.setExaminationsMammographyNormal(true);
		}
		else if(userInfo.getExaminationsMammographyStatus().equals("2")){
			userInfo.setExaminationsMammographyAbnormal(true);
			userInfo.setExaminationsMammographyDiffuse(true);
		}
		else if(userInfo.getExaminationsMammographyStatus().equals("3")){
			userInfo.setExaminationsMammographyAbnormal(true);
			userInfo.setExaminationsMammographyFocal(true);
		}
		
		if(userInfo.getExaminationsBiopsyStatus().equals("1")){
			userInfo.setExaminationsBiopsyNormal(true);
		}
		else if(userInfo.getExaminationsBiopsyStatus().equals("2")){
			userInfo.setExaminationsBiopsyAbnormal(true);
			userInfo.setExaminationsBiopsyCancer(true);
		}
		else if(userInfo.getExaminationsBiopsyStatus().equals("3")){
			userInfo.setExaminationsBiopsyAbnormal(true);
			userInfo.setExaminationsBiopsyProliferation(true);
		}
		else if(userInfo.getExaminationsBiopsyStatus().equals("4")){
			userInfo.setExaminationsBiopsyAbnormal(true);
			userInfo.setExaminationsBiopsyDysplasia(true);
		}
		else if(userInfo.getExaminationsBiopsyStatus().equals("4")){
			userInfo.setExaminationsBiopsyAbnormal(true);
			userInfo.setExaminationsBiopsyPapilloma(true);
		}
		else if(userInfo.getExaminationsBiopsyStatus().equals("5")){
			userInfo.setExaminationsBiopsyAbnormal(true);
			userInfo.setExaminationsBiopsyFibroadenoma(true);
		}
		userInfo.setCreatedTime(System.currentTimeMillis());
		userInfoService.createUserInfo(userInfo);
		mav.addObject("userInfo", userInfo);
		mav.setViewName("frontend/regresult");
		
		return mav;
	}
	
	/** 
	 * <p>Open the register search page</p>
	 * @Title: regSearch 
	 * @return String
	 * @throws 
	 */     
    @RequestMapping(value="/regSearch",method=RequestMethod.GET)
	public ModelAndView regSearch(HttpServletRequest request) {
    	//String local=getLocale(request);
		ModelAndView mav=new ModelAndView();				
		TadminUser tUser=getSessionUser(request);
		if(tUser!=null){
			TuserInfo userInfo=new TuserInfo();		
			mav.addObject("userInfo", userInfo);
			mav.setViewName("frontend/regsearch");
		}
		else{
			tUser=new TadminUser();		
			mav.addObject("user", tUser);
			mav.setViewName("login");
			request.getSession().setAttribute(LOGIN_TO_URL, "/regSearch");			
		}
		return mav;
	}
    
    @RequestMapping(value="searchClientsList",method=RequestMethod.GET)
	@ResponseBody
	public String SearchClientsList(HttpServletRequest request,DataTableParamter dtp){
    	PagingData pagingData=new PagingData();    	
    	TadminUser tUser=getSessionUser(request);
		if(tUser!=null){
			pagingData=adminJobService.loadClientJobList(dtp, tUser);
			
			pagingData.setSEcho(dtp.sEcho);
			if(pagingData.getAaData()==null){
				Object[] objs=new Object[]{};
				pagingData.setAaData(objs);
			}
		}
								
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
	}   
    
    /** 
	 * <p>View User register</p>
	 * @Title: viewreg 
	 * @param request
	 * @param ids
	 * @return ModelAndView
	 * @throws 
	 */ 
	@RequestMapping(value="/viewreg",method=RequestMethod.GET)
	public ModelAndView viewreg(HttpServletRequest request){
		String id=request.getParameter("id");
		ModelAndView mav=new ModelAndView();
		TadminUser tUser=getSessionUser(request);
		if(tUser==null){			
			tUser=new TadminUser();		
			mav.addObject("user", tUser);
			mav.setViewName("login");
			request.getSession().setAttribute(LOGIN_TO_URL, "/regSearch");			
		}
		
		Integer userId=Integer.parseInt(id);
		TuserInfo userInfo=userInfoService.getUserInfoUserId(userId);
		if(userInfo!=null){
			if(userInfo.getFamilyBreastCancer1()!=null&&userInfo.getFamilyBreastCancer1()){			
				userInfo.setFamilyBreastCancer("1");
			}
			if(userInfo.getFamilyBreastCancer2()!=null&&userInfo.getFamilyBreastCancer2()){
				userInfo.setFamilyBreastCancer("2");
			}
			if(userInfo.getFamilyBreastCancer3()!=null&&userInfo.getFamilyBreastCancer3()){
				userInfo.setFamilyBreastCancer("3");
			}
			
			if(userInfo.getFamilyUterineCancer1()!=null&&userInfo.getFamilyUterineCancer1()){			
				userInfo.setFamilyUterineCancer("1");
			}
			if(userInfo.getFamilyUterineCancer2()!=null&&userInfo.getFamilyUterineCancer2()){
				userInfo.setFamilyUterineCancer("2");
			}
			if(userInfo.getFamilyUterineCancer3()!=null&&userInfo.getFamilyUterineCancer3()){
				userInfo.setFamilyUterineCancer("3");
			}
			
			if(userInfo.getFamilyCervicalCancer1()!=null&&userInfo.getFamilyCervicalCancer1()){			
				userInfo.setFamilyCervicalCancer("1");
			}
			if(userInfo.getFamilyCervicalCancer2()!=null&&userInfo.getFamilyCervicalCancer2()){
				userInfo.setFamilyCervicalCancer("2");
			}
			if(userInfo.getFamilyCervicalCancer3()!=null&&userInfo.getFamilyCervicalCancer3()){
				userInfo.setFamilyCervicalCancer("3");
			}
			
			if(userInfo.getFamilyOvarianCancer1()!=null&&userInfo.getFamilyOvarianCancer1()){			
				userInfo.setFamilyOvarianCancer("1");
			}
			if(userInfo.getFamilyOvarianCancer2()!=null&&userInfo.getFamilyOvarianCancer2()){
				userInfo.setFamilyOvarianCancer("2");
			}
			if(userInfo.getFamilyOvarianCancer3()!=null&&userInfo.getFamilyOvarianCancer3()){
				userInfo.setFamilyOvarianCancer("3");
			}
			
			if(userInfo.getComplaintsBreastImplantsGel()!=null&&userInfo.getComplaintsBreastImplantsGel()){			
				userInfo.setComplaintsBreastImplantsMaterials("1");
			}
			if(userInfo.getComplaintsBreastImplantsFat()!=null&&userInfo.getComplaintsBreastImplantsFat()){
				userInfo.setComplaintsBreastImplantsMaterials("2");
			}
			if(userInfo.getComplaintsBreastImplantsOthers()!=null&&userInfo.getComplaintsBreastImplantsOthers()){
				userInfo.setComplaintsBreastImplantsMaterials("3");
			}
			
			if(userInfo.getMensesLastMenstruation()!=null&&!userInfo.getMensesLastMenstruation().isEmpty()){
				String[] strArr=userInfo.getMensesLastMenstruation().split("/");
				if(strArr.length>0){
					userInfo.setMensesLastMenstruationDay(strArr[0]);					
				}
				if(strArr.length>1){
					userInfo.setMensesLastMenstruationMonth(strArr[1]);
				}
			}				
			
			if(userInfo.getObstetricLactationUnderMonth()!=null&&userInfo.getObstetricLactationUnderMonth()){			
				userInfo.setObstetricLactation("1");
			}
			if(userInfo.getObstetricLactationUnderYear()!=null&&userInfo.getObstetricLactationUnderYear()){
				userInfo.setObstetricLactation("2");
			}
			if(userInfo.getObstetricLactationOverYear()!=null&&userInfo.getObstetricLactationOverYear()){
				userInfo.setObstetricLactation("3");
			}
			
			if(userInfo.getAnamnesisBreastDiseasesTrauma()!=null&&userInfo.getAnamnesisBreastDiseasesTrauma()){			
				userInfo.setAnamnesisBreastDiseases("1");
			}
			if(userInfo.getAnamnesisBreastDiseasesMastitis()!=null&&userInfo.getAnamnesisBreastDiseasesMastitis()){
				userInfo.setAnamnesisBreastDiseases("2");
			}
			if(userInfo.getAnamnesisBreastDiseasesFibrous()!=null&&userInfo.getAnamnesisBreastDiseasesFibrous()){
				userInfo.setAnamnesisBreastDiseases("3");
			}
			if(userInfo.getAnamnesisBreastDiseasesCysts()!=null&&userInfo.getAnamnesisBreastDiseasesCysts()){
				userInfo.setAnamnesisBreastDiseases("4");
			}
			if(userInfo.getAnamnesisBreastDiseasesCancer()!=null&&userInfo.getAnamnesisBreastDiseasesCancer()){
				userInfo.setAnamnesisBreastDiseases("5");
			}
			
			if(userInfo.getAnamnesisUterusDiseasesInflammation()!=null&&userInfo.getAnamnesisUterusDiseasesInflammation()){			
				userInfo.setAnamnesisUterusDiseases("1");
			}
			if(userInfo.getAnamnesisUterusDiseasesMyoma()!=null&&userInfo.getAnamnesisUterusDiseasesMyoma()){
				userInfo.setAnamnesisUterusDiseases("2");
			}
			if(userInfo.getAnamnesisUterusDiseasesCancer()!=null&&userInfo.getAnamnesisUterusDiseasesCancer()){
				userInfo.setAnamnesisUterusDiseases("3");
			}
			if(userInfo.getAnamnesisUterusDiseasesEndometriosis()!=null&&userInfo.getAnamnesisUterusDiseasesEndometriosis()){
				userInfo.setAnamnesisUterusDiseases("4");
			}
			
			if(userInfo.getAnamnesisOvaryDiseasesInflammation()!=null&&userInfo.getAnamnesisOvaryDiseasesInflammation()){			
				userInfo.setAnamnesisOvaryDiseases("1");
			}
			if(userInfo.getAnamnesisOvaryDiseasesCyst()!=null&&userInfo.getAnamnesisOvaryDiseasesCyst()){
				userInfo.setAnamnesisOvaryDiseases("2");
			}
			if(userInfo.getAnamnesisOvaryDiseasesCancer()!=null&&userInfo.getAnamnesisOvaryDiseasesCancer()){
				userInfo.setAnamnesisOvaryDiseases("3");
			}
			if(userInfo.getAnamnesisOvaryDiseasesEndometriosis()!=null&&userInfo.getAnamnesisOvaryDiseasesEndometriosis()){
				userInfo.setAnamnesisOvaryDiseases("4");
			}
			
			if(userInfo.getAnamnesisSomaticDiseasesAdiposity()!=null&&userInfo.getAnamnesisSomaticDiseasesAdiposity()){			
				userInfo.setAnamnesisSomaticDiseases("1");
			}
			if(userInfo.getAnamnesisSomaticDiseasesHypertension()!=null&&userInfo.getAnamnesisSomaticDiseasesHypertension()){
				userInfo.setAnamnesisSomaticDiseases("2");
			}
			if(userInfo.getAnamnesisSomaticDiseasesDiabetes()!=null&&userInfo.getAnamnesisSomaticDiseasesDiabetes()){
				userInfo.setAnamnesisSomaticDiseases("3");
			}
			if(userInfo.getAnamnesisSomaticDiseasesThyroid()!=null&&userInfo.getAnamnesisSomaticDiseasesThyroid()){
				userInfo.setAnamnesisSomaticDiseases("4");
			}
			
			
			if(userInfo.getExaminationsPalpationNormal()!=null&&userInfo.getExaminationsPalpationNormal()){			
				userInfo.setExaminationsPalpationStatus("1");
			}
			if(userInfo.getExaminationsPalpationAbnormal()!=null&&userInfo.getExaminationsPalpationAbnormal()&&userInfo.getExaminationsPalpationDiffuse()!=null&&userInfo.getExaminationsPalpationDiffuse()){
				userInfo.setExaminationsPalpationStatus("2");
			}
			if(userInfo.getExaminationsPalpationAbnormal()!=null&&userInfo.getExaminationsPalpationAbnormal()&&userInfo.getExaminationsPalpationFocal()!=null&&userInfo.getExaminationsPalpationFocal()){
				userInfo.setExaminationsPalpationStatus("3");
			}
			
			if(userInfo.getExaminationsUltrasoundNormal()!=null&&userInfo.getExaminationsUltrasoundNormal()){			
				userInfo.setExaminationsUltrasoundStatus("1");
			}
			if(userInfo.getExaminationsUltrasoundAbnormal()!=null&&userInfo.getExaminationsUltrasoundAbnormal()&&userInfo.getExaminationsUltrasoundDiffuse()!=null&&userInfo.getExaminationsUltrasoundDiffuse()){
				userInfo.setExaminationsUltrasoundStatus("2");
			}
			if(userInfo.getExaminationsUltrasoundAbnormal()!=null&&userInfo.getExaminationsUltrasoundAbnormal()&&userInfo.getExaminationsUltrasoundFocal()!=null&&userInfo.getExaminationsUltrasoundFocal()){
				userInfo.setExaminationsUltrasoundStatus("3");
			}
			
			if(userInfo.getExaminationsMammographyNormal()!=null&&userInfo.getExaminationsMammographyNormal()){			
				userInfo.setExaminationsMammographyStatus("1");
			}
			if(userInfo.getExaminationsMammographyAbnormal()!=null&&userInfo.getExaminationsMammographyAbnormal()&&userInfo.getExaminationsMammographyDiffuse()!=null&&userInfo.getExaminationsMammographyDiffuse()){
				userInfo.setExaminationsMammographyStatus("2");
			}
			if(userInfo.getExaminationsMammographyAbnormal()!=null&&userInfo.getExaminationsMammographyAbnormal()&&userInfo.getExaminationsMammographyFocal()!=null&&userInfo.getExaminationsMammographyFocal()){
				userInfo.setExaminationsMammographyStatus("3");
			}
					
			if(userInfo.getExaminationsBiopsyNormal()!=null&&userInfo.getExaminationsBiopsyNormal()){			
				userInfo.setExaminationsBiopsyStatus("1");
			}
			if(userInfo.getExaminationsBiopsyAbnormal()!=null&&userInfo.getExaminationsBiopsyAbnormal()&&userInfo.getExaminationsBiopsyCancer()!=null&&userInfo.getExaminationsBiopsyCancer()){
				userInfo.setExaminationsBiopsyStatus("2");
			}
			if(userInfo.getExaminationsBiopsyAbnormal()!=null&&userInfo.getExaminationsBiopsyAbnormal()&&userInfo.getExaminationsBiopsyProliferation()!=null&&userInfo.getExaminationsBiopsyProliferation()){
				userInfo.setExaminationsBiopsyStatus("3");
			}
			if(userInfo.getExaminationsBiopsyAbnormal()!=null&&userInfo.getExaminationsBiopsyAbnormal()&&userInfo.getExaminationsBiopsyDysplasia()!=null&&userInfo.getExaminationsBiopsyDysplasia()){
				userInfo.setExaminationsBiopsyStatus("4");
			}
			if(userInfo.getExaminationsBiopsyAbnormal()!=null&&userInfo.getExaminationsBiopsyAbnormal()&&userInfo.getExaminationsBiopsyPapilloma()!=null&&userInfo.getExaminationsBiopsyPapilloma()){
				userInfo.setExaminationsBiopsyStatus("5");
			}
			if(userInfo.getExaminationsBiopsyAbnormal()!=null&&userInfo.getExaminationsBiopsyAbnormal()&&userInfo.getExaminationsBiopsyFibroadenoma()!=null&&userInfo.getExaminationsBiopsyFibroadenoma()){
				userInfo.setExaminationsBiopsyStatus("6");
			}
		}	
				
		Map<String, String> monthMap = new LinkedHashMap<String, String>();
		monthMap.put("", "");
		monthMap.put("1", "JAN");
		monthMap.put("2", "FEB");
		monthMap.put("3", "MAR");
		monthMap.put("4", "APR");
		monthMap.put("5", "MAY");
		monthMap.put("6", "JUN");
		monthMap.put("7", "JUL");
		monthMap.put("8", "AUG");
		monthMap.put("9", "SEP");
		monthMap.put("10", "OCT");
		monthMap.put("11", "NOV");
		monthMap.put("12", "DEC");
		
		mav.addObject("monthMap", monthMap);
		
		Map<String, String> dayMap = new LinkedHashMap<String, String>();
		dayMap.put("", "");
		for (int day = 1; day < 32; day++) {			
			dayMap.put(""+day, String.valueOf(day));			
		}
		
		mav.addObject("dayMap", dayMap);
		
		Map<String, String> termMap = new LinkedHashMap<String, String>();
		termMap.put("", "");
		for (int num = 1; num < 51; num++) {			
			termMap.put(""+num, String.valueOf(num));			
		}
		mav.addObject("termMap", termMap);
				
		Map<String, String> familyMap = new LinkedHashMap<String, String>();
		familyMap.put("0", "None");
		familyMap.put("1", "Mother, sister");
		familyMap.put("2", "Aunt, cousin");
		familyMap.put("3", "Grand Mother, grand aunt");
		mav.addObject("familyMap", familyMap);
		
		Map<String, String> degreeMap = new LinkedHashMap<String, String>();
		degreeMap.put("0", "");
		degreeMap.put("1", "1");
		degreeMap.put("2", "2");
		degreeMap.put("3", "3");
		degreeMap.put("4", "4");
		degreeMap.put("5", "5");
		mav.addObject("degreeMap", degreeMap);
		
		Map<String, String> materialsMap = new LinkedHashMap<String, String>();
		materialsMap.put("", "");
		materialsMap.put("1", "Cohesive Silicon Gel");
		materialsMap.put("2", "Autologous fat transfer");
		materialsMap.put("3", "Other");
		mav.addObject("materialsMap", materialsMap);
		
		Map<String, String> lactationMap = new LinkedHashMap<String, String>();
		lactationMap.put("0", "");
		lactationMap.put("1", "Under 1 month");
		lactationMap.put("2", "Under 1 year");
		lactationMap.put("3", "Over 1 year");		
		mav.addObject("lactationMap", lactationMap);
		
		Map<String, String> breastMap = new LinkedHashMap<String, String>();
		breastMap.put("0", "");
		breastMap.put("1", "Trauma");
		breastMap.put("2", "Mastitis");
		breastMap.put("3", "Fibrous-cystic mastopathy");
		breastMap.put("4", "cysts");
		breastMap.put("5", "Cancer");
		mav.addObject("breastMap", breastMap);
		
		Map<String, String> uterusMap = new LinkedHashMap<String, String>();
		uterusMap.put("0", "");
		uterusMap.put("1", "Inflammation");
		uterusMap.put("2", "Myoma");
		uterusMap.put("3", "Cancer");
		uterusMap.put("4", "Endometriosis");		
		mav.addObject("uterusMap", uterusMap);
		
		Map<String, String> ovaryMap = new LinkedHashMap<String, String>();
		ovaryMap.put("0", "");
		ovaryMap.put("1", "Inflammation");
		ovaryMap.put("2", "cysts");
		ovaryMap.put("3", "Cancer");
		ovaryMap.put("4", "Endometriosis");		
		mav.addObject("ovaryMap", ovaryMap);
		
		Map<String, String> somaticMap = new LinkedHashMap<String, String>();
		somaticMap.put("0", "");
		somaticMap.put("1", "Adiposity");
		somaticMap.put("2", "Essential hypertension");
		somaticMap.put("3", "Diabetes");
		somaticMap.put("4", "Thyroid gland diseases");		
		mav.addObject("somaticMap", somaticMap);
		
		
		Map<String, String> examinationsMap = new LinkedHashMap<String, String>();
		examinationsMap.put("0", "");
		examinationsMap.put("1", "Normal");
		examinationsMap.put("2", "Diffuse");
		examinationsMap.put("3", "Focal");			
		mav.addObject("examinationsMap", examinationsMap);
		
		Map<String, String> biopsyMap = new LinkedHashMap<String, String>();
		biopsyMap.put("0", "");
		biopsyMap.put("1", "Normal");
		biopsyMap.put("2", "Cancer");
		biopsyMap.put("3", "Proliferation");
		biopsyMap.put("4", "Dysplasia");
		biopsyMap.put("5", "Intraductal papilloma");	
		biopsyMap.put("6", "Fibroadenoma");	
		mav.addObject("biopsyMap", biopsyMap);
		
		
		mav.addObject("userInfo", userInfo);
		mav.setViewName("frontend/regagain");
		
		return mav;
	}
	
	/** 
	 * <p>Open the userinfo page</p>
	 * @Title: userinfo 
	 * @return String
	 * @throws 
	 */     
    @RequestMapping(value="/userinfo",method=RequestMethod.GET)
	public ModelAndView userinfo(HttpServletRequest request) {
    	ModelAndView mav=new ModelAndView();
		TadminInfo adminInfo=new TadminInfo();
		try{            
            TadminUser adminUser=getSessionUser(request);
    		if(adminUser==null){			
    			adminUser=new TadminUser();		
    			mav.addObject("user", adminUser);
    			mav.setViewName("login");
    			request.getSession().setAttribute(LOGIN_TO_URL, "/userinfo");
    			return mav;
    		}            
	        
			String adminId=adminUser.getAdminId();
			
			adminInfo = adminInfoService.getAdminInfoById(adminId);
			
			if(adminInfo == null){
				adminInfo = new TadminInfo();
				adminInfo.setAdminId(adminId);
				adminInfo.setGender(true);
				adminInfoService.createAdminInfo(adminInfo);
			}
			if(adminInfo.getGender()==null){
				adminInfo.setGender(true);
			}
			adminInfo.setEmail(adminUser.getEmail());
	        
        }catch(MyException be){
        	
        }
		mav.addObject("adminInfo", adminInfo);
		mav.setViewName("frontend/userinfo");
		return mav;
	}
    
    @RequestMapping(value="/chageAvatar",method=RequestMethod.POST)
	public String changeAvatar(HttpServletRequest request,@RequestParam(value = "avatar", required = false) MultipartFile file) throws IOException{		
		InputStream inputStream = file.getInputStream();
		byte [] avatar=ConvertTools.toByteArray(inputStream);
		try{
			TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
			if(adminUser!=null){				
				String adminId=adminUser.getAdminId();
				TadminInfo adminInfo=adminInfoService.getAdminInfoById(adminId);
				if(adminInfo!=null){
					adminInfo.setAvatar(avatar);			
					adminInfoService.updateAdminInfoAvatar(adminInfo);
				}
				else{
					adminInfo=new TadminInfo();	
					adminInfo.setAdminId(adminUser.getAdminId());
					adminInfo.setAvatar(avatar);
					adminInfoService.createAdminInfo(adminInfo);
				}
			}		
		}catch(MyException be){
			
		}		
		return "redirect:/userinfo"; 
	   }
    
    /** 
	 * <p></p>
	 * @Title: logout 
	 * @param session
	 * @return String
	 * @throws 
	 */ 
	@RequestMapping(value="/regLogout")
	public String logout(HttpSession session){		
		if((TadminUser)session.getAttribute(SystemConstants.LOGINED)!=null){		
		}
		session.removeAttribute(SystemConstants.LOGINED);			
		return "redirect:http://www.meikasia.com";
	}
		
}
