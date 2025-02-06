package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.*;
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
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.SampleSpringBootApplication;
import com.example.demo.dbunit.CsvDataSetLoader;
import com.example.demo.model.User;
import com.example.demo.model.UserAddress;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(classes = SampleSpringBootApplication.class)
public class HomeControllerTest {

	private final MockMvc mockMvc;
	@Autowired
	public HomeControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
	
	@Test
	public void formGet処理_画面がformに遷移する() throws Exception {
		
		// andDo(print())でリクエスト・レスポンスを表示
		this.mockMvc.perform(get("/form"))
			.andExpect(status().isOk())
			.andExpect(view().name("user/register/form"));
	}
	
	@Test
	public void formGet処理_ViewにUserオブジェクトが渡される() throws Exception {
		this.mockMvc.perform(get("/form"))
			.andExpect(model().attribute("user", instanceOf(User.class)));
	}
	
	@Test
	public void formGet処理_ViewにmaxUserNumberが渡される() throws Exception {
		this.mockMvc.perform(get("/form"))
			.andExpect(model().attribute("user", hasProperty("no")));
	}
	
	@Test
	@DatabaseSetup("/com/example/demo/controller/homecontroller/setup/")
	public void formPost処理_正常_画面がconfirmに遷移する() throws Exception {
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
		
		// Postメソッドのリクエストパラメータにオブジェクトを渡す場合
		this.mockMvc.perform(post("/form").flashAttr("user", user))
			.andExpect(status().isOk())
			.andExpect(view().name("user/register/confirm"));
	}
	
	@Test
	@DatabaseSetup("/com/example/demo/controller/homecontroller/setup/")
	public void formPost処理_年齢欄に0未満の入力_画面がirregularに遷移する() throws Exception {
		User user = new User();
		user.setNo(4);
		user.setName("test-user");
		user.setAge(-1);
		user.setBirthday("2015/01/01");
		
		this.mockMvc.perform(post("/form").flashAttr("user", user))
			.andExpect(status().isOk())
			.andExpect(view().name("user/register/irregular"));;
	}
}
