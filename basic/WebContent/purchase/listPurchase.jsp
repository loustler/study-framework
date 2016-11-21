<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.model2.mvc.common.SearchVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	SearchVO vo = null;
	List<PurchaseVO> purList = null;
	Map<String, Object> map = null;
	int total = 0;
	if (request.getAttribute("map") != null && request.getAttribute("searchVO") != null) {
		map = (Map<String, Object>) request.getAttribute("map");
		vo = (SearchVO) request.getAttribute("searchVO");
		if (map != null) {
			purList = (List<PurchaseVO>) map.get("getlist");
			total = ((Integer) map.get("Listcount")).intValue();
		}
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
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/listUser.do" method="post">

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37"></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">구매 목록조회</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37"></td>
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
					<td class="ct_list_b" width="150">회원ID</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">회원명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">전화번호</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">배송현황</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">정보수정</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>

				<%
					int no = purList.size();
					for (int i = 0; i < no; i++) {
						PurchaseVO purchaseVO = purList.get(i);
				%>

				<tr class="ct_list_pop">
					<td align="center"><a
						href="/getPurchase.do?tranNo=<%=purchaseVO.getTranNo()%>"><%=i + 1%></a>
					</td>
					<td></td>
					<td align="left"><a
						href="/getUser.do?userId=<%=purchaseVO.getBuyer().getUserId()%>"><%=purchaseVO.getBuyer().getUserId()%></a>
					</td>
					<td></td>
					<td align="left"><%=purchaseVO.getReceiverName()%></td>
					<td></td>
					<td align="left"><%=purchaseVO.getReceiverPhone()%></td>
					<td></td>
					<td align="left"><%=purchaseVO.getTranCode()%></td>
					<td></td>
					<%
						if (purchaseVO.getTranCode().equals("배송 중")) {
					%>
					<td align="left"></td>
					<a href="/updateTranCode.do?tranNo=<%=purchaseVO.getTranNo()%>&tranCode=003">물건도착</a>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>

				<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>

			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<%
						for (int i = 0; i < totalPage; i++) {
					%>
					<td align="center"><a href="/listPurchase.do?page=<%=i + 1%>"
						<%=i + 1%>></a> <%
 	}
 %>
				</tr>
			</table>

			<!--  페이지 Navigator 끝 -->
		</form>

	</div>

</body>
</html>