<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
<!--
	function fncPageNavigator(currentPage) {
		$('#currentPage').val(currentPage);
		$('form').attr('method', 'post').attr('action', '/product/listProduct')
				.submit();
	}

	function fncSortPrice(sort) {
		$('#sort').val(sort);
		$('form').attr('method', 'post').attr('action', '/product/listProduct')
				.submit();
	}

	$(function() {
		$('.ct_list_pop:nth-child(4n+6)').css('background-color', '#6EE0FF');

		$('.ct_list_pop td:nth-child(3)').css('color', 'green');
		
		var menu = '${menu}';
		

		$('.ct_list_pop td:nth-child(3)').on('click', function() {
			
			var productNo =  $(this).find('#prodNo').val();
			var prodName = $(this).text().trim();
			
			//debug
			$.ajax({
				url : '/product/getProductJson',
				method : 'POST',
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				data : JSON.stringify({
					prodNo : $(this).find('#prodNo').val(),
					menu : menu
				}),
				dataType : 'json',
				success : function(JSONData, status) {
					var temp = '<h4>'+' 제품명 : '+JSONData.product.prodName+'</h4>';
					$('#'+prodName+' h4').remove();
					$('#'+prodName).html(temp);
				}
			});
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
							<option value="0" ${search.searchCondition.equals("0") ? "selected" : ""}>상품명</option>
							<option value="1" ${search.searchCondition.equals("1") ? "selected" : ""}>상품가격(이하)</option>
							<option value="2" ${search.searchCondition.equals("2") ? "selected" : ""}>상품가격(이상)</option>
						</select>
						<input type="text" name="searchKeyword" class="ct_input_g" value="${search.searchKeyword}" style="width: 200px; height: 19px" />
					</td>


					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23">
									<img src="/images/ct_btnbg01.gif" width="17" height="23">
								</td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">검색</td>
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
					<td colspan="8">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
					<input type="hidden" id="sort" name="sort" value="${search.sort}" />
					<td align="right">
						가격 <a href="javascript:fncSortPrice('100');"> ▲</a> <a href="javascript:fncSortPrice('101');"> ▼</a>
					</td>
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
				<p:forEach var="product" items="${list}">
					<p:set var="i" value="${i+1}" />
					<tr class="ct_list_pop">

						<td align="center">${i}</td>
						<td></td>
						<td align="center">${product.prodName}
							<input type="hidden" id="prodNo" value="${product.prodNo}" />
						</td>
						<td></td>
						<td align="center">${product.price}</td>
						<td></td>
						<td align="center">${product.manuDay}</td>
						<td></td>
						<td align="center">
							<p:if test="${empty product.proTranCode}">
							판매 중
						</p:if>
							<p:if test="${product.proTranCode=='1  ' }">
							배송대기
						</p:if>
							<p:if test="${product.proTranCode=='2  ' }">
							배송 중
						</p:if>
							<p:if test="${product.proTranCode=='3  ' }">
							재고없음
						</p:if>
						</td>
					</tr>

					<tr>
						<!-- <td colspan="11" bgcolor="D6D7D6" height="1"></td> -->
						<td id="${product.prodName}" colspan="11" bgcolor="D6D7D6" height="1">
					</tr>
				</p:forEach>

			</table>
			<p:import var="pageNavigator" url="../common/pageNavigator.jsp" scope="request" />
			${pageNavigator }
		</form>

	</div>
</body>
</html>


