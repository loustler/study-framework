<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model2.mvc.service.domain.Purchase"%>


<%
	Purchase purchase = (Purchase)request.getAttribute("purchase");	
%>

<html>
<head>
<title>구매내역확인</title>
</head>

<body>

	<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=<%=purchase.getTranNo()%>" method="post">

		다음과 같이 구매가 되었습니다.

		<table border=1>
			<tr>
				<td>물품번호</td>
				<td><%=purchase.getPurchaseProd().getProdNo()%></td>
				<td></td>
			</tr>
			<tr>
				<td>구매자아이디</td>
				<td><%=purchase.getBuyer().getUserId()%></td>
				<td></td>
			</tr>
			<tr>
				<td>구매방법</td>
				<td>
					<%=purchase.getPaymentOption()%>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>구매자이름</td>
				<td><%=purchase.getReceiverName()%></td>
				<td></td>
			</tr>
			<tr>
				<td>구매자연락처</td>
				<td><%=purchase.getReceiverPhone()%></td>
				<td></td>
			</tr>
			<tr>
				<td>구매자주소</td>
				<td><%=purchase.getDlvyAddr()%></td>
				<td></td>
			</tr>
			<tr>
				<td>구매요청사항</td>
				<td><%=purchase.getDlvyReq()%></td>
				<td></td>
			</tr>
			<tr>
				<td>배송희망일자</td>
				<td><%=purchase.getDlvyDate()%></td>
				<td></td>
			</tr>
		</table>
	</form>

</body>
</html>