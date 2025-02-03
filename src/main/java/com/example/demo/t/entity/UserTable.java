package com.example.demo.t.entity;

import java.sql.Date;

import lombok.Data;

/**
 * <h1>[USER_TABLEのエンティティ]</h1><br>
 * <br>
 * USER_TABLEの情報を持つ。
 */

@Data
public class UserTable {
	/** 番号 */
	private long no;

	/** 氏名 */
	private String name;

	/** 年齢 */
	private long age;
	
	/** 誕生日 */
	private Date birthday;
}
