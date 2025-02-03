package com.example.demo.t.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.t.entity.VAddress;

@Mapper
public interface VAddressMapper {
	
	public List<VAddress> findByPrefectureName(String name);
}
