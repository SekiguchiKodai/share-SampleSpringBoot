package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * <h1>[住所]</h1><br>
 * <br>
 * 住所を持つ。
 */
@Data
public class UserAddress {
	/** 都道府県 */
	private String prefecture;

	/** 市区町村 */
	private String city;

	/** 市区町村以下 */
	private List<String> addressLine = new ArrayList<>();
}
