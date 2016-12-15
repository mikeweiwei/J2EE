<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'insertDept.jsp' starting page</title>
    
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
    <h3>添加部門</h3>
    <hr/>
    <form action="deptServlet.action?method=insertDept"  method="post">
	    <table border="1" cellspacing="0" width="75%" >
	    	<tr>	
		    	<th>部門编号</th>
		    	<td><input type="text" name="deptno" id="deptno" value=""/>
		    	</td>
		    </tr>
		    <tr>	
		    	<th>部門名称</th>
		    	<td><input type="text" name="dname" id="dname" value=""/></td>
		    </tr>
	    	<tr>
		    	<th>部门地址</th>
		   		<td><input type="text" name="loc" id="loc" value=""/></td>
		    </tr>
		    
	    	<tr>
		    	<th>操作</th>
		    	<td><input type="submit" value="添加" /></td>
		    </tr>
	    </table>
    </form>
  </body>
</html>
