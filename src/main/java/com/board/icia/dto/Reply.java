package com.board.icia.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Alias("reply")
public class Reply {
	private int board_number;
	private int reply_number;
	private String reply_contents;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//리스폰용
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//리퀘스트용
	private Timestamp reply_date;
	private String reply_id;
	
}
