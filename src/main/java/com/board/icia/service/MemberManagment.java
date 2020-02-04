package com.board.icia.service;
//회원관리 서비스 클래스 (MODEL)

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.Member;

@Service // @Component
public class MemberManagment {
	@Autowired
	private IMemberDao mDao;
	private ModelAndView mav;


	private void hashMapTest(String id, String pwEncode) {
			Map<String,String> hMap= new HashMap<>();
			hMap.put("id", id);
			hMap.put("password", pwEncode);
			boolean result=mDao.hashMapTest(hMap);
			System.out.println("result="+result); //로그인 성공 true, 실패 false
			hMap=mDao.hashMapTest2(id);
			System.out.println("name="+hMap.get("NAME"));
			System.out.println("grade="+hMap.get("GRADE_NAME"));
			
			
	}
	public ModelAndView memberAccess(Member mb, HttpServletRequest req) {
		mav = new ModelAndView();
		String view = null;
		// 스프링에선 복호화 메소드가 없음
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		String pwEncode = mDao.getSecurityPw(mb.getId());
		System.out.println("DB 패스워스" + pwEncode);
		//hashMap 테스트
		hashMapTest(mb.getId(),pwEncode);
		
		if (pwEncode != null) {
			if (pwEncoder.matches(mb.getPassword(), pwEncode)) { // mb.getpw와 pwEncode와 비교해줌
				// 로그인 성공
				HttpSession session = req.getSession();
				//세션 하이잭킹 예방을 위해 기존 세션을 무효화 한다.
				//HttpSession session = req.getSession();
				//session.invalidate();
				session.setAttribute("id", mb.getId());
				// 로그인 성공 후 회원정보를 화면에 출력하기 위해
				mb = mDao.getMemberInfo(mb.getId());
				session.setAttribute("mb", mb);
				//mav.addObject("mb", mb); //리퀘스트 영역에 모델객체를 저장
				//view="forward:/boardlist"; //forward:url, post-post, get-get끼리만
				//view = "boardList";// jsp
				view="redirect:/boardlist"; //리다이렉포워딩 :url, post,get-->get
			} else {// 비번오류
				view = "home";
				mav.addObject("check", 2);// 로그인 실패
			}
		} else {//아이디오류
			view = "home";
			mav.addObject("check", 2);// 로그인 실패
		}
		mav.setViewName(view);
		return mav;
	}







	public ModelAndView memberJoin(Member mb) {
		mav = new ModelAndView();
		String view = null;
		// Encoder (암호화) Decoder (복호화)
		// 스프링은 암호화는 가능하지만 복호화가 불가능하다.
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		// 비번 암호화해서 DB에 저장
		mb.setPassword(pwEncoder.encode(mb.getPassword()));

		if (mDao.memberJoin(mb)) {
			view = "home";// 회원 가입 성공시 로그인
			mav.addObject("check", 1); // 회원 가입 성공
		} else {
			view = "joinFrm";
		}
		mav.setViewName(view);
		return mav;
	}




}
