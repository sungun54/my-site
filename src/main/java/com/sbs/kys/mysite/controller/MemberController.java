package com.sbs.kys.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.kys.mysite.dto.Member;
import com.sbs.kys.mysite.dto.ResultData;
import com.sbs.kys.mysite.service.MemberService;
import com.sbs.kys.mysite.util.Util;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/findLoginId")
	public String showFindLoginId() {
		return "member/findLoginId";
	}
	
	@RequestMapping("/usr/member/doFindLoginId")
	public String doFindLoginId(@RequestParam Map<String, Object> param, String email, String name, Model model) {
		
		Member member = memberService.doFindLoginId(email, name);
		
		if (member == null) {
			model.addAttribute("historyBack", true);
			model.addAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			return "common/redirect";
		}
		
		model.addAttribute("windowClose", true);
		model.addAttribute("alertMsg", member.getLoginId() + "입니다.");		
		return "common/redirect";
	}
	
	@RequestMapping("/usr/member/pwModify")
	public String showPwModify() {
		return "member/pwModify";
	}

	@RequestMapping("/usr/member/doPwModify")
	public String doPwModify(@RequestParam Map<String, Object> param, String loginPwReal, int id, Model model) {
		String loginPw = loginPwReal;
		memberService.pwModify(id, loginPw);

		String redirectUri = (String) param.get("redirectUri");

		model.addAttribute("redirectUri", redirectUri);

		return "common/redirect";
	}

	@RequestMapping("/usr/member/pwConfirm")
	public String showPwConfirm() {
		return "member/pwConfirm";
	}

	@RequestMapping("/usr/member/doPwConfirm")
	public String doPwConfirm(int id, String loginPwReal, String redirectUri, Model model, HttpSession session) {
		String loginPw = loginPwReal;
		System.out.println("sadfsadg : " + loginPwReal);
		Member member = memberService.memberPwConfirm(id, loginPw);

		if (member == null) {
			model.addAttribute("historyBack", true);
			model.addAttribute("alertMsg", "비밀번호가 일치하지 않습니다..");
			return "common/redirect";
		}

		model.addAttribute("redirectUri", redirectUri);

		return "common/redirect";
	}

	@RequestMapping("/usr/member/modify")
	public String showModify() {
		return "member/modify";
	}

	@RequestMapping("/usr/member/doModify")
	public String doModify(@RequestParam Map<String, Object> param, String nickname, int id, Model model) {
		ResultData checkNicknameAbleResultData = memberService.nicknameCheck(nickname);

		if (checkNicknameAbleResultData.isFail()) {
			model.addAttribute("historyBack", true);
			model.addAttribute("alertMsg", checkNicknameAbleResultData.getMsg());
			return "common/redirect";
		}

		memberService.Modify(nickname, id);

		String redirectUri = (String) param.get("redirectUri");
		model.addAttribute("redirectUri", redirectUri);

		return "common/redirect";
	}

	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "member/login";
	}

	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	public String doJoin(@RequestParam Map<String, Object> param, Model model) {
		Util.changeMapKey(param, "loginPwReal", "loginPw");
		
		ResultData checkLoginIdJoinableResultData = memberService
				.checkLoginIdJoinable(Util.getAsStr(param.get("loginId")));

		if (checkLoginIdJoinableResultData.isFail()) {
			model.addAttribute("historyBack", true);
			model.addAttribute("alertMsg", checkLoginIdJoinableResultData.getMsg());
			return "common/redirect";
		}
		int newMemberId = memberService.join(param);

		String redirectUri = (String) param.get("redirectUri");
		model.addAttribute("redirectUri", redirectUri);

		return "common/redirect";
	}

	@RequestMapping("/usr/member/doLogin")
	public String doLogin(String loginId, String loginPwReal, String redirectUri, Model model, HttpSession session) {
		String loginPw = loginPwReal;
		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			model.addAttribute("historyBack", true);
			model.addAttribute("alertMsg", "존재하지 않는 회원입니다.");
			return "common/redirect";
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			model.addAttribute("historyBack", true);
			model.addAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			return "common/redirect";
		}

		session.setAttribute("loginedMemberId", member.getId());

		if (redirectUri == null || redirectUri.length() == 0) {
			redirectUri = "/usr/home/main";
		}

		model.addAttribute("redirectUri", redirectUri);
		model.addAttribute("alertMsg", String.format("%s님 반갑습니다.", member.getNickname()));

		return "common/redirect";
	}

	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpSession session, Model model, String redirectUri) {
		session.removeAttribute("loginedMemberId");

		if (redirectUri == null || redirectUri.length() == 0) {
			redirectUri = "/usr/home/main";
		}

		model.addAttribute("redirectUri", redirectUri);
		return "common/redirect";
	}
}
