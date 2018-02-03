/**   
* @Title: SystemConstants.java 
* @Package com.uswop.commons 
*
* @Description: 系统全局常量类
* 
* @date Sep 9, 2014 7:14:02 PM
* @version V1.0   
*/ 
package com.nuvomed.commons;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: SystemConstants 
 * @Description: TODO
 * @author Phills Li
 * @date Sep 9, 2014 7:14:02 PM 
 *  
 */
public class SystemConstants {
	public static final String LOGINED="Logined";
	
	public static final String RIGHTS="rights";  
	
	public static final String LOGIN_ERROR="Login_Error";
	
	public static final String LOGIN_STATUS="Login_Status";
	
	public static final String LOG_SUCCESS="success";
	
	public static final String LOG_FAILURE="failure";
	
	public static final String LOG_SEPARATOR="-";
	
	public static final String EMAIL_HOST="Email_Host";
	
	public static final String EMAIL_NAME="Email_Username";
	
	public static final String EMAIl_PASSWORD="Email_Password";
	
	public static final String MAX_LOGIN_ERROR_TIMES="Max_Login_Error_Times";
	
	public static final String INIT_AUTH_CODE="1596fa2bbd11959414d3267230013bf3";
	
	public static final String LOGIN_ERROR_LOCK="Login_Error_Locked";
	
	public static final String TABLE_NAME_MENU="Tmenu";
	public static final String TABLE_NAME_CATEGORY="Tcategory";
	public static final String TABLE_NAME_CATE_ATTRIBUTE="TcategoryAttribute";
	public static final String TABLE_NAME_ATTRIBUTE_VALUE="TattributeValue";
	public static final String TABLE_NAME_PRODUCT_ATTRIBUTE="TproductAttribute";
	public static final String TABLE_NAME_PRODUCT="Tproduct";
	
	public static final String TABLE_FIELD_NAME="name";
	public static final String TABLE_FIELD_DESCR="descr";
	public static final String TABLE_FIELD_TITLE="title";
	public static final String TABLE_FIELD_VALUE="value";
	public static final String TABLE_FIELD_ATTRIBUTE_VALUE="attributeValue";
	public static final String TABLE_FIELD_PRODUCTNAME="productName";
	public static final String TABLE_FIELD_SHORTDESCR="shortDescr";
	public static final String TABLE_FIELD_FULLDESCR="fullDescr";
	public static final String TABLE_FIELD_UNITNAME="unitName";
	
	public static final String CONFIG_CLIENT_PWD="Access_Password";
	public static final String CONFIG_CLIENT_LOGO="Restaurant_Logo";
	public static final String CONFIG_API_TOKEN="Token";
	public static final String CONFIG_DISPLAY_CURRENCY="config_display_currency";
	
	public static final String RESTAURANT_NAME="Restaurant_Name";
	
	public static final String ACCESS_PASSWORD="Access_Password";
	
	public static final String CURRENCY="Currency";
	
	public static final String RESTAURANT_LOGO="Restaurant_Logo";
	
	public static final String PAGE_BACKGROUND="Page_Background";
	
    public static final String RESTAURANT_LOGO_File="Restaurant_Logo_File";
	
	public static final String PAGE_BACKGROUND_File="Page_Background_File";
	
	public static final String TOKEN = "Token";
	
	public static final Map<Integer, String> PROMOTION_TYPE=new HashMap<Integer, String>(){
	     private static final long serialVersionUID = 1L;
    {
		put(0, "Straight Down");
		put(1, "The Full Reduction");
		put(2, "Discount");
		put(3, "Combination");
		put(4, "X Give Y");
		put(5, "M For Y Discount");
		put(6, "Custom");
	}
    };
    
    public static final Map<Integer, String> ORDER_STATUS=new HashMap<Integer, String>(){
	     private static final long serialVersionUID = 1L;
   {
		put(0, "Pending");
		put(1, "Paid");
		put(2, "Cancelled");
		
	}
   };
}
