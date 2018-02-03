package com.nuvomed.dto;

import java.io.Serializable;


/**
 * The persistent class for the user_data database table.
 * 
 */
public class TuserData implements Serializable {
	private static final long serialVersionUID = 1L;

	private int dataId;

	private int userId;

	private String fileName;

	private byte[] stream;	
	
	//数据文件类型：1 screening数据；2 医生report 数据;3 系统医生report数据; 4 pdf报告, 5 screen自動報告
	private int dataType;
		
	public TuserData(){}
	public TuserData(int dataId,int userId,String fileName,int dataType) {
		this.dataId=dataId;
		this.userId=userId;
		this.fileName=fileName;
		this.dataType=dataType;
		
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int infoId) {
		this.dataId = infoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getStream() {
		return stream;
	}

	public void setStream(byte[] stream) {
		this.stream = stream;
	}
	

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}	

}