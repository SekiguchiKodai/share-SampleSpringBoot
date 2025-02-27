package com.example.demo.model;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class UserTest extends BaseModelTest {

	/** 個人番号 */
	private long initNo;
	/** 氏名 */
	private String initName;
	/** 年齢 */
	private long initAge;
	/** 誕生日 */
	private String initBirthday;
	/** 住所 */
	private UserAddress initUserAddress = new UserAddress();
	/** エラーメッセージ */
	private String initErrMsg;
	/** 比較用インスタンス */
	private User user;
	
	@BeforeEach
	public void initUser() throws ParseException {
		initNo			= 10L;
		initName		= "テストユーザ";
		initAge			= 30L;
		initBirthday	= "1995/01/01";
		initUserAddress.setPrefecture("千葉県");
		initUserAddress.setCity("千葉市");
		initUserAddress.setAddressLine(List.of("千葉0-0-0"));
		initErrMsg		= "exception message";
		
		user = new User();
		user.setNo(initNo);
		user.setName(initName);
		user.setAge(initAge);
		user.setBirthday(initBirthday);
		user.setUserAddress(initUserAddress);
		user.setErrMsg(initErrMsg);
	}
	
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0101_getter_and_setter{
		
		@Test
		@DisplayName("正常_setterでセットした値をgetterで取得できる")
		public void _010101_getter_and_setter() throws ParseException, IOException {
			long		expectedNo		 = 10L;
			String		expectedName	 = "テストユーザ";
			long		expectedAge		 = 30L;
			String		expectedBirthday = "1995/01/01";
			UserAddress expectedAddress  = initUserAddress;
			String		expectedErrMsg 	 = "exception message";
			
			User user = new User();
			user.setNo(expectedNo);
			user.setName(expectedName);
			user.setAge(expectedAge);
			user.setBirthday(expectedBirthday);
			user.setUserAddress(expectedAddress);
			user.setErrMsg(expectedErrMsg);
			
			assertThat(user.getNo()).isEqualTo(expectedNo);
			assertThat(user.getName()).isEqualTo(expectedName);
			assertThat(user.getAge()).isEqualTo(expectedAge);
			assertThat(user.getBirthday()).isEqualTo(expectedBirthday);
			assertThat(user.getUserAddress()).isEqualTo(expectedAddress);
			assertThat(user.getErrMsg()).isEqualTo(expectedErrMsg);
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0201_equals{
	
		@Test
		@DisplayName("正常_同じインスタンスと比較")
		public void _020101_equals() throws ParseException, IOException {
				assertThat(user.equals(user)).isTrue();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスと比較")
		public void _020102_equals(){
			User sameValues = new User();
			sameValues.setNo(initNo);
			sameValues.setName(initName);
			sameValues.setAge(initAge);
			sameValues.setBirthday(initBirthday);
			sameValues.setUserAddress(initUserAddress);
			sameValues.setErrMsg(initErrMsg);
			
			assertThat(user.equals(sameValues)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスと比較")
		public void _020103_equals(){
			assertThat(user.equals(new Object())).isFalse();
		}
		
		@Test
		@DisplayName("正常_noフィールドが違うインスタンスと比較")
		public void _020104_equals(){
			User notSameNo = new User();
			notSameNo.setNo(initNo + 1L);
			notSameNo.setName(initName);
			notSameNo.setAge(initAge);
			notSameNo.setBirthday(initBirthday);
			notSameNo.setUserAddress(initUserAddress);
			notSameNo.setErrMsg(initErrMsg);
			
			assertThat(user.equals(notSameNo)).isFalse();
		}
		
		@Test
		@DisplayName("正常_nameフィールドが違うインスタンスと比較")
		public void _020105_equals(){
			User notSameName = new User();
			notSameName.setNo(initNo);
			notSameName.setName(initName + "太郎");
			notSameName.setAge(initAge);
			notSameName.setBirthday(initBirthday);
			notSameName.setUserAddress(initUserAddress);
			notSameName.setErrMsg(initErrMsg);
			
			assertThat(user.equals(notSameName)).isFalse();
		}
		
		@Test
		@DisplayName("正常_ageフィールドが違うインスタンスと比較")
		public void _020106_equals(){
			User notSameAge = new User();
			notSameAge.setNo(initNo);
			notSameAge.setName(initName);
			notSameAge.setAge(initAge + 1L);
			notSameAge.setBirthday(initBirthday);
			notSameAge.setUserAddress(initUserAddress);
			notSameAge.setErrMsg(initErrMsg);
			
			assertThat(user.equals(notSameAge)).isFalse();
		}
		
		@Test
		@DisplayName("正常_birthdayフィールドが違うインスタンスと比較")
		public void _020107_equals() throws ParseException{
			User notSameBirthday = new User();
			notSameBirthday.setNo(initNo);
			notSameBirthday.setName(initName);
			notSameBirthday.setAge(initAge);
			notSameBirthday.setBirthday("1000/01/01");
			notSameBirthday.setUserAddress(initUserAddress);
			notSameBirthday.setErrMsg(initErrMsg);
			
			assertThat(user.equals(notSameBirthday)).isFalse();
		}
		
		@Test
		@DisplayName("正常_userAddressフィールドが違うインスタンスと比較")
		public void _020108_equals() throws ParseException{
			User notSameBirthday = new User();
			notSameBirthday.setNo(initNo);
			notSameBirthday.setName(initName);
			notSameBirthday.setAge(initAge);
			notSameBirthday.setBirthday(initBirthday);
			notSameBirthday.setUserAddress(new UserAddress());
			notSameBirthday.setErrMsg(initErrMsg);
			
			assertThat(user.equals(notSameBirthday)).isFalse();
		}
		
		@Test
		@DisplayName("正常_errMsgフィールドが違うインスタンスと比較")
		public void _020109_equals() throws ParseException{
			User notSameBirthday = new User();
			notSameBirthday.setNo(initNo);
			notSameBirthday.setName(initName);
			notSameBirthday.setAge(initAge);
			notSameBirthday.setBirthday(initBirthday);
			notSameBirthday.setUserAddress(initUserAddress);
			notSameBirthday.setErrMsg("different message");
			
			assertThat(user.equals(notSameBirthday)).isFalse();
		}
	}	

	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0301_canEqual{
		
		@Test
		@DisplayName("正常_同じインスタンスを比較")
		public void _030101_canEqual() {
			assertThat(user.canEqual(user)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスを比較")
		public void _030102_canEqual() {
			assertThat(user.canEqual(new Object())).isFalse();
		}
	}
	
	@Nested
	@Order(4)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0401_hashCode {
		
		private int hashValue;
		
		@BeforeEach
		public void initHashCode() {
			hashValue = user.hashCode();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスを比較")
		public void _040101_hashCode() {
			User sameValues = new User();
			sameValues.setNo(initNo);
			sameValues.setName(initName);
			sameValues.setAge(initAge);
			sameValues.setBirthday(initBirthday);
			sameValues.setUserAddress(initUserAddress);
			sameValues.setErrMsg(initErrMsg);
			
			assertThat(sameValues.hashCode()).isEqualTo(hashValue);
		}
		
		@Test
		@DisplayName("正常_別値の別インスタンスを比較")
		public void _040102_hashCode() {
			User notSameNo = new User();
			notSameNo.setNo(initNo + 1L);
			notSameNo.setName(initName);
			notSameNo.setAge(initAge);
			notSameNo.setBirthday(initBirthday);
			notSameNo.setUserAddress(initUserAddress);
			notSameNo.setErrMsg(initErrMsg);
			
			assertThat(notSameNo.hashCode()).isNotEqualTo(hashValue);
		}
		
		@Test
		@DisplayName("正常_フィールド未設定の別インスタンスを比較")
		public void _040103_hashCode() {
			User notSame = new User();
			
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
			String expected = "User(no=10, name=テストユーザ, age=30, birthday=1995/01/01, "
					+ "userAddress=UserAddress(prefecture=千葉県, city=千葉市, addressLine=[千葉0-0-0]))";
			assertThat(user.toString()).isEqualTo(expected);
		}
	}
	
}
