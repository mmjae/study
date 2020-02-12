package com.board.icia;




import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dto.Board;
import com.board.icia.dto.Reply;
import com.board.icia.service.BoardManagement;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;

import lombok.Setter;

@RestController
//@responseBody생략가능
@RequestMapping(value = "/rest") // 공통 url은 뺄 수 있다.
public class BoardRestController {
	// @Setter(onMethod = @Autowired) //lombok을 쓸경우
	@Autowired
	private BoardManagement bm;
	ModelAndView mav;

//	@RequestMapping(value = "/replyinsert" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//	//public @ResponseBody String replyInsert(@RequestBody Reply r) { //json을 커맨드 객체로 저장할경우
//		public @ResponseBody String replyInsert(Reply r,HttpServletRequest req) { //json을 커맨드 객체로 저장할경우
//		r.setReply_id(req.getSession().getAttribute("id").toString());
//		System.out.println(r.getBoard_number());
//		String json= bm.replyInsert(r);
//		return json;
//
//	}
	//예전방식 @RequestMapping(value = "/replyinsert")
//	public String replyinsert(Reply r,HttpServletResponse res) {
//
//		System.out.println(r.getReply_contents());
//		System.out.println(r.getBoard_number());
//		res.setContentType("text/html;sharset=utf-8");
//		try {
//			PrintWriter out=res.getWriter();
//			out.print(json);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return json;
//
//	}
	//jackson 활용
	@RequestMapping(value = "/replyinsert" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//public @ResponseBody String replyInsert(@RequestBody Reply r) { //json을 커맨드 객체로 저장할경우
	//@ResponseBody ResponseEntity<?> //바디값과 헤더에 상태값 적용가능 
	public Map<String,List<Reply>> replyInsert(Reply r,HttpServletRequest req) { //json을 커맨드 객체로 저장할경우
		r.setReply_id(req.getSession().getAttribute("id").toString());
		Map<String,List<Reply>> rMap=bm.replyInsertJackSon(r);
		return rMap; //jackson역활 :Map을 -->json으로 변환함
		//ex:{'rList', rList} -->{"rList",[{},{},{}]}
		//return ReponseEntity.ok(rMap);
	}
	
	//public String boardwrite(@RequestParam("board_title") String title,@RequestParam("board_contents") String con
	//		,@RequestParam("files") List<MultipartFile> file) { //json을 커맨드 객체로 저장할경우
//	@PostMapping(value = "/boardwrite", produces = "application/json;charset=UTF-8")
//		public String boardwrite(Board board,List<MultipartFile> files) {
//		System.out.println("타이틀은"+board.getBoard_title());
//		System.out.println("내용은"+board.getBoard_contents());
//		System.out.println("파일은"+files.get(0).getOriginalFilename());
//		System.out.println("파일은"+files.get(1).getOriginalFilename());
//		
//		for(int i= 0 ; i <files.size();i++) {
//			System.out.println(files.get(i));
//		}
//		return new Gson().toJson(files); //제이슨 형태가 아님으로 에러
//	}
	@PostMapping(value = "/boardwrite", produces = "application/json;charset=UTF-8")
	public String boardwrite(MultipartHttpServletRequest multi) {
	System.out.println("타이틀은"+multi.getParameter("board_title"));
	System.out.println("내용은"+multi.getParameter("board_contents"));
	System.out.println("파일체크"+multi.getParameter("fileCheck"));
	List<MultipartFile> files=multi.getFiles("files");
	
	System.out.println("파일은"+files.get(0).getOriginalFilename());
	
	for(int i= 0 ; i <files.size();i++) {
		System.out.println(files.get(i));
	}
	return new Gson().toJson(files); //제이슨 형태가 아님으로 에러
}
	
	
	
}
