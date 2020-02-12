package com.board.icia.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Alias("bfile")
@Accessors(chain = true)
public class Bfile {
	private String board_file_origin_name;
	private String board_file_sys_name;
}
