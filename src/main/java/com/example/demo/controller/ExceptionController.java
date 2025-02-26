package com.example.demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.common.exception.DatabaseRegisterUserException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(exception = DatabaseRegisterUserException.class)
	public String handleDatabaseRegisterUserException(DatabaseRegisterUserException exceprion) {
		System.out.println(exceprion.getMessage());
		return "user/register/error_register";
	}
	
	@ExceptionHandler(exception = Exception.class)
	public String handleDatabaseRegisterUserException(Exception exceprion) {
		System.out.println(exceprion.getMessage());
		return "error_system";
	}
}
