package com.cognizant.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cognizant.controller.EmpinfoController;

public class AuthenticateInterceptor extends HandlerInterceptorAdapter {

	Logger LOGGER = LogManager.getLogger(EmpinfoController.class.getName());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("Prehandler interceptor");
		
		return super.preHandle(request, response, handler);
	}
	
}
