package com.example.demo.repository;

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

public class UserRepositoryTest extends BaseRepositoryTest {

	private final ImpUserRepository userRepository;
	
	@Autowired
	public UserRepositoryTest(ImpUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
	public class _R0101_findMaxNo {
		
		@Test
		@DisplayName("正常_テーブル内でユーザNoの最大値を取得")
		@DatabaseSetup(value = "/com/example/demo/repository/userrepository/db/setup/")
		public void _R010101_findMaxNo() throws IOException {
			long expectedNo = 3L;
			long actualNo   = -1L;
			
			try {
				actualNo = userRepository.findMaxNo();
				assertThat(actualNo).isEqualTo(expectedNo);	
				
			} finally {
				String resultText = """
						UserテーブルにUser.No"1","2","3"のユーザを登録し、UserRepository.findMaxNoを実行
						expected User.no:%s
						actual   User.no:%s
						"""
						.formatted(expectedNo, actualNo);
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
		@DisplayName("正常_指定したユーザNoに一致するユーザ情報を取得")
		@DatabaseSetup(value = "/com/example/demo/repository/userrepository/db/setup/")
		public void _R020101_findByNo() throws ParseException, IOException {
			UserTable expected = new UserTable();
			expected.setNo(1L);
			expected.setName("test1");
			expected.setAge(10);
			expected.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01").getTime()));
			
			UserTable actual = null;
			
			try {
				actual = userRepository.findByNo(1L);
				
				assertThat(actual.getNo()).isEqualTo(expected.getNo());
				assertThat(actual.getName()).isEqualTo(expected.getName());
				assertThat(actual.getAge()).isEqualTo(expected.getAge());
				assertThat(actual.getBirthday()).isEqualTo(expected.getBirthday());

			} finally {
				String resultText = """
						expected UserTable:%s
						actual   UsetTable:%s
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
	class _C0101_insert {
		
		@Test
		@DisplayName("正常_ユーザー情報を登録")
		@DatabaseSetup(value = "/com/example/demo/repository/userrepository/db/setup/")
		@ExpectedDatabase(value = "/com/example/demo/repository/userrepository/db/insert/", assertionMode = DatabaseAssertionMode.NON_STRICT)
		public void _C010101_insert() throws ParseException {
			UserTable newUser = new UserTable();
			newUser.setNo(4L);
			newUser.setName("test4");
			newUser.setAge(40);
			newUser.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse("1985/01/01").getTime()));
			
			userRepository.insert(newUser);
		}
	}
	
}
