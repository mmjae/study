package com.board.icia.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Alias("reply")
public class Reply {
	private int board_number;
	private int reply_number;
	private String reply_contents;
	private Timestamp reply_date;
	private String reply_id;
}
