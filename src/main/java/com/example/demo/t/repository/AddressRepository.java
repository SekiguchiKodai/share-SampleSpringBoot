package com.example.demo.t.repository;

import java.util.List;

import com.example.demo.t.entity.VAddress;

public interface AddressRepository {
	
	public List<VAddress> findByPrefectureName(String name);
}
