package com.board.icia.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

//@Setter@Getter
//@AllArgsConstructor
//@NoArgsConstructor

@Alias("member")
@Accessors(chain = true)
@Data
public class Member {
	private String id;
	private String password;
	private String name;
	private String birth;
	private String address;
	private String phone_number;
	private int point;
	
	private String grade_name;
}
