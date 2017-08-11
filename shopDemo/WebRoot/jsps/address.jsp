<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8" />
		<title>厨房妈妈</title>
		<meta name="description" content="厨房妈妈" />
		<meta name="keywords" content="厨房妈妈"/>
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
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
		<header data-am-widget="header" class="am-header am-header-default header">
		  <div class="am-header-left am-header-nav">
		     <a href="<c:url value = '/jsps/member.jsp'/>" class=""> 
		       <i class="am-header-icon am-icon-angle-left"></i>
		     </a>
		  </div>
		  <h1 class="am-header-title"> <a href="#title-link" class="" style="color: #333;">收货地址</a></h1>
		  <div class="am-header-right am-header-nav">
		     <a href="<c:url value = '/jsps/addAddress.jsp'/>" class="">添加地址</a>
		  </div>
	  </header>
	  <ul class="address-list">
	  <c:choose>
	  	<c:when test="${empty addressList }">
	  		<div class="nokeshuo">
				<img src="<c:url value = '/images/none.png'/>" />
				<p>
					您还没有添加地址，快点击<a href="<c:url value = '/jsps/addAddress.jsp'/>"><u>添加</u></a>吧
				</p>
			</div>
	  	</c:when>
	  	
	  	<c:otherwise>
	  		<c:forEach items="${addressList }" var = "address">
			   	<li>
	    		<p>收货人：${address.name}&nbsp;&nbsp;${address.phone }</p>
	    		<p class="order-add1">收货地址：${address.addressName }</p>
	    	    <hr>
	    	    <div class="address-cz">
	    	    	<label class="am-radio am-warning">
                       <input type="radio" name="radio3" value="" data-am-ucheck="" class="am-ucheck-radio"><span class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span> 设为默认
                    </label>
                    <a href="<c:url value = '/AddressServlet?method=load&aid=${address.aid }'/>"><img src="<c:url value = '/images/bj.png'/>" style="width: 18px;">&nbsp;编辑</a>
                    <a href="<c:url value = '/AddressServlet?method=delete&aid=${address.aid }'/>">删除</a>
	    	    </div>
	    	</li>
		</c:forEach>
	  	</c:otherwise>
	  
	  </c:choose>
		
	  

	 
	    </ul>
	</body>
</html>
