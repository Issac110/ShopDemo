<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>厨房妈妈</title>
		<meta name="description" content="厨房妈妈" />
		<meta name="keywords" content="厨房妈妈"/>
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<link rel="stylesheet" href="<c:url value = '/css/amazeui.min.css' />" />
		<link rel="stylesheet" href="<c:url value = '/css/style.css' />" />
		<script type="text/javascript" src="<c:url value = '/js/jquery.min.js' />" ></script>
		<script type="text/javascript" src="<c:url value = '/js/amazeui.min.js' />" ></script>
	</head>
	<body>
	<div class="member">
		<div class="member-pic">
			<img src="<c:url value = '/images/default_photo.png'/>" />
		</div>
		<c:choose>
			<c:when test="${empty sessionScope.session_user.username }">
				<div class="member-infor">
					请<a href="<c:url value = '/jsps/login.jsp'/>"><u>登录</u></a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="member-infor">${sessionScope.session_user.username }</div>
			</c:otherwise>
		</c:choose>

	</div>
	<ul class="member-nav">
		<li><a href="<c:url value = '/AddressServlet?method=findByUser'/>"><i class="am-icon-map-marker"></i><span>收货地址</span></a></li>
		<li><a href="<c:url value = '/OrderServlet?method=myOrders'/>"><i class="am-icon-newspaper-o"></i><span>我的订单</span></a></li>
		<li><a href="<c:url value = '/jsps/cart.jsp' /> "><i
				class="am-icon-cart-arrow-down"></i><span>购物车</span></a></li>
		<li><a href="#"><i class="am-icon-bell-o"></i><span>系统通知</span></a></li>
		<li><a href="#"><i class="am-icon-credit-card"></i><span>会员卡</span></a></li>
		<li><a href="#"><i class="am-icon-cc-mastercard"></i><span>优惠券</span></a></li>
		<li><a href="#"><i class="am-icon-dollar"></i><span>积分</span></a></li>
	</ul>
	<ul class="member-nav mt">
			<li><a href="<c:url value = '/jsps/we.jsp'/>"><i class="am-icon-phone"></i>联系我们</a></li>
		</ul>
		
		<jsp:include page="/jsps/navbar.jsp"></jsp:include>
	</body>
</html>
