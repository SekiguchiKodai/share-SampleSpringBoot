package com.example.demo.model;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class ViewCommonDataTest extends BaseModelTest {

	private String initErrMsg;
	
	private ViewCommonData viewCommonData;
	
	@BeforeEach
	public void initViewCommonData() {
		initErrMsg = "exception message";
		
		viewCommonData = new ViewCommonData();
		viewCommonData.setErrMsg(initErrMsg);
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0101_getter_and_setter{
		
		@Test
		@DisplayName("正常_setterでセットした値をgetterで取得できる")
		public void _010101_getter_and_setter() throws ParseException, IOException {
			String expectedErrMsg = "exception message";
			
			viewCommonData = new ViewCommonData();
			viewCommonData.setErrMsg(expectedErrMsg);;
			
			assertThat(viewCommonData.getErrMsg()).isEqualTo(expectedErrMsg);
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0201_equals{
	
		@Test
		@DisplayName("正常_同じインスタンスと比較")
		public void _020101_equals() throws ParseException, IOException {
				assertThat(viewCommonData.equals(viewCommonData)).isTrue();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスと比較")
		public void _020102_equals(){
			ViewCommonData sameValues = new ViewCommonData();
			sameValues.setErrMsg(initErrMsg);
			
			assertThat(viewCommonData.equals(sameValues)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスと比較")
		public void _020103_equals(){
			assertThat(viewCommonData.equals(new Object())).isFalse();
		}
		
		@Test
		@DisplayName("正常_errMsgフィールドが違うインスタンスと比較")
		public void _020104_equals(){
			ViewCommonData notSameErrMsg = new ViewCommonData();
			notSameErrMsg.setErrMsg(initErrMsg + "hoge");
			
			assertThat(viewCommonData.equals(notSameErrMsg)).isFalse();
		}
	}	

	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0301_canEqual{
		
		@Test
		@DisplayName("正常_同じインスタンスを比較")
		public void _030101_canEqual() {
			assertThat(viewCommonData.canEqual(viewCommonData)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスを比較")
		public void _030102_canEqual() {
			assertThat(viewCommonData.canEqual(new Object())).isFalse();
		}
	}
	
	@Nested
	@Order(4)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0401_hashCode {
		
		private int hashValue;
		
		@BeforeEach
		public void initHashCode() {
			hashValue = viewCommonData.hashCode();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスを比較")
		public void _040101_hashCode() {
			ViewCommonData sameValues = new ViewCommonData();
			sameValues.setErrMsg(initErrMsg);
			
			assertThat(sameValues.hashCode()).isEqualTo(hashValue);
		}
		
		@Test
		@DisplayName("正常_別値の別インスタンスを比較")
		public void _040102_hashCode() {
			ViewCommonData notSameNo = new ViewCommonData();
			notSameNo.setErrMsg(initErrMsg + "hoge");
			
			assertThat(notSameNo.hashCode()).isNotEqualTo(hashValue);
		}
		
		@Test
		@DisplayName("正常_フィールド未設定の別インスタンスを比較")
		public void _040103_hashCode() {
			ViewCommonData notSame = new ViewCommonData();
			
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
			String expected = "ViewCommonData(errMsg=exception message)";
			
			assertThat(viewCommonData.toString()).isEqualTo(expected);
		}
	}
}
