<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>厨房妈妈</title>
<meta name="description" content="厨房妈妈" />
<meta name="keywords" content="厨房妈妈" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="css/amazeui.min.css" />
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/amazeui.min.js"></script>
</head>
<body>
	<header data-am-widget="header"
		class="am-header am-header-default header">
		<div class="am-header-left am-header-nav">
			<a href="<c:url value = '/jsps/member.jsp'/>" class=""> <i
				class="am-header-icon am-icon-angle-left"></i>
			</a>
		</div>
		<h1 class="am-header-title">
			<a href="#title-link" class="" style="color: #333;">全部订单</a>
		</h1>
		<div class="am-header-right am-header-nav">
			<a href="#right-link" class=""> </a>
		</div>
	</header>
	<div class="cate-search"
		style="position: relative; top: 0; border-bottom: 0;">
		<input type="text" class="cate-input" placeholder="搜索全部订单"> <input
			type="button" class="cate-btn">
	</div>
	<ul class="order-style">
		<li class="current"><a href="#">全部</a></li>
		<li><a href="#">待付款</a></li>
		<li><a href="#">待收货</a></li>
		<li><a href="#">待评价</a></li>
		<li><a href="#">退换货</a></li>
	</ul>

	<c:choose>
		<c:when test="${empty orderList }">
			<div class="nokeshuo">
				<img src="<c:url value = '/images/none.png'/>" />
				<p>
					您目前还没有任何订单，快去<a href="<c:url value = '/jsps/index.jsp'/>"><u>购物</u></a>吧
				</p>
			</div>
		</c:when>
		<c:otherwise>
			<c:forEach items="${orderList }" var="order">
				<div class="c-comment">
					<span class="c-comment-num">订单编号：<c:if
							test="${fn:length(order.oid)>12 }">  
                         ${fn:substring(order.oid, 0, 12)}...  
                   </c:if> <c:if test="${fn:length(order.oid)<=12 }">  
                         ${order.oid }  
                   </c:if></span> <span class="c-comment-suc"> <c:choose>
							<c:when test="${order.orderState eq 1}">
					待付款
				</c:when>

							<c:when test="${order.orderState eq 2}">
					待发货
				</c:when>

							<c:when test="${order.orderState eq 3}">
					卖家已发货
				</c:when>

							<c:when test="${order.orderState eq 4}">
					交易完成
				</c:when>
						</c:choose>
					</span>
				</div>
				<div class="c-comment-list" style="border: 0;">
					<c:forEach items="${order.orderItemList }" var="orderItem">
						<a class="o-con"
							href="<c:url value='/GoodServlet?method=findByGoodId&goodId=${orderItem.good.gid }'/>">
							<div class="o-con-img">
								<img src="${orderItem.good.image }">
							</div>
							<div class="o-con-txt">
								<p>${orderItem.good.gname }</p>
								<p class="price">￥${orderItem.good.price }</p>
								<p>
									合计：<span>￥${orderItem.subtotal }</span>
								</p>
							</div>
							<div class="o-con-much">
								<h4>x${orderItem.count }</h4>
							</div>

						</a>
					</c:forEach>

					<div class="c-com-money">
						实付金额：<span>￥ ${order.total }</span>
					</div>
				</div>
				<div class="c-com-btn">
					<c:choose>

						<c:when test="${order.orderState eq 1 }">
							<a
								href="<c:url value = '/OrderServlet?method=toPay&oid=${order.oid }'/>">立即支付</a>
							<a
								href="<c:url value = '/OrderServlet?method=deleteOrder&oid=${order.oid }'/>">取消订单</a>
						</c:when>

						<c:when test="${order.orderState eq 2 }">
							<a href="#">申请退款</a>
						</c:when>

						<c:when test="${order.orderState eq 3 }">
							<a href="#">确认收货</a>
						</c:when>
						<c:otherwise>
							<a href="#">退换货</a>
							<a href="#">评价</a>
						</c:otherwise>
					</c:choose>

				</div>


				<div class="clear"></div>
			</c:forEach>
		</c:otherwise>
	</c:choose>

</body>
</html>
