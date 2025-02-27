package com.example.demo.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.FlashMap;

import com.example.demo.model.User;
import com.example.demo.model.UserAddress;
import com.example.demo.test.dbunit.CsvDataSetLoader;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
@Transactional
public class FinishControllerTest extends BaseControllerTest {
	
	@Autowired
	public FinishControllerTest(MockMvc mockMvc) {
		super(mockMvc);
	}

	@Test
	@DisplayName("P010101_POST_commit-commit_正常_ユーザ情報がDBに登録されて画面がfinishに遷移")
	@DatabaseSetup(value = "/com/example/demo/controller/finishcontroller/db/setup/")
	@ExpectedDatabase(value = "/com/example/demo/controller/finishcontroller/db/commit/education.USER_TABLE.csv", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void POST_commit_正常_ユーザ情報がDBに登録されて画面がfinishに遷移() throws Exception {
		User user = new User();
		user.setNo(4);
		user.setName("test4");
		user.setAge(40);
		user.setBirthday("1985/01/01");
		
		mockMvc.perform(post("/commit").param("commit", "commit").flashAttr("user", user))
			   .andDo(print(fileWriter))
			   .andExpect(status().isOk())
			   .andExpect(view().name("user/register/finish"));
	}
	
	@Test
	@DisplayName("P020101_POST_commit-back_正常_form画面にリダイレクトしUserオブジェクトをViewに渡す")
	@DatabaseSetup(value = "/com/example/demo/controller/finishcontroller/db/setup/")
	public void POST_commit_正常_form画面にリダイレクトしUserオブジェクトをViewに渡す() throws Exception {
		User user = new User();
		user.setNo(4);
		user.setName("test4");
		user.setAge(40);
		user.setBirthday("1985/01/01");
		
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
		
		assertThat(actual.getName()).isEqualTo("test4");
			
	}
	
}
