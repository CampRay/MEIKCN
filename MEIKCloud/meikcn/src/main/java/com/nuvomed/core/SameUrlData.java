package com.nuvomed.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义Action方法注解（在controller方法上加上@SameUrlData）
 * 
 * 一个用户相同url同时提交相同数据 验证 
 * @author Phills
 *
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)
public @interface SameUrlData {

	
}
