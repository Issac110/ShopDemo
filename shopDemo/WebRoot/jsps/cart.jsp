<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>购物车-厨房妈妈</title>
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
			<a href='<c:url value="/jsps/index.jsp"/>' class=""> <i
				class="am-header-icon am-icon-angle-left"></i>
			</a>
		</div>
		<h1 class="am-header-title">
			<a href="#title-link" class="" style="color: #333;">购物车</a>
		</h1>
		<div class="am-header-right am-header-nav">
			<a href="#right-link" class=""> </a>
		</div>
	</header>

	<div class="c-comment-list" style="border: 0;">
	
	<c:forEach items="${sessionScope.cart.cartItems}" var = "cartItem">

		<a class="o-con" href="">
			<div class="o-con-img">
				<img src="<c:url value='/${cartItem.good.image}'/>">
			</div>
			<div class="o-con-txt">
				<p>${cartItem.good.gname}</p>
				<p class="price">￥${cartItem.good.price}</p>
				<p>
					小计<span>￥${cartItem.subtotal}</span>
				</p>
			</div>
			<div class="o-con-much">
				<h4>x${cartItem.count}</h4>
			</div>

		</a> 
		</c:forEach>
		<div class="c-com-money">
			合计：<span>￥${sessionScope.cart.total}</span>
		</div>
		
	</div>
	<c:choose>
		<c:when test="${empty sessionScope.cart.cartItems}">
			<div class="nokeshuo">
				<img src="<c:url value = '/images/none.png'/>" />
				<p>购物车快饿瘪了，快去<a href="<c:url value = '/jsps/index.jsp'/>" ><u>购物</u></a>吧</p>
			</div>
		</c:when>

		<c:otherwise>
			<div class="c-com-btn">
				<a href="<c:url value='/OrderServlet?method=loadToTureOrder' /> ">去结算</a>
				<a href='<c:url  value="/CartServlet?method=clear"/>'>清空购物车</a>
			</div>
		</c:otherwise>
	</c:choose>


</body>
</html>

