<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
       <h2 class="fl">添加商品</h2>
       <a href = "<c:url value = '/AdminGoodServlet?method=findAll'/>" class="fr top_rt_btn">返回产品列表</a>
      </div>
     <section>
      <form action="<c:url value='/AdminAddGoodServlet'/>" method="post" enctype="multipart/form-data">
      <ul class="ulColumn2">
       <li>
        <span class="item_name" style="width:120px;">商品名称：</span>
        <input name = "gname" type="text" class="textbox textbox_295" placeholder="商品名称..."/>
        
       </li>

       <li>
        <span class="item_name" style="width:120px;">商品价格：</span>
        <input name="price" type="text" class="textbox" placeholder="商品价格..."/>
        
       </li>
       
        <li>
        <span class="item_name" style="width:120px;">商品简述：</span>
        <input name = "desc" type="text" class="textbox" placeholder="商品简述..."/>
        
       </li>
       
       <li>
        <span class="item_name" style="width:120px;">分类：</span>
        <select class="select" name = "cid">
        <c:forEach items="${categoryList }" var ="c">
          <option value="${c.cid }">${c.cname }</option>
        </c:forEach>
        </select>
        
       </li>	

       <li>
        <span class="item_name" style="width:120px;">上传图片：</span>
        <label class="uploadImg">
         <input name = "image" type="file"/>
         <span>上传图片</span>
        </label>
        <c:if test="${!empty msg }">
        <span class="errorTips">${msg }</span>
        </c:if>
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

