package com.example.demo.t.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.example.demo.t.entity.UserTable;
import com.example.demo.t.model.User;
import com.example.demo.t.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository  = userRepository;
	}
	
	public int getNewNo() {
		int maxNo = userRepository.findMaxNo();
		return maxNo + 1;
	}
	
	public UserTable findByNo(Long no) {
		return userRepository.findByNo(no);
	}
	
	public int insert(User user) throws ParseException {
		UserTable ut = new UserTable();
		ut.setNo(user.getNo());
		ut.setName(user.getName());
		ut.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse(user.getBirthday()).getTime()));
		ut.setAge(user.getAge());
		return userRepository.insert(ut);
	}
}
