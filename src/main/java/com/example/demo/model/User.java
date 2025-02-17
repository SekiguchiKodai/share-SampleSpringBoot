package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

/**
 * <h1>[ユーザー情報]</h1><br>
 * <br>
 *  USERの情報を持つ。
 */
@Data								// Lombokのアノテーション Getter Setter の自動生成を行う。
@Generated
@EqualsAndHashCode(callSuper=true)	// @Dataアノテーションが付与されたクラスを継承した場合に明示する必要がある。
public class User extends ViewCommonData {
	
	/** 個人番号 */
	private long no;
	
	/** 氏名 */
	private String name;
	
	/** 年齢 */
	private long age;
	
	/** 誕生日 */
	private String birthday;
	
	/** 住所 */
	private UserAddress userAddress = new UserAddress();
}
