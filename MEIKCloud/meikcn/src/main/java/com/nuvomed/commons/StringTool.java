package com.nuvomed.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTool {

	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(str);
	   if( isNum.matches()){
		   return true;
	   }
	   return false;	   
	}
	
	public static boolean isEmptyOrNull(String str) {
		if(str==null||str.trim().isEmpty()){
			return true;
		}
		return false;
	}

}
