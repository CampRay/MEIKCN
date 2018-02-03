package com.nuvomed.dto;

import java.io.Serializable;

/**
 * The persistent class for the user_data database table.
 * 
 */
public class Tversion implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String version;

	private String fileName;

	private byte[] stream;

	private int type;

	public Tversion() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}