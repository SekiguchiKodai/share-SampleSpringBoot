package com.example.demo.entity;

import static org.assertj.core.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

public class VAddressTest extends BaseEntityTest {

	/** 都道府県コード */
	private String initPrefectureCd;
	/** 都道府県名 */
	private String initPrefectureName;
	/** 都道府県名(カナ) */
	private String initPrefectureNameKana;
	/** 市区町村コード */
	private String initCityCd;
	/** 市区町村名 */
	private String initCityName;
	/** 市区町村名(カナ) */
	private String initCityNameKana;
	/** 比較用インスタンス */
	private VAddress vAddress;
	
	@BeforeEach
	public void initVAddress() {
		initPrefectureCd			= "01";
		initPrefectureName		= "北海道";
		initPrefectureNameKana	= "ホッカイドウ";
		initCityCd 				= "01100";
		initCityName				= "札幌市";
		initCityNameKana			= "サッポロシ";
		
		vAddress = new VAddress();
		vAddress.setPrefectureCd(initPrefectureCd);
		vAddress.setPrefectureName(initPrefectureName);
		vAddress.setPrefectureNameKana(initPrefectureNameKana);
		vAddress.setCityCd(initCityCd);
		vAddress.setCityName(initCityName);
		vAddress.setCityNameKana(initCityNameKana);
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0101_getter_and_setter{
	
		@Test
		@DisplayName("正常_setterでセットした値をgetterで取得できる")
		public void _010101_getter_and_setter() throws ParseException {
			String expectedPrefectureCd			= "01";
			String expectedPrefectureName		= "北海道";
			String expectedPrefectureNameKana	= "ホッカイドウ";
			String expectedCityCd 				= "01100";
			String expectedCityName				= "札幌市";
			String expectedCityNameKana			= "サッポロシ";
			
			VAddress vAddress = new VAddress();
			vAddress.setPrefectureCd(expectedPrefectureCd);
			vAddress.setPrefectureName(expectedPrefectureName);
			vAddress.setPrefectureNameKana(expectedPrefectureNameKana);
			vAddress.setCityCd(expectedCityCd);
			vAddress.setCityName(expectedCityName);
			vAddress.setCityNameKana(expectedCityNameKana);
			
			assertThat(vAddress.getPrefectureCd()).isEqualTo(expectedPrefectureCd);
			assertThat(vAddress.getPrefectureName()).isEqualTo(expectedPrefectureName);
			assertThat(vAddress.getPrefectureNameKana()).isEqualTo(expectedPrefectureNameKana);
			assertThat(vAddress.getCityCd()).isEqualTo(expectedCityCd);
			assertThat(vAddress.getCityName()).isEqualTo(expectedCityName);
			assertThat(vAddress.getCityNameKana()).isEqualTo(expectedCityNameKana);
		}
	}
	
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0201_equals{
	
		@Test
		@DisplayName("正常_同じインスタンスと比較")
		public void _020101_equals() throws ParseException {
			assertThat(vAddress.equals(vAddress)).isTrue();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスと比較")
		public void _020102_equals(){
			VAddress sameValues = new VAddress();
			sameValues.setPrefectureCd(initPrefectureCd);
			sameValues.setPrefectureName(initPrefectureName);
			sameValues.setPrefectureNameKana(initPrefectureNameKana);
			sameValues.setCityCd(initCityCd);
			sameValues.setCityName(initCityName);
			sameValues.setCityNameKana(initCityNameKana);
			
			assertThat(vAddress.equals(sameValues)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスと比較")
		public void _020103_equals(){
			assertThat(vAddress.equals(new Object())).isFalse();
		}
		
		@Test
		@DisplayName("正常_prefectureCdフィールドが違うインスタンスと比較")
		public void _020104_equals(){
			VAddress notSamePrefectureCd = new VAddress();
			notSamePrefectureCd.setPrefectureCd("99");
			notSamePrefectureCd.setPrefectureName(initPrefectureName);
			notSamePrefectureCd.setPrefectureNameKana(initPrefectureNameKana);
			notSamePrefectureCd.setCityCd(initCityCd);
			notSamePrefectureCd.setCityName(initCityName);
			notSamePrefectureCd.setCityNameKana(initCityNameKana);
			
			assertThat(vAddress.equals(notSamePrefectureCd)).isFalse();
		}
		
		@Test
		@DisplayName("正常_prefectureNameフィールドが違うインスタンスと比較")
		public void _020105_equals(){
			VAddress notSamePrefectureName = new VAddress();
			notSamePrefectureName.setPrefectureCd(initPrefectureCd);
			notSamePrefectureName.setPrefectureName(initPrefectureName+"県");
			notSamePrefectureName.setPrefectureNameKana(initPrefectureNameKana);
			notSamePrefectureName.setCityCd(initCityCd);
			notSamePrefectureName.setCityName(initCityName);
			notSamePrefectureName.setCityNameKana(initCityNameKana);
			
			assertThat(vAddress.equals(notSamePrefectureName)).isFalse();
		}
		
		@Test
		@DisplayName("正常_prefectureNameKanaフィールドが違うインスタンスと比較")
		public void _020106_equals(){
			VAddress notSamePrefectureNameKana = new VAddress();
			notSamePrefectureNameKana.setPrefectureCd(initPrefectureCd);
			notSamePrefectureNameKana.setPrefectureName(initPrefectureName);
			notSamePrefectureNameKana.setPrefectureNameKana(initPrefectureNameKana+"ケン");
			notSamePrefectureNameKana.setCityCd(initCityCd);
			notSamePrefectureNameKana.setCityName(initCityName);
			notSamePrefectureNameKana.setCityNameKana(initCityNameKana);
			
			assertThat(vAddress.equals(notSamePrefectureNameKana)).isFalse();
		}
		
		@Test
		@DisplayName("正常_cityCdフィールドが違うインスタンスと比較")
		public void _020107_equals() throws ParseException{
			VAddress notSameCityCd = new VAddress();
			notSameCityCd.setPrefectureCd(initPrefectureCd);
			notSameCityCd.setPrefectureName(initPrefectureName);
			notSameCityCd.setPrefectureNameKana(initPrefectureNameKana);
			notSameCityCd.setCityCd("99999");
			notSameCityCd.setCityName(initCityName);
			notSameCityCd.setCityNameKana(initCityNameKana);
			
			assertThat(vAddress.equals(notSameCityCd)).isFalse();
		}
		
		@Test
		@DisplayName("正常_cityNameフィールドが違うインスタンスと比較")
		public void _020108_equals() throws ParseException{
			VAddress notSameCityName = new VAddress();
			notSameCityName.setPrefectureCd(initPrefectureCd);
			notSameCityName.setPrefectureName(initPrefectureName);
			notSameCityName.setPrefectureNameKana(initPrefectureNameKana);
			notSameCityName.setCityCd(initPrefectureCd);
			notSameCityName.setCityName(initCityName+"村");
			notSameCityName.setCityNameKana(initCityNameKana);
			
			assertThat(vAddress.equals(notSameCityName)).isFalse();
		}
		
		@Test
		@DisplayName("正常_cityNameKanaフィールドが違うインスタンスと比較")
		public void _020109_equals() throws ParseException{
			VAddress notSameCityNameKana = new VAddress();
			notSameCityNameKana.setPrefectureCd(initPrefectureCd);
			notSameCityNameKana.setPrefectureName(initPrefectureName);
			notSameCityNameKana.setPrefectureNameKana(initPrefectureNameKana);
			notSameCityNameKana.setCityCd(initCityCd);
			notSameCityNameKana.setCityName(initCityName);
			notSameCityNameKana.setCityNameKana(initCityNameKana+"ムラ");
			
			assertThat(vAddress.equals(notSameCityNameKana)).isFalse();
		}
	}
	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0301_canEqual{
		
		@Test
		@DisplayName("正常_同じインスタンスを比較")
		public void _030101_canEqual() {
			assertThat(vAddress.canEqual(vAddress)).isTrue();
		}
		
		@Test
		@DisplayName("正常_別クラスのインスタンスを比較")
		public void _030102_canEqual() {
			assertThat(vAddress.canEqual(new Object())).isFalse();
		}
	}

	@Nested
	@Order(4)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _0401_hashCode {
		
		private int hashValue;
		
		@BeforeEach
		public void initHashCode() {
			hashValue = vAddress.hashCode();
		}
		
		@Test
		@DisplayName("正常_同値の別インスタンスを比較")
		public void _040101_hashCode() {
			VAddress sameValues = new VAddress();
			sameValues.setPrefectureCd(initPrefectureCd);
			sameValues.setPrefectureName(initPrefectureName);
			sameValues.setPrefectureNameKana(initPrefectureNameKana);
			sameValues.setCityCd(initCityCd);
			sameValues.setCityName(initCityName);
			sameValues.setCityNameKana(initCityNameKana);
			
			assertThat(sameValues.hashCode()).isEqualTo(hashValue);
		}
		
		@Test
		@DisplayName("正常_別値の別インスタンスを比較")
		public void _040102_hashCode() {
			VAddress notSamePrefectureCd = new VAddress();
			notSamePrefectureCd.setPrefectureCd("99");
			notSamePrefectureCd.setPrefectureName(initPrefectureName);
			notSamePrefectureCd.setPrefectureNameKana(initPrefectureNameKana);
			notSamePrefectureCd.setCityCd(initCityCd);
			notSamePrefectureCd.setCityName(initCityName);
			notSamePrefectureCd.setCityNameKana(initCityNameKana);
			
			assertThat(notSamePrefectureCd.hashCode()).isNotEqualTo(hashCode());
		}
		
		@Test
		@DisplayName("正常_フィールド未設定の別インスタンスを比較")
		public void _040103_hashCode() {
			VAddress notSame = new VAddress();
			
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
			String expected = "VAddress("
					+ "prefectureCd=01, prefectureName=北海道, prefectureNameKana=ホッカイドウ, "
					+ "cityCd=01100, cityName=札幌市, cityNameKana=サッポロシ)";
			assertThat(vAddress.toString()).isEqualTo(expected);
		}
	}
}
