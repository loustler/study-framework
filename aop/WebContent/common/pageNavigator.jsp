<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "httc://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- PageNavigation Start... -->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-toc: 10px;">
		<tr>
			<td align="center">
				<input type="hidden" id="currentPage" name="currentPage" value="" />
				<input type="hidden" id="menu" name="menu" value="${menu}" />
				
				<c:if test="${resultPage.getCurrentPage()<=resultPage.getPageUnit()}">
						◀ 이전
				</c:if>
				<c:if test="${resultPage.getCurrentPage()>resultPage.getPageUnit()}">
					<a href="javascript:fncPageNavigator('${resultPage.getCurrentPage() -1}')">◀ 이전</a>
				</c:if>

				<c:forEach var="i" begin="${resultPage.getBeginUnitPage()}" end="${resultPage.getEndUnitPage()}" step="1">
					<a href="javascript:fncPageNavigator('${i}');">${i}</a>
				</c:forEach>

				<c:if test="${resultPage.getEndUnitPage()>=resultPage.getMaxPage()}">
					이후 ▶
				</c:if>
				<c:if test="${resultPage.getEndUnitPage()<resultPage.getMaxPage()}">
					<a href="javascript:fncPageNavigator('${resultPage.getEndUnitPage()+1}')">이후 ▶</a>
				</c:if>
			</td>
		</tr>
	</table>
	<!-- PageNavigation End... -->
</body>
</html>