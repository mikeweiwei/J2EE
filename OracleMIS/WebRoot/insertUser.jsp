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
		<title>用户</title>

		<link rel="stylesheet" href="css/work02.css" />
		<!-- 导入 jQuery框架 -->
		<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
		
		<!-- 应用jQuery -->
		<script type="text/javascript">
			var sum = 1;
			var xmlHttp;
			//创建Ajax核心对象XMLHttpRequest
			function createXMLHttp() {
				if (window.XMLHttpRequest) {
					xmlHttp = new XMLHttpRequest();
				} else {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				}
			}
			function chraname(){
				var nameObj = document.getElementById("uname");
				var value = nameObj.value;
			 	createXMLHttp();
			 	var divObj1 = document.getElementById("divuname");
			 	xmlHttp.open("GET","userServlet.action?method=check&name="+value,false); 
			 	xmlHttp.onreadystatechange = function(){
			 		if(xmlHttp.readyState == 4){
			 			if(xmlHttp.status == 200){
			 				var info = xmlHttp.responseText;
			 				if(info == "true"){
			 					divObj1.style.color = "red";
			 					divObj1.innerHTML="["+value+"]"+"<p>用户名已存在</p>";
			 					sum = 0;
			 				}else{
			 					divObj1.style.color = "black";
			 					divObj1.innerHTML="["+value+"]"+"<p>恭喜你，用户名可以使用</p>";
			 					sum = 1;
			 				}
			 			}
			 		}
			 	
			 	}; 
			 	xmlHttp.send(null); 
			 	
			}
			
			function charuname() {
				var nameObj = document.getElementById("uname");
				var value = nameObj.value;
				var divObj1 = document.getElementById("divuname");
				var regex = /^[\u4e00-\u9fa50-9a-zA-Z_]{2,19}$/;
				if (regex.test(value)) {
					divObj1.style.color = "black";
					return true;
				} else {
					divObj1.style.color = "red";
					return false;
				}
			}
			function charupwd() {
				var pwdObj = document.getElementById("upwd");
				var value = pwdObj.value;
				var divObj2 = document.getElementById("divupwd");
				var regex = /^[a-z0-9A_Z]{6,8}$/;
				if (regex.test(value)) {
					divObj2.style.color = "black";
					return true;
				} else {
					divObj2.style.color = "red";
					return false;
				}
			}

			function charped2() {
				var value1 = document.getElementById("upwd").value;
				var value2 = document.getElementById("conpwd").value;
				var obj = document.getElementById("divconpwd");
				if (value1 == value2) {
					obj.style.color = "black";
					return true;
				} else {
					obj.style.color = "red";
					return false;
				}

			}

			function charphone() {
				var pwdObj = document.getElementById("utel");
				var value = pwdObj.value;
				var divObj2 = document.getElementById("divutel");
				var regex = /^1[34578]\d{9}$/;
				if (regex.test(value)) {
					divObj2.style.color = "black";
					return true;
				} else {
					divObj2.style.color = "red";
					return false;
				}
			}

			function charbox() {
				
					return true;
				
			}
			function checkAll() {
				if(sum == 0){
					return false;
				}else{
						return ((charphone() && charbox()) && (charped2() && charupwd()))
						&& charuname();
					}
			}
			window.onload = function() {
				document.getElementById("uname").onblur = function() {
					charuname();
					chraname();
				}
				document.getElementById("upwd").onblur = function() {
					charupwd();
				}
				document.getElementById("conpwd").onblur = function() {
					charped2();
				}
				document.getElementById("utel").onblur = function() {
					charphone();
				}

			}
		</script>
	</head>

	<body>
		<h3>新用户正在</h3>
		<hr/>
		<br/>
		<form action="userServlet.action?method=insertUser"  method="post" onsubmit="return checkAll();">
		<div id="out">
			<div class="int1">
				用户添加
			</div>
			<div class="int2">
				<div class="int21"><b class="int22">*</b>用户名：</div>
				<div class="int23">
					<input type="text" name="uname" id="uname" value="" maxlength="10" class="int"/>
				</div>
				<div class="int24"  id="divuname">
						由字母、数字、下划线 和<br/>
						汉子组成，长度在2-20之间
				</div>
			</div>
			<div class="int2">
				<div class="int21">
					<b class="int22">*</b>密码：
				</div>
				<div class="int23">
					<input type="password" name="upwd" id="upwd" value="" maxlength="8" class="int"/>
				</div>
				<div class="int24" id="divupwd">
						密码至少不得少于6位，<br/>
						最多不超过8位
				</div>
			</div>
			<div class="int2">
				<div class="int21">
					<b class="int22">*</b>密码确认：
				</div>
				<div class="int23">
					<input type="password" name="conpwd" id="conpwd" value="" class="int"/>
				</div>
				<div class="int24" id="divconpwd">
						必须与上一行密码相同
				</div>
			</div>
			<div class="int2">
				<div class="int21">
					<b class="int22">*</b>手机号：
				</div>
				<div class="int23">
					<input type="tel" name="utel" id="utel" value="" class="int"/>
				</div>
				<div class="int24" id="divutel">
						必须是有效的手机号
				</div>
			</div>
			<div class="int6">
				<input type="submit" value="添加" class="int01"/>
			</div>
		</div>
		</form>
	</body>
</html>
