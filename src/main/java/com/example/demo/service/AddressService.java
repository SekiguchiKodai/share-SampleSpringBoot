package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.VAddress;
import com.example.demo.repository.AddressRepository;

@Service
public class AddressService {

	private final AddressRepository addressRepository;
	
	@Autowired
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public List<VAddress> findByPrefectureName(String name) {
		return addressRepository.findByPrefectureName(name);
	}
	
}
