package com.example.demo.controller;

import static org.hamcrest.Matchers.*;
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

import com.example.demo.model.User;
import com.example.demo.service.UserService;

public class HomeControllerTest extends BaseControllerTest {

	@MockitoBean
	private UserService mockUserService;
	
	@Autowired
	public HomeControllerTest(MockMvc mockMvc, UserService userService) {
		super(mockMvc);
		this.mockUserService = userService;
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _G0101_readForm {
		
		@Test
		@DisplayName("正常_User-Noを取得したUserオブジェクトをViewが受け取り画面がformに遷移する")
		public void _G010101_readForm() throws Exception {
			long expectedNo = 5L;
			
			int mockResturnNo = (int) expectedNo;
			doReturn(mockResturnNo).when(mockUserService).getNewNo();
			
			mockMvc.perform(get("/form"))
				   .andDo(print(fileWriter))
				   .andExpect(status().isOk())
				   .andExpect(view().name("user/register/form"))
				   .andExpect(model().attribute("user", hasProperty("no", is(expectedNo))));
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _P0101_confirm {
		
		@Test
		@DisplayName("正常_ユーザ情報をViewが受け取り画面がconfirmに遷移")
		public void _P010101_confirm() throws Exception {
			User user = new User();
			user.setNo(4L);
			user.setName("test-user");
			user.setAge(10L);
			user.setBirthday("2015/01/01");
			
			// flashAttr() Postメソッドのリクエストパラメータにオブジェクトを渡す場合
			mockMvc.perform(post("/form").flashAttr("user", user))
				   .andDo(print(fileWriter))
				   .andExpect(status().isOk())
				   .andExpect(view().name("user/register/confirm"))
				   .andExpect(model().attribute("user", sameInstance(user)));
		}
		
		@Test
		@DisplayName("正常_フォーム年齢欄の入力値が0未満の場合画面がirregularに遷移")
		public void _P010102_confirm() throws Exception {
			User user = new User();
			user.setNo(4L);
			user.setName("test-user");
			user.setAge(-1L);
			user.setBirthday("2015/01/01");
			
			mockMvc.perform(post("/form").flashAttr("user", user))
				   .andDo(print(fileWriter))
				   .andExpect(status().isOk())
				   .andExpect(view().name("user/register/irregular"))
				   .andExpect(model().attribute("user", hasProperty("errMsg", is("年齢の値が不正です。"))));
		}
	}
}
