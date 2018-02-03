package com.nuvomed.commons;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nuvomed.dto.TadminNodes;

public class SystemConfig {
					
	public static Map<String,Long> Admin_Nodes_Url_Map=new LinkedHashMap<String,Long>();
					
	public static Map<TadminNodes,List<TadminNodes>> Admin_Nodes_Menu_Map=new LinkedHashMap<TadminNodes,List<TadminNodes>>();
	
	public static Map<String,List<TadminNodes>> Admin_Nodes_Group_Map=new LinkedHashMap<String,List<TadminNodes>>();
	
	public static Map<String,String> Admin_Setting_Map=new Hashtable<String,String>();
	
}
