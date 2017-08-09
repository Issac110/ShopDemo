<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
		<!--  
			<div class="am-header-left am-header-nav">
				<a href="<c:url value='/jsps/index.jsp'/>" class="">
					<i class="am-header-icon am-icon-angle-left"></i>
				</a>
			</div>
		-->
		<h1 class="am-header-title">
			<a href="#title-link" class="" style="color: #333;">当前订单</a>
		</h1>
		<div class="am-header-right am-header-nav">
			<a href="#right-link" class=""> </a>
		</div>
	</header>
	<form id="form" action="<c:url value = '/OrderServlet'/>" method="post">
		<input type="hidden" name="method" value="add" /> 
		<input type="hidden" name="order" value="${order.oid}" />
		<div class="c-comment">
			<span class="c-comment-num">订单编号:${order.oid}</span>
		</div>
			<div class="c-comment">
			<span  class="c-comment-num" style = "color:red">${msg }</span>
		</div>
		<div class="c-comment-list" style="border: 0;">

			<c:forEach var="orderItem" items="${order.orderItemList}">
				<a class="o-con" href="">
					<div class="o-con-img">
						<img src='<c:url value ="${orderItem.good.image}" />' />
					</div>
					<div class="o-con-txt">
						<p>${orderItem.good.gname}</p>
						<p class="price">￥${orderItem.good.price}</p>
						<p>
							合计：<span>￥${orderItem.subtotal}</span>
						</p>
					</div>
					<div class="o-con-much">
						<h4>x${orderItem.count}</h4>
					</div>

				</a>

			</c:forEach>

			<ul class="address-list">

				<c:forEach items="${addressList }" var="a">
					<li>
						<p>收货人：${a.name }&nbsp;&nbsp;${a.phone }</p>
						<p class="order-add1">收货地址：${a.addressName }</p>
						<hr>
						<div class="address-cz">
							<label class="am-radio am-warning"> <input type="radio"
								name="adderss" value="${a.aid }" data-am-ucheck=""
								class="am-ucheck-radio"><span class="am-ucheck-icons"><i
									class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span>
								设为收货地址
							</label> <a href=""><img src="images/bj.png" style="width: 18px;">&nbsp;编辑</a>
							<a href="">删除</a>
						</div>
					</li>

				</c:forEach>


			</ul>
			<div class="clear"></div>
			<div class="search-input">
				<input name="comment" type="text" placeholder="请填写备注......." />
			</div>

			<div class="clear"></div>


			<div class="c-com-money">
				实付金额：<span>￥${order.total}</span>
			</div>
		</div>
	</form>
	<div class="c-com-btn">
		<a onclick="document.getElementById('form').submit();">立即支付</a> <a
			href="<c:url value = '/OrderServlet?method=deleteOrder&oid=${order.oid }'/>">取消订单</a>
	</div>

</body>
</html>
