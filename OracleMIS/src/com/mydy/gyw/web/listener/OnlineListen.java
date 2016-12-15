package com.mydy.gyw.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineListen implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext context = se.getSession().getServletContext();
		//假如有count属性
		Integer i = (Integer)context.getAttribute("count");
		Integer i_history = (Integer)context.getAttribute("sum");
		if(i == null){
			i = new Integer(1);
			i_history= new Integer(1);
		}else{
			i++;
			i_history++;
		}
		context.setAttribute("count", i);
		context.setAttribute("sum", i_history);
		System.out.println("目前在线"+i+"个人");
		
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		int i = (Integer)se.getSession().getServletContext().getAttribute("count");
		int sum = (Integer)se.getSession().getServletContext().getAttribute("sum");
		if(i > 0){
			i--;
		}	
		se.getSession().getServletContext().setAttribute("count", i);
		System.out.println("-------------------------------------------");
		System.out.println("当前在线人数"+i+"个人");
		System.out.println("-------------------------------------------");
	}

}
