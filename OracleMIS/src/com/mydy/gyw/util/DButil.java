package com.mydy.gyw.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DButil {
	static String url = null;
	static String driver = null;
	static String user = null;
	static String password = null;
	static Properties pro = new Properties();
	static {
		try {
			InputStream fin = null;
			fin = DButil.class.getClassLoader().getResourceAsStream("DBconfig.properties");
			pro.load(fin);

			// System.out.println(pro);
			url = pro.getProperty("url");
			driver = pro.getProperty("driver");
			user = pro.getProperty("user");
			password = pro.getProperty("password");

			Class.forName(driver);
//			System.out.println("加载驱动完成");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getconoforacle() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}
	public static void closeAll(ResultSet res,Statement stmt,Connection conn){
		if(res != null ){
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt != null ){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null ){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		
	}

}
