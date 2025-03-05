package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.common.exception.DatabaseRegisterUserException;

@ControllerAdvice
public class ExceptionController {
	
	private final Logger logger;
	
	public ExceptionController(@Value("${logger.name.exceptioncontroller}") String loggerName) {
		logger = LoggerFactory.getLogger(loggerName);
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(exception = DatabaseRegisterUserException.class)
	public String handleDatabaseRegisterUserException(DatabaseRegisterUserException exceprion) {
		logger.warn(exceprion.getMessage(), exceprion);
		return "user/register/error_register";
	}
	
	@ExceptionHandler(exception = Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception exceprion) {
		logger.error(exceprion.getMessage(), exceprion);
		return "error_system";
	}
}
