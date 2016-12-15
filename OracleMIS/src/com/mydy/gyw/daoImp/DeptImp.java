package com.mydy.gyw.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mydy.gyw.bean.Gyw_dept;
import com.mydy.gyw.dao.DeptDao;
import com.mydy.gyw.util.DButil;

public class DeptImp implements DeptDao{
	Connection conn = DButil.getconoforacle();
	@Override
	public List<Map<String, Object>> ShowDept() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from gyw_dept";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("deptno", res.getInt("deptno"));
				map.put("dname", res.getString("dname"));
				map.put("loc", res.getString("loc"));
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.closeAll(res, pstmt, null);
		}
		return list;
	}

	@Override
	public boolean InsertDept(Gyw_dept dept) {
		String sql = " insert into gyw_dept values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setObject(1, dept.getDeptno());
			 pstmt.setObject(2, dept.getDname());
			 pstmt.setObject(3, dept.getLoc());
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
	public boolean DeleteDept(int deptno) {
		String sql = "delete from gyw_dept where deptno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, deptno);
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
	public boolean ModifiyDept(Gyw_dept dept) {
		String sql = "update gyw_dept set deptno=?,dname=?,loc=? where deptno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, dept.getDeptno());
			pstmt.setObject(2, dept.getDname());
			pstmt.setObject(3, dept.getLoc());
			pstmt.setObject(4, dept.getDeptno());
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
	public Gyw_dept FindDept(int deptno) {
		Gyw_dept dept = new Gyw_dept();
		String sql = "select * from gyw_dept where deptno = ?";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, deptno);
			res = pstmt.executeQuery();
			while(res.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				dept.setDeptno(res.getInt("deptno"));
				dept.setLoc(res.getString("loc"));
				dept.setDname(res.getString("dname"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.closeAll(res, pstmt, null);
		}
		return dept;
	}

}
