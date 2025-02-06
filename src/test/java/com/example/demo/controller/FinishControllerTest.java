package com.example.demo.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.FlashMap;

import com.example.demo.SampleSpringBootApplication;
import com.example.demo.dbunit.CsvDataSetLoader;
import com.example.demo.model.User;
import com.example.demo.model.UserAddress;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
@AutoConfigureMockMvc
@SpringBootTest(classes = SampleSpringBootApplication.class)
@Transactional
public class FinishControllerTest {
	
	private final MockMvc mockMvc;
	@Autowired
	public FinishControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	@DatabaseSetup(value = "/com/example/demo/controller/finishcontroller/setup/")
	@ExpectedDatabase(value = "/com/example/demo/controller/finishcontroller/commit/", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void commitPost_確定_ユーザ情報がDBに登録されfinish画面に遷移する() throws Exception {
		User user = new User();
		user.setNo(4);
		user.setName("test4");
		user.setAge(40);
		user.setBirthday("1985/01/01");
		
		this.mockMvc.perform(post("/commit").param("commit", "commit").flashAttr("user", user))
			.andExpect(status().isOk())
			.andExpect(view().name("user/register/finish"));
	}
	
	@Test
	@DatabaseSetup(value = "/com/example/demo/controller/finishcontroller/setup/")
	public void commitPost_入力画面に戻る_form画面にリダイレクトで遷移してUserオブジェクトをViewに渡す() throws Exception {
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
		
		MvcResult mvcResult = this.mockMvc.perform(post("/commit").param("back", "back").flashAttr("user", user))
								  .andExpect(status().isFound())		// ステータス302が返ることを確認
								  .andExpect(redirectedUrl("/form"))	// リダイレクト先のURL
								  .andReturn();							// リクエストの結果を取得
		
		FlashMap flashMap = mvcResult.getFlashMap(); // 引き継ぎパラメータを取得
		User actual = (User) flashMap.get("user");
		
		assertThat(actual.getName()).isEqualTo("test4");
			
	}
	
}
