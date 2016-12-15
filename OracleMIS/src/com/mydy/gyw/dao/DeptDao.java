package com.mydy.gyw.dao;

import java.util.List;
import java.util.Map;

import com.mydy.gyw.bean.Gyw_dept;

public interface DeptDao {
	//查詢所有部门表
	public List<Map<String, Object>> ShowDept();
	//添加部门信息
	public boolean InsertDept(Gyw_dept dept);
	//删除部门信息
	public boolean DeleteDept(int deptno);
	//修改部门信息
	public boolean ModifiyDept(Gyw_dept dept);
	//deptno--->dept
	public Gyw_dept FindDept(int deptno);
}
