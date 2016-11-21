<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('img[src="../images/top/login.jpeg"]').on(
				'click',
				function() {
					$(window.parent.frames["rightFrame"].document.location)
							.attr("href", "/user/login");
				});

		$('img[src="../images/top/logout.jpeg"]').on('click', function() {
			$(window.parent.document.location).attr("href", "/user/logout");
		});
	})
</script>
</head>

<body topmargin="0" leftmargin="0">

	<table width="100%" height="70" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="10"></td>
			<td height="10">&nbsp;</td>
		</tr>
		<tr>
			<td width="800" height="30">
				<img alt="Image Loading Fail" src="../images/top/file_image.jpg" width="200%" height="30">
			</td>
		</tr>
		<tr>
			<td height="20" align="right" background="/images/img_bg.gif">
				<table width="200" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="115">
							<u:if test="${sessionScope.user == null}">
								<img alt="not image" src="../images/top/login.jpeg" width="70" height="40" />
							</u:if>
						</td>
						<td width="14">&nbsp;</td>
						<td width="56">
							<u:if test="${sessionScope.user != null }">
								<img alt="Not image" src="../images/top/logout.jpeg" width="70" height="40"/>
							</u:if>
						</td>
					</tr>
				</table>
			</td>
			<td height="20" background="/images/img_bg.gif">&nbsp;</td>
		</tr>
	</table>

</body>
</html>