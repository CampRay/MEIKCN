package com.nuvomed.dto;

public class Tlanguage implements java.io.Serializable {

	private static final long serialVersionUID = 2909518251922228073L;
	
	private Integer id;
	private String name;
	
	private String local;
	private String flagImage;
	private boolean status;
	
	private int sort;

	public Tlanguage() {
	}

	public Tlanguage(String name, String local, boolean status, int sort) {
		this.name = name;
		this.local = local;
		this.status = status;
		this.sort = sort;
	}

	public Tlanguage(String name, String local, String flagImage,
			boolean status, int sort) {
		this.name = name;
		this.local = local;
		this.flagImage = flagImage;
		this.status = status;
		this.sort = sort;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getFlagImage() {
		return this.flagImage;
	}

	public void setFlagImage(String flagImage) {
		this.flagImage = flagImage;
	}

	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Tlanguage [id=" + id + ", name=" + name + ", local=" + local
				+ ", flagImage=" + flagImage + ", status=" + status + ", sort="
				+ sort + "]";
	}
	
	
}
