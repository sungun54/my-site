<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name} 게시물 작성" />
<%@ include file="../part/head.jspf"%>
<%@ include file="../part/toastuiEditor.jspf"%>
<script>
	var ArticleWriteForm__submitDone = false;
	function ArticleWriteForm__submit(form) {
		if (ArticleWriteForm__submitDone) {
			alert('처리중입니다.');
			return;
		}
		form.title.value = form.title.value.trim();
		if (form.title.value.length == 0) {
			form.title.focus();
			alert('제목을 입력해주세요.');
			return;
		}
		var bodyEditor = $(form).find('.toast-editor.input-body').data(
				'data-toast-editor');
		var body = bodyEditor.getMarkdown().trim();
		if (body.length == 0) {
			bodyEditor.focus();
			alert('특이사항을 입력해주세요.');
			return;
		}
		form.body.value = body;

		form.submit();
	}
</script>
<form method="POST" class="table-box con form1"
	action="${board.code}-doWrite"
	onsubmit="ArticleWriteForm__submit(this); return false;">
	<input type="hidden" name="redirectUri"
		value="/usr/article/${board.code}-detail?id=#id"> <input
		type="hidden" name="body" />
	<table>
		<colgroup>
			<col width="100"></col>
		</colgroup>
		<tbody>
			<tr>
				<th>제목</th>
				<td>
					<div class="form-control-box">
						<input type="text" placeholder="제목을 입력해주세요." name="title"
							maxlength="100" />
					</div>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<div class="form-control-box">
						<script type="text/x-template">
# 제목
![img](https://placekitten.com/200/287)
이미지는 이렇게 씁니다.

# 유투브 동영상 첨부

아래와 같이 첨부할 수 있습니다.

```youtube
https://www.youtube.com/watch?v=LmgWxezH7cc```
                        </script>
						<div data-relTypeCode="article" data-relId="0"
							class="toast-editor input-body"></div>
					</div>
				</td>
			</tr>

			<tr>
				<th>작성</th>
				<td>
					<button class="btn btn-primary" type="submit">작성</button> <a
					class="btn btn-info" href="${listUrl}">리스트</a>
				</td>
			</tr>
		</tbody>
	</table>
</form>
<%@ include file="../part/foot.jspf"%>