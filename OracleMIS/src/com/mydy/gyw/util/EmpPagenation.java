package com.mydy.gyw.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpPagenation {
	private Connection con = null;

	private int pageCode; //当前页的 页码
	private int pageSize; //每页显示的记录数
	private int num; //当前页码的增量(若当前页码为5, 则其前有 5-num个页码, 后有5+num个页码；
						//例如: num=2; 当前为5; 则Web页上有 3 4 5 6 7 的页码显示)
	
	private String sqlCount = "select count(1) from tb_users";; //统计记录数的sql语句
	private int countRows; //记录总数
	
	/**
	 * 
	 * @param pageCode 	当前页的码
	 * @param pageSize 	每页显示的记录数
	 * @param num		当前页码的增量(其作用决定每个Web页要的页码情况)
	 * @param sql　		统计当前表中符合条件的记录数总数的sql语句
	 */
	public EmpPagenation(Connection con, int pageCode, int pageSize, int num) {
		this.con = con;
		this.pageCode = pageCode;
		this.pageSize = pageSize;
		this.num = num;
		
		//this.sqlCount = sql;
		this.countRows = this.countRows();
	}
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}

	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSqlCount() {
		return sqlCount;
	}
	public void setSqlCount(String sqlCount) {
		this.sqlCount = sqlCount;
	}
	public int getCountRows() {
		return countRows;
	}
	public void setCountRows(int countRows) {
		this.countRows = countRows;
	}

	//1 统计总的记录数
	private int countRows(){
		String sql = "select count(1) from gyw_emp";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if( rs.next() ){
				count = rs.getInt( 1 );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 2 一页上显示的页码数 
	 * @return
	 * 		当web页所要显示的页码
	 */
	public List<Map<String, Object>> pageCodes(){
		int totalPages = (int)(Math.ceil(countRows*1.0/pageSize)); //总页数
		
		List<Map<String, Object>> pages = new ArrayList<Map<String,Object>>();
		int startPage = (pageCode - num) < 1 ? 1 : (pageCode - num);
		int endPage = (pageCode + num ) > totalPages ? totalPages : (pageCode + num );
		for(int i = startPage ; i <= endPage;  i++ ){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageCode", i);
			pages.add( map );
		}
		return pages;
	}
	
	/**
	 * 3 存放首页＼末页＼当前页＼下一页＼上一页　页码．
	 * @param pageCode 
	 * 			当前页 页码
	 * @param pageSize
	 * 			当前页显示的记录数
	 * @param num 当前页码的增量(若当前页码为5, 则其前有 5-num, 后有5+num)　
	 * @return
	 */
	public Map<String, Object> pageSECPNT(){
		int totalPages = (int)(Math.ceil(countRows*1.0/pageSize)); //总页数
		
		int pageStart = 1; //首页
		int pageEnd = totalPages; //末页
		int pageCurrent = pageCode; //当前页
		int nextPage = (pageCode + 1) > pageEnd ? pageEnd : (pageCode + 1); 
		int previousPage = (pageCode - 1) < 1 ? 1 : (pageCode-1); 
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("pageStart", pageStart);
		map.put("pageEnd", pageEnd);
		map.put("pageCurrent", pageCurrent);
		map.put("nextPage", nextPage);
		map.put("previousPage", previousPage);
		map.put("totalPages", totalPages);
		
		return map;
	}
	
	//4 显示某页的用户信息
	public List<Map<String, Object>> showPage() {
		List<Map<String, Object>> usersList = new ArrayList<Map<String, Object>>();
		                     
		String sql = " select * from ( select  empno, ename, job,mgr,hiredate,sal,comm,deptno, rownum RNO from gyw_emp) where RNO between ? and ?";
		//sql = sqlString;
		int startNum = 1 + (pageCode - 1) * pageSize;
		int endNum = pageCode * pageSize;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			 //empno, ename, job,mgr,hiredate,sal,comm,deptno,rno
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> u1 = new HashMap<String, Object>();
				u1.put("empno", rs.getObject("empno"));
				u1.put("ename", rs.getString("ename"));
				u1.put("job", rs.getString("job"));
				u1.put("mgr", rs.getObject("mgr"));
				u1.put("hiredate", String.format("%tF",rs.getDate("hiredate")));
				u1.put("sal", rs.getObject("sal"));
				u1.put("comm", rs.getObject("comm"));
				u1.put("deptno", rs.getObject("deptno"));
				u1.put("rno", rs.getInt("rno"));
				usersList.add(u1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersList;
	}	
	
	
	//通常方法(暂时不用)
	public List<Map<String, Object>> showPageGen( String sqlString ) {
		List<Map<String, Object>> usersList = new ArrayList<Map<String, Object>>();
		String sql = "select * from ( select ID, UNAME, PASSWORD, REGISTER, PHONE, rownum RNO from tb_users) where RNO between ? and ?";
		sql = sqlString;
		int startNum = 1 + (pageCode - 1) * pageSize;
		int endNum = pageCode * pageSize;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();//获取所有字段列表清单
			int fieldNum = metaData.getColumnCount(); //获取字段的个数
			
			while (rs.next()) {
				Map<String, Object> u1 = new HashMap<String, Object>();
				
				//一个记录的所有字段
				for( int i=1; i<= fieldNum; i++){
					String columnName = metaData.getColumnName(1);
					Object columnValue = rs.getObject(columnName);
					u1.put(columnName, columnValue);
				}				
				usersList.add(u1);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return usersList;
	}	
}
