package com.mydy.gyw.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mydy.gyw.bean.Gyw_dept;
import com.mydy.gyw.daoImp.DeptImp;

public class DeptServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("showdept".equals(method)){
			showdept(request, response);
		}else if("updateDept".equals(method)){
			updateDept(request, response);
		}if("findDept".equals(method)){
			findDept(request, response);
		}else if("deleteDept".equals(method)){
			deleteDept(request, response);
		}if("insertDept".equals(method)){
			insertDept(request, response);
		}
	}
	protected void showdept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DeptImp deptimp = new DeptImp();
		List<Map<String, Object>> list = deptimp.ShowDept();
		request.getSession().setAttribute("deptlist", list);
//		System.out.println(list);
		request.getRequestDispatcher("/ShowDept.jsp").forward(request, response);
	}
	protected void updateDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("deptno");
		int deptno = Integer.parseInt(id);
		String dname = request.getParameter("dname");
		dname = new String(dname.getBytes("iso-8859-1"),"utf-8"); 
		String loc = request.getParameter("loc");
		loc = new String(loc.getBytes("iso-8859-1"),"utf-8"); 
		Gyw_dept dept = new Gyw_dept();
		dept.setDeptno(deptno);
		dept.setDname(dname);
		dept.setLoc(loc);
//		System.out.println(dept);
		DeptImp deptimp = new DeptImp();
		boolean b = deptimp.ModifiyDept(dept);
		if(b){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("updateDept", "部门信息修改成功！");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}
	protected void findDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("deptno");
		int deptno = Integer.parseInt(id);
		DeptImp deptimp = new DeptImp();
		Gyw_dept dept = deptimp.FindDept(deptno);
		request.setAttribute("deptno", dept.getDeptno());
		request.setAttribute("dname", dept.getDname());
		request.setAttribute("loc", dept.getLoc());
		request.getRequestDispatcher("/modifyDept.jsp").forward(request, response);
	}
	protected void deleteDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("deptno");
		int deptno = Integer.parseInt(id);
		DeptImp deptimp = new DeptImp();
		boolean b = deptimp.DeleteDept(deptno);
		if(b){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("deleteDept", "部门信息删除成功！");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
	}
	protected void insertDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("deptno");
		int deptno = Integer.parseInt(id);
		String dname = request.getParameter("dname");
		dname = new String(dname.getBytes("iso-8859-1"),"utf-8"); 
		String loc = request.getParameter("loc");
		loc = new String(loc.getBytes("iso-8859-1"),"utf-8"); 
		Gyw_dept dept = new Gyw_dept();
		dept.setDeptno(deptno);
		dept.setDname(dname);
		dept.setLoc(loc);
//		System.out.println(dept);
		DeptImp deptimp = new DeptImp();
		boolean b = deptimp.InsertDept(dept);
		if(b){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("insertDept", "部门信息添加成功！");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}
	
}
