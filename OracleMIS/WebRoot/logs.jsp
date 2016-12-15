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
    
    <title>My JSP 'logs.jsp' starting page</title>
    
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
		<!--  快速转换位置按钮  -->
		<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
		<tr>
 			<td height="26" align="center">
  				日志
			</td>
		</tr>
		</table>
  
		<!--  内容列表   -->
		

		<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
		<tr bgcolor="#E7E7E7">
			<th height="24" colspan="15" align="left">&nbsp;日志&nbsp;</th>
		</tr>
		<!--ID, UNAME, PASSWORD, REGISTER, PHONE, rownum RNO-->
		<tr align="center" bgcolor="#FAFAF1" height="22">
			<th width="5%">时间</th>
			<th width="6%">IP：端口</th>
			<th width="8%">URI</th>
		</tr>
		<c:forEach items="${logs}" var="map">
			<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
			
			
			<td width="6%">${map.time}</td>
			<td width="8%">${map.ip}</td>
			<td width="5%">${map.uri}</td>
			</tr>
		</c:forEach>
	</table>

	</body>
</html>
