package com.board.icia;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.Member;
import com.board.icia.service.MemberManagment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
@Transactional  //테스트 조작시 자동으로 롤백처리해줌
public class JoinTest {
	@Autowired
	MemberManagment mm;
	@Autowired
	IMemberDao mDao;
	@Test
	public void test() {
		log.info("mm={}",mm);
		Member mb= new Member();
		mb.setId("ASXAX").setPassword("1111").setName("마재").setBirth("12321").setAddress("asd").setPhone_number("123123").setPoint(0);
		int result =(int) mm.memberJoin(mb).getModel().get("check");
		assertThat(result, is(0));
	}
}
