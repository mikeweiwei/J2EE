<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更新dept</title>


  </head>
  
  <body>
    <form action="deptServlet.action?method=updateDept"  method="post">
    	<table border="1" cellspacing="0" width="75%" >
	    	<%-- empno, ename, job,mgr,hiredate,sal,comm,deptno--%>
	    	<tr>	
		    	<th>部门编号</th>
		    	<td><input type="text" name="deptno" id="deptno" value="${deptno}"/>
		    	</td>
		    </tr>
		    <tr>	
		    	<th>部门名称</th>
		    	<td><input type="text" name="dname" id="dname" value="${dname}"/></td>
		    </tr>
	    	<tr>
		    	<th>部门地址</th>
		    	<td><input type="text" name="loc" id="loc" value="${loc}"/></td>
		    	
		    </tr>
	    	<tr>
		    	<th>操作</th>
		    	<td><input type="submit" value="修改" /></td>
		    </tr>
	    </table>
    
    </form>	
  </body>
</html>
