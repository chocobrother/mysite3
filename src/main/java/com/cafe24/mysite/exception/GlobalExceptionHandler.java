package com.cafe24.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Log LOG = LogFactory.getLog( GlobalExceptionHandler.class );
	
	@ExceptionHandler(Exception.class)
	public void handlerException(
			HttpServletRequest request,
			HttpServletResponse response, 
			Exception e)throws Exception {

		//1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOG.warn(errors);
//		request.setAttribute("errors", errors.toString());
		
		e.printStackTrace();
		

		String accept = request.getHeader("accept");
		
		if(accept.matches(".*application/json.*")) {
			
			//실패 JSON 응답을해줘야댐
			JSONResult result = JSONResult.fail(errors.toString());
		
			
			//json 형식으로 맞춰주는 것 
			String json = new ObjectMapper().writeValueAsString(result);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(json);
		}else {
			//2. 사과페이지로 돌리기
			request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").
			forward(request,response);
		}
		
		
	}
	
	
}
