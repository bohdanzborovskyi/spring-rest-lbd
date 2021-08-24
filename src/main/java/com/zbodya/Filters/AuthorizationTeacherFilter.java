package com.zbodya.Filters;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

import java.io.IOException;

import javax.servlet.*;

//@Order(3)
//@WebFilter(urlPatterns = "/api/teachers/*")
//public class AuthorizationTeacherFilter  implements Filter
//{
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException 
//	{			
//			HttpServletRequest httpRequest = (HttpServletRequest)request;
//			if(httpRequest.getHeader("role")!=null && httpRequest.getHeader("role").equals("TEACHER_ROLE"))
//				chain.doFilter(request, response);
//			else 
//			{ 	
//				HttpServletResponse httpResponse = (HttpServletResponse)response;
//				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"User unauthorized");				
//			}
//	}
//
//}
