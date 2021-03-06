package com.sbs.kys.mysite.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sbs.kys.mysite.dao.MemberDao;
import com.sbs.kys.mysite.dto.Member;
import com.sbs.kys.mysite.dto.ResultData;
import com.sbs.kys.mysite.util.Util;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MailService mailService;
	@Value("${custom.siteMainUri}")
	private String siteMainUri;
	@Value("${custom.siteName}")
	private String siteName;

	public ResultData checkLoginIdJoinable(String loginId) {
		int count = memberDao.getLoginIdDupCount(loginId);

		if (count == 0) {
			return new ResultData("S-1", "가입가능한 로그인 아이디 입니다.", "loginId", loginId);
		}

		return new ResultData("F-1", "이미 사용중인 로그인 아이디 입니다.", "loginId", loginId);
	}

	public int join(Map<String, Object> param) {
		memberDao.join(param);

		sendJoinCompleteMail((String) param.get("email"));

		return Util.getAsInt(param.get("id"));
	}

	private void sendJoinCompleteMail(String email) {
		String mailTitle = String.format("[%s] 가입이 완료되었습니다.", siteName);

		StringBuilder mailBodySb = new StringBuilder();
		mailBodySb.append("<h1>가입이 완료되었습니다.</h1>");
		mailBodySb.append(String.format("<p><a href=\"%s\" target=\"_blank\">%s</a>로 이동</p>", siteMainUri, siteName));

		mailService.send(email, mailTitle, mailBodySb.toString());
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int loginedMemberId) {
		return memberDao.getMemberById(loginedMemberId);
	}

	public ResultData nicknameCheck(String nickname) {
		int count = memberDao.nicknameCheck(nickname);

		if (count == 0) {
			return new ResultData("S-1", "사용가능한 닉네임 입니다.", "nickname", nickname);
		}

		return new ResultData("F-1", "이미 사용중인 닉네임 입니다.", "nickname", nickname);
	}

	public void Modify(String nickname, int id) {
		memberDao.modify(nickname, id);
	}

	public Member memberPwConfirm(int id, String loginPw) {
		return memberDao.memberPwConfirm(id, loginPw);
	}

	public void pwModify(int id, String loginPw) {
		memberDao.pwModify(id, loginPw);		
	}

	public Member doFindLoginId(String email, String name) {
		return memberDao.doFindLoginId(email, name);
	}

	public ResultData isJoinableEmail(String email) {
		int count = memberDao.emailCheck(email);

		if (count == 0) {
			return new ResultData("S-1", "사용가능한 이메일 입니다.", "email", email);
		}

		return new ResultData("F-1", "이미 사용중인 이메일 입니다.", "email", email);
	}

}
