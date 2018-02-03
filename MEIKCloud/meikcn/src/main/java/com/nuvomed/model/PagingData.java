package com.nuvomed.model;

import java.io.Serializable;


public class PagingData implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 1L;

	private String sEcho; //原样返回从客户端发送请求中带来的值，该上XSS攻击

    private int iTotalRecords; //总记录数

    private int iTotalDisplayRecords; //过滤后的总记录数

    private Object[] aaData; // 要返回的当前页数据    

    public PagingData() {
        this.sEcho = "";
        this.iTotalRecords = 0;
        this.iTotalDisplayRecords = 0;
        this.aaData = null;             
    }

    public PagingData(final int recordsTotal, final int recordsFiltered, Object[] data) {
    	this.iTotalRecords =recordsTotal;
        this.iTotalDisplayRecords = recordsFiltered;
        this.aaData = data;             
    }

	public String getSEcho() {
		return sEcho;
	}

	public void setSEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public int getITotalRecords() {
		return iTotalRecords;
	}

	public void setITotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getITotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setITotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public Object[] getAaData() {
		if(aaData==null){
			aaData=new Object[]{};
		}
		return aaData;
	}

	public void setAaData(Object[] aaData) {
		this.aaData = aaData;
	}

	
	
    
}

