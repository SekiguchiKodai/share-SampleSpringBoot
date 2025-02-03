package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.VAddress;

@Mapper
public interface VAddressMapper {
	
	public List<VAddress> findByPrefectureName(String name);
}
