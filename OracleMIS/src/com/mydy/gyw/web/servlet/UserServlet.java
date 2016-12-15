package com.mydy.gyw.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mydy.gyw.bean.Gyw_user;
import com.mydy.gyw.daoImp.UserImp;
import com.mydy.gyw.util.DButil;
import com.mydy.gyw.util.UserPagenation;



public class UserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");

		if("login".equals(method)){
			login(request, response);
		}else if ("regUser".equals(method)){
			try {
				insert(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}if("check".equals(method)){
			try {
				QureyName(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}if("updateUserById".equals(method)){
		
				QueryByid(request, response);
			}
		if("Delete".equals(method)){
			try {
				DeleteByName(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}if("qureyuser".equals(method)){
			try {
				qureyuser(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}if("modify".equals(method)){
			try {
				modify(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}if("insertUser".equals(method)){
			try {
				insertUser(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
	}
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("uname");
		String password = request.getParameter("pwd");
		name = new String(name.getBytes("iso-8859-1"),"utf-8"); 
		ServletContext ctx = getServletContext();
		ctx.setAttribute("name", name);
		//System.out.println(request.getAttribute("name"));
		Gyw_user user = new Gyw_user();
		user.setUname(name);
		user.setUpassword(Integer.parseInt(password));
		UserImp userimp = new UserImp();
		if(userimp.QueryUser(user)){
			request.getSession().setAttribute("login", name);
			request.getRequestDispatcher("/index.html").forward(request, response);
		}else{
			ctx.setAttribute("error", "用戶名或密码错误！");
			response.sendRedirect("login.jsp");
			
		}
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String name = request.getParameter("uname");
		name = new String(name.getBytes("iso-8859-1"),"utf-8"); 
		String password = request.getParameter("upwd");
		String phone = request.getParameter("utel");
		Gyw_user user = new Gyw_user();
		user.setUname(name);
		user.setUpassword(Integer.parseInt(password));
		user.setPhone(NumberFormat.getInstance().parse(phone));
		
		UserImp userimp = new UserImp();
		if(userimp.insertUser(user)){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("success", "注册成功！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			response.sendRedirect("register.jsp");
		}
	}
	protected void QureyName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String name = request.getParameter("name");
		name = java.net.URLDecoder.decode(name,"utf-8");
		UserImp userimp = new UserImp();
		response.setContentType("text/html"); 
		response.setCharacterEncoding("utf-8");
		boolean b = userimp.QueryName(name);
		PrintWriter out = response.getWriter(); 
		if(b){
			out.print("true"); 
		}else{
			out.print("false"); 
		}
		out.flush();
		out.close();
	}
	protected void QueryByid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = DButil.getconoforacle();
		String id2 = request.getParameter("id");
		int id = Integer.parseInt(id2);
		UserPagenation user = new UserPagenation(con, id, 3, 2);
		
		List<Map<String, Object>> pageCodes = user.pageCodes();
		Map<String, Object> pageSECPNT = user.pageSECPNT();
		List<Map<String, Object>> showPage = user.showPage();
		
		request.getSession().setAttribute("pageCodes", pageCodes);
		request.getSession().setAttribute("pageSECPNT", pageSECPNT);
		request.getSession().setAttribute("showPage", showPage);
		
		request.getRequestDispatcher("/ShowUser.jsp").forward(request, response);
	}
	
	protected void DeleteByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String name = request.getParameter("name");
		name = java.net.URLDecoder.decode(name,"utf-8");
		UserImp userimp = new UserImp();
		boolean b = userimp.deleteUser(name);
		if(b){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("delete", "删除成功");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
	}
	protected void qureyuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String name = request.getParameter("name");
		name = new String(name.getBytes("iso-8859-1"),"utf-8"); 
		UserImp userimp = new UserImp();
		Gyw_user user = userimp.modify_query(name);
		request.setAttribute("name", user.getUname());
		request.setAttribute("password", user.getUpassword());
		request.setAttribute("phone", user.getPhone());
		request.getRequestDispatcher("/modifyUser.jsp").forward(request, response);
		
	}
	protected void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String name = request.getParameter("uname");
		name = new String(name.getBytes("iso-8859-1"),"utf-8"); 
		String password = request.getParameter("upwd");
		String phone = request.getParameter("utel");
		Gyw_user user = new Gyw_user();
		user.setUname(name);
		user.setUpassword(Integer.parseInt(password));
		user.setPhone(NumberFormat.getInstance().parse(phone));
		
		UserImp userimp = new UserImp();
		boolean b = userimp.modify(user);
		if(b){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("modify", "修改成功");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
	}
	protected void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String name = request.getParameter("uname");
		name = new String(name.getBytes("iso-8859-1"),"utf-8"); 
		String password = request.getParameter("upwd");
		String phone = request.getParameter("utel");
		Gyw_user user = new Gyw_user();
		user.setUname(name);
		user.setUpassword(Integer.parseInt(password));
		user.setPhone(NumberFormat.getInstance().parse(phone));
		
		UserImp userimp = new UserImp();
		if(userimp.insertUser(user)){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("success", "添加成功！");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}

}
