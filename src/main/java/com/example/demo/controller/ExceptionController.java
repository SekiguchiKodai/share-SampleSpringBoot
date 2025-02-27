package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.common.exception.DatabaseRegisterUserException;

@ControllerAdvice
public class ExceptionController {
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(exception = DatabaseRegisterUserException.class)
	public String handleDatabaseRegisterUserException(DatabaseRegisterUserException exceprion) {
		System.out.println(exceprion.getMessage());
		return "user/register/error_register";
	}
	
	@ExceptionHandler(exception = Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception exceprion) {
		System.out.println(exceprion.getMessage());
		return "error_system";
	}
}
