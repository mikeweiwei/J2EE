package com.mydy.gyw.dao;

import java.util.List;
import java.util.Map;

import com.mydy.gyw.bean.Gyw_emp;

public interface EmpDao {
	//添加emp
	public boolean insert(Gyw_emp emp);
	//根据id查员工
	public boolean queryId(int id);
	//查询所有job
	public List<Map<String, Object>> queryJob();
	//更员工信息
	public boolean updateEmp(Gyw_emp emp);
	//删除员工信息
	public boolean deleteEmp(int id);
	//查询经理编号
	public List<Map<String, Object>> queryManager();
	//(查询部门信息)
	public List<Map<String, Object>> queryDept();
	//查詢一个员工根据empno
	public List<Map<String, Object>> EmpbyEmpno(int empno);
	//查詢一个员工根据ename
	public List<Map<String, Object>> EmpbyEname(String ename);
	//emp,dept多表查询
	public List<Map<String, Object>> EmpDept();
	
}
