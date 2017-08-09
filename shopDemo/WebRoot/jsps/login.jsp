<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
<title>登录-厨房妈妈</title>
<link rel="stylesheet" href="<c:url value = "/css/login.css"/>" />
</head>

<body>
<div class="login" style="background:url(<c:url value = "/images/login-bg.png"/>) no-repeat;">
	<div class="welcome"><img src='<c:url value="/images/welcome.png"/>'></div>
	
	 <div style="width:100%;text-align:center;margin-bottom:20px">
        <span style="width:100%;color:red">${msg }</span>
    </div>
	
	<div class="login-form">
	<form  id ="form" action="<c:url value='/UserServlet'/>" method="post">
	<input type="hidden" name="method" value="login"/>
	<div class="login-inp"><label>登录</label><input type="text" name = "username"  value = "${form.username }" placeholder=""></div>
		<div class="login-inp"><label>密码</label><input type="password" name = "password" value = "${form.password }" placeholder=""></div>
		<div class="login-inp"><a href="javascript:document.getElementById('form').submit();" >立即登录</a></div>
	</form>
		
	</div>
	<div class="login-txt"><a href="#">立即注册</a>|<a href="#">忘记密码？</a></div>
</div>
<div style="text-align:center;">

</div>
</body>
</html>
