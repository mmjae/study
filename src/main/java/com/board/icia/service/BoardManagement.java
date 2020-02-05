package com.board.icia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dao.IBoardDao;
import com.board.icia.dto.Board;
import com.board.icia.dto.Reply;
import com.board.icia.userClass.Paging;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardManagement {
	@Autowired
	private IBoardDao bDao;
	ModelAndView mav;

	public ModelAndView getBoardList(Integer pageNum) {
		mav = new ModelAndView();
		List<Board> bList = null;
		String view = null;
		Integer pageNumber = (pageNum == null) ? 1 : pageNum;
		bList = bDao.getBoardList(pageNumber);
		if (bList != null && bList.size() != 0) {
			System.out.println(bList.size());
			mav.addObject("bList", bList);
			mav.addObject("paging", getPaging(pageNumber));
			view = "boardList"; // jsp
		} else {
			mav.addObject("blist", "내용이 없습니다.");
			view = "home";
		}
		mav.setViewName(view);
		return mav;
	}

	private Object getPaging(Integer pageNumber) {
		int maxNum=bDao.getBoardCount(); // 전체 글의 개수
		int listCount=10; // 10 // 페이지당 나타낼 글의 갯수
		int pageCount=2; // 2 // 페이지그룹당 페이지 갯수
		String boardName="boardlist"; //게시판이 여러개일 때 url
		Paging paging=new Paging(maxNum,pageNumber,listCount,pageCount,boardName);
		return paging.makeHtmlPaging();
	}

	public ModelAndView getContents(Integer bNum) {
		mav = new ModelAndView();
		String view = null;
		Board board = bDao.getContents(bNum);
		mav.addObject("board", board);
		log.info(board.getBoard_contents());
		log.info("board:{}", board);
		List<Reply> rList = bDao.getReplyList(bNum);
		mav.addObject("rList", rList);
		log.info("rList:{}", rList);
		log.info("rList:{}", rList.size());
		view = "boardContentsAjax"; // jsp
		mav.setViewName(view);
		return mav;
	}

	public String replyInsert(Reply r) {
		String json=null;
		if (bDao.replyInsert(r)) {
			List<Reply> rList=bDao.getReplyList(r.getBoard_number());
			json=new Gson().toJson(rList);
		}else {
			json=null;
		}
		return json;
	}

	public Map<String, List<Reply>> replyInsertJackSon(Reply r) {
		Map<String, List<Reply>> rMap=null;
		if(bDao.replyInsert(r)) {
			List<Reply> rList=bDao.getReplyList(r.getBoard_number());
			rMap=new HashMap<String, List<Reply>>();
			rMap.put("rList", rList);
			System.out.println(rMap);
		}else {
			rMap=null;
		}
		return rMap;
	}
}
