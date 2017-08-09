<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>厨房妈妈</title>
<meta name="description" content="厨房妈妈" />
<meta name="keywords" content="厨房妈妈" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="<c:url value = '/css/amazeui.min.css' />" />
<link rel="stylesheet" href="<c:url value = '/css/style.css' />" />
<script type="text/javascript"
	src="<c:url value = '/js/jquery.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value = '/js/amazeui.min.js' />"></script>
</head>
<body>	
	<div data-am-widget="navbar"
		class="am-navbar am-cf am-navbar-default footer " id="">
		<ul class="am-navbar-nav am-cf am-avg-sm-4">
			<li><a href="<c:url value = '/jsps/index.jsp'/>" class=""> <span class=""><img
						src="<c:url value = '/images/nav.png'/>" /></span> <span
					class="am-navbar-label">点餐</span>
			</a></li>
			<li><a href="#" class=""> <span class=""><img
						src="<c:url value = '/images/nav2.png'/>" /></span> <span
					class="am-navbar-label">客说</span>
			</a></li>
			<li><a href="<c:url value = '/jsps/we.jsp' />" class=""> <span class=""><img
						src="<c:url value = '/images/nav3.png'/>" /></span> <span
					class="am-navbar-label">我们</span>
			</a></li>
			<li><a href="<c:url value = '/jsps/member.jsp'/>" class=""> <span class=""><img
						src="<c:url value = '/images/nav4.png'/>" /></span> <span
					class="am-navbar-label">我的</span>
			</a></li>

		</ul>
	</div>

</body>
</html>
