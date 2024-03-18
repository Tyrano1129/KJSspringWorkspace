package com.mysql.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Member {
	 private int num;
	 private String id;
	 private String pw;
	 private String email;
}
