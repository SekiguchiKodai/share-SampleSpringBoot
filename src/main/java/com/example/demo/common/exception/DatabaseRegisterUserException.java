package com.example.demo.common.exception;

import org.springframework.dao.DataAccessException;

public class DatabaseRegisterUserException extends DataAccessException {

	public DatabaseRegisterUserException(String msg) {
		super(msg);
	}
	
	public DatabaseRegisterUserException(String msg, Throwable e) {
		super(msg, e);
	}
}
