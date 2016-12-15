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

import net.sf.json.JSONArray;

import com.mydy.gyw.bean.Gyw_emp;
import com.mydy.gyw.daoImp.EmpImp;
import com.mydy.gyw.daoImp.UserImp;
import com.mydy.gyw.util.DButil;
import com.mydy.gyw.util.EmpPagenation;

public class EmpServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("showemp".equals(method)){
			showEmp(request, response);
		}else if("queryid".equals(method)){
			try {
				QureyId(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}if("QureyJob".equals(method)){
			try {
				QureyJob(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("QureyManager".equals(method)){
			try {
				QureyManager(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}if("QureyDept".equals(method)){
			try {
				QureyDept(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("insertEmp".equals(method)){
			try {
				insertEmp(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}if("FindEmp".equals(method)){
			try {
				FindEmp(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("updateEmp".equals(method)){
			try {
				updateEmp(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}if("deleteEmp".equals(method)){
			try {
				deleteEmp(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("empdept".equals(method)){
			empdept(request, response);
		}
	}
	
	protected void showEmp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = DButil.getconoforacle();
		String id = request.getParameter("id");
		int pnum = Integer.parseInt(id);
		EmpPagenation emp = new EmpPagenation(con, pnum, 4, 2);
		
		List<Map<String, Object>> pageCodes = emp.pageCodes();
		Map<String, Object> pageSECPNT = emp.pageSECPNT();
		List<Map<String, Object>> showPage = emp.showPage();
		
		request.getSession().setAttribute("pageCodes", pageCodes);
		request.getSession().setAttribute("pageSECPNT", pageSECPNT);
		request.getSession().setAttribute("showPage", showPage);
		
		request.getRequestDispatcher("/ShowEmp.jsp").forward(request, response);
	}
	protected void QureyId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		int empno = 0;
		String id = request.getParameter("id");
		id = id.trim();
		if(id != null){
			empno = Integer.parseInt(id);
		}
		EmpImp empimp = new EmpImp();
		response.setContentType("text/html"); 
		response.setCharacterEncoding("utf-8");
		boolean b = empimp.queryId(empno);
		PrintWriter out = response.getWriter(); 
		if(b){
			out.print("true"); 
		}else{
			out.print("false"); 
		}
		out.flush();
		out.close();
	}
	protected void QureyJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		EmpImp empimp = new EmpImp();
		List<Map<String, Object>> list = empimp.queryJob();
		String job = JSONArray.fromObject( list ).toString();
		response.getWriter().print( job );
		
	}
	protected void QureyManager(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		EmpImp empimp = new EmpImp();
		List<Map<String, Object>> list = empimp.queryManager();
		String manager = JSONArray.fromObject( list ).toString();
		response.getWriter().print( manager );
		
	}
	protected void QureyDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		EmpImp empimp = new EmpImp();
		List<Map<String, Object>> list = empimp.queryDept();
		String manager = JSONArray.fromObject( list ).toString();
		response.getWriter().print( manager );
		
	}
	protected void insertEmp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		ename = new String(ename.getBytes("iso-8859-1"),"utf-8"); 
//		System.out.println(ename);
		String mgr = request.getParameter("mgr");
		String job = request.getParameter("job");
		String hiredate = request.getParameter("hiredate");
		String sal = request.getParameter("sal");
		String comm = request.getParameter("comm");
		String deptno = request.getParameter("deptno");
		Gyw_emp emp = new Gyw_emp();
		emp.setEmpno(NumberFormat.getInstance().parse(empno));
		emp.setEname(ename);
		emp.setJob(job);
		emp.setMgr(NumberFormat.getInstance().parse(mgr));
		emp.setHiredate(hiredate);
		emp.setSal(NumberFormat.getInstance().parse(sal));
		emp.setComm(NumberFormat.getInstance().parse(comm));
		emp.setDeptno(NumberFormat.getInstance().parse(deptno));
		EmpImp empimp = new EmpImp();
		boolean b = empimp.insert(emp);
		if(b){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("insertEmp", "员工添加成功！");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
	}
	
	protected void FindEmp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String idname = request.getParameter("idname");
		
		String regex = "\\d{1,5}";
		int id = 0;
		if( idname != null && idname.matches(regex) ){
			id = Integer.parseInt( idname );
			//System.out.println(id);
			EmpImp empimp = new EmpImp();
			List<Map<String, Object>> list = empimp.EmpbyEmpno(id);
			String emp = JSONArray.fromObject( list ).toString();
			//System.out.println(emp);
			response.getWriter().print( emp );
		}else{
			EmpImp empimp = new EmpImp();
			List<Map<String, Object>> list = empimp.EmpbyEname(idname);
			String emp = JSONArray.fromObject( list ).toString();
			//System.out.println(emp);
			response.getWriter().print( emp );
		}
		
	}
	protected void updateEmp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		ename = new String(ename.getBytes("iso-8859-1"),"utf-8"); 
		String mgr = request.getParameter("mgr");
		String job = request.getParameter("job");
		String hiredate = request.getParameter("hiredate");
		String sal = request.getParameter("sal");
		String comm = request.getParameter("comm");
		String deptno = request.getParameter("deptno");
		Gyw_emp emp = new Gyw_emp();
		emp.setEmpno(NumberFormat.getInstance().parse(empno));
		emp.setEname(ename);
		emp.setJob(job);
		emp.setMgr(NumberFormat.getInstance().parse(mgr));
		emp.setHiredate(hiredate);
		emp.setSal(NumberFormat.getInstance().parse(sal));
		emp.setComm(NumberFormat.getInstance().parse(comm));
		emp.setDeptno(NumberFormat.getInstance().parse(deptno));
		EmpImp empimp = new EmpImp();
		boolean b = empimp.updateEmp(emp);
		if(b){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("updateEmp", "员工修改成功！");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}
	protected void deleteEmp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String empno = request.getParameter("empno");
		int id = Integer.parseInt( empno );
//		System.out.println(id);
		EmpImp empimp = new EmpImp();
		boolean b = empimp.deleteEmp(id);
		if(b){
			ServletContext ctx = getServletContext();
			ctx.setAttribute("deleteEmp", "员工刪除成功！");
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
	}
	protected void empdept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmpImp empimp = new EmpImp();
		List<Map<String, Object>> list = empimp.EmpDept();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/EmpDept.jsp").forward(request, response);
	}
	
}
