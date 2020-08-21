package com.sbs.kys.mysite.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.kys.mysite.dto.Member;

@Mapper
public interface MemberDao {
	void join(Map<String, Object> param);
	int getLoginIdDupCount(@Param("loginId") String loginId);
	Member getMemberByLoginId(String loginId);
	Member getMemberById(int loginedMemberId);
	int nicknameCheck(String nickname);
	void modify(String nickname, int id);
	Member memberPwConfirm(int id, String loginPw);
	void pwModify(int id, String loginPw);
	Member doFindLoginId(String email, String name);
}
