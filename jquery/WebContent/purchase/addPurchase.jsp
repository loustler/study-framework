<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>구매내역확인</title>
</head>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
<!--
	function updatePurchase() {
		$('form').attr('method', 'post').attr('action',
				'/purchase/updatePurchaseView?tranNo=${purchase.tranNo}');
	}
	-->
</script>

<body>

	<form name="updatePurchase">

		다음과 같이 구매가 되었습니다.

		<table border=1>
			<tr>
				<td>물품번호</td>
				<td>${purchase.purchaseProd.prodNo}</td>
			</tr>
			<tr>
				<td>구매자아이디</td>
				<td>${purchase.buyer.userId}</td>
			</tr>
			<tr>
				<td>구매방법</td>
				<td>${purchase.paymentOption}</td>
			</tr>
			<tr>
				<td>구매자이름</td>
				<td>${purchase.receiverName}</td>
			</tr>
			<tr>
				<td>구매자연락처</td>
				<td>${purchase.receiverPhone}</td>
			</tr>
			<tr>
				<td>구매자주소</td>
				<td>${purchase.dlvyAddr}</td>
			</tr>
			<tr>
				<td>구매요청사항</td>
				<td>${purchase.dlvyReq}</td>
			</tr>
			<tr>
				<td>배송희망일자</td>
				<td>${purchase.dlvyDate}</td>
			</tr>
		</table>
	</form>

</body>
</html>