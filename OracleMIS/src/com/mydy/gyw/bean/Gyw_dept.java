package com.mydy.gyw.bean;

public class Gyw_dept {
	private Number deptno;
	private String dname;
	private String loc;
	public Gyw_dept() {
	}
	public Number getDeptno() {
		return deptno;
	}
	public void setDeptno(Number deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	@Override
	public String toString() {
		return "Gyw_dept [deptno=" + deptno + ", dname=" + dname + ", loc="
				+ loc + "]";
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
