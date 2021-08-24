package com.zbodya.Filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;


@WebFilter
@Order(1)
public class CountTImeFilter implements Filter
{
	
	Logger LOG = LoggerFactory.getLogger(getClass().getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long stopTime = System.currentTimeMillis();
        LOG.info("Time of request is: " + (stopTime - startTime) + " ms.");
	}

}
