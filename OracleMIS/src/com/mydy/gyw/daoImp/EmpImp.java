package com.mydy.gyw.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mydy.gyw.bean.Gyw_emp;
import com.mydy.gyw.dao.EmpDao;
import com.mydy.gyw.util.DButil;



public class EmpImp implements EmpDao{

	private Connection conn = DButil.getconoforacle();
	@Override
	public boolean insert(Gyw_emp emp) {
		String sql = "insert into gyw_emp values(?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,?)";
		PreparedStatement pstmt = null;
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setObject(1, emp.getEmpno());
			 pstmt.setObject(2, emp.getEname());
			 pstmt.setObject(3, emp.getJob());
			 pstmt.setObject(4, emp.getMgr());
			 pstmt.setObject(5, emp.getHiredate());
			 pstmt.setObject(6, emp.getSal());
			 pstmt.setObject(7, emp.getComm());
			 pstmt.setObject(8, emp.getDeptno());
			 int i = pstmt.executeUpdate();
			 if(i > 0){
				 return true;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.closeAll(null, pstmt, null);
		}
		
		return false;
	}
	@Override
	public boolean queryId(int id) {
		String sql = "select * from gyw_emp where empno = ?";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, id);
			res = pstmt.executeQuery();
			if(res.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Map<String, Object>> queryJob() {
		List<Map<String, Object>> usersList = new ArrayList<Map<String, Object>>();
        String sql = "select distinct job from gyw_emp";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> u1 = new HashMap<String, Object>();
				u1.put("job", rs.getString("job"));
				usersList.add(u1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersList;
	}
	@Override
	public List<Map<String, Object>> queryManager() {
		List<Map<String, Object>> usersList = new ArrayList<Map<String, Object>>();
        String sql = "select empno from gyw_emp where job = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "MANAGER");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> u1 = new HashMap<String, Object>();
				u1.put("manager", rs.getObject("empno"));
				usersList.add(u1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersList;
	}
	@Override
	public List<Map<String, Object>> queryDept() {
		List<Map<String, Object>> usersList = new ArrayList<Map<String, Object>>();
        String sql = "select * from gyw_dept";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> u1 = new HashMap<String, Object>();
				u1.put("deptno", rs.getObject("deptno"));
				u1.put("dname", rs.getObject("dname"));
				usersList.add(u1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersList;
	}
	@Override
	public List<Map<String, Object>> EmpbyEmpno(int empno) {
		List<Map<String, Object>> usersList = new ArrayList<Map<String, Object>>();
		String sql = "select * from gyw_emp where empno = ?";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, empno);
			res = pstmt.executeQuery();
			while(res.next()){
				Map<String, Object> u1 = new HashMap<String, Object>();
				u1.put("empno", res.getInt("empno"));
				u1.put("ename", res.getObject("ename"));
				u1.put("job", res.getObject("job"));
				u1.put("mgr", res.getObject("MGR"));
				u1.put("hiredate", String.format("%tF", res.getDate("hiredate")));
				u1.put("sal", res.getObject("sal"));
				u1.put("comm", res.getObject("comm"));
				String dname = QueryDeptnameByDeptno(res.getInt("deptno"));
				u1.put("deptno", res.getInt("deptno"));
				usersList.add(u1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}
	@Override
	public List<Map<String, Object>> EmpbyEname(String ename) {
		List<Map<String, Object>> usersList = new ArrayList<Map<String, Object>>();
		String sql = "select * from gyw_emp where ename = ?";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, ename);
			res = pstmt.executeQuery();
			while(res.next()){
				Map<String, Object> u1 = new HashMap<String, Object>();
				u1.put("empno", res.getInt("empno"));
				u1.put("ename", res.getObject("ename"));
				u1.put("job", res.getObject("job"));
				u1.put("mgr", res.getObject("MGR"));
				u1.put("hiredate", String.format("%tF", res.getDate("hiredate")));
				u1.put("sal", res.getObject("sal"));
				u1.put("comm", res.getObject("comm"));
				String dname = QueryDeptnameByDeptno(res.getInt("deptno"));
				u1.put("deptno", res.getInt("deptno"));
				usersList.add(u1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}
	
	public String QueryDeptnameByDeptno(int deptno){
		String sql = "select dname from gyw_dept where deptno = ?";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, deptno);
			res = pstmt.executeQuery();
			while(res.next()){
				return res.getString("dname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public boolean updateEmp(Gyw_emp emp) {
		String sql = "update gyw_emp set empno=?,ename=?,job=?,mgr=?,"
				+ "hiredate=to_date(?,'YYYY-MM-DD'),sal=?,comm=?,deptno=? where empno = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, emp.getEmpno());
			 pstmt.setObject(2, emp.getEname());
			 pstmt.setObject(3, emp.getJob());
			 pstmt.setObject(4, emp.getMgr());
			 pstmt.setObject(5, emp.getHiredate());
			 pstmt.setObject(6, emp.getSal());
			 pstmt.setObject(7, emp.getComm());
			 pstmt.setObject(8, emp.getDeptno());
			 pstmt.setObject(9, emp.getEmpno());
			 
			int i = pstmt.executeUpdate();
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean deleteEmp(int id) {
		String sql = "delete from gyw_emp where empno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, id);
			int i = pstmt.executeUpdate();
			if(i > 0){
				conn.commit();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public List<Map<String, Object>> EmpDept() {
		List<Map<String, Object>> usersList = new ArrayList<Map<String, Object>>();
		String sql = "select empno,ename,a.deptno,dname from gyw_emp a,gyw_dept b where a.deptno=b.deptno";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()){
				Map<String, Object> u1 = new HashMap<String, Object>();
				u1.put("empno", res.getInt("empno"));
				u1.put("ename", res.getObject("ename"));
				u1.put("deptno", res.getInt("deptno"));
				u1.put("dname", res.getObject("dname"));
				usersList.add(u1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

}
