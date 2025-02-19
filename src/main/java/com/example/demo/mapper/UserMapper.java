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
	 * find max user no
	 * @return
	 */
	public int findMaxNo();
	
	/**
	 * find one user
	 * @param no
	 * @return
	 */
	public UserTable findByNo(long no);
	
	/**
	 * insert user
	 * @param user
	 * @return
	 */
	public int insert(UserTable ut);
	
	
}
