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
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/amazeui.min.js"></script>
</head>
<body>
	<header data-am-widget="header"
		class="am-header am-header-default header">
		<div class="am-header-left am-header-nav">
			<a href="<c:url value = '/jsps/index.jsp'/>" class=""> <i
				class="am-header-icon am-icon-angle-left"></i>
			</a>
		</div>
		<h1 class="am-header-title">
			<a href="#title-link" class="" style="color: #333;">支付成功</a>
		</h1>
		<div class="am-header-right am-header-nav">
			<a href="#right-link" class=""> </a>
		</div>
	</header>

	<div class="nokeshuo">
		<img src="<c:url value = '/images/none.png'/>" />
		<p>
			支付成功，点击<a href="<c:url value ='/jsps/member.jsp'/>"><u>这里</u></a>查看订单
		</p>
	</div>

</body>
</html>
