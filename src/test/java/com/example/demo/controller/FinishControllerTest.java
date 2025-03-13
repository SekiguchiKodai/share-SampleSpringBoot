package com.example.demo.controller;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.FlashMap;

import com.example.demo.entity.UserTable;
import com.example.demo.model.User;
import com.example.demo.model.UserAddress;
import com.example.demo.service.UserService;
import com.github.springtestdbunit.annotation.DatabaseSetup;

//@TestExecutionListeners({
//	DependencyInjectionTestExecutionListener.class,
//	TransactionalTestExecutionListener.class,
//	DbUnitTestExecutionListener.class
//})
//@Transactional
public class FinishControllerTest extends BaseControllerTest {
	
	private long initNo;
	private int initAge;
	private String initName, initBirthday;
	private User user;
	
	@MockitoBean
	private UserService mockUserService;
	
	@Autowired
	public FinishControllerTest(MockMvc mockMvc, UserService userService) {
		super(mockMvc);
		this.mockUserService = userService;
	}

	@BeforeEach
	public void initUser() {
		initNo = 5L;
		initName = "test5";
		initAge = 50;
		initBirthday = "1975/01/01";
		
		user = new User();
		user.setNo(initNo);
		user.setName(initName);
		user.setAge(initAge);
		user.setBirthday(initBirthday);
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
//	@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
	class _P0101_commit {
		
		@Test
		@DisplayName("正常_登録ユーザの情報をviewが受け取り画面がfinishに遷移する")
//		@DatabaseSetup(value = "/com/example/demo/controller/finishcontroller/db/setup/")
//		@ExpectedDatabase(value = "/com/example/demo/controller/finishcontroller/db/commit/education.USER_TABLE.csv", assertionMode = DatabaseAssertionMode.NON_STRICT)
		public void _P010101_commit() throws Exception {
			
			UserTable ut = new UserTable();
			ut.setNo(initNo);
			ut.setName(initName);
			ut.setAge(initAge);
			ut.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse(initBirthday).getTime()));
			
			doReturn(ut).when(mockUserService).register(user);
			
			mockMvc.perform(post("/commit").param("commit", "commit").flashAttr("user", user))
				   .andDo(print(fileWriter))
				   .andExpect(status().isOk())
				   .andExpect(view().name("user/register/finish"))
				   .andExpect(model().attribute("userTable", hasProperty("name", is(initName))));
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.MethodName.class)
//	@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
	class _P0201_back {
		
		@Test
		@DisplayName("正常_form画面にリダイレクトしUserオブジェクトをViewに渡す")
		@DatabaseSetup(value = "/com/example/demo/controller/finishcontroller/db/setup/")
		public void _P020101_back() throws Exception {
			
			UserAddress ua = new UserAddress();
			ua.setPrefecture("北海道");
			ua.setCity("函館市");
			ua.setAddressLine(Arrays.asList("33","4","6"));
			user.setUserAddress(ua);
			
			MvcResult mvcResult = mockMvc.perform(post("/commit").param("back", "back").flashAttr("user", user))
										 .andDo(print(fileWriter))
										 .andExpect(status().isFound())			// ステータス302が返ることを確認
										 .andExpect(redirectedUrl("/form"))	// リダイレクト先のURL
										 .andReturn();							// リクエストの結果を取得
			
			FlashMap flashMap = mvcResult.getFlashMap(); // 引き継ぎパラメータを取得
			User actual = (User) flashMap.get("user");
			
			assertThat(actual.getName()).isEqualTo(initName);
		}
	}
	

	
}
