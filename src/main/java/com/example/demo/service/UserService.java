package com.example.demo.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.exception.DatabaseRegisterUserException;
import com.example.demo.entity.UserTable;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository  = userRepository;
	}
	
	public long getNewNo() {
		long maxNo = userRepository.findMaxNo();
		return maxNo + 1L;
	}
	
	@Transactional
	public UserTable register(User user) throws ParseException {
		UserTable newUT = new UserTable();
		newUT.setNo(user.getNo());
		newUT.setName(user.getName());
		newUT.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse(user.getBirthday()).getTime()));
		newUT.setAge(user.getAge());
		
		UserTable registeredUT = null;
		try {
			// 登録・登録情報の取得
			userRepository.insert(newUT);
			registeredUT = userRepository.findByNo(newUT.getNo());
		} catch (DataAccessException e) {
			throw new DatabaseRegisterUserException("ユーザ登録時に不具合が発生", e);
		}
		
		return registeredUT;
	}
}
