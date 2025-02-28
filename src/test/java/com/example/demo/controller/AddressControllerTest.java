package com.example.demo.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.VAddress;
import com.example.demo.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressControllerTest extends BaseControllerTest {
	
	@MockitoBean
	private AddressService mockAddressService;
	
	@Autowired
	public AddressControllerTest(MockMvc mockMvc, AddressService addressService) {
		super(mockMvc);
		this.mockAddressService = addressService;
	}

	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _G0101_cityList{
		
		@Test
		@DisplayName("正常_特定の都道府県を指定して市区町村情報を取得")
		public void _G010101_cityList() throws Exception {
			// 期待値
			String expectedPrefectureName = "千葉県";
			String[] expectedCityNames = {"千葉市", "銚子市", "市川市", "船橋市"};
			
			// Mock AddressService.findByPrefecture()の戻り値を用意
			VAddress vAddress1 = new VAddress(); vAddress1.setPrefectureName("千葉県"); vAddress1.setCityName("千葉市");
			VAddress vAddress2 = new VAddress(); vAddress2.setPrefectureName("千葉県"); vAddress2.setCityName("銚子市");
			VAddress vAddress3 = new VAddress(); vAddress3.setPrefectureName("千葉県"); vAddress3.setCityName("市川市");
			VAddress vAddress4 = new VAddress(); vAddress4.setPrefectureName("千葉県"); vAddress4.setCityName("船橋市");
			List<VAddress> vAddresses = List.of(vAddress1, vAddress2, vAddress3, vAddress4);
			//Mock AddressService.findByPrefecture()の戻り値を設定
			doReturn(vAddresses).when(mockAddressService).findByPrefectureName(expectedPrefectureName);
			
			// テスト実行
			String responseJsonBody = mockMvc.perform(get("/cityList").param("prefectureName", "千葉県"))
											 .andDo(print(fileWriter))
											 .andExpect(status().isOk())
											 .andReturn().getResponse().getContentAsString();
			
			// Json文字列をVAddress配列に変換
			ObjectMapper mapper = new ObjectMapper();
			VAddress[] actual = mapper.readValue(responseJsonBody, VAddress[].class);
			
			assertThat(actual).extracting("prefectureName", String.class).containsOnly(expectedPrefectureName);
			assertThat(actual).extracting("cityName", String.class).contains(expectedCityNames);
		}
	}
}
