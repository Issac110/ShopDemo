<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="<c:url value = '/adminjsps/css/style.css' />">
<!--[if lt IE 9]>
<script src="<c:url value = '/adminjsps/js/html5.js' />"></script>
<![endif]-->
<script src="<c:url value = '/adminjsps/js/jquery.js' />"></script>
<script src="<c:url value = '/adminjsps/js/jquery.mCustomScrollbar.concat.min.js' />" > </script>
<script>

	(function($){
		$(window).load(function(){
			
			$("a[rel='load-content']").click(function(e){
				e.preventDefault();
				var url=$(this).attr("href");
				$.get(url,function(data){
					$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
					//scroll-to appended content 
					$(".content").mCustomScrollbar("scrollTo","h2:last");
				});
			});
			
			$(".content").delegate("a[href='top']","click",function(e){
				e.preventDefault();
				$(".content").mCustomScrollbar("scrollTo",$(this).attr("href"));
			});
			
		});
	})(jQuery);
</script>
</head>
<body>

<aside  class="lt_aside_nav content mCustomScrollbar">
 <h2><a href="index.html" target="body">起始页</a></h2>
 <ul>
  <li>
   <dl>
    <dt>商品管理</dt>
    <!--当前链接则添加class:active-->
    <dd><a href="<c:url value = '/AdminGoodServlet?method=findAll' />" class="active" target="body">商品列表 </a></dd>
    <dd><a href="<c:url value = '/AdminGoodServlet?method=addPre' />" target="body">添加商品 </a></dd>
    <dd><a href="<c:url value = '/AdminGoodServlet?method=findAllByDelete'/>" target="body">商品回收站 </a></dd>
   </dl>
  </li>
    <li>
   <dl>
    <dt>标签管理</dt>
    <dd><a href="<c:url value = '/AdminCategoryServlet?method=findAll'/>"  target="body">标签列表 </a></dd>
    <dd><a href="<c:url value = '/adminjsps/tag_detail.jsp'/>" target="body">添加标签 </a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>订单信息</dt>
    <dd><a href="<c:url value = '/OrderServlet?method=findAll'/>" target="body">订单列表 </a></dd>
    <dd><a href="order_detail.html" target="body">订单详情 </a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>会员管理</dt>
    <dd><a href="user_list.html">会员列表 </a></dd>
    <dd><a href="user_detail.html">添加会员（详情） </a></dd>
    <dd><a href="user_rank.html">会员等级 </a></dd>
    <dd><a href="adjust_funding.html">会员资金管理 </a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>基础设置</dt>
    <dd><a href="setting.html">站点基础设置 </a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>配送与支付设置</dt>
    <dd><a href="express_list.html">配送方式</a></dd>
    <dd><a href="pay_list.html">支付方式</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>在线统计</dt>
    <dd><a href="discharge_statistic.html">流量统计</a></dd>
    <dd><a href="sales_volume.html">销售额统计</a></dd>
   </dl>
  </li>
  <li>
   <p class="btm_infor">© DeathGhost.cn 版权所有</p>
  </li>
 </ul>
</aside>
</body>

</html>
