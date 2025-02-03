package com.example.demo.t.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.t.entity.VAddress;
import com.example.demo.t.mapper.VAddressMapper;

@Repository
public class ImpAddressRepository implements AddressRepository {

	private final VAddressMapper vAddressMapper;
	
	@Autowired
	public ImpAddressRepository(VAddressMapper vAddressMapper) {
		this.vAddressMapper = vAddressMapper;
	}
	
	public List<VAddress> findByPrefectureName(String name) {
		return vAddressMapper.findByPrefectureName(name);
	}
}
