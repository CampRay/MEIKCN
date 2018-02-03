package com.nuvomed.commons;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * <p>获取系统Spring上下文对象的工具类</p>
 * @ClassName: MyApplicationContextUtil 
 * @author Phills Li 
 *
 */
public class MyApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		MyApplicationContextUtil.context = contex;
	}

	public static ApplicationContext getContext() {
		return context;
	}

	/**
	* 根据bean的id来查找对象
	* @param id
	* @return
	*/
	@SuppressWarnings("unchecked")
	public static <T> T getBeanById(String id) {
		  return (T) context.getBean(id);
	}


	/**
    * 根据bean的class来查找对象
    * @param c
    * @return
    */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T getBeanByClass(Class c) {
    	return (T) context.getBean(c);
	}


	/**
	* 根据bean的class来查找所有的对象(包括子类)
	* @param c
	* @return
	*/
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map getBeansByClass(Class c) {
		  return context.getBeansOfType(c);
	}

	public static String getMessage(String key) {
		return context.getMessage(key, null, Locale.getDefault());
	}
}
