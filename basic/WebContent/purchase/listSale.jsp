<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@ page import="com.model2.mvc.common.SearchVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	SearchVO vo = null;
	List<ProductVO> proList = null;
	List<PurchaseVO> purList = null;
	String menu = null;
	int total = 0;
	if (request.getAttribute("count") != null && request.getAttribute("purList") != null
			&& request.getAttribute("proList") != null && request.getAttribute("searchVO") != null
			&& request.getAttribute("menu") != null) {
		total = ((Integer) request.getAttribute("count")).intValue();
		proList = (ArrayList<ProductVO>) request.getAttribute("proList");
		purList = (ArrayList<PurchaseVO>) request.getAttribute("purList");
		vo = (SearchVO) request.getAttribute("searchVO");
		menu = (String) request.getAttribute("menu");
	} else {
		System.out.println("listSale request Att Error");
	}

	System.out.println("listProduct total : " + total);
	System.out.println("listProduct pageUnit : " + vo.getPageUnit());

	int currentPage = vo.getPage();

	int totalPage = 0;
	if (total > 0) {
		totalPage = total / vo.getPageUnit();
		if (total / vo.getPageUnit() > 0) {
			totalPage += 1;
		}
	}
%>


<html>
<head>
<title>상품 관리</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
	function fncGetProductList() {
		document.detailForm.submit();
	}
	-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/listProduct.do?menu=<%=menu%>"
			method="post">

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">상품 관리</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>

					<td align="right"><select name="searchCondition"
						class="ct_input_g" style="width: 80px">
							<option value="0">상품번호</option>
							<option value="1">상품명</option>
							<option value="2">상품가격</option>
					</select> <input type="text" name="searchKeyword" class="ct_input_g"
						style="width: 200px; height: 19px" /></td>


					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetProductList();">검색</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 <%=total%> 건수, 현재 <%=currentPage%> 페이지
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
				<%
					int no = proList.size();
					int tempSize = proList.size() - purList.size();
					for (int i = 0; i < no; i++) {
						ProductVO productVO = proList.get(i);
				%>
				<tr class="ct_list_pop">
					<td align="center"><%=i + 1%></td>
					<td></td>

					<td align="left"><a
						href="/getProduct.do?prodNo=<%=productVO.getProdNo()%>&menu=<%=menu%>">
							<%=productVO.getProdName()%>
					</a></td>

					<td></td>
					<td align="left"><%=productVO.getPrice()%></td>
					<td></td>
					<td align="left"><%=productVO.getManuDate()%></td>
					<td></td>
					<%
						for (int j = 0; j < purList.size(); j++) {
								if (purList.size() > i) {
									PurchaseVO purchaseVO = purList.get(i);
					%>
					<%
						if (productVO.getProdNo() == purchaseVO.getPurchaseProd().getProdNo()
											&& purchaseVO.getTranCode().equals("배송대기")) {
					%>
					<td align="left"><%=purchaseVO.getTranCode()%> <a
						href="/updateTranCode.do?prodNo=<%=purchaseVO.getPurchaseProd().getProdNo()%>&tranCode=002">배송하기</a>
					</td>

					<%
						} else {
					%>

					<%
						}
					%>
					<td align="left"><%=purchaseVO.getTranCode()%></td>
					<%
						} else {
					%>
					<td align="left">판매 중</td>
					<%
						}
							}
					%>
				</tr>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>
				<%
					}
				%>

			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center">
						<%
							for (int i = 0; i < totalPage; i++) {
						%> <a href="/listProduct.do?page=<%=i + 1%>&menu=<%=menu%>"><%=i + 1%></a>
						<%
							}
						%>
					
				</tr>
			</table>
			<!--  페이지 Navigator 끝 -->

		</form>

	</div>
</body>
</html>

