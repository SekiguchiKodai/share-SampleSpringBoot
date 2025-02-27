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

public class UserAddressTest extends BaseModelTest {

	/** 都道府県 */
	private String initPrefecture;
	/** 市区町村 */
	private String initCity;
	/** 市区町村以下 */
	private List<String> initAddressLine;
	/** 比較用インスタンス */
	private UserAddress userAddress; 
	
	@BeforeEach
	public void initUserAddress() {
		initPrefecture = "北海道";
		initCity = "札幌市";
		initAddressLine = List.of("札幌0-0-0");
		
		userAddress = new UserAddress();
		userAddress.setPrefecture(initPrefecture);
		userAddress.setCity(initCity);
		userAddress.setAddressLine(initAddressLine);
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0101_getter_and_setter{
		
		@Test
		@DisplayName("正常_setterでセットした値をgetterで取得できる")
		public void _010101_getter_and_setter() throws ParseException, IOException {
			String expectedPrefecture		 = "千葉県";
			String expectedCity				 = "千葉市";
			List<String> expectedAddressLine = List.of("千葉0-0-0");
			
			UserAddress userAddress = new UserAddress();
			userAddress.setPrefecture(expectedPrefecture);
			userAddress.setCity(expectedCity);
			userAddress.setAddressLine(expectedAddressLine);
			
			assertThat(userAddress.getPrefecture()).isEqualTo(expectedPrefecture);
			assertThat(userAddress.getCity()).isEqualTo(expectedCity);
			assertThat(userAddress.getAddressLine()).isEqualTo(expectedAddressLine);
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0201_equals{
	
		@Test
		@DisplayName("正常_同じインスタンスと比較")
		public void _020101_equals() throws ParseException, IOException {
				assertThat(userAddress.equals(userAddress)).isTrue();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスと比較")
		public void _020102_equals(){
			UserAddress sameValues = new UserAddress();
			sameValues.setPrefecture(initPrefecture);
			sameValues.setCity(initCity);
			sameValues.setAddressLine(initAddressLine);
			
			assertThat(userAddress.equals(sameValues)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスと比較")
		public void _020103_equals(){
			assertThat(userAddress.equals(new Object())).isFalse();
		}
		
		@Test
		@DisplayName("正常_prefectureフィールドが違うインスタンスと比較")
		public void _020104_equals(){
			UserAddress notSameNo = new UserAddress();
			notSameNo.setPrefecture(initPrefecture + "県");
			notSameNo.setCity(initCity);
			notSameNo.setAddressLine(initAddressLine);
			
			assertThat(userAddress.equals(notSameNo)).isFalse();
		}
		
		@Test
		@DisplayName("正常_cityフィールドが違うインスタンスと比較")
		public void _020105_equals(){
			UserAddress notSameName = new UserAddress();
			notSameName.setPrefecture(initPrefecture);
			notSameName.setCity(initCity + "市");
			notSameName.setAddressLine(initAddressLine);
			
			assertThat(userAddress.equals(notSameName)).isFalse();
		}
		
		@Test
		@DisplayName("正常_addressLineフィールドが違うインスタンスと比較")
		public void _020106_equals(){
			UserAddress notSameAge = new UserAddress();
			notSameAge.setPrefecture(initPrefecture);
			notSameAge.setCity(initCity);
			notSameAge.setAddressLine(List.of("hoge"));
			
			assertThat(userAddress.equals(notSameAge)).isFalse();
		}
	}	

	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0301_canEqual{
		
		@Test
		@DisplayName("正常_同じインスタンスを比較")
		public void _030101_canEqual() {
			assertThat(userAddress.canEqual(userAddress)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスを比較")
		public void _030102_canEqual() {
			assertThat(userAddress.canEqual(new Object())).isFalse();
		}
	}
	
	@Nested
	@Order(4)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0401_hashCode {
		
		private int hashValue;
		
		@BeforeEach
		public void initHashCode() {
			hashValue = userAddress.hashCode();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスを比較")
		public void _040101_hashCode() {
			UserAddress sameValues = new UserAddress();
			sameValues.setPrefecture(initPrefecture);
			sameValues.setCity(initCity);
			sameValues.setAddressLine(initAddressLine);
			
			assertThat(sameValues.hashCode()).isEqualTo(hashValue);
		}
		
		@Test
		@DisplayName("正常_別値の別インスタンスを比較")
		public void _040102_hashCode() {
			UserAddress notSameNo = new UserAddress();
			notSameNo.setPrefecture(initPrefecture + "県");
			notSameNo.setCity(initCity);
			notSameNo.setAddressLine(initAddressLine);
			
			assertThat(notSameNo.hashCode()).isNotEqualTo(hashValue);
		}
		
		@Test
		@DisplayName("正常_フィールド未設定の別インスタンスを比較")
		public void _040103_hashCode() {
			UserAddress notSame = new UserAddress();
			
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
			String expected = "UserAddress(prefecture=北海道, city=札幌市, addressLine=[札幌0-0-0])";
			assertThat(userAddress.toString()).isEqualTo(expected);
		}
	}
}
