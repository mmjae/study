package com.board.icia;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
@Transactional//기본 롤백 DB에가서 CX//RUD를 롤백시킴
public class MemberDaoTest {
	@Autowired
	private IMemberDao mDao;
	
	@Test
	public void loginTest() {
		
		log.info("dao:{}",mDao);
		Member mb=new Member().setId("MA").setPassword("1111");
		
		assertThat(mDao.getLoginResult(mb), is(not(false)));
		
		mb=mDao.getMemberInfo("AAA");
		assertThat(mb.getName(),is("에이"));
		
	}
	
	
	
	
	
	
	
}
