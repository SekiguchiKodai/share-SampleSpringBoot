package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserTable;
import com.example.demo.mapper.UserMapper;

@Repository
public class ImpUserRepository implements UserRepository {
	
	private final UserMapper userMapper;
	
	@Autowired
	public ImpUserRepository(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public UserTable findByNo(Long no) {
		return userMapper.findByNo(no);
	}
	
	public long findMaxNo() {
		return userMapper.findMaxNo();
	}
	
	public int insert(UserTable ut) {
		return userMapper.insert(ut);
	}
}
