<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
		<title>main</title>
		
	<style>
		body{
			/*background: url(img/bjsxt.jpg);*/
			background: #9AD075;
		}
		div{
			margin-left: auto;
			margin-right: auto;
		}
	</style>
	
	</head>
	<body>
	<c:if test="${delete != null}"><script type="text/javascript">
	alert("${delete}");
	</script></c:if>
	<c:remove var="delete"/>
	<c:if test="${modify != null}"><script type="text/javascript">
	alert("${modify}");
	</script></c:if>
	<c:remove var="modify"/>
	<c:if test="${logdelete != null}"><script type="text/javascript">
	alert("${logdelete}");
	</script></c:if>
	<c:remove var="logdelete"/>
	<c:if test="${success != null}"><script type="text/javascript">
	alert("${success}");
	</script></c:if>
	<c:remove var="success"/>
	<c:if test="${insertEmp != null}"><script type="text/javascript">
	alert("${insertEmp}");
	</script></c:if>
	<c:remove var="insertEmp"/>
	<c:if test="${deleteEmp != null}"><script type="text/javascript">
	alert("${deleteEmp}");
	</script></c:if>
	<c:remove var="deleteEmp"/>
	<c:if test="${updateEmp != null}"><script type="text/javascript">
	alert("${updateEmp}");
	</script></c:if>
	<c:remove var="updateEmp"/>
	<c:if test="${updateDept != null}"><script type="text/javascript">
	alert("${updateDept}");
	</script></c:if>
	<c:remove var="updateDept"/>
	<c:if test="${deleteDept != null}"><script type="text/javascript">
	alert("${deleteDept}");
	</script></c:if>
	<c:remove var="deleteDept"/>
	<c:if test="${insertDept != null}"><script type="text/javascript">
	alert("${insertDept}");
	</script></c:if>
	<c:remove var="insertDept"/>
		<h3>欢迎你!${name}&nbsp;&nbsp;&nbsp;</h3>
	</body>
</html>
