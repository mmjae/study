package com.board.icia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dto.Member;
import com.board.icia.service.MemberManagment;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	private MemberManagment mm; //회원관리(BL)
	
	@RequestMapping(value = "/", method = RequestMethod.GET) //method를 지우면 get이든 post를 같이 받음
	public ModelAndView home() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("home"); //로그인 화면
		//mav=mm.access();
		return mav;
	}
	
	@RequestMapping(value = "/access", method = RequestMethod.POST) //method를 지우면 get이든 post를 같이 받음
	public ModelAndView access(Member mb,HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav=mm.memberAccess(mb,req);
		return mav;
	}
	@RequestMapping(value = "/joinfrm", method = RequestMethod.GET) //method를 지우면 get이든 post를 같이 받음
	public ModelAndView joinFrm() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("joinFrm"); //jsp
		return mav;
	}
	@RequestMapping(value = "/memberjoin", method = RequestMethod.POST) //method를 지우면 get이든 post를 같이 받음
	public ModelAndView memberJoin(Member mb) {
		ModelAndView mav=new ModelAndView();
		mav=mm.memberJoin(mb);
		return mav;
	}
	@RequestMapping(value = "/logout", method = RequestMethod.POST) //method를 지우면 get이든 post를 같이 받음
	public ModelAndView logOut(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		HttpSession session=req.getSession();
		session.invalidate();
		String view=null;
		view="redirect:home";
		mav.setViewName(view);
		return mav;
	}
	
	
	
	
}//Controller End
