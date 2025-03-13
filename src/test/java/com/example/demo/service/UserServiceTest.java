package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
import org.springframework.dao.QueryTimeoutException;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.exception.DatabaseRegisterUserException;
import com.example.demo.entity.UserTable;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.test.dbunit.CsvDataSetLoader;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

public class UserServiceTest extends BaseServiceTest {
	
	private UserService userService;
	@MockitoSpyBean
	private UserRepository spyUserRepository;
	
	@Autowired
	public UserServiceTest(UserService userService ,UserRepository userRepository) {
		this.userService = userService;
		this.spyUserRepository = userRepository;
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0101_getNewNo {
		
		@Test
		@DisplayName("正常_ユーザNoの最大値+1を取得")
		public void _010101_getNewNo() throws IOException {
			long expectedNo = 4L;
			long actualNo   = -1L;
			
			// Mock UserRepository.findMaxNoの戻り値を設定
			long returnNo = 3L;
			doReturn(returnNo).when(spyUserRepository).findMaxNo();
			
			try {
				// テスト対象のメソッド実行
				actualNo = userService.getNewNo();
				
				// Repositoryのメソッド呼び出し回数を確認
				verify(spyUserRepository, times(1)).findMaxNo();
				// 戻り値と期待値を比較
				assertThat(actualNo).isEqualTo(expectedNo);
				
			} finally {
				String textBlock = """
						expected no:%d
						actual   no:%d
						"""
						.formatted(expectedNo, actualNo);
				fileWriter.write(textBlock);
			}
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
	class _0201_register {
		
		@Test
		@DisplayName("正常_ユーザ登録を行った後ユーザ番号をもとに登録したユーザ情報を取得する")
		public void _020101_register() throws IOException, ParseException {
			// Mock 戻り値のUserTableオブジェクトを作成
			long userNo = 10L;
			UserTable expectedUT = new UserTable();
			expectedUT.setNo(userNo);
			expectedUT.setName("test10");
			expectedUT.setAge(10);
			expectedUT.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01").getTime()));
			
			// Mock UserRepository.insert と findByNoの戻り値を設定
			doReturn(1).when(spyUserRepository).insert(expectedUT);
			doReturn(expectedUT).when(spyUserRepository).findByNo(userNo);
			
			User newUser = new User();
			newUser.setNo(userNo);
			newUser.setName("test10");
			newUser.setAge(10);
			newUser.setBirthday("2015/01/01");
			
			UserTable actualUT = null;
			try {
				// テスト対象のメソッド実行
				actualUT = userService.register(newUser);
				
				// Repositoryのメソッド呼び出し回数を確認
				verify(spyUserRepository, times(1)).findByNo(userNo);
				verify(spyUserRepository, times(1)).insert(expectedUT);
				
				// 戻り値と期待値を比較
				assertThat(actualUT).isEqualTo(expectedUT);
				
			} finally {
				String textBlock = """
						expected UserTable:%s
						actual   UserTable:%s
						"""
						.formatted(expectedUT, actualUT);
				fileWriter.write(textBlock);
			}
		}
		

	}
	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
	class _0202_register{
		
		@Test
		@DisplayName("異常_ユーザ情報取得時にDataAccessExceptionが発生")
		public void _020201_register() throws IOException {
			String expectedMsg = "ユーザ登録時に不具合が発生";
			
			User newUser = new User();
			newUser.setNo(10L);
			newUser.setName("test10");
			newUser.setAge(10);
			newUser.setBirthday("2015/01/01");
			
			// Mock UserRepository.findByNo 実行時にDataAccessExceptionを発生
			doReturn(1).when(spyUserRepository).insert(any(UserTable.class));
			doThrow(new QueryTimeoutException("message")).when(spyUserRepository).findByNo(anyLong());
			
			Throwable e = null;
			try {
				e = assertThrows(DatabaseRegisterUserException.class, () -> userService.register(newUser));
				// 発生源の例外をラップしてthrows出来ている事を確認
				assertThat(e.getCause()).isInstanceOf(QueryTimeoutException.class);
				assertThat(e.getMessage()).isEqualTo(expectedMsg);
				
			} finally {
				String textBlock = """
						expected Cause Exception Class:%s
						actual   Cause Exception Class:%s
						expected Exception Message    :%s
						actual   Exception Message    :%s
						"""
						.formatted(
								QueryTimeoutException.class.getSimpleName(),
								e.getCause().getClass().getSimpleName(),
								expectedMsg,
								e.getMessage()
								);
				fileWriter.write(textBlock);
			}
			
		}
		
		@Test
		@DisplayName("異常_ユーザ情報取得時にDataAccessException以外の例外が発生")
		public void _020202_register() throws IOException {
			String expectedMsg = "message";
			
			User newUser = new User();
			newUser.setNo(10L);
			newUser.setName("test10");
			newUser.setAge(10);
			newUser.setBirthday("2015/01/01");
			
			// Mock UserRepository.findByNo 実行時にDataAccessExceptionを発生
			doReturn(1).when(spyUserRepository).insert(any(UserTable.class));
			doThrow(new IllegalArgumentException(expectedMsg)).when(spyUserRepository).findByNo(anyLong());
			
			Throwable e = null;
			try {
				e = assertThrows(IllegalArgumentException.class, () -> userService.register(newUser));
				// 発生した例外をthrows出来ている事を確認
				assertThat(e.getMessage()).isEqualTo(expectedMsg);
				
			} finally {
				String textBlock = """
						expected Cause Exception Class:%s
						actual   Cause Exception Class:%s
						expected Exception Message    :%s
						actual   Exception Message    :%s
						"""
						.formatted(
								IllegalArgumentException.class.getSimpleName(),
								e.getClass().getSimpleName(),
								expectedMsg,
								e.getMessage()
								);
				fileWriter.write(textBlock);
			}
			
		}
		
		@Test
		@Transactional(propagation = Propagation.NOT_SUPPORTED)
		@DisplayName("異常_ユーザ情報取得中にExceptionが発生した時ロールバックを実行")
		public void _020203_register() throws IOException {
			doCallRealMethod().when(spyUserRepository).findMaxNo();
			
			long expectedNo = spyUserRepository.findMaxNo();
			
			User newUser = new User();
			newUser.setNo(10L);
			newUser.setName("test10");
			newUser.setAge(10);
			newUser.setBirthday("2015/01/01");
			
			doCallRealMethod().when(spyUserRepository).insert(any(UserTable.class));
			// Mock UserRepository.findByNo 実行時にExceptionを発生
			doThrow(new QueryTimeoutException("message")).when(spyUserRepository).findByNo(anyLong());
			
			long actualNo = -1;
			try {
				// テスト対象メソッド実行
				assertThrows(DatabaseRegisterUserException.class, () -> userService.register(newUser));
				// ロールバックの確認 (registerメソッド実行前と後でユーザ数が変わらない事)
				actualNo = spyUserRepository.findMaxNo();
				assertThat(actualNo).isEqualTo(expectedNo);
				
			} finally {
				String textBlock = """
						expected Number of User:%d
						actual   Number of User:%d
						"""
						.formatted(expectedNo, actualNo);
				fileWriter.write(textBlock);
			}
		}
	}
}
