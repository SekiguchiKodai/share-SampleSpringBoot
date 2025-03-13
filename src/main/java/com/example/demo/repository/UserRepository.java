package com.example.demo.repository;

import com.example.demo.entity.UserTable;

public interface UserRepository {

	public long findMaxNo();
	
	public UserTable findByNo(Long no);
	
	public int insert(UserTable ut);
}
