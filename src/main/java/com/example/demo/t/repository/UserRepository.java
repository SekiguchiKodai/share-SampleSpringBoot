package com.example.demo.t.repository;

import com.example.demo.t.entity.UserTable;

public interface UserRepository {

	public UserTable findByNo(Long no);
	
	public int findMaxNo();
	
	public int insert(UserTable ut);
}
