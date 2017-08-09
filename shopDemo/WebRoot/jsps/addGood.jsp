<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
 <head> 
  <meta charset="utf-8" /> 
  <meta name="description" content="" /> 
  <meta name="keywords" content="" /> 
  <title>添加商品-厨房妈妈</title> 
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/amazeui.min.css" /> 
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/addGood.css" /> 
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script> 
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/amazeui.min.js"></script> 
 </head> 
 
 
 <body> 
  <header data-am-widget="header" class="am-header am-header-default sq-head "> 
   <div class="am-header-left am-header-nav"> 
    <a href="#left-link" class=""> <i class="am-header-icon am-icon-angle-left"></i> </a> 
   </div> 
   <h1 class="am-header-title"> <a href="<c:url value = '/jsps/index.jsp'/>" class="" style="color: #333;">添加商品</a> </h1> 
   <div class="am-header-right am-header-nav"></div> 
  </header> 
  <div class="content-list" id="outer"> 
   <div id="content"> 
    <ul class="list-pro"> 
     <li><a href="#"><img src="${pageContext.request.contextPath }/${good.image}" class="list-pic" /></a> 
      <div class="shop-list-mid"> 
       <div class="tit"> 
        <a href="#">${good.gname }</a> 
       </div> 
       <div class="am-gallery-desc">
        ￥${good.price }
       </div> 
      </div> 
      <div class="list-cart"> 
       <div class="d-stock "> 
        <a class="decrease">-</a> 
        <form id="form" action="<c:url value='/CartServlet'/>" method="post">
        	<input type="hidden" name="method" value="add"/>
  			<input type="hidden" name="gid" value="${good.gid }"/>
            <input id="num" readonly="" class="text_box" name="count" type="text" value="1" /> 
        </form>
        <a class="increase">+</a> 
       </div> 
      </div></li> 
    </ul> 
   </div> 
  </div> 
  <!--底部--> 
  <div style="height: 100px;"></div> 
  <div class="fix-bot"> 
   <a href="" class="list-js">小计：<i>0元</i><em>(0份)</em></a> 
   <a href="javascript:document.getElementById('form').submit();" class="list-jsk">加入购物车</a> 
  </div> 
  <script>
//购物数量加减
window.onload = function(){
	$('.decrease').fadeIn();
	$('.text_box').fadeIn();
};
$(function(){
		$('.increase').click(function(){
			var current_num = parseInt($("#num").val());
			current_num += 1;
			if(current_num > 0){
				$('.decrease').fadeIn();
				$('.text_box').fadeIn();
			}
			$("#num").val(current_num);
			
		});	
		$('.decrease').click(function(){
			var current_num = parseInt($("#num").val());
			if(current_num > 0){
				current_num -= 1;
                if(current_num < 1){
                	$('.decrease').fadeOut();
                	$('.text_box').fadeOut();
                }
                $("#num").val(current_num);
				
			}
		});
	});
</script>   
 </body>
</html>
