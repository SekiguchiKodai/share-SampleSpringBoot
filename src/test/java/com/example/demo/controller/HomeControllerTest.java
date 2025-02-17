package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
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
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.model.UserAddress;
import com.example.demo.test.dbunit.CsvDataSetLoader;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
@Transactional
public class HomeControllerTest extends AbstractControllerTest {

	@Autowired
	public HomeControllerTest(MockMvc mockMvc) {
		super(mockMvc);
	}
	
	@Test
	@DisplayName("G010101_GET_form_正常_画面がformに遷移")
	public void GET_form_正常_画面がformに遷移() throws Exception {
		mockMvc.perform(get("/form"))
			   .andDo(print(fileWriter))
			   .andExpect(status().isOk())
			   .andExpect(view().name("user/register/form"));
	}
	
	@Test
	@DisplayName("G010102_GET_form_正常_UserオブジェクトをViewに渡す")
	public void GET_form_正常_UserオブジェクトをViewに渡す() throws Exception {
		mockMvc.perform(get("/form"))
		 	   .andDo(print(fileWriter))
			   .andExpect(model().attribute("user", instanceOf(User.class)));
	}
	
	@Test
	@DisplayName("G010103_GET_form_正常_採番したUser-NoをViewに渡す")
	@DatabaseSetup("/com/example/demo/controller/homecontroller/db/setup/")
	public void GET_form_正常_採番したmaxUserNumberをViewに渡す() throws Exception {
		long expectedNo = 4L;
		
		mockMvc.perform(get("/form"))
			   .andDo(print(fileWriter))
			   .andExpect(model().attribute("user", hasProperty("no", is(expectedNo))));
	}
	
	@Test
	@DisplayName("P010101_POST_form_正常_画面がconfirmに遷移")
	@DatabaseSetup("/com/example/demo/controller/homecontroller/db/setup/")
	public void POST_form_正常_画面がconfirmに遷移() throws Exception {
		User user = new User();
		user.setNo(4);
		user.setName("test-user");
		user.setAge(10);
		user.setBirthday("2015/01/01"); 
		
		UserAddress ua = new UserAddress();
		ua.setPrefecture("北海道");
		ua.setCity("函館市");
		ua.setAddressLine(Arrays.asList("33","4","6"));
		user.setUserAddress(ua);
		
		// flashAttr() Postメソッドのリクエストパラメータにオブジェクトを渡す場合
		mockMvc.perform(post("/form").flashAttr("user", user))
			   .andDo(print(fileWriter))
			   .andExpect(status().isOk())
			   .andExpect(view().name("user/register/confirm"));
	}
	
	@Test
	@DisplayName("P010102_POST_form_正常_フォーム年齢欄の入力値が0未満の場合画面がirregularに遷移")
	@DatabaseSetup("/com/example/demo/controller/homecontroller/db/setup/")
	public void POST_form_正常_フォーム年齢欄の入力値が0未満の場合画面がirregularに遷移() throws Exception {
		User user = new User();
		user.setNo(4);
		user.setName("test-user");
		user.setAge(-1);
		user.setBirthday("2015/01/01");
		
		mockMvc.perform(post("/form").flashAttr("user", user))
			   .andDo(print(fileWriter))
			   .andExpect(status().isOk())
			   .andExpect(view().name("user/register/irregular"));;
	}
}
