package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.demo.entity.VAddress;
import com.example.demo.repository.ImpAddressRepository;

public class AddressServiceTest extends BaseServiceTest {
	
	private final AddressService addressService;
	@MockitoBean
	private final ImpAddressRepository mockAddressRepository;
	
	@Autowired
	public AddressServiceTest(AddressService addressService, ImpAddressRepository addressRepository) {
		this.addressService = addressService;
		this.mockAddressRepository = addressRepository;
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.DisplayName.class)
	class _0101_findByPrefectureName {
		
		@Test
		@DisplayName("正常_指定した都道府県に所属する市区町村情報を取得")
		public void _010101_findByPrefectureName() throws IOException {
			// 期待値
			String targetPrefectureName = "千葉県";
			String[] expectedCityNames = {"千葉市", "銚子市", "市川市", "船橋市"};
			List<VAddress> actual = null;
			
			// Mock Repositoryの戻り値を用意
			VAddress vAddress1 = new VAddress(); vAddress1.setPrefectureName("千葉県"); vAddress1.setCityName("千葉市");
			VAddress vAddress2 = new VAddress(); vAddress2.setPrefectureName("千葉県"); vAddress2.setCityName("銚子市");
			VAddress vAddress3 = new VAddress(); vAddress3.setPrefectureName("千葉県"); vAddress3.setCityName("市川市");
			VAddress vAddress4 = new VAddress(); vAddress4.setPrefectureName("千葉県"); vAddress4.setCityName("船橋市");
			List<VAddress> returnAddresses = List.of(vAddress1, vAddress2, vAddress3, vAddress4);
			// Mock Repositoryの戻り値を設定
			when(mockAddressRepository.findByPrefectureName(targetPrefectureName)).thenReturn(returnAddresses);
			
			try {
				// テスト対象のメソッド実行
				actual = addressService.findByPrefectureName(targetPrefectureName);
				
				// Repositoryの呼び出し回数を確認
				verify(mockAddressRepository, times(1)).findByPrefectureName(targetPrefectureName);
				// 戻り値と期待値を比較
				assertThat(actual).extracting("prefectureName", String.class).containsOnly(targetPrefectureName);
				assertThat(actual).extracting("cityName", String.class).contains(expectedCityNames);
				
			} finally {
				String expectedMsg = String.join(",", expectedCityNames);
				String actualMsg   = String.join(",", actual.stream().map(vAddress -> vAddress.getCityName()).toList());
				
				String textBlock = """
						target prefecture  :%s
						expected city names:%s
						actual   city names:%s
						"""
						.formatted(targetPrefectureName, expectedMsg, actualMsg);
				fileWriter.write(textBlock);
			}
		}
	}
	

}
