<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원정보수정" />
<%@ include file="../part/head.jspf"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
	var MemberUpdateForm__submitDone = false;
	function MemberUpdateForm__submit(form) {
		if (MemberUpdateForm__submitDone) {
			alert('처리중입니다.');
			return;
		}



		form.nickname.value = form.nickname.value.trim();

		if (form.nickname.value.length == 0) {
			form.nickname.focus();
			alert('닉네임을 입력해주세요.');

			return;
		}

		if (form.nickname.value.length > 8) {
			form.nickname.focus();
			alert('닉네임은 8자 이하로 입력해주세요.');
			return;
		}

		form.submit();
		MemberUpdateForm__submitDone = true;
	}
</script>
<form method="POST" class="table-box con form1" action="doModify"
	onsubmit="MemberUpdateForm__submit(this); return false;">
	<input type="hidden" name="redirectUri" value="/usr/home/main">
	<input type="hidden" name="loginPwReal">
	<input type="hidden" name="id" value="${loginedMember.id}">
	<table>
		<colgroup>
			<col width="70">
		</colgroup>
		<tbody>
			<tr>
				<th>로그인 아이디</th>
				<td>
					<div class="form-control-box">
						<input type="text" placeholder="로그인 아이디 입력해주세요." name="loginId"
							maxlength="30"  value="${loginedMember.loginId}" disabled/>
					</div>
				</td>
			</tr>
			<tr>
				<th>로그인 비번</th>
				<td>
					<div class="form-control-box">
						<input type="password" placeholder="로그인 비밀번호를 입력해주세요."
							name="loginPw" maxlength="30" disabled/>
					</div>
				</td>
			</tr>
			<tr>
				<th>로그인 비번 확인</th>
				<td>
					<div class="form-control-box">
						<input type="password" placeholder="로그인 비밀번호 확인을 입력해주세요."
							name="loginPwConfirm" maxlength="30" disabled/>
					</div>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<div class="form-control-box">
						<input type="text" placeholder="이름을 입력해주세요." name="name"
							maxlength="20"  value="${loginedMember.name}" disabled/>
					</div>
				</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td>
					<div class="form-control-box">
						<input type="text" placeholder="닉네임 입력해주세요." name="nickname"
							maxlength="20"   value="${loginedMember.nickname}"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<div class="form-control-box">
						<input type="email" placeholder="이메일 입력해주세요." name="email"
							maxlength="50"  value="${loginedMember.email}" disabled/>
					</div>
				</td>
			</tr>
<!-- 		<tr>
				<th>휴대폰</th>
				<td>
					<div class="form-control-box">
						<input type="tel" placeholder="휴대전화번호를 입력해주세요." name="cellphoneNo"
							maxlength="12" />
					</div>
				</td>
			</tr>
			 -->
			<tr>
				<th>수정</th>
				<td>
					<button class="btn btn-primary" type="submit">수정</button>
					<button class="btn btn-info" type="button"
						onclick="history.back();">취소</button>
				</td>
			</tr>

		</tbody>
	</table>
</form>
<%@ include file="../part/foot.jspf"%>