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
	<title>平台登录</title>
	<meta charset="utf-8" />	
	<style type="text/css">
			a:link{
				text-decoration: none;
			}
		</style>
	</head>
	<body>
    <form action="userServlet.action?method=login" method="post">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  		<tr><td bgcolor="#e5f6cf">&nbsp;</td></tr>
  		<tr><td height="608" background="img/login_03.gif">
  			<table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      		<tr><td height="266" background="img/login_04.gif">&nbsp;</td></tr>
      		<tr><td height="95">
      			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          	<tr>
	            <td width="424" height="95" background="img/login_06.gif">&nbsp;</td>
	            <td width="183" background="img/login_07.gif">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	              	<tr>
	                <td width="21%" height="30"><div align="center"><span>用户</span></div></td>
	                <td width="79%" height="30">
	                <input type="text" name="uname"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
	              	</tr>
	              	<tr>
	                <td height="30"><div align="center"><span>密码</span></div></td>
	                <td height="30">
	                <input type="password" name="pwd"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
	              	</tr>
	              	<tr>
	                <td height="30">&nbsp;</td>
	                <td height="30">
	                	<input type="submit" value="登  陆"/>&nbsp;
	                	<a href="register.jsp"><input type="button" value="注  册"/></a> 
	                </td>
	              	</tr>
	            	</table>
          		</td>
            	<td width="255" background="img/login_08.gif">&nbsp;</td>
          		</tr>
        		</table>
        		</td>
      		</tr>
      		<tr>
        		<td height="247" valign="top" background="img/login_09.gif">
        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          		<tr>
	            <td width="22%" height="30">&nbsp;</td>
	            <td width="56%">&nbsp;</td>
	            <td width="22%">&nbsp;</td>
          		</tr>
          		<tr>
	            <td>&nbsp;</td>
	            <td height="30">
		            <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              	<tr>
	                <td width="44%" height="20">&nbsp;</td>
	                <td width="56%">版本 2016V1.6 </td>
	              	</tr>
            		</table>
            	</td>
            	<td>&nbsp;</td>
          		</tr>
        		</table>
        		</td>
      		</tr>
    	</table>
    	</td>
  		</tr>
		<tr><td bgcolor="#a2d962">&nbsp;</td></tr>
	</table>
	</form>
	<c:if test="${error != null}"><script type="text/javascript">
	alert("${error}");
	</script></c:if>
	<c:remove var="error"/>
	<c:if test="${success != null}"><script type="text/javascript">
	alert("${success}");
	</script></c:if>
	<c:remove var="success"/>
</body>
</html>
