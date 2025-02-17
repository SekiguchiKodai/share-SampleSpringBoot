package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;
import lombok.Generated;

/**
 * <h1>[USER_TABLEのエンティティ]</h1><br>
 * <br>
 * USER_TABLEの情報を持つ。
 */

@Data
@Generated
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
