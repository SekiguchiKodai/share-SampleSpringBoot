package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.VAddress;
import com.example.demo.test.dbunit.CsvDataSetLoader;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

public class AddressRepositoryTest extends BaseRepositoryTest{
	
	private final ImpAddressRepository addressRepository;
	
	@Autowired
	public AddressRepositoryTest(ImpAddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.DisplayName.class)
	@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
	class _R0101_findByPrefectureName {
		
		@Test
		@DisplayName("正常_指定した都道府県に所属する市区町村情報を取得")
		public void _R010101_findByPrefectureName() throws IOException {
			String targetPrefectureName = "千葉県";
			String[] expectedCityNames = {"千葉市", "銚子市", "市川市", "船橋市"};
			List<VAddress> actual = null;
			
			try {
				actual = addressRepository.findByPrefectureName(targetPrefectureName);
				
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
