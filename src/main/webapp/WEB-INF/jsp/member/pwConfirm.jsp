<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="비밀번호 확인" />
<%@ include file="../part/head.jspf"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
	var MemberConfirmForm__submit__submitDone = false;
	function MemberConfirmForm__submit(form) {
		if (MemberConfirmForm__submit__submitDone) {
			alert('처리중입니다.');
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();

		if (form.loginPw.value.length == 0) {
			form.loginPw.focus();
			alert('로그인 비밀번호를 입력해주세요.');

			return;
		}

		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value = '';
		form.loginPwConfirm.value = '';

		form.submit();
		MemberConfirmForm__submit__submitDone = true;
	}
</script>
<form method="POST" class="table-box con form1" action="doPwConfirm"
	onsubmit="MemberConfirmForm__submit(this); return false;">
	<input type="hidden" name="redirectUri" value="/usr/member/pwModify">
	<input type="hidden" name="loginPwReal">
	<input type="hidden" name="id" value="${loginedMember.id}">
	<table>
		<colgroup>
			<col width="70">
		</colgroup>
		<tbody>
			<tr>
				<th>로그인 비번</th>
				<td>
					<div class="form-control-box">
						<input type="password" placeholder="로그인 비밀번호를 입력해주세요."
							name="loginPw" maxlength="30"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>확인</th>
				<td>
					<button class="btn btn-primary" type="submit">확인</button>
					<button class="btn btn-info" type="button"
						onclick="history.back();">취소</button>
				</td>
			</tr>

		</tbody>
	</table>
</form>
<%@ include file="../part/foot.jspf"%>