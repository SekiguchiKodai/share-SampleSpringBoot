package com.example.demo.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.VAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressControllerTest extends BaseControllerTest {
	
	@Autowired
	public AddressControllerTest(MockMvc mockMvc) {
		super(mockMvc);
	}

	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.MethodName.class)
	class _G0101_cityList{
		
		@Test
		@DisplayName("正常_特定の都道府県を指定して市区町村情報を取得")
		public void _G010101_cityList() throws Exception {
			String expectedPrefectureName = "千葉県";
			String[] expectedCityNames = {"千葉市", "銚子市", "市川市", "船橋市"};
			
			String responseJsonBody = mockMvc.perform(get("/cityList").param("prefectureName", "千葉県"))
											 .andDo(print(fileWriter))
											 .andExpect(status().isOk())
											 .andReturn().getResponse().getContentAsString();
			
			ObjectMapper mapper = new ObjectMapper();
			
			VAddress[] actual = mapper.readValue(responseJsonBody, VAddress[].class);
			
			assertThat(actual).extracting("prefectureName", String.class).containsOnly(expectedPrefectureName);
			assertThat(actual).extracting("cityName", String.class).contains(expectedCityNames);
		}
	}
}
