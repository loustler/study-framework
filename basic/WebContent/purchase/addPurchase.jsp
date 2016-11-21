<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.model2.mvc.service.user.vo.UserVO" %>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO" %>
<%@ page import="com.model2.mvc.service.product.vo.ProductVO" %>
<%
	UserVO userVO = null;
	PurchaseVO purchaseVO = null;
	ProductVO productVO = null;
	
	if(request.getAttribute("purchaseVO")!=null&&request.getAttribute("userVO")!=null&&request.getAttribute("productVO")!=null) {
		userVO = (UserVO)request.getAttribute("userVO");
		purchaseVO = (PurchaseVO)request.getAttribute("purchaseVO");
		productVO = (ProductVO)request.getAttribute("productVO");
	} else {
		System.out.println("addPurchase vo error");
	}

%>




<html>
<head>
<title>구매내역확인</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=<%=purchaseVO.getTranNo() %>" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%= productVO.getProdNo()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%=userVO.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
			<%=purchaseVO.getPaymentOption() %>		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%=purchaseVO.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%=purchaseVO.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%=purchaseVO.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%=purchaseVO.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%=purchaseVO.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>