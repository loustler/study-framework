<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="l" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>회원 목록 조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script type="text/javascript">
<!--
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
	function fncPageNavigator(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/listUser.do" method="post">

			<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="15" height="37">
						<img src="/images/ct_ttl_img01.gif" width="15" height="37" />
					</td>
					<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">회원 목록조회</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37">
						<img src="/images/ct_ttl_img03.gif" width="12" height="37">
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
				<tr>
					<td align="right">
						<select name="searchCondition" class="ct_input_g" style="width: 80px">
							<option value="0"${!empty search.searchCondition } && ${search.searchCondition.equals("0")} ? "selected" : "">회원ID</option>
							<option value="1"${!empty search.searchCondition } && ${search.searchCondition.equals("1")} ? "selected" : "">회원명</option>
						</select>
						<input type="text" name="searchKeyword" value="${search.searchKeyword }" class="ct_input_g" style="width: 200px; height: 20px">
					</td>
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23">
									<img src="/images/ct_btnbg01.gif" width="17" height="23" />
								</td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
									<a href="javascript:fncGetUserList('1');">검색</a>
								</td>
								<td width="14" height="23">
									<img src="/images/ct_btnbg03.gif" width="14" height="23" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 ${resultPage.getTotalCount()} 건수, 현재 ${resultPage.getCurrentPage()} 페이지</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">회원ID</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">회원명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">이메일</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				<l:forEach var="user" items="${requestScope.list}" begin="0" step="1">
					<l:set var="i" value="${i+1}" />
					<tr class="ct_list_pop">
						<td align="center">${i}</td>
						<td></td>
						<td align="left">
							<a href="/getUser.do?userId=${user.getUserId()}">${user.getUserId()}</a>
						</td>
						<td></td>
						<td align="left">${user.getUserName() }</td>
						<td></td>
						<td align="left">${user.getEmail() }</td>
					</tr>
					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>
				</l:forEach>
			</table>

			<l:import var="pageNavigator" url="../common/pageNavigator.jsp" scope="request" />
			${pageNavigator }
		</form>
	</div>

</body>
</html>