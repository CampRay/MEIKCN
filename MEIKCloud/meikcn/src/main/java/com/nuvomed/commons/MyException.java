package com.nuvomed.commons;

public class MyException extends RuntimeException {
	private static final long serialVersionUID = 2741768848973601742L;
	
	protected String errID;

	protected String msg = "";

	protected String str = "";

	public MyException() {
		super();
	}
	
	public MyException(Throwable ex) {
		super(ex);
		this.msg = ex.getMessage();
		this.str = "com.nuvomed.MyException:"
				+ "\n    nested exception:" + ex.toString();
	}

	public MyException(String ID, Throwable ex) {
		super(ex);
		this.errID = ID;
		this.msg = ex.getMessage();
		this.str = "com.nuvomed.MyException:" + ex.getMessage()
				+ "\n    nested exception:" + ex.toString();
	}

	public MyException(String ID, String message, Throwable ex) {
		super(ex);
		this.errID = ID;
		this.msg = message + " nested exception:" + ex.getMessage();
		this.str = "com.nuvomed.MyException:" + message
				+ "\n    nested exception:" + ex.toString();
	}

	public MyException(int ID, String message, Throwable ex) {
		super(ex);
		this.errID = String.valueOf(ID);
		this.msg = message + " nested exception:" + ex.getMessage();
		this.str = "com.nuvomed.MyException:" + message
				+ "\n    nested exception:" + ex.toString();
	}

	public MyException(String ID, String message) {
		this.errID = ID;
		this.msg = message;
		this.str = "com.nuvomed.MyException:" + message;
	}

	public MyException(int ID, String message) {
		this.errID = String.valueOf(ID);
		this.msg = message;
		this.str = "com.nuvomed.MyException:" + message;
	}

	public MyException(String ID) {
		this.errID = ID;
	}

	public MyException(int ID) {
		this.errID = String.valueOf(ID);
	}

	public String getErrorID() {
		return this.errID;
	}

	public String getMessage() {
		return this.msg;
	}
	
	public String toString() {
		return this.str;
	}

}
