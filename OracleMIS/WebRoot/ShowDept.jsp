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
    
    <title>部门表</title>
   

  </head>
  
  <body>
    <table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
		<tr bgcolor="#E7E7E7">
			<th height="24" colspan="15" align="left">&nbsp;分页查询员工表&nbsp;</th>
		</tr>
		<!-- //empno, ename, job,mgr,hiredate,sal,comm,deptno,rno-->
		<tr align="center" bgcolor="#FAFAF1" height="22">
			<th width="5%">选择</th>
			<th width="6%">部门编号</th>
			<th width="8%">部门名称</th>
			<th width="6%">部门地址</th>
			<th width="6%">操作</th>
		</tr>
		
		<c:forEach items="${deptlist}" var="map">
			<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
			
			<td><input type="checkbox" name="checkItem" id="checkItem" value="${map.deptno}" class="np"></td>
			<td width="6%">${map.deptno}</td>
			<td width="6%">${map.dname}</td>
			<td width="8%">${map.loc}</td>

			<td width="10%"> 
				<a href="deptServlet.action?method=deleteDept&deptno=${map.deptno}">删 除</a>
				<a href="deptServlet.action?method=findDept&deptno=${map.deptno}">编辑</a>
				</td>
			</tr>
		</c:forEach>
	</table>
  </body>
</html>
