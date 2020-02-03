package com.board.icia.dto;


import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;

@Alias("board")
@Data
@Accessors(chain = true)
public class Board {
	private int board_number;
	private String board_title;
	private String board_contents;
	private String board_id;
	private String name;
	private Timestamp board_date;
	private int b_views;
}
