package com.mydy.gyw.web.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("show".equals(method)){
			showlog(request, response);
		}if("delete".equals(method)){
			delete(request, response);
		}
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sPath = "E:/tom/apache-tomcat-8.0.32/webapps/OracleMIS/logs/RequestLogs.txt";
		FileOutputStream out = new FileOutputStream(sPath,false);
		out.write(new String("").getBytes());
		out.close(); 
		request.setAttribute("logdelete", "日志删除成功");
		request.getRequestDispatcher("/main.jsp").forward(request, response);
		 
	}
	protected void showlog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Map<String, Object>> pages = new ArrayList<Map<String,Object>>();
		Scanner in = new Scanner(new File("E:/tom/apache-tomcat-8.0.32/webapps/OracleMIS/logs/RequestLogs.txt"));
		while (in.hasNextLine()) {
			Map<String, Object> map = new HashMap<String, Object>();
            String str = in.nextLine();
            String[] split = str.split("#");
            map.put("time", split[0]);
            map.put("ip", split[1]);
            map.put("uri", split[2]);
            pages.add( map );
        }
		request.setAttribute("logs", pages);
		request.getRequestDispatcher("/logs.jsp").forward(request, response);
	}
	
	
	 
}
