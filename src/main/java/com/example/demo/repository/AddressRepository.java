package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.VAddress;

public interface AddressRepository {
	
	public List<VAddress> findByPrefectureName(String name);
}
