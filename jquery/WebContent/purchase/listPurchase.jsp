<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
<!--
	function fncPageNavigator(currentPage) {
		$('#currentPage').val(currentPage);
		$('form').attr('method', 'post').attr('action',
				'/purchase/listPurchase').submit();
	}

	function fncSortPrice(sort) {
		$('#sort').val(sort);
		$('form').attr('method', 'post').attr('action',
				'/purchase/listPurchase').submit();
	}

	$(function() {
		$('.ct_list_pop td:nth-child(1)').css('color', 'red');
		$('.ct_list_pop:nth-child(4n+6)').css('background-color', '#FFACB7');
		$('.ct_list_pop td:nth-child(1)').css('color', 'red').on(
				'click',
				function() {
					self.location = '/purchase/getPurchase?tranNo='
							+ $(this).text().trim();
				});

		$('.ct_list_pop td:contains("구입취소")').on(
				'click',
				function() {
					self.location = '/purchase/removePurchase?tranNo='
							+ $(this).find('#prodNo').val() + '&tranCode=0';
				});

		$('.ct_list_pop td:contains("물건도착")').on(
				'click',
				function() {
					self.location = '/purchase/updateTranCode?tranNo='
							+ $(this).find('#prodNo').val() + '&tranCode=3';
				});

	});
	-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm">

			<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="15" height="37">
						<img src="/images/ct_ttl_img01.gif" width="15" height="37">
					</td>
					<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">구매 목록조회</td>
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
					<td colspan="8">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
					<input type="hidden" id="sort" name="sort" value="${search.sort}" />
					<td align="right">
						제품명 <a href="javascript:fncSortPrice('100');"> ▲</a> <a href="javascript:fncSortPrice('101');"> ▼</a>
					</td>
					<td></td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">주문번호</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">배송주소</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">배송현황</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">정보수정</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>


				<p:forEach var="purchase" items="${list}">
					<p:set var="i" value="${i+1}" />
					<tr class="ct_list_pop">
						<td align="center">${purchase.tranNo}</td>
						<td></td>
						<td align="center">${purchase.purchaseProd.prodName }</td>
						<td></td>
						<td align="center">${purchase.purchaseProd.price}원</td>
						<td></td>
						<td align="center">${purchase.dlvyAddr}</td>
						<td></td>
						<td align="center">
							<p:if test="${purchase.tranCode == '1  ' }">
								배송준비
							</p:if>
							<p:if test="${purchase.tranCode == '2  ' }">
								배송 중 
							</p:if>

							<p:if test="${purchase.tranCode == '3  ' }">
							 	수령완료
							</p:if>

						</td>
						<td></td>
						<td align="center">
							<p:if test="${purchase.tranCode == '1  ' }">
								구입취소
							</p:if>

							<p:if test="${purchase.tranCode == '2  ' }">
								물건도착
							</p:if>
							<input type="hidden" id="prodNo" value="${purchase.tranNo }">
						</td>
						<td align="center"></td>

					</tr>
					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>
				</p:forEach>

			</table>
			<p:import var="pageNavigator" url="../common/pageNavigator.jsp" />
			${pageNavigator}

		</form>

	</div>

</body>
</html>