package com.board.icia;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.dto.Board;
import com.board.icia.service.BoardManagement;
import com.board.icia.userClass.DbException;

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
	
	@GetMapping(value = "/boarddelete")
	public ModelAndView boardDelete(Integer bNum,RedirectAttributes attr) throws DbException { //파라메터가 안넘어올수도 있기떄문에 Interger 초기값은 null
		mav = bm.boardDelete(bNum,attr);
		return mav;
	}
	

	@RequestMapping(value = "/contents", method = RequestMethod.GET) // method를 지우면 get이든 post를 같이 받음
	public ModelAndView contents(Integer bNum) { //파라메터가 안넘어올수도 있기떄문에 Interger 초기값은 null
		mav = bm.getContents(bNum);
		return mav;
	}
	@GetMapping(value = "/writefrm") // method를 지우면 get이든 post를 같이 받음
	public ModelAndView writeFrm() { //파라메터가 안넘어올수도 있기떄문에 Interger 초기값은 null
		mav = new ModelAndView();
		mav.setViewName("writeFrm");
		return mav;
	}
	@PostMapping (value = "/boardwrite")
	public ModelAndView boardWrite(MultipartHttpServletRequest multi) {
		mav= bm.boardWrite(multi);
		return mav ;
	}
	
}
