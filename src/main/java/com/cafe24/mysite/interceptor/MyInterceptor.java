package com.cafe24.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response,
			Object arg2) throws Exception {
		
		System.out.println("my interceptor : prehandle " );
		
		return true;
		
		
	}
	
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

		System.out.println("my interceptor : postHandle " );
		

	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("my interceptor : afterCompletion " );

	}

	



}
