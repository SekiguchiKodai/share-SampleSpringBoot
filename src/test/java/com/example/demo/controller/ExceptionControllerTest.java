package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.common.exception.DatabaseRegisterUserException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

public class ExceptionControllerTest extends BaseControllerTest {

	@MockitoBean
	private UserService userService;
	
	@Autowired
	public ExceptionControllerTest(MockMvc mockMvc) {
		super(mockMvc);
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class _0101_handleDatabaseRegisterUserException {
		
		@Test
		@DisplayName("正常_ユーザ登録時にDatabaseRegisterUserExceptionが発生した場合画面がerror_registerに遷移する")
		public void _010101_handleDatabaseRegisterUserException() throws Exception {
			// Mock UserService.register()実行時に例外を発生させる
			doThrow(new DatabaseRegisterUserException("message")).when(userService).register(any(User.class));
			// テスト実行
			mockMvc.perform(post("/commit").param("commit", "commit").flashAttr("user", new User()))
				   .andDo(print(fileWriter))
				   .andExpect(status().isInternalServerError())
				   .andExpect(view().name("user/register/error_register"));
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class _0201_handleException {
		
		@Test
		@DisplayName("正常_ユーザ登録時に何らかのExceptionが発生した場合画面がerror_systemに遷移する")
		public void _020101_handleException() throws Exception {

			// Mock UserService.register()実行時に例外を発生させる
			doThrow(new IllegalArgumentException()).when(userService).register(any(User.class));
			// テスト実行
			mockMvc.perform(post("/commit").param("commit", "commit").flashAttr("user", new User()))
				   .andDo(print(fileWriter))
				   .andExpect(status().isInternalServerError())
				   .andExpect(view().name("error_system"));
		}
	}
}
