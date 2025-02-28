package com.example.demo.mapper;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.UserTable;
import com.example.demo.test.dbunit.CsvDataSetLoader;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class UserMapperTest extends BaseMapperTest {
	
	private final UserMapper userMapper;
	
	@Autowired
	public UserMapperTest(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
	class _R0101_findMaxNo {
		
		@Test
		@DisplayName("正常_ユーザNoの最大値を取得")
		@DatabaseSetup(value = "/com/example/demo/mapper/usermapper/db/setup/")
		public void _R010101_findMaxNo() throws IOException {
			int expected = 3;
			int actual = 0;
			try {
				actual = userMapper.findMaxNo();
				assertThat(actual).isEqualTo(expected);
				
			} finally  {
				String resultText = """
						UserテーブルにUser.Noが"3"のユーザを登録し、テストを実行
						expected User.no:%s
						actual   User.no:%s
						"""
						.formatted(expected, actual);
				fileWriter.write(resultText);
			}
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
	class _R0201_findByNo {
		
		@Test
		@DisplayName("正常_ユーザー情報を取得")
		@DatabaseSetup(value = "/com/example/demo/mapper/usermapper/db/setup/")
		public void _R020101_findByNo() throws ParseException, IOException {
			UserTable expected = new UserTable();
			expected.setNo(1);
			expected.setName("test1");
			expected.setAge(10);
			expected.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01").getTime()));
			
			UserTable actual = null;
			
			try {
				actual = userMapper.findByNo(1);
				assertThat(actual).isNotNull();
				assertThat(actual.getNo()).isEqualTo(expected.getNo());
				assertThat(actual.getName()).isEqualTo(expected.getName());
				assertThat(actual.getAge()).isEqualTo(expected.getAge());
				assertThat(actual.getBirthday()).isEqualTo(expected.getBirthday());
				
			} finally {
				String resultText = """
						expected UserTable:%s
						actual   UserTable:%s
						"""
						.formatted(expected, actual);
				fileWriter.write(resultText);
			}
		}
	}
	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
	class _C0301_insert {
		
		@Test
		@DisplayName("正常_ユーザー情報を登録")
		@DatabaseSetup(value = "/com/example/demo/mapper/usermapper/db/setup/")
		@ExpectedDatabase(value = "/com/example/demo/mapper/usermapper/db/insert/", assertionMode = DatabaseAssertionMode.NON_STRICT)
		public void _C030101_insert() throws ParseException {
			UserTable expected = new UserTable();
			expected.setNo(4L);
			expected.setName("test4");
			expected.setAge(40L);
			expected.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse("1985/01/01").getTime()));
			
			userMapper.insert(expected);
		}
	}
}
