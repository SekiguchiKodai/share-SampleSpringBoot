package com.example.demo.t.mapper;

import static org.assertj.core.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.SampleSpringBootApplication;
import com.example.demo.t.dbunit.CsvDataSetLoader;
import com.example.demo.t.entity.UserTable;
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
@Transactional	//Test実施後、ロールバックしてDBの汚染を防ぐ
// SpringBootTest.WebEnvironment.NONE Webサーバを起動せずテストを実施する
@SpringBootTest(classes = {SampleSpringBootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserMapperTest {
	
	private final UserMapper userMapper;
	
	@Autowired
	public UserMapperTest(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Test
	@DatabaseSetup(value = "/usermapper/setup/")
	public void findMaxNoメソッドで最大Noを取得() {
		int maxUserNo = userMapper.findMaxNo();
		assertThat(maxUserNo).isEqualTo(3);
	}
	
	@Test
	@DatabaseSetup(value = "/usermapper/setup/")
	public void findByNoメソッドでユーザー情報を取得() throws ParseException {
		UserTable expected = new UserTable();
		expected.setNo(1L);
		expected.setName("test1");
		expected.setAge(10L);
		expected.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01").getTime()));
		
		UserTable actual = userMapper.findByNo(1L);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getNo()).isEqualTo(expected.getNo());
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getAge()).isEqualTo(expected.getAge());
		assertThat(actual.getBirthday()).isEqualTo(expected.getBirthday());
	}
	
	@Test
	@DatabaseSetup(value = "/usermapper/setup/")
	@ExpectedDatabase(value = "/usermapper/insert/", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void insertメソッドを実行() throws ParseException {
		UserTable expected = new UserTable();
		expected.setNo(4L);
		expected.setName("test4");
		expected.setAge(40L);
		expected.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse("1985/01/01").getTime()));
		
		userMapper.insert(expected);
	}
	
	
}
