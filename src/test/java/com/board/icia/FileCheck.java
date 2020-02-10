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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.icia.service.BoardManagement;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class FileCheck {
	
	@Autowired
	BoardManagement bm;
	
	@Test
	public void bmTest() {
		log.info("bm={}",bm);
		assertThat(bm, is(notNullValue()));
	}
	
	@Test
	public void bmListCheck(MultipartHttpServletRequest multi) {
		
		assertThat(bm.boardWrite(multi), is(nullValue()));
		
	}
}
