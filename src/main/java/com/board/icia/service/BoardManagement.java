package com.board.icia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dao.IBoardDao;
import com.board.icia.dto.Board;

@Service
public class BoardManagement {
	@Autowired
	private IBoardDao bDao;
	ModelAndView mav;
	public ModelAndView getBoardList(Integer pageNum, HttpServletRequest req) {
		mav=new ModelAndView();
		List<Board> bList=null;
		String view=null;
		Integer pageNumber=(pageNum==null)?1: pageNum;
			bList=bDao.getBoardList(pageNumber);
			if(bList!=null&&bList.size()!=0) {  
				System.out.println(bList.size());
				mav.addObject("bList",bList);
				view="boardList"; //jsp
			}else {
				mav.addObject("blist","내용이 없습니다.");
				view="home";
			}
		mav.setViewName(view);			
		return mav;
	}
	public ModelAndView getContents(Integer bNum, HttpServletRequest req) {
		mav=new ModelAndView();
		String view=null;
		view="boardList";
		mav.setViewName(view);
		return mav;
	}
}
