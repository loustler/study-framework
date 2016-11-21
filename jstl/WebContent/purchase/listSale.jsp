<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
	function fncPageNavigator(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	-->
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/listSale.do" method="post">

			<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="15" height="37">
						<img src="/images/ct_ttl_img01.gif" width="15" height="37" />
					</td>
					<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">상품 목록조회</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37">
						<img src="/images/ct_ttl_img03.gif" width="12" height="37" />
					</td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
				<tr>

					<td align="right">
						<select name="searchCondition" class="ct_input_g" style="width: 80px">
							<option value="0" ${!empty search.searchCondition && search.searchCondition == '0' ? "selected" : ""}>상품번호</option>
							<option value="1" ${!empty search.searchCondition && search.searchCondition == '1' ? "selected" : ""}>상품명</option>
							<option value="2" ${!empty search.searchCondition && search.searchCondition == '2' ? "selected" : ""}>상품가격</option>
						</select>
						<input type="text" name="searchKeyword" class="ct_input_g" value="${sarch.searchKeyword }" style="width: 200px; height: 19px" />
					</td>


					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23">
									<img src="/images/ct_btnbg01.gif" width="17" height="23">
								</td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
									<a href="javascript:fncPageNavigator('1');">검색</a>
								</td>
								<td width="14" height="23">
									<img src="/images/ct_btnbg03.gif" width="14" height="23">
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
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				<p:forEach var="product" items="${list }">
					<p:set var="i" value="${i+1}" />
					<tr class="ct_list_pop">
						<td align="center">${i}</td>
						<td></td>

						<td align="left">
							<a href="/getProduct.do?prodNo=${product.getProdNo()}&menu=${menu}"> ${product.getProdName()} </a>
						</td>

						<td></td>
						<td align="left">${product.getPrice()}</td>
						<td></td>
						<td align="left">${product.getManuDay()}</td>
						<td></td>
						<td align="left">${product.getProTranCode()}
							<p:if test="${!empty product.getProTranCode() && product.getProTranCode() == '배송대기'}">
								<a href="/updateTranCode.do?prodNo=${product.getProdNo()}&tranCode=2">배송하기</a>
							</p:if>
						</td>
					</tr>

					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>
				</p:forEach>

				<p:import var="pageNavigator" url="../common/pageNavigator.jsp" />
				${pageNavigator}
				</form>

				</div>
</body>
</html>


