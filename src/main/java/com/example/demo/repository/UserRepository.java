package com.example.demo.repository;

import com.example.demo.entity.UserTable;

public interface UserRepository {

	public UserTable findByNo(Long no);
	
	public int findMaxNo();
	
	public int insert(UserTable ut);
}
