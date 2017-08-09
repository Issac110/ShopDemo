<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">

		.table{
			width:100%;
			height:100%;
	
		}
		.table td{
			
		}
		iframe {
			width: 100%;
			height: 100%;
		}
	</style>
  </head>
  
  <body style="margin: initial;">

<div style="height: 70px;"> 

 <iframe frameborder="0" src="<c:url value= '/adminjsps/top.jsp' />" name="top" ></iframe> 


</div>

<div> 

<table class="table" align="center">

	<tr >
		<td width="210" style="align="center" valign="top">
			<iframe frameborder="0"  src='<c:url value = "/adminjsps/left.jsp"/>' name="left"></iframe>
		</td>
		<td>
			<iframe frameborder="0" src="<c:url value='/adminjsps/body.jsp' />" name="body"></iframe>
		</td>
	</tr>
</table>

</div>

  </body>
</html>
