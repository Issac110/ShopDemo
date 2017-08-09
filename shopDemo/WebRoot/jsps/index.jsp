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
	<div data-am-widget="slider" class="am-slider am-slider-default"
		data-am-slider='{}'>
		<ul class="am-slides">
			<li><img
				src="${pageContext.request.contextPath }/images/banner.jpg"></li>
			<li><img
				src="${pageContext.request.contextPath }/images/banner1.jpg">
			</li>
		</ul>
	</div>
	<a href="#" class="search"> 开启你的美食之旅... </a>
	<ul class="nav">
		<li><a href="#"> <img
				src="${pageContext.request.contextPath }/images/icon.jpg" />
				<p>最新推荐</p>
		</a></li>
		<li><a href="#"> <img
				src="${pageContext.request.contextPath }/images/icon1.jpg" />
				<p>热门菜谱</p>
		</a></li>
		<li><a href="#"> <img
				src="${pageContext.request.contextPath }/images/icon2.jpg" />
				<p>人气菜肴</p>
		</a></li>
		<li><a href="#"> <img
				src="${pageContext.request.contextPath }/images/icon3.jpg" />
				<p>优惠券</p>
		</a></li>
	</ul>

	<c:forEach items="${categoryList }" var="category">

		<div data-am-widget="titlebar"
			class="am-titlebar am-titlebar-default title">
			<h2 class="am-titlebar-title ">${category.cname }</h2>
			<nav class="am-titlebar-nav">
				<a href="#more" class="">more &raquo;</a>
			</nav>
		</div>


		<ul data-am-widget="gallery"
			class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default product">
			<c:forEach items="${GoodList }" var="good">
				<c:choose>
					<c:when test="${good.category.cid == category.cid}">
						<li>
							<div class="am-gallery-item">
								<a
									href="<c:url value='/GoodServlet?method=findByGoodId&goodId=${good.gid }'/>"
									class=""> <img
									src="${pageContext.request.contextPath }/${good.image}" alt="" />
									<h3 class="am-gallery-title">${good.gname}</h3>
									<div class="am-gallery-desc">
										<em>￥${good.price }</em><i class="am-icon-cart-plus"></i>
									</div>
								</a>
							</div>
						</li>
					</c:when>

				</c:choose>
			</c:forEach>
		</ul>

	</c:forEach>

	<div class="h50"></div>
	<jsp:include page="/jsps/navbar.jsp"></jsp:include>

</body>
</html>
