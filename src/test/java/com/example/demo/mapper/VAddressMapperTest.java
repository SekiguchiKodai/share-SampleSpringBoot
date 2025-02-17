package com.example.demo.mapper;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.VAddress;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class VAddressMapperTest extends AbstractMapperTest {
	
	private final VAddressMapper vAddressMapper;
	@Autowired
	public VAddressMapperTest(VAddressMapper vAddressMapper) {
		this.vAddressMapper = vAddressMapper;
	}
	
	@Test
	@DisplayName("010101_findByPrefecture_正常_特定の都道府県を指定して市区町村情報を取得")
	@DatabaseSetup(value = "/com/example/demo/mapper/vaddressmapper/db/setup/")
	public void findByPrefectureメソッドを実行して市区町村情報を取得() throws IOException {
		String targetPrefecture = "京都府";
		String[] expectedCities = {"京都市北区","京都市上京区", "京都市左京区","京都市右京区"};
		List<VAddress> actualAddresses = null;
		
		try {
			actualAddresses = vAddressMapper.findByPrefectureName(targetPrefecture);
			
			assertThat(actualAddresses).extracting("prefectureName", String.class).containsOnly(targetPrefecture);
			assertThat(actualAddresses).extracting("cityName", String.class).contains(expectedCities);
		} finally {
			String expectedMsg = String.join(",", expectedCities);
			String actualMsg   = actualAddresses.stream()
												.map(vAdress -> vAdress.getCityName())
												.collect(Collectors.joining(","));
			
			String textBlock ="""
					target prefecture  :%s
					expected city names:%s
					actual   city names:%s
					"""
					.formatted(targetPrefecture, expectedMsg, actualMsg);
			
			fileWriter.write(textBlock);
		}
	}
	
}
