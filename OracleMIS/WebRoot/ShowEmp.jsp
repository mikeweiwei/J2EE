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
    
    <title>ShowEmp</title>
    <link rel="stylesheet" type="text/css" href="css/base.css">
		<style type="text/css">
		a:hover{
			background-color: #fff;
			text-decoration: none;
		}
	</style>
	
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
			alert( "分别选中了: "+items );
		}
	</script>
	<!-- 控制分页显示按钮的点击后的样式 -->
	<link rel="stylesheet" type="text/css" href="css/top.css"/>
	<script type="text/javascript">
			function getNum( id ){
				var currValue = $("#currPage").val();
				var obj = $("#"+id);
				var value = obj.val().trim();
				var result = null;
				if("上一页" == value ){
					//当前码-1
					result = Number(currValue)-1;
				}else if("下一页" == value){
					//当前码+1
					result = Number(currValue)+1; 
				}else{
					//value
					result = Number(value);
				}
				alert("将要显示: 第 "+ result +"页。");
				window.self.location = "myEmpDeptServlet.action?method=showPage&pageNo="+result;
			}
			
	</script>
	</head>
	<body>
		<!--  快速转换位置按钮  -->
		<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
		<tr>
 			<td height="26" align="center">
  				Oracle企业的員工信息
			</td>
		</tr>
		</table>
  
		<!--  内容列表   -->
		<form name="form2">

		<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
		<tr bgcolor="#E7E7E7">
			<th height="24" colspan="15" align="left">&nbsp;分页查询员工表&nbsp;</th>
		</tr>
		<!-- //empno, ename, job,mgr,hiredate,sal,comm,deptno,rno-->
		<tr align="center" bgcolor="#FAFAF1" height="22">
			<th width="5%">选择</th>
			<th width="6%">员工编号</th>
			<th width="8%">姓名</th>
			<th width="6%">职位</th>
			<th width="5%">经理编号</th>
			<th width="5%">入职日期</th>
			<th width="5%">薪水</th>
			<th width="5%">奖金</th>
			<th width="5%">部门编号</th>
			<th width="5%">记录号</th>
			<th width="5%">操作</th>
		</tr>
		<c:forEach items="${showPage}" var="map">
			<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
			
			<td><input type="checkbox" name="checkItem" id="checkItem" value="${map.id}" class="np"></td>
			<td width="6%">${map.empno}</td>
			<td width="6%">${map.ename}</td>
			<td width="8%">${map.job}</td>
			<td width="5%">${map.mgr}</td>
			<td width="5%">${map.hiredate}</td>
			<td width="5%">${map.sal}</td>
			<td width="5%">${map.comm}</td>
			<td width="5%">${map.deptno}</td>
			<td width="5%">${map.rno}</td>
			<td width="10%"> 
				<a href="empServlet.action?method=deleteEmp&empno=${map.empno}">删 除</a>
				</td>
			</tr>
		</c:forEach>
		
		<tr bgcolor="#FAFAF1">
			<td height="48" colspan="15" valign="bottom" class="aa">
				&nbsp;&nbsp;&nbsp;
				<input type="button" value="全 选" onclick="selectCheckbox('ALL');"/>&nbsp;
				<input type="button" value="取 消" onclick="selectCheckbox('noALL');"/>&nbsp;
				<input type="button" value="反 选" onclick="selectCheckbox();"/>&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;
			  	<a href="empServlet.action?method=showemp&id=${pageSECPNT.pageStart}" style="text-decoration : none">
			  		<input type="button" value="首页"/>
			  	</a>
  			  	<a href="empServlet.action?method=showemp&id=${pageSECPNT.previousPage}" style="text-decoration : none ">
			  		<input type="button" value="上一页"/>
  			  	</a>
  			  	<c:forEach items="${pageCodes}" var="map">
  			  		<c:if test="${map.pageCode == pageSECPNT.pageCurrent}">
  			  			<a href="empServlet.action?method=showemp&id=${map.pageCode}" style="text-decoration : none ">
			  				<input type="button" value="&nbsp;${map.pageCode}&nbsp;" style="color:red; font-size: 12pt;"/>
  			  			</a>&nbsp;
  			  		</c:if>
  			  		<c:if test="${map.pageCode != pageSECPNT.pageCurrent}">
  			  			<a href="empServlet.action?method=showemp&id=${map.pageCode}" style="text-decoration : none ">
			  				<input type="button" value="&nbsp;${map.pageCode}&nbsp;"/>
  			  			</a>&nbsp;
  			  		</c:if>
  			  	</c:forEach>
  			  	<a href="empServlet.action?method=showemp&id=${pageSECPNT.nextPage}" style="text-decoration : none ">
  			  		<input type="button" value="下一页"/>
  			  	</a>
  			  	<a href="empServlet.action?method=showemp&id=${pageSECPNT.pageEnd}" style="text-decoration : none ">
  			  	<input type="button" value="末页"/></a>
  			  	&nbsp;<span style="font-size:10pt;font-weight: bold; color:blue;">共${pageSECPNT.totalPages}页</span> 
    		</td>
    	</tr>
		<tr align="right" bgcolor="#EEF4EA">
			<td height="36" colspan="15" align="center"><!--翻页代码 --></td>
		</tr>
	</table>

	</form>

  </body>
</html>
