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

<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">订单详情</h2>
      </div>
			<table class="table">
				<tr>
					<td>收件人：${order.address.name }</td>
					<td>联系电话：${order.address.phone }</td>
					<td>收件地址：${order.address.addressName }</td>
					<td>付款时间：${order.paytime }</td>
				</tr>
				<tr>
					<td>下单时间：${order.ordertime }</td>
					<td>付款时间：${order.paytime }</td>
					<td>确认时间：---</td>
					<td>评价时间时间：---</td>
				</tr>
				<tr>
					<td>订单状态：<a><c:choose>
								<c:when test="${order.orderState eq 1 }">未付款</c:when>
								<c:when test="${order.orderState eq 2 }">已付款但未发货</c:when>
								<c:when test="${order.orderState eq 3 }">已发货但未确认收货 </c:when>
								<c:otherwise>已确认交易成功</c:otherwise>

							</c:choose></a></td>
					<td colspan="3">订单备注：<mark>${order.comment }</mark></td>
				</tr>
			</table>
			<table class="table">
      
      <c:forEach items="${order.orderItemList }" var = "orderItem">
      <tr>
        <td class="center"><img src="${orderItem.good.image }" width="50" height="50"/></td>
        <td>${orderItem.good.gname }</td>
        <td class="center">A15902</td>
        <td class="center"><strong class="rmb_icon">${orderItem.good.price }</strong></td>
        <td class="center">
         <p>颜色：蓝色</p>
         <p>尺码：XXL码</p>
        </td>
        <td class="center"><strong>${orderItem.count }</strong></td>
        <td class="center"><strong class="rmb_icon">${orderItem.subtotal }</strong></td>
        <td class="center">包</td>
       </tr>
      
      </c:forEach>


      </table>
      <aside class="mtb" style="text-align:right;">
       <label>管理员操作：</label>
       <input type="text" class="textbox textbox_295" placeholder="管理员操作备注"/>
       <input type="button" value="打印订单" class="group_btn"/>
       <input type="button" value="确认订单" class="group_btn"/>
       <input type="button" value="付款" class="group_btn"/>
       <input type="button" value="配货" class="group_btn"/>
       <input type="button" value="发货" class="group_btn"/>
       <input type="button" value="确认收货" class="group_btn"/>
      </aside>
 </div>
</section>
</body>
</html>
