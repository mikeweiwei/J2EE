package com.mydy.gyw.bean;
// EMPNO ENAME JOB  MGR HIREDATE    SAL  COMM   DEPTNO 
public class Gyw_emp {
	private Number empno;
	private String ename;
	private String job;
	private Number mgr;
	private String hiredate;
	private Number sal;
	private Number comm;
	private Number deptno;
	public Gyw_emp() {
	}
	public Number getEmpno() {
		return empno;
	}
	public void setEmpno(Number empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Number getMgr() {
		return mgr;
	}
	public void setMgr(Number mgr) {
		this.mgr = mgr;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public Number getSal() {
		return sal;
	}
	public void setSal(Number sal) {
		this.sal = sal;
	}
	public Number getComm() {
		return comm;
	}
	public void setComm(Number comm) {
		this.comm = comm;
	}
	public Number getDeptno() {
		return deptno;
	}
	public void setDeptno(Number deptno) {
		this.deptno = deptno;
	}
	
}
