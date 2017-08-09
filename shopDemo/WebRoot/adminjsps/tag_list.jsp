<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/adminjsps/css/style.css' />">
<!--[if lt IE 9]>
<script src="<c:url value = '/adminjsps/js/html5.js' />"></script>
<![endif]-->
<script src="<c:url value = '/adminjsps/js/jquery.js' />"></script>
<script
	src="<c:url value = '/adminjsps/js/jquery.mCustomScrollbar.concat.min.js' />">
	
</script>
<script>
	(function($) {
		$(window).load(
				function() {

					$("a[rel='load-content']").click(
							function(e) {
								e.preventDefault();
								var url = $(this).attr("href");
								$.get(url, function(data) {
									$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
									//scroll-to appended content 
									$(".content").mCustomScrollbar("scrollTo",
											"h2:last");
								});
							});

					$(".content").delegate(
							"a[href='top']",
							"click",
							function(e) {
								e.preventDefault();
								$(".content").mCustomScrollbar("scrollTo",
										$(this).attr("href"));
							});

				});
	})(jQuery);
</script>
</head>
<body>


	<section class="rt_wrap content mCustomScrollbar">
		<div class="rt_content">
			<div class="page_title">
				<h2 class="fl">标签列表</h2>
				<a href="<c:url value = '/adminjsps/tag_detail.jsp'/>"
					class="fr top_rt_btn add_icon">添加标签</a>
			</div>
			<c:if test="${!empty msg}">
			<span class="errorTips">${msg }</span>
			</c:if>
			
			<table class="table" style="width:50%;">
				<tr>

					<th>标签名称</th>
					<th>操作</th>
				</tr>

				<c:forEach items="${categoryList }" var="c">

					<tr>
						<td style="text-align:center;">${c.cname }</td>
						<td class="center"><a href="<c:url value = '/AdminCategoryServlet?method=editPre&cid=${c.cid }'/>" title="编辑"
							class="link_icon">&#101;</a> <a onclick="return confirm('确定删除？')" href="<c:url value = '/AdminCategoryServlet?method=delete&cid=${c.cid }' /> " title="删除"
							class="link_icon">&#100;</a></td>
					</tr>

				</c:forEach>

			</table>

			<aside class="paging">
				<a>第一页</a> <a>1</a> <a>2</a> <a>3</a> <a>…</a> <a>1004</a> <a>最后一页</a>
			</aside>
		</div>
	</section>
</body>
</html>
