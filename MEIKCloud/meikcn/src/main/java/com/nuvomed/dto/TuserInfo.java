package com.nuvomed.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the user_info database table.
 * 
 */
public class TuserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int infoId;
	
	//用户帐户ID
	private String cid;	
	
	private TadminUser adminUser;

	private int userId;
	
	private Long createdTime;
	
	private String createdTimeStr;

	private String createdBy;
	
	private String language;
	
	private String firstName;
	
	private String lastName;
	
	private String otherName;
	
	private String birthday;
	
	private String mobile;
	
	private String email;
	
	private String height;
	
	private String weight;
	
	private String address;
	
	private String address2;
	
	private String city;
	
	private String province;
	
	private String zipcode;
	
	private String country;
	
	private String familyBreastCancer;
	
	private Boolean familyBreastCancer1;
	
	private Boolean familyBreastCancer2;
	
	private Boolean familyBreastCancer3;
	
	private String familyBreastCancerDesc;
	
	private String familyUterineCancer;
	
	private Boolean familyUterineCancer1;
	
	private Boolean familyUterineCancer2;
	
	private Boolean familyUterineCancer3;
	
	private String familyUterineCancerDesc;
	
	private String familyCervicalCancer;
	
	private Boolean familyCervicalCancer1;
	
	private Boolean familyCervicalCancer2;
	
	private Boolean familyCervicalCancer3;	
	
	private String familyCervicalCancerDesc;
	
	private String familyOvarianCancer;
	
	private Boolean familyOvarianCancer1;
	
	private Boolean familyOvarianCancer2;
	
	private Boolean familyOvarianCancer3;	
	
	private String familyOvarianCancerDesc;
	
	private String familyCancerDesc;
	
	private Boolean complaintsPalpableLumps;	
	
	private Boolean complaintsPain;	
	
	private int complaintsDegree;
	
	private Boolean complaintsColostrum;	
	
	private Boolean complaintsSerous;	
	
	private Boolean complaintsBlood;	
	
	private int complaintsLumpsLeftPosition;
	
	private int complaintsLumpsRightPosition;
	
	private Boolean complaintsPregnancy;
	
	private String complaintsPregnancyTerm;
	
	private Boolean complaintsLactation;	
	
	private String complaintsLactationTerm;
	
	private Boolean complaintsBreastImplants;	
	
	private Boolean complaintsBreastImplantsLeft;
	
	private Boolean complaintsBreastImplantsRight;	
	
	private String complaintsBreastImplantsLeftYear;
	
	private String complaintsBreastImplantsRightYear;
	
	private String complaintsBreastImplantsMaterials;
	
	private Boolean complaintsBreastImplantsGel;	
	
	private Boolean complaintsBreastImplantsFat;	
	
	private Boolean complaintsBreastImplantsOthers;	
	
	private String complaintsOtherDesc;
	
	private String mensesLastMenstruationDay;
	
	private String mensesLastMenstruationMonth;
	
	private String mensesLastMenstruation;
	
	private Boolean mensesCycleDisorder;
	
	private String mensesCycleDisorderDesc;
	
	private Boolean mensesPostmenopause;
	
	private String mensesPostmenopauseYear;
	
	private Boolean mensesHormonalContraceptives;
	
	private String mensesHormonalContraceptivesName;
	
	private String mensesHormonalContraceptivesPeriod;
	
	private String mensesDesc;
	
	
	private Boolean obstetricInfertility;	
	
	private Boolean obstetricAbortions;	
	
	private String obstetricAbortionsTimes;
	
	private Boolean obstetricBirths;			
	
	private String obstetricBirthsTimes;
	
	private String obstetricLactation;
	
	private Boolean obstetricLactationUnderMonth;
	
	private Boolean obstetricLactationUnderYear;	
	
	private Boolean obstetricLactationOverYear;	
	
	private String obstetricDesc;
	
	private String anamnesisBreastDiseases;
	
	private Boolean anamnesisBreastDiseasesTrauma;			
			
	private Boolean anamnesisBreastDiseasesMastitis;
	
	private Boolean anamnesisBreastDiseasesFibrous;	
	
	private Boolean anamnesisBreastDiseasesCysts;
	
	private Boolean anamnesisBreastDiseasesCancer;
	
	private String anamnesisBreastDiseasesDesc;
	
	private String anamnesisOvaryDiseases;
	
	private Boolean anamnesisOvaryDiseasesInflammation;			
	
	private Boolean anamnesisOvaryDiseasesCyst;
	
	private Boolean anamnesisOvaryDiseasesCancer;	
	
	private Boolean anamnesisOvaryDiseasesEndometriosis;		
	
	private String anamnesisOvaryDiseasesDesc;
	
	private String anamnesisUterusDiseases;
	
	private Boolean anamnesisUterusDiseasesInflammation;			
	
	private Boolean anamnesisUterusDiseasesMyoma;
	
	private Boolean anamnesisUterusDiseasesCancer;	
	
	private Boolean anamnesisUterusDiseasesEndometriosis;		
	
	private String anamnesisUterusDiseasesDesc;
	
	private String anamnesisSomaticDiseases;
	
	private Boolean anamnesisSomaticDiseasesAdiposity;			
	
	private Boolean anamnesisSomaticDiseasesHypertension;
	
	private Boolean anamnesisSomaticDiseasesDiabetes;	
	
	private Boolean anamnesisSomaticDiseasesThyroid;		
	
	private String anamnesisSomaticDiseasesDesc;
	
	private String anamnesisDesc;
	
	
	private Boolean examinationsPalpation;	
	
	private String examinationsPalpationYear;
	
	private String examinationsPalpationStatus;
	
	private Boolean examinationsPalpationNormal;
	
	private Boolean examinationsPalpationAbnormal;	
	
	private Boolean examinationsPalpationDiffuse;
	
	private Boolean examinationsPalpationFocal;
	
	private String examinationsPalpationDesc;
	
	private Boolean examinationsUltrasound;	
	
	private String examinationsUltrasoundYear;
	
	private String examinationsUltrasoundStatus;
	
	private Boolean examinationsUltrasoundNormal;
	
	private Boolean examinationsUltrasoundAbnormal;	
	
	private Boolean examinationsUltrasoundDiffuse;
	
	private Boolean examinationsUltrasoundFocal;
	
	private String examinationsUltrasoundDesc;			

	private Boolean examinationsMammography;	
	
	private String examinationsMammographyYear;
	
	private String examinationsMammographyStatus;
	
	private Boolean examinationsMammographyNormal;
	
	private Boolean examinationsMammographyAbnormal;	
	
	private Boolean examinationsMammographyDiffuse;
	
	private Boolean examinationsMammographyFocal;
	
	private String examinationsMammographyDesc;	
	

	private Boolean examinationsBiopsy;	
	
	private String examinationsBiopsyYear;
	
	private String examinationsBiopsyStatus;
	
	private Boolean examinationsBiopsyNormal;
	
	private Boolean examinationsBiopsyAbnormal;	
	
	private Boolean examinationsBiopsyCancer;
	
	private Boolean examinationsBiopsyProliferation;
	
	private Boolean examinationsBiopsyDysplasia;
	
	private Boolean examinationsBiopsyPapilloma;
	
	private Boolean examinationsBiopsyFibroadenoma;
	
	private String examinationsBiopsyDesc;	
	
	
	private Boolean examinationsMeik;
	
	private String examinationsMeikYear;
	
	private String examinationsMeikPoint;	
	
	private String examinationsMeikDesc;	
	
	private String examinationsDesc;
	

	
	private Boolean visualSwollen;
	
	private int visualSwollenLeft;
	
	private int visualSwollenRight;
	
	private String visualSwollenDesc;
	
	private Boolean visualPalpable;
	
	private int visualPalpableLeft;
	
	private int visualPalpableRight;
	
	private String visualPalpableDesc;
	
	private Boolean visualSerous;
	
	private int visualSerousLeft;
	
	private int visualSerousRight;
	
	private String visualSerousDesc;
	
	
	private Boolean visualWounds;
	
	private int visualWoundsLeft;
	
	private int visualWoundsRight;
	
	private String visualWoundsDesc;
	
	private Boolean visualScars;
	
	private int visualScarsLeft;
	
	private int visualScarsRight;
	
	private String visualScarsDesc;
	
	private String visualDesc;
	
	private Boolean regAgree;
	
	private Tuser user;

	private String clientName;
	private String code;
	
	public TuserInfo() {
	}

	public String getClientName() {
		clientName=lastName;
		if(firstName!=null&&!firstName.isEmpty()){
			clientName=clientName+", "+firstName;
		}
		if(otherName!=null&&!otherName.isEmpty()){
			clientName=clientName+" "+otherName;
		}			
		return clientName;	
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}		
	
	public TadminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(TadminUser adminUser) {
		this.adminUser = adminUser;
	}

	public String getCode() {
		if(user!=null){
			this.code=user.getCode();
		}
		return this.code;
	}

	public void setCode(String code) {		
		this.code = code;
	}

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	
	public String getCid() {
		if(adminUser!=null){
			cid=adminUser.getAdminId();
		}
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public int getUserId() {
		if(user!=null){
			userId=user.getUserId();
		}
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getFamilyBreastCancer1() {
		return familyBreastCancer1;
	}

	public void setFamilyBreastCancer1(Boolean familyBreastCancer1) {
		this.familyBreastCancer1 = familyBreastCancer1;
	}

	public Boolean getFamilyBreastCancer2() {
		return familyBreastCancer2;
	}

	public void setFamilyBreastCancer2(Boolean familyBreastCancer2) {
		this.familyBreastCancer2 = familyBreastCancer2;
	}

	public Boolean getFamilyBreastCancer3() {
		return familyBreastCancer3;
	}

	public void setFamilyBreastCancer3(Boolean familyBreastCancer3) {
		this.familyBreastCancer3 = familyBreastCancer3;
	}

	public String getFamilyBreastCancerDesc() {
		return familyBreastCancerDesc;
	}

	public void setFamilyBreastCancerDesc(String familyBreastCancerDesc) {
		this.familyBreastCancerDesc = familyBreastCancerDesc;
	}

	public Boolean getFamilyUterineCancer1() {
		return familyUterineCancer1;
	}

	public void setFamilyUterineCancer1(Boolean familyUterineCancer1) {
		this.familyUterineCancer1 = familyUterineCancer1;
	}

	public Boolean getFamilyUterineCancer2() {
		return familyUterineCancer2;
	}

	public void setFamilyUterineCancer2(Boolean familyUterineCancer2) {
		this.familyUterineCancer2 = familyUterineCancer2;
	}

	public Boolean getFamilyUterineCancer3() {
		return familyUterineCancer3;
	}

	public void setFamilyUterineCancer3(Boolean familyUterineCancer3) {
		this.familyUterineCancer3 = familyUterineCancer3;
	}

	public String getFamilyUterineCancerDesc() {
		return familyUterineCancerDesc;
	}

	public void setFamilyUterineCancerDesc(String familyUterineCancerDesc) {
		this.familyUterineCancerDesc = familyUterineCancerDesc;
	}

	public Boolean getFamilyCervicalCancer1() {
		return familyCervicalCancer1;
	}

	public void setFamilyCervicalCancer1(Boolean familyCervicalCancer1) {
		this.familyCervicalCancer1 = familyCervicalCancer1;
	}

	public Boolean getFamilyCervicalCancer2() {
		return familyCervicalCancer2;
	}

	public void setFamilyCervicalCancer2(Boolean familyCervicalCancer2) {
		this.familyCervicalCancer2 = familyCervicalCancer2;
	}

	public Boolean getFamilyCervicalCancer3() {
		return familyCervicalCancer3;
	}

	public void setFamilyCervicalCancer3(Boolean familyCervicalCancer3) {
		this.familyCervicalCancer3 = familyCervicalCancer3;
	}

	public String getFamilyCervicalCancerDesc() {
		return familyCervicalCancerDesc;
	}

	public void setFamilyCervicalCancerDesc(String familyCervicalCancerDesc) {
		this.familyCervicalCancerDesc = familyCervicalCancerDesc;
	}

	public Boolean getFamilyOvarianCancer1() {
		return familyOvarianCancer1;
	}

	public void setFamilyOvarianCancer1(Boolean familyOvarianCancer1) {
		this.familyOvarianCancer1 = familyOvarianCancer1;
	}

	public Boolean getFamilyOvarianCancer2() {
		return familyOvarianCancer2;
	}

	public void setFamilyOvarianCancer2(Boolean familyOvarianCancer2) {
		this.familyOvarianCancer2 = familyOvarianCancer2;
	}

	public Boolean getFamilyOvarianCancer3() {
		return familyOvarianCancer3;
	}

	public void setFamilyOvarianCancer3(Boolean familyOvarianCancer3) {
		this.familyOvarianCancer3 = familyOvarianCancer3;
	}

	public String getFamilyOvarianCancerDesc() {
		return familyOvarianCancerDesc;
	}

	public void setFamilyOvarianCancerDesc(String familyOvarianCancerDesc) {
		this.familyOvarianCancerDesc = familyOvarianCancerDesc;
	}

	public String getFamilyCancerDesc() {
		return familyCancerDesc;
	}

	public void setFamilyCancerDesc(String familyCancerDesc) {
		this.familyCancerDesc = familyCancerDesc;
	}

	public Boolean getComplaintsPalpableLumps() {
		return complaintsPalpableLumps;
	}

	public void setComplaintsPalpableLumps(Boolean complaintsPalpableLumps) {
		this.complaintsPalpableLumps = complaintsPalpableLumps;
	}

	public Boolean getComplaintsPain() {
		return complaintsPain;
	}

	public void setComplaintsPain(Boolean complaintsPain) {
		this.complaintsPain = complaintsPain;
	}

	public int getComplaintsDegree() {
		return complaintsDegree;
	}

	public void setComplaintsDegree(int complaintsDegree) {
		this.complaintsDegree = complaintsDegree;
	}

	public Boolean getComplaintsColostrum() {
		return complaintsColostrum;
	}

	public void setComplaintsColostrum(Boolean complaintsColostrum) {
		this.complaintsColostrum = complaintsColostrum;
	}

	public Boolean getComplaintsSerous() {
		return complaintsSerous;
	}

	public void setComplaintsSerous(Boolean complaintsSerous) {
		this.complaintsSerous = complaintsSerous;
	}

	public Boolean getComplaintsBlood() {
		return complaintsBlood;
	}

	public void setComplaintsBlood(Boolean complaintsBlood) {
		this.complaintsBlood = complaintsBlood;
	}

	public int getComplaintsLumpsLeftPosition() {
		return complaintsLumpsLeftPosition;
	}

	public void setComplaintsLumpsLeftPosition(int complaintsLumpsLeftPosition) {
		this.complaintsLumpsLeftPosition = complaintsLumpsLeftPosition;
	}

	public int getComplaintsLumpsRightPosition() {
		return complaintsLumpsRightPosition;
	}

	public void setComplaintsLumpsRightPosition(int complaintsLumpsRightPosition) {
		this.complaintsLumpsRightPosition = complaintsLumpsRightPosition;
	}

	public Boolean getComplaintsPregnancy() {
		return complaintsPregnancy;
	}

	public void setComplaintsPregnancy(Boolean complaintsPregnancy) {
		this.complaintsPregnancy = complaintsPregnancy;
	}

	public String getComplaintsPregnancyTerm() {
		return complaintsPregnancyTerm;
	}

	public void setComplaintsPregnancyTerm(String complaintsPregnancyTerm) {
		this.complaintsPregnancyTerm = complaintsPregnancyTerm;
	}

	public Boolean getComplaintsLactation() {
		return complaintsLactation;
	}

	public void setComplaintsLactation(Boolean complaintsLactation) {
		this.complaintsLactation = complaintsLactation;
	}

	public String getComplaintsLactationTerm() {
		return complaintsLactationTerm;
	}

	public void setComplaintsLactationTerm(String complaintsLactationTerm) {
		this.complaintsLactationTerm = complaintsLactationTerm;
	}

	public Boolean getComplaintsBreastImplants() {
		return complaintsBreastImplants;
	}

	public void setComplaintsBreastImplants(Boolean complaintsBreastImplants) {
		this.complaintsBreastImplants = complaintsBreastImplants;
	}

	public Boolean getComplaintsBreastImplantsLeft() {
		return complaintsBreastImplantsLeft;
	}

	public void setComplaintsBreastImplantsLeft(Boolean complaintsBreastImplantsLeft) {
		this.complaintsBreastImplantsLeft = complaintsBreastImplantsLeft;
	}

	public Boolean getComplaintsBreastImplantsRight() {
		return complaintsBreastImplantsRight;
	}

	public void setComplaintsBreastImplantsRight(Boolean complaintsBreastImplantsRight) {
		this.complaintsBreastImplantsRight = complaintsBreastImplantsRight;
	}

	public String getComplaintsBreastImplantsLeftYear() {
		return complaintsBreastImplantsLeftYear;
	}

	public void setComplaintsBreastImplantsLeftYear(String complaintsBreastImplantsLeftYear) {
		this.complaintsBreastImplantsLeftYear = complaintsBreastImplantsLeftYear;
	}

	public String getComplaintsBreastImplantsRightYear() {
		return complaintsBreastImplantsRightYear;
	}

	public void setComplaintsBreastImplantsRightYear(String complaintsBreastImplantsRightYear) {
		this.complaintsBreastImplantsRightYear = complaintsBreastImplantsRightYear;
	}

	public Boolean getComplaintsBreastImplantsGel() {
		return complaintsBreastImplantsGel;
	}

	public void setComplaintsBreastImplantsGel(Boolean complaintsBreastImplantsGel) {
		this.complaintsBreastImplantsGel = complaintsBreastImplantsGel;
	}

	public Boolean getComplaintsBreastImplantsFat() {
		return complaintsBreastImplantsFat;
	}

	public void setComplaintsBreastImplantsFat(Boolean complaintsBreastImplantsFat) {
		this.complaintsBreastImplantsFat = complaintsBreastImplantsFat;
	}

	public Boolean getComplaintsBreastImplantsOthers() {
		return complaintsBreastImplantsOthers;
	}

	public void setComplaintsBreastImplantsOthers(Boolean complaintsBreastImplantsOthers) {
		this.complaintsBreastImplantsOthers = complaintsBreastImplantsOthers;
	}

	public String getComplaintsOtherDesc() {
		return complaintsOtherDesc;
	}

	public void setComplaintsOtherDesc(String complaintsOtherDesc) {
		this.complaintsOtherDesc = complaintsOtherDesc;
	}

	public String getMensesLastMenstruation() {
		return mensesLastMenstruation;
	}

	public void setMensesLastMenstruation(String mensesLastMenstruation) {
		this.mensesLastMenstruation = mensesLastMenstruation;
	}

	public Boolean getMensesCycleDisorder() {
		return mensesCycleDisorder;
	}

	public void setMensesCycleDisorder(Boolean mensesCycleDisorder) {
		this.mensesCycleDisorder = mensesCycleDisorder;
	}

	public String getMensesCycleDisorderDesc() {
		return mensesCycleDisorderDesc;
	}

	public void setMensesCycleDisorderDesc(String mensesCycleDisorderDesc) {
		this.mensesCycleDisorderDesc = mensesCycleDisorderDesc;
	}

	public Boolean getMensesPostmenopause() {
		return mensesPostmenopause;
	}

	public void setMensesPostmenopause(Boolean mensesPostmenopause) {
		this.mensesPostmenopause = mensesPostmenopause;
	}

	public String getMensesPostmenopauseYear() {
		return mensesPostmenopauseYear;
	}

	public void setMensesPostmenopauseYear(String mensesPostmenopauseYear) {
		this.mensesPostmenopauseYear = mensesPostmenopauseYear;
	}

	public Boolean getMensesHormonalContraceptives() {
		return mensesHormonalContraceptives;
	}

	public void setMensesHormonalContraceptives(Boolean mensesHormonalContraceptives) {
		this.mensesHormonalContraceptives = mensesHormonalContraceptives;
	}

	public String getMensesHormonalContraceptivesName() {
		return mensesHormonalContraceptivesName;
	}

	public void setMensesHormonalContraceptivesName(String mensesHormonalContraceptivesName) {
		this.mensesHormonalContraceptivesName = mensesHormonalContraceptivesName;
	}

	public String getMensesHormonalContraceptivesPeriod() {
		return mensesHormonalContraceptivesPeriod;
	}

	public void setMensesHormonalContraceptivesPeriod(String mensesHormonalContraceptivesPeriod) {
		this.mensesHormonalContraceptivesPeriod = mensesHormonalContraceptivesPeriod;
	}

	public String getMensesDesc() {
		return mensesDesc;
	}

	public void setMensesDesc(String mensesDesc) {
		this.mensesDesc = mensesDesc;
	}

	public Boolean getObstetricInfertility() {
		return obstetricInfertility;
	}

	public void setObstetricInfertility(Boolean obstetricInfertility) {
		this.obstetricInfertility = obstetricInfertility;
	}

	public Boolean getObstetricAbortions() {
		return obstetricAbortions;
	}

	public void setObstetricAbortions(Boolean obstetricAbortions) {
		this.obstetricAbortions = obstetricAbortions;
	}

	public String getObstetricAbortionsTimes() {
		return obstetricAbortionsTimes;
	}

	public void setObstetricAbortionsTimes(String obstetricAbortionsTimes) {
		this.obstetricAbortionsTimes = obstetricAbortionsTimes;
	}

	public Boolean getObstetricBirths() {
		return obstetricBirths;
	}

	public void setObstetricBirths(Boolean obstetricBirths) {
		this.obstetricBirths = obstetricBirths;
	}

	public String getObstetricBirthsTimes() {
		return obstetricBirthsTimes;
	}

	public void setObstetricBirthsTimes(String obstetricBirthsTimes) {
		this.obstetricBirthsTimes = obstetricBirthsTimes;
	}

	public Boolean getObstetricLactationUnderMonth() {
		return obstetricLactationUnderMonth;
	}

	public void setObstetricLactationUnderMonth(Boolean obstetricLactationUnderMonth) {
		this.obstetricLactationUnderMonth = obstetricLactationUnderMonth;
	}

	public Boolean getObstetricLactationUnderYear() {
		return obstetricLactationUnderYear;
	}

	public void setObstetricLactationUnderYear(Boolean obstetricLactationUnderYear) {
		this.obstetricLactationUnderYear = obstetricLactationUnderYear;
	}

	public Boolean getObstetricLactationOverYear() {
		return obstetricLactationOverYear;
	}

	public void setObstetricLactationOverYear(Boolean obstetricLactationOverYear) {
		this.obstetricLactationOverYear = obstetricLactationOverYear;
	}

	public String getObstetricDesc() {
		return obstetricDesc;
	}

	public void setObstetricDesc(String obstetricDesc) {
		this.obstetricDesc = obstetricDesc;
	}

	public Boolean getAnamnesisBreastDiseasesTrauma() {
		return anamnesisBreastDiseasesTrauma;
	}

	public void setAnamnesisBreastDiseasesTrauma(Boolean anamnesisBreastDiseasesTrauma) {
		this.anamnesisBreastDiseasesTrauma = anamnesisBreastDiseasesTrauma;
	}

	public Boolean getAnamnesisBreastDiseasesMastitis() {
		return anamnesisBreastDiseasesMastitis;
	}

	public void setAnamnesisBreastDiseasesMastitis(Boolean anamnesisBreastDiseasesMastitis) {
		this.anamnesisBreastDiseasesMastitis = anamnesisBreastDiseasesMastitis;
	}

	public Boolean getAnamnesisBreastDiseasesFibrous() {
		return anamnesisBreastDiseasesFibrous;
	}

	public void setAnamnesisBreastDiseasesFibrous(Boolean anamnesisBreastDiseasesFibrous) {
		this.anamnesisBreastDiseasesFibrous = anamnesisBreastDiseasesFibrous;
	}

	public Boolean getAnamnesisBreastDiseasesCysts() {
		return anamnesisBreastDiseasesCysts;
	}

	public void setAnamnesisBreastDiseasesCysts(Boolean anamnesisBreastDiseasesCysts) {
		this.anamnesisBreastDiseasesCysts = anamnesisBreastDiseasesCysts;
	}

	public Boolean getAnamnesisBreastDiseasesCancer() {
		return anamnesisBreastDiseasesCancer;
	}

	public void setAnamnesisBreastDiseasesCancer(Boolean anamnesisBreastDiseasesCancer) {
		this.anamnesisBreastDiseasesCancer = anamnesisBreastDiseasesCancer;
	}

	public String getAnamnesisBreastDiseasesDesc() {
		return anamnesisBreastDiseasesDesc;
	}

	public void setAnamnesisBreastDiseasesDesc(String anamnesisBreastDiseasesDesc) {
		this.anamnesisBreastDiseasesDesc = anamnesisBreastDiseasesDesc;
	}

	public Boolean getAnamnesisOvaryDiseasesInflammation() {
		return anamnesisOvaryDiseasesInflammation;
	}

	public void setAnamnesisOvaryDiseasesInflammation(Boolean anamnesisOvaryDiseasesInflammation) {
		this.anamnesisOvaryDiseasesInflammation = anamnesisOvaryDiseasesInflammation;
	}

	public Boolean getAnamnesisOvaryDiseasesCyst() {
		return anamnesisOvaryDiseasesCyst;
	}

	public void setAnamnesisOvaryDiseasesCyst(Boolean anamnesisOvaryDiseasesCyst) {
		this.anamnesisOvaryDiseasesCyst = anamnesisOvaryDiseasesCyst;
	}

	public Boolean getAnamnesisOvaryDiseasesCancer() {
		return anamnesisOvaryDiseasesCancer;
	}

	public void setAnamnesisOvaryDiseasesCancer(Boolean anamnesisOvaryDiseasesCancer) {
		this.anamnesisOvaryDiseasesCancer = anamnesisOvaryDiseasesCancer;
	}

	public Boolean getAnamnesisOvaryDiseasesEndometriosis() {
		return anamnesisOvaryDiseasesEndometriosis;
	}

	public void setAnamnesisOvaryDiseasesEndometriosis(Boolean anamnesisOvaryDiseasesEndometriosis) {
		this.anamnesisOvaryDiseasesEndometriosis = anamnesisOvaryDiseasesEndometriosis;
	}

	public String getAnamnesisOvaryDiseasesDesc() {
		return anamnesisOvaryDiseasesDesc;
	}

	public void setAnamnesisOvaryDiseasesDesc(String anamnesisOvaryDiseasesDesc) {
		this.anamnesisOvaryDiseasesDesc = anamnesisOvaryDiseasesDesc;
	}

	public Boolean getAnamnesisUterusDiseasesInflammation() {
		return anamnesisUterusDiseasesInflammation;
	}

	public void setAnamnesisUterusDiseasesInflammation(Boolean anamnesisUterusDiseasesInflammation) {
		this.anamnesisUterusDiseasesInflammation = anamnesisUterusDiseasesInflammation;
	}

	public Boolean getAnamnesisUterusDiseasesMyoma() {
		return anamnesisUterusDiseasesMyoma;
	}

	public void setAnamnesisUterusDiseasesMyoma(Boolean anamnesisUterusDiseasesMyoma) {
		this.anamnesisUterusDiseasesMyoma = anamnesisUterusDiseasesMyoma;
	}

	public Boolean getAnamnesisUterusDiseasesCancer() {
		return anamnesisUterusDiseasesCancer;
	}

	public void setAnamnesisUterusDiseasesCancer(Boolean anamnesisUterusDiseasesCancer) {
		this.anamnesisUterusDiseasesCancer = anamnesisUterusDiseasesCancer;
	}

	public Boolean getAnamnesisUterusDiseasesEndometriosis() {
		return anamnesisUterusDiseasesEndometriosis;
	}

	public void setAnamnesisUterusDiseasesEndometriosis(Boolean anamnesisUterusDiseasesEndometriosis) {
		this.anamnesisUterusDiseasesEndometriosis = anamnesisUterusDiseasesEndometriosis;
	}

	public String getAnamnesisUterusDiseasesDesc() {
		return anamnesisUterusDiseasesDesc;
	}

	public void setAnamnesisUterusDiseasesDesc(String anamnesisUterusDiseasesDesc) {
		this.anamnesisUterusDiseasesDesc = anamnesisUterusDiseasesDesc;
	}

	public Boolean getAnamnesisSomaticDiseasesAdiposity() {
		return anamnesisSomaticDiseasesAdiposity;
	}

	public void setAnamnesisSomaticDiseasesAdiposity(Boolean anamnesisSomaticDiseasesAdiposity) {
		this.anamnesisSomaticDiseasesAdiposity = anamnesisSomaticDiseasesAdiposity;
	}

	public Boolean getAnamnesisSomaticDiseasesHypertension() {
		return anamnesisSomaticDiseasesHypertension;
	}

	public void setAnamnesisSomaticDiseasesHypertension(Boolean anamnesisSomaticDiseasesHypertension) {
		this.anamnesisSomaticDiseasesHypertension = anamnesisSomaticDiseasesHypertension;
	}

	public Boolean getAnamnesisSomaticDiseasesDiabetes() {
		return anamnesisSomaticDiseasesDiabetes;
	}

	public void setAnamnesisSomaticDiseasesDiabetes(Boolean anamnesisSomaticDiseasesDiabetes) {
		this.anamnesisSomaticDiseasesDiabetes = anamnesisSomaticDiseasesDiabetes;
	}

	public Boolean getAnamnesisSomaticDiseasesThyroid() {
		return anamnesisSomaticDiseasesThyroid;
	}

	public void setAnamnesisSomaticDiseasesThyroid(Boolean anamnesisSomaticDiseasesThyroid) {
		this.anamnesisSomaticDiseasesThyroid = anamnesisSomaticDiseasesThyroid;
	}

	public String getAnamnesisSomaticDiseasesDesc() {
		return anamnesisSomaticDiseasesDesc;
	}

	public void setAnamnesisSomaticDiseasesDesc(String anamnesisSomaticDiseasesDesc) {
		this.anamnesisSomaticDiseasesDesc = anamnesisSomaticDiseasesDesc;
	}

	public String getAnamnesisDesc() {
		return anamnesisDesc;
	}

	public void setAnamnesisDesc(String anamnesisDesc) {
		this.anamnesisDesc = anamnesisDesc;
	}

	public Boolean getExaminationsPalpation() {
		return examinationsPalpation;
	}

	public void setExaminationsPalpation(Boolean examinationsPalpation) {
		this.examinationsPalpation = examinationsPalpation;
	}

	public String getExaminationsPalpationYear() {
		return examinationsPalpationYear;
	}

	public void setExaminationsPalpationYear(String examinationsPalpationYear) {
		this.examinationsPalpationYear = examinationsPalpationYear;
	}

	public Boolean getExaminationsPalpationNormal() {
		return examinationsPalpationNormal;
	}

	public void setExaminationsPalpationNormal(Boolean examinationsPalpationNormal) {
		this.examinationsPalpationNormal = examinationsPalpationNormal;
	}

	public Boolean getExaminationsPalpationAbnormal() {
		return examinationsPalpationAbnormal;
	}

	public void setExaminationsPalpationAbnormal(Boolean examinationsPalpationAbnormal) {
		this.examinationsPalpationAbnormal = examinationsPalpationAbnormal;
	}

	public Boolean getExaminationsPalpationDiffuse() {
		return examinationsPalpationDiffuse;
	}

	public void setExaminationsPalpationDiffuse(Boolean examinationsPalpationDiffuse) {
		this.examinationsPalpationDiffuse = examinationsPalpationDiffuse;
	}

	public Boolean getExaminationsPalpationFocal() {
		return examinationsPalpationFocal;
	}

	public void setExaminationsPalpationFocal(Boolean examinationsPalpationFocal) {
		this.examinationsPalpationFocal = examinationsPalpationFocal;
	}

	public String getExaminationsPalpationDesc() {
		return examinationsPalpationDesc;
	}

	public void setExaminationsPalpationDesc(String examinationsPalpationDesc) {
		this.examinationsPalpationDesc = examinationsPalpationDesc;
	}

	public Boolean getExaminationsUltrasound() {
		return examinationsUltrasound;
	}

	public void setExaminationsUltrasound(Boolean examinationsUltrasound) {
		this.examinationsUltrasound = examinationsUltrasound;
	}

	public String getExaminationsUltrasoundYear() {
		return examinationsUltrasoundYear;
	}

	public void setExaminationsUltrasoundYear(String examinationsUltrasoundYear) {
		this.examinationsUltrasoundYear = examinationsUltrasoundYear;
	}

	public Boolean getExaminationsUltrasoundNormal() {
		return examinationsUltrasoundNormal;
	}

	public void setExaminationsUltrasoundNormal(Boolean examinationsUltrasoundNormal) {
		this.examinationsUltrasoundNormal = examinationsUltrasoundNormal;
	}

	public Boolean getExaminationsUltrasoundAbnormal() {
		return examinationsUltrasoundAbnormal;
	}

	public void setExaminationsUltrasoundAbnormal(Boolean examinationsUltrasoundAbnormal) {
		this.examinationsUltrasoundAbnormal = examinationsUltrasoundAbnormal;
	}

	public Boolean getExaminationsUltrasoundDiffuse() {
		return examinationsUltrasoundDiffuse;
	}

	public void setExaminationsUltrasoundDiffuse(Boolean examinationsUltrasoundDiffuse) {
		this.examinationsUltrasoundDiffuse = examinationsUltrasoundDiffuse;
	}

	public Boolean getExaminationsUltrasoundFocal() {
		return examinationsUltrasoundFocal;
	}

	public void setExaminationsUltrasoundFocal(Boolean examinationsUltrasoundFocal) {
		this.examinationsUltrasoundFocal = examinationsUltrasoundFocal;
	}

	public String getExaminationsUltrasoundDesc() {
		return examinationsUltrasoundDesc;
	}

	public void setExaminationsUltrasoundDesc(String examinationsUltrasoundDesc) {
		this.examinationsUltrasoundDesc = examinationsUltrasoundDesc;
	}

	public Boolean getExaminationsMammography() {
		return examinationsMammography;
	}

	public void setExaminationsMammography(Boolean examinationsMammography) {
		this.examinationsMammography = examinationsMammography;
	}

	public String getExaminationsMammographyYear() {
		return examinationsMammographyYear;
	}

	public void setExaminationsMammographyYear(String examinationsMammographyYear) {
		this.examinationsMammographyYear = examinationsMammographyYear;
	}

	public Boolean getExaminationsMammographyNormal() {
		return examinationsMammographyNormal;
	}

	public void setExaminationsMammographyNormal(Boolean examinationsMammographyNormal) {
		this.examinationsMammographyNormal = examinationsMammographyNormal;
	}

	public Boolean getExaminationsMammographyAbnormal() {
		return examinationsMammographyAbnormal;
	}

	public void setExaminationsMammographyAbnormal(Boolean examinationsMammographyAbnormal) {
		this.examinationsMammographyAbnormal = examinationsMammographyAbnormal;
	}

	public Boolean getExaminationsMammographyDiffuse() {
		return examinationsMammographyDiffuse;
	}

	public void setExaminationsMammographyDiffuse(Boolean examinationsMammographyDiffuse) {
		this.examinationsMammographyDiffuse = examinationsMammographyDiffuse;
	}

	public Boolean getExaminationsMammographyFocal() {
		return examinationsMammographyFocal;
	}

	public void setExaminationsMammographyFocal(Boolean examinationsMammographyFocal) {
		this.examinationsMammographyFocal = examinationsMammographyFocal;
	}

	public String getExaminationsMammographyDesc() {
		return examinationsMammographyDesc;
	}

	public void setExaminationsMammographyDesc(String examinationsMammographyDesc) {
		this.examinationsMammographyDesc = examinationsMammographyDesc;
	}

	public Boolean getExaminationsBiopsy() {
		return examinationsBiopsy;
	}

	public void setExaminationsBiopsy(Boolean examinationsBiopsy) {
		this.examinationsBiopsy = examinationsBiopsy;
	}

	public String getExaminationsBiopsyYear() {
		return examinationsBiopsyYear;
	}

	public void setExaminationsBiopsyYear(String examinationsBiopsyYear) {
		this.examinationsBiopsyYear = examinationsBiopsyYear;
	}

	public Boolean getExaminationsBiopsyNormal() {
		return examinationsBiopsyNormal;
	}

	public void setExaminationsBiopsyNormal(Boolean examinationsBiopsyNormal) {
		this.examinationsBiopsyNormal = examinationsBiopsyNormal;
	}

	public Boolean getExaminationsBiopsyAbnormal() {
		return examinationsBiopsyAbnormal;
	}

	public void setExaminationsBiopsyAbnormal(Boolean examinationsBiopsyAbnormal) {
		this.examinationsBiopsyAbnormal = examinationsBiopsyAbnormal;
	}

	public Boolean getExaminationsBiopsyCancer() {
		return examinationsBiopsyCancer;
	}

	public void setExaminationsBiopsyCancer(Boolean examinationsBiopsyCancer) {
		this.examinationsBiopsyCancer = examinationsBiopsyCancer;
	}

	public Boolean getExaminationsBiopsyProliferation() {
		return examinationsBiopsyProliferation;
	}

	public void setExaminationsBiopsyProliferation(Boolean examinationsBiopsyProliferation) {
		this.examinationsBiopsyProliferation = examinationsBiopsyProliferation;
	}

	public Boolean getExaminationsBiopsyDysplasia() {
		return examinationsBiopsyDysplasia;
	}

	public void setExaminationsBiopsyDysplasia(Boolean examinationsBiopsyDysplasia) {
		this.examinationsBiopsyDysplasia = examinationsBiopsyDysplasia;
	}

	public Boolean getExaminationsBiopsyPapilloma() {
		return examinationsBiopsyPapilloma;
	}

	public void setExaminationsBiopsyPapilloma(Boolean examinationsBiopsyPapilloma) {
		this.examinationsBiopsyPapilloma = examinationsBiopsyPapilloma;
	}

	public Boolean getExaminationsBiopsyFibroadenoma() {
		return examinationsBiopsyFibroadenoma;
	}

	public void setExaminationsBiopsyFibroadenoma(Boolean examinationsBiopsyFibroadenoma) {
		this.examinationsBiopsyFibroadenoma = examinationsBiopsyFibroadenoma;
	}

	public String getExaminationsBiopsyDesc() {
		return examinationsBiopsyDesc;
	}

	public void setExaminationsBiopsyDesc(String examinationsBiopsyDesc) {
		this.examinationsBiopsyDesc = examinationsBiopsyDesc;
	}

	public Boolean getExaminationsMeik() {
		return examinationsMeik;
	}

	public void setExaminationsMeik(Boolean examinationsMeik) {
		this.examinationsMeik = examinationsMeik;
	}

	public String getExaminationsMeikYear() {
		return examinationsMeikYear;
	}

	public void setExaminationsMeikYear(String examinationsMeikYear) {
		this.examinationsMeikYear = examinationsMeikYear;
	}

	public String getExaminationsMeikPoint() {
		return examinationsMeikPoint;
	}

	public void setExaminationsMeikPoint(String examinationsMeikPoint) {
		this.examinationsMeikPoint = examinationsMeikPoint;
	}

	public String getExaminationsMeikDesc() {
		return examinationsMeikDesc;
	}

	public void setExaminationsMeikDesc(String examinationsMeikDesc) {
		this.examinationsMeikDesc = examinationsMeikDesc;
	}

	public String getExaminationsDesc() {
		return examinationsDesc;
	}

	public void setExaminationsDesc(String examinationsDesc) {
		this.examinationsDesc = examinationsDesc;
	}

	public Boolean getVisualSwollen() {
		return visualSwollen;
	}

	public void setVisualSwollen(Boolean visualSwollen) {
		this.visualSwollen = visualSwollen;
	}

	public int getVisualSwollenLeft() {
		return visualSwollenLeft;
	}

	public void setVisualSwollenLeft(int visualSwollenLeft) {
		this.visualSwollenLeft = visualSwollenLeft;
	}

	public int getVisualSwollenRight() {
		return visualSwollenRight;
	}

	public void setVisualSwollenRight(int visualSwollenRight) {
		this.visualSwollenRight = visualSwollenRight;
	}

	public String getVisualSwollenDesc() {
		return visualSwollenDesc;
	}

	public void setVisualSwollenDesc(String visualSwollenDesc) {
		this.visualSwollenDesc = visualSwollenDesc;
	}

	public Boolean getVisualPalpable() {
		return visualPalpable;
	}

	public void setVisualPalpable(Boolean visualPalpable) {
		this.visualPalpable = visualPalpable;
	}

	public int getVisualPalpableLeft() {
		return visualPalpableLeft;
	}

	public void setVisualPalpableLeft(int visualPalpableLeft) {
		this.visualPalpableLeft = visualPalpableLeft;
	}

	public int getVisualPalpableRight() {
		return visualPalpableRight;
	}

	public void setVisualPalpableRight(int visualPalpableRight) {
		this.visualPalpableRight = visualPalpableRight;
	}

	public String getVisualPalpableDesc() {
		return visualPalpableDesc;
	}

	public void setVisualPalpableDesc(String visualPalpableDesc) {
		this.visualPalpableDesc = visualPalpableDesc;
	}

	public Boolean getVisualSerous() {
		return visualSerous;
	}

	public void setVisualSerous(Boolean visualSerous) {
		this.visualSerous = visualSerous;
	}

	public int getVisualSerousLeft() {
		return visualSerousLeft;
	}

	public void setVisualSerousLeft(int visualSerousLeft) {
		this.visualSerousLeft = visualSerousLeft;
	}

	public int getVisualSerousRight() {
		return visualSerousRight;
	}

	public void setVisualSerousRight(int visualSerousRight) {
		this.visualSerousRight = visualSerousRight;
	}

	public String getVisualSerousDesc() {
		return visualSerousDesc;
	}

	public void setVisualSerousDesc(String visualSerousDesc) {
		this.visualSerousDesc = visualSerousDesc;
	}

	public Boolean getVisualWounds() {
		return visualWounds;
	}

	public void setVisualWounds(Boolean visualWounds) {
		this.visualWounds = visualWounds;
	}

	public int getVisualWoundsLeft() {
		return visualWoundsLeft;
	}

	public void setVisualWoundsLeft(int visualWoundsLeft) {
		this.visualWoundsLeft = visualWoundsLeft;
	}

	public int getVisualWoundsRight() {
		return visualWoundsRight;
	}

	public void setVisualWoundsRight(int visualWoundsRight) {
		this.visualWoundsRight = visualWoundsRight;
	}

	public String getVisualWoundsDesc() {
		return visualWoundsDesc;
	}

	public void setVisualWoundsDesc(String visualWoundsDesc) {
		this.visualWoundsDesc = visualWoundsDesc;
	}

	public Boolean getVisualScars() {
		return visualScars;
	}

	public void setVisualScars(Boolean visualScars) {
		this.visualScars = visualScars;
	}

	public int getVisualScarsLeft() {
		return visualScarsLeft;
	}

	public void setVisualScarsLeft(int visualScarsLeft) {
		this.visualScarsLeft = visualScarsLeft;
	}

	public int getVisualScarsRight() {
		return visualScarsRight;
	}

	public void setVisualScarsRight(int visualScarsRight) {
		this.visualScarsRight = visualScarsRight;
	}

	public String getVisualScarsDesc() {
		return visualScarsDesc;
	}

	public void setVisualScarsDesc(String visualScarsDesc) {
		this.visualScarsDesc = visualScarsDesc;
	}

	public String getVisualDesc() {
		return visualDesc;
	}

	public void setVisualDesc(String visualDesc) {
		this.visualDesc = visualDesc;
	}

	public Tuser getUser() {
		return user;
	}

	public void setUser(Tuser user) {
		this.user = user;
	}

	public String getCreatedTimeStr() {		
		if(createdTime!=null){
			Date date=new Date(createdTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
		}else
		return this.createdTimeStr;		
	}

	public void setCreatedTimeStr(String createdTimeStr) {
		this.createdTimeStr = createdTimeStr;
	}

	public String getFamilyBreastCancer() {
		return familyBreastCancer;
	}

	public void setFamilyBreastCancer(String familyBreastCancer) {
		this.familyBreastCancer = familyBreastCancer;
	}

	public String getFamilyUterineCancer() {
		return familyUterineCancer;
	}

	public void setFamilyUterineCancer(String familyUterineCancer) {
		this.familyUterineCancer = familyUterineCancer;
	}

	public String getFamilyCervicalCancer() {
		return familyCervicalCancer;
	}

	public void setFamilyCervicalCancer(String familyCervicalCancer) {
		this.familyCervicalCancer = familyCervicalCancer;
	}

	public String getFamilyOvarianCancer() {
		return familyOvarianCancer;
	}

	public void setFamilyOvarianCancer(String familyOvarianCancer) {
		this.familyOvarianCancer = familyOvarianCancer;
	}

	public String getComplaintsBreastImplantsMaterials() {
		return complaintsBreastImplantsMaterials;
	}

	public void setComplaintsBreastImplantsMaterials(String complaintsBreastImplantsMaterials) {
		this.complaintsBreastImplantsMaterials = complaintsBreastImplantsMaterials;
	}

	public String getMensesLastMenstruationDay() {
		return mensesLastMenstruationDay;
	}

	public void setMensesLastMenstruationDay(String mensesLastMenstruationDay) {
		this.mensesLastMenstruationDay = mensesLastMenstruationDay;
	}

	public String getMensesLastMenstruationMonth() {
		return mensesLastMenstruationMonth;
	}

	public void setMensesLastMenstruationMonth(String mensesLastMenstruationMonth) {
		this.mensesLastMenstruationMonth = mensesLastMenstruationMonth;
	}

	public String getObstetricLactation() {
		return obstetricLactation;
	}

	public void setObstetricLactation(String obstetricLactation) {
		this.obstetricLactation = obstetricLactation;
	}

	public String getAnamnesisBreastDiseases() {
		return anamnesisBreastDiseases;
	}

	public void setAnamnesisBreastDiseases(String anamnesisBreastDiseases) {
		this.anamnesisBreastDiseases = anamnesisBreastDiseases;
	}

	public String getAnamnesisOvaryDiseases() {
		return anamnesisOvaryDiseases;
	}

	public void setAnamnesisOvaryDiseases(String anamnesisOvaryDiseases) {
		this.anamnesisOvaryDiseases = anamnesisOvaryDiseases;
	}

	public String getAnamnesisUterusDiseases() {
		return anamnesisUterusDiseases;
	}

	public void setAnamnesisUterusDiseases(String anamnesisUterusDiseases) {
		this.anamnesisUterusDiseases = anamnesisUterusDiseases;
	}

	public String getAnamnesisSomaticDiseases() {
		return anamnesisSomaticDiseases;
	}

	public void setAnamnesisSomaticDiseases(String anamnesisSomaticDiseases) {
		this.anamnesisSomaticDiseases = anamnesisSomaticDiseases;
	}

	public String getExaminationsPalpationStatus() {
		return examinationsPalpationStatus;
	}

	public void setExaminationsPalpationStatus(String examinationsPalpationStatus) {
		this.examinationsPalpationStatus = examinationsPalpationStatus;
	}

	public String getExaminationsUltrasoundStatus() {
		return examinationsUltrasoundStatus;
	}

	public void setExaminationsUltrasoundStatus(String examinationsUltrasoundStatus) {
		this.examinationsUltrasoundStatus = examinationsUltrasoundStatus;
	}

	public String getExaminationsMammographyStatus() {
		return examinationsMammographyStatus;
	}

	public void setExaminationsMammographyStatus(String examinationsMammographyStatus) {
		this.examinationsMammographyStatus = examinationsMammographyStatus;
	}

	public String getExaminationsBiopsyStatus() {
		return examinationsBiopsyStatus;
	}

	public void setExaminationsBiopsyStatus(String examinationsBiopsyStatus) {
		this.examinationsBiopsyStatus = examinationsBiopsyStatus;
	}

	public Boolean getRegAgree() {
		return regAgree;
	}

	public void setRegAgree(Boolean regAgree) {
		this.regAgree = regAgree;
	}

	
}