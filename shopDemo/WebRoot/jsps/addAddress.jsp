<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>收货地址</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/css/ui.css'/>">

</head>

<body>
	<div class="aui-container">
		<div class="aui-page">
			<div class="aui-t-header">
				<div class="header">
					<div class="header-background"></div>
					<div class="toolbar statusbar-padding">
						<button class="bar-button back-button" onclick="history.go(-1);">
							<i class="icon icon-back">
							<a href=""></a>
							</i>
						</button>
						<div class="header-title">
							<div class="title">收货地址</div>
						</div>
					</div>
				</div>
			</div>



			<!-- 单选复选表单 begin -->
			<div class="aui-form-content">

				<form id="form" action="<c:url value = '/AddressServlet'/>"
					method="post">
					<input type="hidden" name="method" value="add" />
					<input type="hidden" name = "aid" value ="${address.aid }" />
					<div class="aui-pu-list">
						<h2>普通表单</h2>
						<div class="aui-form-cells">


							<div class="aui-form-cell b-line">

								<div class="aui-form-cell-tb">
									<span style="width:100%;color:red">${msg }</span>
								</div>
							</div>
							<div class="aui-form-cell b-line">

								<div class="aui-form-cell-tb">
									<input value = "${address.name }" name="name" type="text" placeholder="收件人">
								</div>
							</div>


							<div class="aui-form-cell b-line">

								<div class="aui-form-cell-tb">
									<input value = "${address.phone }" name="phone" type="number" pattern="[0-9]*"
										placeholder="联系电话">
								</div>
							</div>
							<div class="aui-form-cell b-line">

								<div class="aui-form-cell-tb">
									<select id="s_province" name="s_province"></select><br>
								</div>




							</div>

							<div class="aui-form-cell b-line">

								<div class="aui-form-cell-tb">
									<select id="s_city" name="s_city"></select><br>
									<!-- sel -->
								</div>
							</div>

							<div class="aui-form-cell b-line">

								<div class="aui-form-cell-tb">
									<select id="s_county" name="s_county"></select><br>
								</div>
							</div>
							<script type="text/javascript"
								src="<c:url value = '/js/area.js'/>"></script>
							<script type="text/javascript">
								_init_area();
							</script>


							<div class="aui-form-cell b-line">

								<div class="aui-form-cell-tb">
									<input value = "${fn:substringAfter(address.addressName,' ' ) }" name="detailAddress" type="text" placeholder="详细地址">
								</div>
							</div>
						</div>
					</div>
					<div class="aui-btn-list">
						<a onclick="javascript:document.getElementById('form').submit();"
							class="btn aui-aui-btn">确认</a> <a href="javascript:;"
							class="btn btn-cancel">取消</a>
					</div>
				</form>

			</div>
			<!-- 单选复选表单 end -->

		</div>
	</div>

</body>
</html>