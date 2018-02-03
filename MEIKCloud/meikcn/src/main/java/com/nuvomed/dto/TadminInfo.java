package com.nuvomed.dto;


public class TadminInfo implements java.io.Serializable {

	private static final long serialVersionUID = -6411039121482151510L;
	
	private String adminId;
	private String firstName;
	private String lastName;
	private Boolean gender;
	private String birthday;	
	private String email;
	private String mobile;
	private String position;
	private String about;
	private byte[] avatarSmall;
	private byte[] avatar;
	private Boolean notify;

	public TadminInfo() {
	}

	public TadminInfo(String adminId) {
		this.adminId = adminId;
	}

	public TadminInfo(String adminId, String firstName, String lastName,
			Boolean gender, String birthday,String email, String mobile, String position,
			String about, byte[] avatarSmall, byte[] avatar) {
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.email=email;
		this.mobile = mobile;
		this.position = position;
		this.about = about;
		this.avatarSmall = avatarSmall;
		this.avatar = avatar;
	}

	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAbout() {
		return this.about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public byte[] getAvatarSmall() {
		return this.avatarSmall;
	}

	public void setAvatarSmall(byte[] avatarSmall) {
		this.avatarSmall = avatarSmall;
	}

	public byte[] getAvatar() {
		return this.avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public Boolean getNotify() {
		return notify;
	}

	public void setNotify(Boolean notify) {
		this.notify = notify;
	}	
}
