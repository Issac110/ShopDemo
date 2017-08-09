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
       <h2 class="fl">添加标签</h2>
       <a class="fr top_rt_btn" href="<c:url value = '/AdminCategoryServlet?method=findAll'/>">返回标签列表</a>
      </div>
     <section>
     <form action="<c:url value = '/AdminCategoryServlet' />"  method = "post">
      <ul class="ulColumn2">
       <li>
        <span class="item_name" style="width:120px;">标签名称：</span>
        <input type="hidden" name ="method" value = "add" /> 
        <input type="text" name  = "cname" class="textbox textbox_295" value = "${category.cname }" placeholder="标签名称..."/>
       
       </li>
 
       <li>
        <span class="item_name" style="width:120px;"></span>
        <input type="submit" class="link_btn"/>
       </li>
      </ul>
      </form>
     </section>
 </div>
</section>

</body>
</html>
