<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <title>top.jsp</title>
    <meta charset="utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		body { padding:0px; margin:0px; }
		#tpa {
			color: #009933;
			margin:0px;
			padding:0px;
			float:right;
			padding-right:10px;
		}
		
		#tpa dd {
			margin:0px;
			padding:0px;
			float:left;
			margin-right:2px;
		}
		
		#tpa dd.ditem {
			margin-right:8px;
		}
		
		#tpa dd.img {
		  padding-top:6px;
		}
		
		div.item{
		  text-align:center;
			background:url(img/frame/topitembg.gif) 0px 3px no-repeat;
			width:82px;
			height:26px;
			line-height:28px;
		}
		
		.itemsel {
		  width:80px;
		  text-align:center;
		  background:#226411;
			border-left:1px solid #c5f097;
			border-right:1px solid #c5f097;
			border-top:1px solid #c5f097;
			height:26px;
			line-height:28px;
		}
		
		*html .itemsel {
			height:26px;
			line-height:26px;
		}
		
		a:link,a:visited {
		 	text-decoration: underline;
		}
		
		.item a:link, .item a:visited {
			font-size: 12px;
			color: #ffffff;
			text-decoration: none;
			font-weight: bold;
		}
		
		.itemsel a:hover {
			color: #ffffff;
			font-weight: bold;
			border-bottom:2px solid #E9FC65;
		}
		
		.itemsel a:link, .itemsel a:visited {
			font-size: 12px;
			color: #ffffff;
			text-decoration: none;
			font-weight: bold;
		}
		
		.itemsel a:hover {
			color: #ffffff;
			border-bottom:2px solid #E9FC65;
		}
		
		.rmain {
		  padding-left:10px;
		  /* background:url(img/frame/toprightbg.gif) no-repeat; */
		}
	</style>
	<link href="css/base.css" rel="stylesheet" type="text/css">	
	
	<script language='javascript'>
		var preFrameW = '206,*';
		var FrameHide = 0;
		var curStyle = 1;
		var totalItem = 9;

		function ChangeMenu(way) {
			var addwidth = 10;
			var fcol = top.document.all.btFrame.cols;
			if(way == 1) addwidth = 10;
			else if(way == -1) addwidth = -10;
			else if(way == 0) {
				if(FrameHide == 0) {
					preFrameW = top.document.all.btFrame.cols;
					top.document.all.btFrame.cols = '0,*';
					FrameHide = 1;
					return;
				} else {
					top.document.all.btFrame.cols = preFrameW;
					FrameHide = 0;
					return;
				}
			}
			fcols = fcol.split(',');
			fcols[0] = parseInt(fcols[0]) + addwidth;
			top.document.all.btFrame.cols = fcols[0] + ',*';
		}
		
		function mv(selobj,moveout,itemnum){
		   if(itemnum==curStyle) return false;
		   if(moveout=='m') selobj.className = 'itemsel';
		   if(moveout=='o') selobj.className = 'item';
		   return true;
		}
		
		function changeSel(itemnum) {
		  curStyle = itemnum;
		 
		for (var i = 1; i <= totalItem; i++) {
				if (document.getElementById('item' + i))
					document.getElementById('item' + i).className = 'item';
			}
			document.getElementById('item' + itemnum).className = 'itemsel';
		}
		var t = null;
		t = setTimeout(time, 1000);//开始执行
		function time() {
			//clearTimeout(t);//清除定时器
			var dtt = new Date();
			var y = dtt.getFullYear();
			var m = dtt.getMonth() + 1;
			var d = dtt.getDate();
			var h = dtt.getHours();
			var mi = dtt.getMinutes();
			var s = dtt.getSeconds();
			document.getElementById("timeShow").innerHTML = "现在的时间为：" + y +"年" + m + "月" + 
			d + "日" + h + "时" + mi + "分" + s + "秒";
			t = setTimeout(time, 1000); //设定定时器，循环执行            
		}
	</script>
  </head>

  <body bgColor='#ffffff' >
	<table width="100%" border="0" cellpadding="0" cellspacing="0" background="img/frame/topbg.gif">
  	<tr>
    	<td width='20%' height="60"><img src="img/frame/logo.gif" /></td>
    	<td width='80%' align="right" valign="bottom">
    	<table width="750" border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<td align="right">${name}&nbsp;|&nbsp;<label id="timeShow"></lable></td>
    		<td width="20%">&nbsp;|&nbsp;你是第【${count}】个访客</td>	
    	</tr>
      	<tr>
        <td align="right" height="34" class="rmain" colspan="2">
		<dl id="tpa">
			<dd class='img'><a href="javascript:ChangeMenu(-1);"><img vspace="5" src="img/frame/arrl.gif" border="0" width="5" height="8" alt="缩小左框架"  title="缩小左框架" /></a></dd>
			<dd class='img'><a href="javascript:ChangeMenu(0);"><img vspace="3" src="img/frame/arrfc.gif" border="0" width="12" height="12" alt="显示/隐藏左框架" title="显示/隐藏左框架" /></a></dd>
			<dd class='img' style="margin-right:10px;"><a href="javascript:ChangeMenu(1);"><img vspace="5" src="img/frame/arrr.gif" border="0" width="5" height="8" alt="增大左框架" title="增大左框架" /></a></dd>
			<dd><div class='itemsel' id='item1' onMouseMove="mv(this,'move',1);" onMouseOut="mv(this,'o',1);"><a href="main.jsp" onclick="changeSel(1)" target="main">主菜单</a></div></dd>
			<dd><div class='item' id='item2' onMouseMove="mv(this,'m',2);" onMouseOut="mv(this,'o',2);"><a href="menu.html" onclick="changeSel(2)" target="menu">内容发布</a></div></dd>
			<dd><div class='item' id='item8' onMouseMove="mv(this,'m',8);" onMouseOut="mv(this,'o',8);"><a href="menu.html" onclick="changeSel(8)" target="menu">插件模块</a></div></dd>
			<dd><div class='item' id='item4' onMouseMove="mv(this,'m',4);" onMouseOut="mv(this,'o',4);"><a href="menu.html" onclick="changeSel(4)" target="menu">HTML更新</a></div></dd>
			<dd><div class='item' id='item5' onMouseMove="mv(this,'m',5);" onMouseOut="mv(this,'o',5);"><a href="menu.html" onclick="changeSel(5)" target="menu">系统设置</a></div></dd>
			<dd><div class='item' id='item9' onMouseMove="mv(this,'m',9);" onMouseOut="mv(this,'o',9);"><a href="loginOutServlet.action" onclick="changeSel(9)" target="_top">安全退出</a></div></dd>
		</dl>
		</td>
      	</tr>
    	</table>
    	</td>
  	</tr>
	</table>
  </body>
</html>