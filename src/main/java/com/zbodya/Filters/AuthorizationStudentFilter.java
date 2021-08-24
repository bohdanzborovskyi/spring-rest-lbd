package com.zbodya.Filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;



//@Order(2)
//@WebFilter(urlPatterns = "/api/students/*")
//public class AuthorizationStudentFilter implements Filter
//{
//	Logger LOG = LoggerFactory.getLogger(getClass().getName());
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException 
//	{			
//			HttpServletRequest httpRequest = (HttpServletRequest)request;
//			if(httpRequest.getHeader("role")!=null
//					&&(httpRequest.getHeader("role").equals("STUDENT_ROLE")
//					|| httpRequest.getHeader("role").equals("TEACHER_ROLE")))
//				chain.doFilter(request, response);
//			else 
//			{ 	
//				HttpServletResponse httpResponse = (HttpServletResponse)response;
//				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"User unauthorized");				
//			}
//	}
//
//}
