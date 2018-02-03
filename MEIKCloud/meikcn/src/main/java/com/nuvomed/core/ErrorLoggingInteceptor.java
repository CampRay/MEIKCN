package com.nuvomed.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: ErrorLoggingInteceptor
 * @Description: TODO
 * @author Phills Li
 * @date Sep 9, 2014 4:51:40 PM
 *
 */
public class ErrorLoggingInteceptor implements HandlerInterceptor {
	private Logger logger = Logger.getLogger(ErrorLoggingInteceptor.class);

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		if (arg3 != null) {
			logger.error("Failed to handle the http request " + arg0.getRequestURL()+": "+arg3.getMessage());
		}
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
