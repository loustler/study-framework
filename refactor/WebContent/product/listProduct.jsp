<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model2.mvc.common.Page"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model2.mvc.service.domain.Product"%>
<%@ page import="com.model2.mvc.common.Search"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>

<%
	List<Product> list = (List<Product>) request.getAttribute("list");
	Page resultPage = (Page) request.getAttribute("page");
	Search search = (Search) request.getAttribute("search");

	String condition = CommonUtil.null2str(search.getSearchCondition());
	String keyword = CommonUtil.null2str(search.getSearchKeyword());
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
	function fncGetProductList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	-->
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/listProduct.do?" method="post">


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
							<option value="0" <%=condition.equals("0") ? "selected" : ""%>>상품번호</option>
							<option value="1" <%=condition.equals("1") ? "selected" : ""%>>상품명</option>
							<option value="2" <%=condition.equals("2") ? "selected" : ""%>>상품가격</option>
						</select>
						<input type="text" name="searchKeyword" class="ct_input_g" value="<%=keyword%>" style="width: 200px; height: 19px" />
					</td>


					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23">
									<img src="/images/ct_btnbg01.gif" width="17" height="23">
								</td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
									<a href="javascript:fncGetProductList('1');">검색</a>
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
					<td colspan="11">
						전체
						<%=resultPage.getTotalCount()%>
						건수, 현재
						<%=resultPage.getCurrentPage()%>
						페이지
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
					for (int i = 0; i < list.size(); i++) {
						Product product = list.get(i);
				%>
				<tr class="ct_list_pop">
					<td align="center"><%=i + 1%></td>
					<td></td>

					<td align="left">
						<a href="/getProduct.do?prodNo=<%=product.getProdNo()%>&menu=search"> <%=product.getProdName()%>
						</a>
					</td>

					<td></td>
					<td align="left"><%=product.getPrice()%></td>
					<td></td>
					<td align="left"><%=product.getManuDay()%></td>
					<td></td>

					<td align="left">판매 중</td>

				</tr>

				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>
				<%
					}
				%>


				<!-- PageNavigation Start... -->
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
					<tr>
						<td align="center">
							<input type="hidden" id="currentPage" name="currentPage" value="" />
							<%
								if (resultPage.getCurrentPage() <= resultPage.getPageUnit()) {
							%>
							◀ 이전
							<%
								} else {
							%>
							<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage() - 1%>');">◀ 이전</a>
							<%
								}
							%>
							<%
								for (int i = resultPage.getBeginUnitPage(); i <= resultPage.getEndUnitPage(); i++) {
							%>
							<a href="javascript:fncGetProductList('<%=i%>')"><%=i%></a>
							<%
								}
							%>
							<%
								if (resultPage.getEndUnitPage() >= resultPage.getMaxPage()) {
							%>
							이후 ▶
							<%
								} else {
							%>
							<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage() + 1%>');">이후 ▶</a>
							<%
								}
							%>
						</td>
					</tr>
				</table>
				<!-- PageNavigation End... -->
				</form>

				</div>
</body>
</html>


