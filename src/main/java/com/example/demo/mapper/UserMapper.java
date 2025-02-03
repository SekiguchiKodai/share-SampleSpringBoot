package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.UserTable;

/**
 * <h1>[ユーザーテーブルのMapper]</h1><br>
 * <br>
 * ユーザーテーブルのSQLをマップする。
 */
@Mapper
public interface UserMapper {
	
	/**
	 * SELECT ONE USER
	 * @param no
	 * @return
	 */
	public UserTable findByNo(long no);
	
	/**
	 * INSERT
	 * @param user
	 * @return
	 */
	public int insert(UserTable ut);
	
	public int findMaxNo();
}
