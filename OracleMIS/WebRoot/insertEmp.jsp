<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'ajaxjson02.jsp' starting page</title>
  	<link rel="stylesheet" href="css/work02.css" />
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="js/showdate.js"></script>
	<script type="text/javascript">
		
	
		function getIDs(){
		var obj=$("#empno").val();
		if(obj==null){
			obj = 1;
		}
			$.get(
				"empServlet.action",
				{method: 'queryid',id: obj},
				function( result ){
					if(result == "true"){
					$("#error").text("编号已存在！");
					}else{
					$("#error").text("");
					}
				}
			);
		}
		function getJobs(){
			$.get(
				"empServlet.action",
				{method: 'QureyJob'},
				function( result ){
					//alert( result );
					var json = eval( "(" + result + ")" );
					var obj = $("#job");
					$.each( json,  function(i, item){
						obj.append("<option value='" + item.job + "'>" + item.job + "</option>");
					} );
				}
			);
		}
		function getManager(){
			$.get(
				"empServlet.action",
				{method: 'QureyManager'},
				function( result ){
					//alert( result );
					var json = eval( "(" + result + ")" );
					var obj = $("#mgr");
					$.each( json,  function(i, item){
						obj.append("<option value='" + item.manager + "'>" + item.manager + "</option>");
					} );
				}
			);
		}
		function getDept(){
			$.get(
				"empServlet.action",
				{method: 'QureyDept'},
				function( result ){
					//alert( result );
					var json = eval( "(" + result + ")" );
					var obj = $("#deptno");
					$.each( json,  function(i, item){
						obj.append("<option value='" + item.deptno + "'>" + item.dname + "</option>");
					} );
				}
			);
		}
		$( function(){
			getDept();
			getJobs();
			getManager();
			$("#empno").blur(function (){
				getIDs();
			});
			
		});
	</script> 
	 
  </head>
  
<body>
    <h3>添加员工</h3>
    <hr/>
    <form action="empServlet.action?method=insertEmp"  method="post">
	    <table border="1" cellspacing="0" width="75%" >
	    	<%-- empno, ename, job,mgr,hiredate,sal,comm,deptno--%>
	    	<tr>	
		    	<th>员工编号</th>
		    	<td><input type="text" name="empno" id="empno" value=""/>
		    	<span id="error"></span>
		    	</td>
		    </tr>
		    <tr>	
		    	<th>姓名</th>
		    	<td><input type="text" name="ename" id="ename" value=""/></td>
		    </tr>
	    	<tr>
		    	<th>职位</th>
		   		<td>
		    		<select name="job" id="job">
		    			<option value="-1">---请选择---</option>
		    		</select> 
		    	</td>
		    </tr>
		    
		    <tr>
		    	<th>经理编号</th>
		   		<td>
		    		<select name="mgr" id="mgr">
		    			<option value="-1">---请选择---</option>
		    		</select> 
		    	</td>
		    </tr>
		    <tr>	
		    	<th>入职日期</th>
		    	<td><input type="text" name="hiredate" id="time" value="" onClick="return Calendar('time');" /></td>
		    </tr>	
		    <tr>
		    	<th>薪水</th>
		    	<td><input type="text" name="sal" id="sal" value=""/></td>
		   	</tr>
		   	<tr> 	
		    	<th>奖金</th>
		    	<td><input type="text" name="comm" id="comm" value=""/></td>
		    </tr>
		    <tr>
		    	<th>部门</th>
		   		<td>
		    		<select name="deptno" id="deptno">
		    			<option value="-1">---请选择---</option>
		    		</select> 
		    	</td>
		    </tr>
	    	<tr>
		    	<th>操作</th>
		    	<td><input type="submit" value="添加" /></td>
		    </tr>
	    </table>
    </form>
  </body>
</html>

