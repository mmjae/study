package com.board.icia.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.MillisecondInstantPrinter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.dao.IBoardDao;
import com.board.icia.dto.Bfile;
import com.board.icia.dto.Board;
import com.board.icia.dto.Reply;
import com.board.icia.exception.PageException;
import com.board.icia.userClass.DbException;
import com.board.icia.userClass.Paging;
import com.board.icia.userClass.UploadFile;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.BFILE;

@Slf4j
@Service
public class BoardManagement {
	@Autowired
	private IBoardDao bDao;
	@Autowired
	private UploadFile upload;
	ModelAndView mav;

	@Transactional()
	public ModelAndView boardDelete(Integer bNum, RedirectAttributes attr) throws DbException {
		mav = new ModelAndView();
		System.out.println("삭제 글 번호" + bNum);
		boolean r = bDao.replyDelete(bNum);
		List<Bfile> bfList=bDao.getbfList(bNum);
		boolean f=bDao.fileDelete(bNum);
		upload.delete(bfList);
		boolean a = bDao.boardDelete(bNum);
		if (a == false) { // 원글을 삭제한 경우 예외 발생시켜서 롤백
			throw new DbException();
		}
		if (r && a) {
			System.out.println("삭제 트렌잭션 성공");
			attr.addFlashAttribute("bNum", bNum);
		} else {
			System.out.println("삭제 트렌잭션 실패 ");
		}
		mav.setViewName("redirect:boardlist");
		
		return mav;
	}
	public ModelAndView getBoardList(Integer pageNum) {
		mav = new ModelAndView();
		List<Board> bList = null;
		String view = null;
		Integer pageNumber = (pageNum == null) ? 1 : pageNum;
		if(pageNumber<=0) {
			throw new PageException("잘못된 페이지 번호"); 
		}
		bList = bDao.getBoardList(pageNumber);
		if (bList != null && bList.size() != 0) {
			System.out.println(bList.size());
			mav.addObject("bList", bList);
			mav.addObject("paging", getPaging(pageNumber));
			// ModelMap map=mav.getModelMap();
			view = "boardList"; // jsp
			// List<Board> list=(List<Board>)(map.getAttribute("bList"));
			// for(int i=0;i<list.size();i++) {
			// System.out.println(list.get(i).getBoard_number());
			// }
		} else {
			mav.addObject("blist", "내용이 없습니다.");
			view = "home";
		}
		mav.setViewName(view);
		return mav;
	}

	private Object getPaging(Integer pageNumber) {
		int maxNum = bDao.getBoardCount(); // 전체 글의 개수
		int listCount = 10; // 10 // 페이지당 나타낼 글의 갯수
		int pageCount = 2; // 2 // 페이지그룹당 페이지 갯수
		String boardName = "boardlist"; // 게시판이 여러개일 때 url
		Paging paging = new Paging(maxNum, pageNumber, listCount, pageCount, boardName);
		return paging.makeHtmlPaging();
	}

	public ModelAndView getContents(Integer bNum) {
		mav = new ModelAndView();
		String view = null;
		Board board = bDao.getContents(bNum);
		mav.addObject("board", board);
		//log.info(board.getBoard_contents());
		//log.info("board:{}", board);
		List<Bfile> bfList=bDao.getbfList(bNum);
		log.info("size:{}",bfList.size());
		mav.addObject("bfList",bfList);
		List<Reply> rList = bDao.getReplyList(bNum);
		mav.addObject("rList", rList);
		//log.info("rList:{}", rList);
		//log.info("rList:{}", rList.size());
		view = "boardContentsAjax"; // jsp
		mav.setViewName(view);
		return mav;
	}

	public String replyInsert(Reply r) {
		String json = null;
		if (bDao.replyInsert(r)) {
			List<Reply> rList = bDao.getReplyList(r.getBoard_number());
			json = new Gson().toJson(rList);
		} else {
			json = null;
		}
		return json;
	}

	public Map<String, List<Reply>> replyInsertJackSon(Reply r) {
		Map<String, List<Reply>> rMap = null;
		if (bDao.replyInsert(r)) {
			List<Reply> rList = bDao.getReplyList(r.getBoard_number());
			rMap = new HashMap<String, List<Reply>>();
			rMap.put("rList", rList);
			System.out.println(rMap);
		} else {
			rMap = null;
		}
		return rMap;
	}


	@Transactional
	public ModelAndView boardWrite(MultipartHttpServletRequest multi) {
		// 1개의 file 태그를 이용해서 여러파일을 첨부할 때

		String view = null;
		String title = multi.getParameter("board_title");
		String contents = multi.getParameter("board_contents");
		int check = Integer.parseInt(multi.getParameter("fileCheck"));
		List<MultipartFile> file = multi.getFiles("files");
		System.out.println("체크" + check); // 첨부되면 1 미첨부 0
		String id = (String) multi.getSession().getAttribute("id");
		System.out.println("아이디는" + id);
		Board board = new Board();
		board.setBoard_title(title);
		board.setBoard_contents(contents);
		board.setBoard_id(id);
		boolean b = bDao.boardWrite(board);
		if (b) {
			view = "redirect:/boardlist";
		} else {
			view = "writeFrm";
		}
		boolean f = false;
		if (check == 1) {
			//int bNum = bDao.getCurBoardNum(); // 현재 글번호
			System.out.println("보드넘버"+board.getBoard_number());
			f = upload.fileUp(multi, board.getBoard_number());
			if (f) {
				view = "redirect:/boardlist";
			} else {
				view = "writeFrm";
			}
		}
		mav.setViewName(view);
//		for(int i=0; i<file.size();i++) {
//		System.out.println("파일이름"+file.get(i).getOriginalFilename());	
//		}
		return mav;
	}

	public void download(HttpServletRequest req, HttpServletResponse resp) {
		String root = req.getSession().getServletContext().getRealPath("/");
		System.out.println("root=" + root);
		String path = root + "upload/"+req.getParameter("sysfilename");
		String oriFileName=req.getParameter("orifilename");
		try {
			upload.download(path, oriFileName, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public ModelAndView execute(Integer bNum, RedirectAttributes attr, int cmd) {
//		mav=new ModelAndView();
//		switch(cmd) {
//		case 1:
//			mav=fct1();
//			mav=fct2();
//			break;
//		case 2:
//		}
//		return mav;
//	}
	
}
