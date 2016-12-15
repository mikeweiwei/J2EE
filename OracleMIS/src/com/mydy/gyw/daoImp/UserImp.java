package com.mydy.gyw.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mydy.gyw.bean.Gyw_user;
import com.mydy.gyw.dao.UserDao;
import com.mydy.gyw.util.DButil;

public class UserImp implements UserDao{
	
	private Connection conn = DButil.getconoforacle();
	@Override
	public boolean QueryUser(Gyw_user user) {
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select * from gyw_user where uname = ? and upassword = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, user.getUname());
			pstmt.setObject(2, user.getUpassword());
			res = pstmt.executeQuery();
			while(res.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertUser(Gyw_user user){
		PreparedStatement pstmt = null;
		String sql = " insert into gyw_user values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, user.getUname());
			pstmt.setObject(2, user.getUpassword());
			pstmt.setObject(3, user.getPhone());
			int i = pstmt.executeUpdate();
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean QueryName(String name){
		String sql = "select * from gyw_user where uname = ?";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, name);
			res = pstmt.executeQuery();
			while(res.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean deleteUser(String name) {
		PreparedStatement pstmt = null;
		String sql = "delete from gyw_user where uname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, name);
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
	public boolean modify(Gyw_user user) {
		String sql = " update gyw_user set uname=?,upassword=?,phone=? where uname=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, user.getUname());
			pstmt.setObject(2, user.getUpassword());
			pstmt.setObject(3, user.getPhone());
			pstmt.setObject(4, user.getUname());
			int i = pstmt.executeUpdate();
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Gyw_user modify_query(String name){
		String sql = "select * from gyw_user where uname = ?";
		PreparedStatement pstmt = null;
		ResultSet res = null;
		Gyw_user user = new Gyw_user();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setObject(1, name);
				res = pstmt.executeQuery();
				while(res.next()){
					user.setUname(res.getString("uname"));
					user.setUpassword(res.getInt("upassword"));
					user.setPhone((Number) res.getObject("phone"));
					//System.out.println(res.getObject("phone"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		return user;
		
	}
	
}
