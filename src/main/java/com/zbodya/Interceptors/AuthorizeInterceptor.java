package com.zbodya.Interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zbodya.Service.AuthorizationService;

@Component
public class AuthorizeInterceptor extends HandlerInterceptorAdapter 
{
	
	@Autowired 
	AuthorizationService authServ;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		System.out.println("Path: " + request.getRequestURI().substring(request.getContextPath().length()) + " role: " + request.getHeader("role"));
		if(authServ.isAuthorized(request.getRequestURI().substring(request.getContextPath().length()), request.getHeader("role")))					
			return true;
		else 
		{			
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"User unauthorized");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write("User unauthorized");
			return false;
		}
		
	}

	

}
