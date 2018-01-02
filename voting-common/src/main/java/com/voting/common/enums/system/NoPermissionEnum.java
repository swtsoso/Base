package com.voting.common.enums.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 无权限代码
 * @version 1.0
 */
public enum NoPermissionEnum {

	/**
	 * 未登录
	 */
	NO_LOGIN(1001, "未登录"),
	/**
	 * 登录超时
	 */
	TIME_OUT(1002, "登录超时"),
	/**
	 * 无权限
	 */
	NO_PERMISSION(1003, "无权限"),
	
	
	CLIENT_NO_LOGIN(1004,"客户端未登录");

	/**
	 * 代码
	 */
	private int value;
	/**
	 * 描述
	 */
	private String message;

	private NoPermissionEnum(int value, String message) {
		this.value = value;
		this.message = message;
	}

	/**
	 * {@linkplain #value}
	 */
	public int getValue() {
		return value;
	}

	/**
	 * {@linkplain #message}
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * {@linkplain #message}
	 */
	public static String getMessage(Integer value) {
		for (NoPermissionEnum item : NoPermissionEnum.values()) {
			if (item.value == value) {
				return item.message;
			}
		}
		return "";
	}
	
	/**
	 * 获取所有枚举的代码和描述列表
	 */
	public static List<Map<String, Object>> getAllStatusMap(){
		List<Map<String, Object>> list = new ArrayList<>();
		for (NoPermissionEnum item : NoPermissionEnum.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", item.value);
			map.put("value", item.message);
			list.add(map);
		}
		return list;
	}

}
