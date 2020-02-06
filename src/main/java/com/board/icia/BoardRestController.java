package com.board.icia;




import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	public Map<String,List<Reply>> replyInsert(Reply r,HttpServletRequest req) { //json을 커맨드 객체로 저장할경우
		r.setReply_id(req.getSession().getAttribute("id").toString());
		Map<String,List<Reply>> rMap=bm.replyInsertJackSon(r);
		return rMap; //jackson역활 :Map을 -->json으로 변환함
		//ex:{'rList', rList} -->{"rList",[{},{},{}]}
	}
}
