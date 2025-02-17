package com.example.demo.entity;

import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class VAddress {
	
	/** 都道府県コード */
	private String prefectureCd;
	
	/** 都道府県名 */
	private String prefectureName;
	
	/** 都道府県名(カナ) */
	private String prefectureNameKana;
	
	/** 市区町村コード */
	private String cityCd;
	
	/** 市区町村名 */
	private String cityName;
	
	/** 市区町村名(カナ) */
	private String cityNameKana;
}
