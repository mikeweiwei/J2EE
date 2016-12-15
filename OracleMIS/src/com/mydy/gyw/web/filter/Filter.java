package com.mydy.gyw.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class Filter implements javax.servlet.Filter {
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain filter) throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		
		
		String method = httprequest.getMethod();
		if("post".equals(method)){
			httprequest.setCharacterEncoding("utf-8");
		}else if("get".equals(method)){
			httprequest = new HttpServletRequestWrapper(httprequest){
				@Override
				public String getParameter(String name) {
					
					String Name =  super.getParameter(name);
					try {
						Name = new String(Name.getBytes("ISO-8895-1"),"UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					return Name;
				}
			};
			
		}
		httpresponse.setContentType("text/html");
		httpresponse.setCharacterEncoding("utf-8");
		filter.doFilter(httprequest, httpresponse);
	  
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
