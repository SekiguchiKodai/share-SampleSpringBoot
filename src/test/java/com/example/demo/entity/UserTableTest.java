package com.example.demo.entity;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class UserTableTest extends BaseEntityTest {
	
	/** 番号 */
	private long initNo;
	/** 氏名 */
	private String initName;
	/** 年齢 */
	private int initAge;
	/** 誕生日 */
	private Date initBirthday;
	/** 比較用インスタンス */
	private UserTable ut;
	
	@BeforeEach
	public void initUserTable() throws ParseException {
		initNo		 = 10L;
		initName	 = "テストユーザ";
		initAge		 = 30;
		initBirthday = new Date(new SimpleDateFormat("yyyy/MM/dd").parse("1995/01/01").getTime());
		
		ut = new UserTable();
		ut.setNo(initNo);
		ut.setName(initName);
		ut.setAge(initAge);
		ut.setBirthday(initBirthday);
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0101_getter_and_setter{
		
		@Test
		@DisplayName("正常_setterでセットした値をgetterで取得できる")
		public void _010101_getter_and_setter() throws ParseException, IOException {
			long	expectedNo		 = 10L;
			String	expectedName	 = "テストユーザ";
			int	expectedAge		 = 30;
			Date	expectedBirthday = new Date(new SimpleDateFormat("yyyy/MM/dd").parse("1995/01/01").getTime());
			
			UserTable ut = new UserTable();
			ut.setNo(expectedNo);
			ut.setName(expectedName);
			ut.setAge(expectedAge);
			ut.setBirthday(expectedBirthday);
			
			assertThat(ut.getNo()).isEqualTo(expectedNo);
			assertThat(ut.getName()).isEqualTo(expectedName);
			assertThat(ut.getAge()).isEqualTo(expectedAge);
			assertThat(ut.getBirthday()).isEqualTo(expectedBirthday);
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0201_equals{
	
		@Test
		@DisplayName("正常_同じインスタンスと比較")
		public void _020101_equals() throws ParseException, IOException {
				assertThat(ut.equals(ut)).isTrue();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスと比較")
		public void _020102_equals(){
			UserTable sameValues = new UserTable();
			sameValues.setNo(initNo);
			sameValues.setName(initName);
			sameValues.setAge(initAge);
			sameValues.setBirthday(initBirthday);
			
			assertThat(ut.equals(sameValues)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスと比較")
		public void _020103_equals(){
			assertThat(ut.equals(new Object())).isFalse();
		}
		
		@Test
		@DisplayName("正常_noフィールドが違うインスタンスと比較")
		public void _020104_equals(){
			UserTable notSameNo = new UserTable();
			notSameNo.setNo(initNo + 1L);
			notSameNo.setName(initName);
			notSameNo.setAge(initAge);
			notSameNo.setBirthday(initBirthday);
			
			assertThat(ut.equals(notSameNo)).isFalse();
		}
		
		@Test
		@DisplayName("正常_nameフィールドが違うインスタンスと比較")
		public void _020105_equals(){
			UserTable notSameName = new UserTable();
			notSameName.setNo(initNo);
			notSameName.setName(initName + "太郎");
			notSameName.setAge(initAge);
			notSameName.setBirthday(initBirthday);
			
			assertThat(ut.equals(notSameName)).isFalse();
		}
		
		@Test
		@DisplayName("正常_ageフィールドが違うインスタンスと比較")
		public void _020106_equals(){
			UserTable notSameAge = new UserTable();
			notSameAge.setNo(initNo);
			notSameAge.setName(initName);
			notSameAge.setAge(initAge + 1);
			notSameAge.setBirthday(initBirthday);
			
			assertThat(ut.equals(notSameAge)).isFalse();
		}
		
		@Test
		@DisplayName("正常_birthdayフィールドが違うインスタンスと比較")
		public void _020107_equals() throws ParseException{
			UserTable notSameBirthday = new UserTable();
			notSameBirthday.setNo(initNo);
			notSameBirthday.setName(initName);
			notSameBirthday.setAge(initAge);
			notSameBirthday.setBirthday(new Date(new SimpleDateFormat("yyyy/MM/dd").parse("1000/01/01").getTime()));
			
			assertThat(ut.equals(notSameBirthday)).isFalse();
			
		}
	}	

	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0301_canEqual{
		
		@Test
		@DisplayName("正常_同じインスタンスを比較")
		public void _030101_canEqual() {
			assertThat(ut.canEqual(ut)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスを比較")
		public void _030102_canEqual() {
			assertThat(ut.canEqual(new Object())).isFalse();
		}
	}
	
	@Nested
	@Order(4)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0401_hashCode {
		
		private int hashValue;
		
		@BeforeEach
		public void initHashCode() {
			hashValue = ut.hashCode();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスを比較")
		public void _040101_hashCode() {
			UserTable sameValues = new UserTable();
			sameValues.setNo(initNo);
			sameValues.setName(initName);
			sameValues.setAge(initAge);
			sameValues.setBirthday(initBirthday);
			
			assertThat(sameValues.hashCode()).isEqualTo(hashValue);
		}
		
		@Test
		@DisplayName("正常_別値の別インスタンスを比較")
		public void _040102_hashCode() {
			UserTable notSameNo = new UserTable();
			notSameNo.setNo(initNo + 1L);
			notSameNo.setName(initName);
			notSameNo.setAge(initAge);
			notSameNo.setBirthday(initBirthday);
			
			assertThat(notSameNo.hashCode()).isNotEqualTo(hashValue);
		}
		
		@Test
		@DisplayName("正常_フィールド未設定の別インスタンスを比較")
		public void _040103_hashCode() {
			UserTable notSame = new UserTable();
			
			assertThat(notSame.hashCode()).isNotEqualTo(hashValue);
		}
	}
	
	@Nested
	@Order(5)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0401_toString{
		
		@Test
		@DisplayName("正常_toStringの出力値を比較")
		public void _040101_toString() {
			String expected = "UserTable(no=10, name=テストユーザ, age=30, birthday=1995-01-01)";
			assertThat(ut.toString()).isEqualTo(expected);
		}
	}
	
}
