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
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/amazeui.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/amazeui.min.js" ></script>
	</head>
	<body>
	<header data-am-widget="header" class="am-header am-header-default header">
		  <div class="am-header-left am-header-nav">
		     <a href="<c:url value = '/jsps/index.jsp'/>" class=""> 
		       <i class="am-header-icon am-icon-angle-left"></i>
		     </a>
		  </div>
		  <h1 class="am-header-title"> <a href="#title-link" class="" style="color: #333;">厨房妈妈</a></h1>
		  <div class="am-header-right am-header-nav">
		     <a href="#right-link" class=""> </a>
		  </div>
	  </header>
		<div data-am-widget="slider" class="am-slider am-slider-default" data-am-slider='{}' >
			  <ul class="am-slides">
			      <li><img src="${pageContext.request.contextPath }/${good.image}"> </li>
			      <li><img src="${pageContext.request.contextPath }/${good.image}"> </li>
			  </ul> 
		</div>
		<div class="detail">
			<h2>${good.gname }</h2>
			<div class="price">
				<b>￥${good.price }</b><span>（积分可抵扣5元）</span>
			</div>
			<div class="kucun">
				<p>库存：1000</p>
				<p>运费：免运费</p>
			</div>
		</div>
		<div class="comment">
			<h2>宝贝评价（0）</h2>
			<ul>
				<li><a hhref="">有图（0）</a></li>
				<li><a hhref="">好评（0）</a></li>
				<li><a hhref="">中评（0）</a></li>
				<li><a hhref="">差评（0）</a></li>
			</ul>
		</div>
        <div class="detail-con">
        	<p>  排毒排便－香蕉牛奶汁</p>
	      	<p>  适量加入牛奶调理，可以补充更多钙质，对于正在减肥中的女孩来说，也比较有饱足感。经常失眠或是容易经痛的女孩也可以喝喝看！</p>
	      	<br />
	      	<br />
	      	<p>  止咳防晕－芒果汁</p>
	      	<p>  退火利尿－椰子汁</p>
	      	<p>  不过有的人会怕椰子的味道，也因为椰子水生冷寒性，因此女孩们如果想喝椰子水来消暑，或是肠胃不好的人，在喝之前还是要三思！</p>
	      	<p>  水果之王－奇异果汁</p>
	      	<img src="${pageContext.request.contextPath }/images/banner.jpg" />
        </div>
		<div class="h50"></div>
		<ul class="fixed-btn">
			<li ><a href="" class="current">立即购买</a></li>
			<li><a href="<c:url value='/GoodServlet?method=load&gid=${good.gid }'/>">加入购物车</a></li>
		</ul>
		
	</body>
</html>
