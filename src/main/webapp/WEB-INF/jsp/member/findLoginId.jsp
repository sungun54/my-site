<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>
	<script>
		var MemberFindLoginIdForm__submitDone = false;
		function MemberFindLoginIdForm__submit(form) {
			if (MemberFindLoginIdForm__submitDone) {
				alert('처리중입니다.');
				return;
			}

			if (form.name.value.length == 0) {
				form.name.focus();
				alert('이름을 입력해주세요.');

				return;
			}

			form.email.value = form.email.value.trim();

			if (form.email.value.length == 0) {
				form.email.focus();
				alert('이메일을 입력해주세요.');

				return;
			}

			form.submit();
			MemberFindLoginIdForm__submitDone = true;
		}
	</script>
	<h2>아이디 찾기</h2>
	<form method="POST" class="table-box con form1" action="doFindLoginId"
		onsubmit="MemberFindLoginIdForm__submit(this); return false;">
		<table>
			<colgroup>
				<col width="70">
			</colgroup>
			<tbody>
				<tr>
					<th>이름</th>
					<td>
						<div class="form-control-box">
							<input type="text" placeholder="이름을 입력해주세요." name="name"
								maxlength="30" autofocus="autofocus" />
						</div>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<div class="form-control-box">
							<input type="text" placeholder="이메일을 입력해주세요." name="email"
								maxlength="30" />
						</div>
					</td>
				</tr>
				<tr>
					<th>로그인</th>
					<td>
						<button class="btn btn-primary" type="submit">로그인</button>
						<button class="btn btn-info" onclick="history.back();"
							type="button">취소</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

	<%@ include file="../part/foot.jspf"%>