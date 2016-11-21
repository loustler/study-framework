<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>회원정보조회</title>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
<!--
	$(function() {

		$('td.ct_btn01:contains("수정")').on('click', function() {
			self.location = '/user/updateUserView?userId=${user.userId}';
		});

		$('td.ct_btn01:contains("확인")').on('click', function() {
			history.go(-1);
		});

		$('td.ct_btn01:contains("회원탈퇴")').on('click', function() {
			self.location = '/user/removeUser?userId=${user.userId}';
		});
	});
	-->
</script>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

	<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="15" height="37">
				<img src="/images/ct_ttl_img01.gif" width="15" height="37" />
			</td>
			<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="93%" class="ct_ttl01">회원정보조회</td>
						<td width="20%" align="right">&nbsp;</td>
					</tr>
				</table>
			</td>
			<td width="12" height="37">
				<img src="/images/ct_ttl_img03.gif" width="12" height="37" />
			</td>
		</tr>
	</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 13px;">

		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>

		<tr>
			<td width="104" class="ct_write">
				아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle" />
			</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">${user.userId}</td>
		</tr>

		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>

		<tr>
			<td width="104" class="ct_write">
				이름 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle" />
			</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">${user.userName}</td>
		</tr>

		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>

		<tr>
			<td width="104" class="ct_write">주소</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">${user.addr}</td>
		</tr>

		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>

		<tr>
			<td width="104" class="ct_write">휴대전화번호</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">${user.phone}</td>
		</tr>

		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>

		<tr>
			<td width="104" class="ct_write">이메일</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">${user.email}</td>
		</tr>

		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>

		<tr>
			<td width="104" class="ct_write">가입일자</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">${user.regDate}</td>
		</tr>

		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>

	</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
		<tr>
			<td width="53%"></td>
			<td align="right">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<u:if test="${sessionScope.user.userId == requestScope.user.userId}">
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23" />
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">회원탈퇴</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23" />
							</td>
							<td width="17" height="23">
						</u:if>
						<td width="17" height="23">
							<img src="/images/ct_btnbg01.gif" width="17" height="23" />
						</td>
						<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">수정</td>
						<td width="14" height="23">
							<img src="/images/ct_btnbg03.gif" width="14" height="23" />
						</td>
						<td width="15"></td>
						<td width="17" height="23">
							<img src="/images/ct_btnbg01.gif" width="17" height="23" />
						</td>
						<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">확인</td>
						<td width="14" height="23">
							<img src="/images/ct_btnbg03.gif" width="14" height="23" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>