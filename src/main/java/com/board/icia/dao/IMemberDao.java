package com.board.icia.dao;

import java.util.Map;

import com.board.icia.dto.Member;

public interface IMemberDao {
	public boolean getLoginResult(Member mb);

	public boolean memberJoin(Member mb);

	public String getSecurityPw(String id);

	public Member getMemberInfo(String id);

	public boolean hashMapTest(Map<String, String> hMap);

	public Map<String, String> hashMapTest2(String id);
}
