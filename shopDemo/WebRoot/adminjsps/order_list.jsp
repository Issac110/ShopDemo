<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
       <h2 class="fl">订单列表示例</h2>
       <a class="fr top_rt_btn add_icon">添加商品</a>
      </div>
      <section class="mtb">
       <select class="select">
        <option>订单状态</option>
        <option>待付款</option>
        <option>待发货</option>
        <option>待评价</option>
       </select>
       <input type="text" class="textbox textbox_225" placeholder="输入订单编号或收件人姓名/电话..."/>
       <input type="button" value="查询" class="group_btn"/>
      </section>
      <table class="table">
       <tr>
        <th>订单编号</th>
        <th>收件人</th>
        <th>联系电话</th>
        <th>收件人地址</th>
        <th>订单金额</th>
        <th>配送方式</th>
        <th>操作</th>
       </tr>
       
       <c:forEach items="${orderList }" var = "order">
              <tr>
        <td class="center"><c:if test="${fn:length(order.oid)>12 }">  
                         ${fn:substring(order.oid, 0, 12)}...  
                   </c:if>  
                  <c:if test="${fn:length(order.oid)<=12 }">  
                         ${order.oid }  
                   </c:if>  </td>
        <td>${order.address.name }</td>
        <td>${order.address.phone }</td>
        <td>
         <address>${order.address.addressName }</address>
        </td>
        <td class="center"><strong class="rmb_icon">${order.total }</strong></td>
        <td class="center">申通快递</td>
        <td class="center">
         <a href="<c:url value = '/OrderServlet?method=load&oid=${order.oid }'/>"  title="查看订单" class="link_icon" >&#118;</a>
         <a href="#" title="删除" class="link_icon">&#100;</a>
        </td>
       </tr>
       
       </c:forEach>


      </table>
      <aside class="paging">
       <a>第一页</a>
       <a>1</a>
       <a>2</a>
       <a>3</a>
       <a>…</a>
       <a>1004</a>
       <a>最后一页</a>
      </aside>
 </div>
</section>
</body>
</html>
