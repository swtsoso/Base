package com.voting.common.enums.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异常类型代码
 * @date 2016年7月29日
 * @author Jianwen Zhu
 * @version 1.0
 */
public enum ExceptionEnum {

	/**
	 * 业务参数异常
	 */
	BUSINESS_PARAM_EXCEPTION(601, "业务参数异常"),
	/**
	 * 系统参数异常
	 */
	SYSTEM_PARAM_EXCEPTION(602, "系统参数异常"),
	/**
	 * 系统异常
	 */
	SYSTEM_EXCEPTION(503, "系统异常"),
	/**
	 * 业务异常
	 */
	BUSINESS_EXCEPTION(504, "业务异常"),
	/**
	 * 其它异常
	 */
	OTHER_EXCEPTION(504, "其它异常");

	/**
	 * 代码
	 */
	private int value;
	/**
	 * 描述
	 */
	private String message;

	private ExceptionEnum(int value, String message) {
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
	public static String getMessage(byte value) {
		for (ExceptionEnum item : ExceptionEnum.values()) {
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
		for (ExceptionEnum item : ExceptionEnum.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("key", item.value);
			map.put("value", item.message);
			list.add(map);
		}
		return list;
	}

}
