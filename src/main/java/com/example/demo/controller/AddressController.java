package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.VAddress;
import com.example.demo.service.AddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AddressController {
	
	private final AddressService addressService;
	
	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	// koment
	@GetMapping("/cityList")
	@ResponseBody
	private String cityList(@RequestParam String prefectureName) throws JsonProcessingException, InterruptedException {
		List<VAddress> vAddresses = addressService.findByPrefectureName(prefectureName);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(vAddresses);
		
		return json;
	}
}
