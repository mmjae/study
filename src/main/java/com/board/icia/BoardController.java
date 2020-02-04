package com.board.icia;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.service.BoardManagement;

@Controller
public class BoardController {
	
	@Autowired
	private BoardManagement bm; //게시판 서비스 클래스
	
	ModelAndView mav;
	
	@RequestMapping(value = "/boardlist") // method를 지우면 get이든 post를 같이 받음
	public ModelAndView boardList(Integer pageNum) { //파라메터가 안넘어올수도 있기떄문에 Interger 초기값은 null
		mav = bm.getBoardList(pageNum);
		return mav;
	}

	@RequestMapping(value = "/contents", method = RequestMethod.GET) // method를 지우면 get이든 post를 같이 받음
	public ModelAndView contents(Integer bNum) { //파라메터가 안넘어올수도 있기떄문에 Interger 초기값은 null
		mav = bm.getContents(bNum);
		return mav;
	}
	
	
	
}
