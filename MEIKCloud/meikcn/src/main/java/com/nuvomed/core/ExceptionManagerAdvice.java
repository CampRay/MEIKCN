/**   
 * @Title: ExceptionManagerAdvice.java 
 * @Package com.bps.core 
 *
 * @Description: User Points Management System
 * 
 * @date Nov 22, 2014 2:41:16 PM
 * @version V1.0   
 */ 
package com.nuvomed.core;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

import com.nuvomed.commons.MyException;

/** 
 * <p>Description：AOP处理类，主要对Service层出现的异常进行处理</p>
 * @ClassName: ExceptionManagerAdvice 
 * @author Phills Li 
 * 
 */
public class ExceptionManagerAdvice implements ThrowsAdvice {
	
	/**
	 * 对切面中的业务方法抛出异常后进行自定义增强的处理方法 
	 * 参数解释 Method method 执行的方法 
	 * Object[] args 方法参数
	 *  Object target 代理的目标对象
	 * Throwable throwable 产生的异常 
	 */
	public void afterThrowing(Method method, Object[] args, Object target,RuntimeException  throwable) {
		String className=target.toString();
		className=className.replace("com.nuvomed.service.impl","").split("@")[0];
		if(throwable instanceof MyException){
			MyException mposEx = (MyException) throwable;
			if(mposEx.getErrorID()==null&&!mposEx.getErrorID().isEmpty()){
				throw new MyException("error"+className+"."+method.getName(),throwable.getMessage());
			}
		}else{
			throw new MyException("error"+className+"."+method.getName(),throwable.getMessage());
		}
	}

}
