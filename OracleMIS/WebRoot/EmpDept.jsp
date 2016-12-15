<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'EmpDept.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
		<tr bgcolor="#E7E7E7">
			<th height="24" colspan="15" align="left">&nbsp;多表&nbsp;</th>
		</tr>
		<!-- //empno, ename, job,mgr,hiredate,sal,comm,deptno,rno-->
		<tr align="center" bgcolor="#FAFAF1" height="22">
			<th width="5%">員工編號</th>
			<th width="6%">员工姓名</th>
			<th width="8%">部门编号</th>
			<th width="6%">部门名称</th>
		</tr>
		
		<c:forEach items="${list}" var="map">
			<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
			
			<td width="6%">${map.empno}</td>
			<td width="6%">${map.ename}</td>
			<td width="8%">${map.deptno}</td>
			<td width="8%">${map.dname}</td>

			</tr>
		</c:forEach>
	</table>
  </body>
</html>
