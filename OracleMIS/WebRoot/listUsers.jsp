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
    <title>用户表</title>
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<!-- 导入jQuery -->
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
	<script language="javascript">
		function viewArc(aid){
			if(aid==0) aid = getOneItem();
			window.open("archives.asp?aid="+aid+"&action=viewArchives");
		}
		function editArc(aid){
			if(aid==0) aid = getOneItem();
			location="archives.asp?aid="+aid+"&action=editArchives";
		}
		function updateArc(aid){
			var qstr=getCheckboxItem();
			if(aid==0) aid = getOneItem();
			location="archives.asp?aid="+aid+"&action=makeArchives&qstr="+qstr+"";
		}
		function checkArc(aid){
			var qstr=getCheckboxItem();
			if(aid==0) aid = getOneItem();
			location="archives.asp?aid="+aid+"&action=checkArchives&qstr="+qstr+"";
		}
		function moveArc(aid){
			var qstr=getCheckboxItem();
			if(aid==0) aid = getOneItem();
			location="archives.asp?aid="+aid+"&action=moveArchives&qstr="+qstr+"";
		}
		function adArc(aid){
			var qstr=getCheckboxItem();
			if(aid==0) aid = getOneItem();
			location="archives.asp?aid="+aid+"&action=commendArchives&qstr="+qstr+"";
		}
		function delArc(aid){
			var qstr=getCheckboxItem();
			if(aid==0) aid = getOneItem();
			location="archives.asp?aid="+aid+"&action=delArchives&qstr="+qstr+"";
		}

		//获得选中文件的文件名
		function getCheckboxItem(){
			var allSel="";
			if(document.form2.id.value) return document.form2.id.value;
			for(var i=0;i<document.form2.id.length;i++){
				if(document.form2.id[i].checked){
					if(allSel=="")
						allSel=document.form2.id[i].value;
					else
						allSel=allSel+"`"+document.form2.id[i].value;
				}
			}
			return allSel;
		}
		
		//获得选中其中一个的id
		function getOneItem(){
			var allSel="";
			if(document.form2.id.value) return document.form2.id.value;
			for(var i=0;i<document.form2.id.length;i++){
				if(document.form2.id[i].checked){ 
						allSel = document.form2.id[i].value;
						break;
				}
			}
			return allSel;
		}

		//对多选框的选择（全选、全否、反选）
		function selCheckbox( str ){
			var objs = document.getElementsByName("checkItem");
			if( "all" === str ){
				for(var i=0;i< objs.length;i++){
					objs[i].checked = true;
				}
			}else if("noall" === str ){
				for(var i=0;i< objs.length;i++){
					objs[i].checked = false;
				}
			}else{
				for(var i=0;i< objs.length;i++){
					objs[i].checked = !objs[i].checked;
				}
			}
		}
		
		//jQuery实现全选、全否、反选
		function selectCheckbox( str ){
			if( 'ALL' === str ){
				$(":checkbox").each( function(){
					this.checked = true;
				});
			}else if( 'noALL' === str ){
				$(":checkbox").each( function(){
					this.checked = false;
				});
			}else{
				$(":checkbox").each( function(){
					this.checked = !this.checked;
				});
			}
		}
		
		//获取所有被选中项的value值
		function getValueOfSelected(){
			var items = "";
			$(":checkbox").each( function(){
				if( this.checked ){
					items = items + this.value + ",";
				}
			});
			items = items.substring(0, items.length-1);
			alert( items );
			//之后，使用ajax发送到Servlet
			$.get( 
				"usersServlet.action?method=deleteListUsers",
				{items:items},
				function( data ){
					location.reload(); //刷新页面
					//删除后，其它所有选项置于未选中状态。
					$(":checkbox").each( function(){
						this.checked = false;
					});	
				}
			); 
		}
		
	</script>
	</head>
	<body>
		<!--  快速转换位置按钮  -->
		<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
		<tr>
 			<td height="26" align="center">
  				Oracle企业的信息化后台管理系统
			</td>
		</tr>
		</table>
  
		<!--  内容列表   -->
		<form name="form2">

		<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
		<tr bgcolor="#E7E7E7">
			<th height="24" colspan="10" align="left">&nbsp;用户信息列表&nbsp;</th>
		</tr>
		<tr align="center" bgcolor="#FAFAF1" height="22">
			<th width="10%">ID</th>
			<th width="5%">选择</th>
			<th width="15%">编号</th>
			<th width="15%">姓名</th>
			<th width="15%">电话</th>
			<th width="10%">密码</th>
			<th width="10%">注册日期</th>
			<th width="20%">操作</th>
		</tr>
		<c:forEach items="${listUsers}" var="user" varStatus="id">
			<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
			<td width="10%">${id.index+1}</td>
			<td><input type="checkbox" name="checkItem" id="checkItem" value="${user.id}" class="np"></td>
			<td width="15%">${user.id}</td>
			<td width="15%">${user.uname}</td>
			<td width="20%">${user.phone}</td>
			<td width="10%">${user.password}</td>
			<td width="10%">${user.register}</td>
			<td width="20%">
				<a href="usersServlet.action?method=updateUserById&id=${user.id}">编 辑</a> | 
				<a href="usersServlet.action?method=deleteUserById&id=${user.id}">删 除</a></td>
			</tr>
		</c:forEach>
		
		<tr bgcolor="#FAFAF1">
			<td height="48" colspan="8" valign="bottom">
				&nbsp;&nbsp;&nbsp;
				<input type="button" value="全 选" onclick="selectCheckbox('ALL');"/>&nbsp;
				<input type="button" value="取 消" onclick="selectCheckbox('noALL');"/>&nbsp;
				<input type="button" value="反 选" onclick="selectCheckbox();"/>&nbsp;&nbsp;&nbsp;
				<a href="javascript:getValueOfSelected();"><input type="button" value="删除选中的用户"/></a>
			</td>
		</tr>	
		<tr align="right" bgcolor="#EEF4EA">
			<td height="36" colspan="10" align="center"><!--翻页代码 --></td>
		</tr>
	</table>

	</form>

	<!--  搜索表单  -->
	<form name='form3' action='' method='get'>
		<input type='hidden' name='dopost' value='' />
		<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
	  	<tr bgcolor='#EEF4EA'>
	    	<td background='img/frame/wbg.gif' align='center'>
      		<table border='0' cellpadding='0' cellspacing='0'>
        	<tr>
          		<td width='90' align='center'>搜索条件：</td>
          		<td width='160'>
          		<select name='cid' style='width:150'>
          			<option value='0'>选择类型...</option>
          			<option value='1'>名称</option>
          		</select>
        		</td>
	        	<td width='70'>
	          		关键字：
	        	</td>
		        <td width='160'>
		          	<input type='text' name='keyword' value='' style='width:150px' />
		        </td>
		        <td width='110'>
		    		<select name='orderby' style='width:80px'>
		            <option value='id'>排序...</option>
		            <option value='pubdate'>发布时间</option>
		      	</select>
		        </td>
	        	<td>
	          		<input name="imageField" type="image" src="img/frame/search.gif" width="45" height="20" border="0" class="np" />
	        	</td>
       		</tr>
      		</table>
    		</td>
  		</tr>
		</table>
	</form>
	</body>
</html>		