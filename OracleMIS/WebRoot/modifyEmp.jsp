<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改員工信息</title>
    <link rel="stylesheet" href="css/work02.css" />
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>

  </head>
  <script type="text/javascript">
   
		function setValue( id, value ){
			$(id).val( value );
		}
	
		function getInputData(){
			var value = $("#idoruname").val(); //获取输入的值
			//jQuery+ajax
			$.post( 
				"empServlet.action",
				{method: 'FindEmp', idname: value},
				function( result ){
					//alert( result );
					//接收回传的信息
					//eval("var json = " + result );
					var json = $.parseJSON(result);
					
					$.each( json,  function(i, item){
						setValue( "#empno", item.empno ) ;
						setValue( "#ename", item.ename ) ;
						setValue( "#mgr", item.mgr ) ;
						setValue( "#job", item.job ) ;
						setValue( "#hiredate", item.hiredate ) ;
						setValue( "#comm", item.comm ) ;
						setValue( "#sal", item.sal ) ;
						setValue( "#deptno", item.deptno ) ;
					} );
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
					//alert(json);
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
			$("#idoruname").focus( function(){
				$( this ).val("");
			});
			$("#btn01").click( function(){
				getInputData();
			});
			
		});
	</script>  
  <body>
    <h3>修改員工信息</h3>
    <hr/>
    	<input type="text" name="idoruname" id="idoruname" value="输入id或用户名"/>
    	<input type="button" value="JSON01" id="btn01"/>
    <hr/>
    <form action="empServlet.action?method=updateEmp"  method="post">
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
		    	<td><input type="text" name="hiredate" id="hiredate" value=""/></td>
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
