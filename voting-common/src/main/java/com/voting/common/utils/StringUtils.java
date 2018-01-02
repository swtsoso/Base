package com.voting.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.voting.common.exception.SystemParamException;

/**
 * 字符串工具s
 * @version 1.0
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 是否为空
	 * @param str 字符串
	 */
	public static void isEmptyException(String str) {
		if (isEmpty(str)) {
			throw SystemParamException.DEFALUT_NULL_OR_EMPTY_EXCETION;
		}
	}

	/**
	 * 是否为空
	 * @param str 字符串
	 * @param e 想要抛出的异常
	 */

	public static void isEmptyException(String str, RuntimeException e) {
		if (isEmpty(str)) {
			throw e;
		}
	}

	/**
	 * 是否为空
	 * @param str 字符串
	 * @param message 异常描述
	 */
	public static void isEmptyException(String str, String message) {
		if (isEmpty(str)) {
			throw SystemParamException.getNullOrEmptyException(message);
		}
	}

	public static String valueOf(Object object){
		return object == null ? "" : object.toString();
	}

	public static String appends(Object ...objects){
		if(objects ==  null || objects.length == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Object object : objects) {
			sb.append(StringUtils.valueOf(object));
		}
		return sb.toString();
	}

	/**
	 * 在以逗号分隔的strList内判断是否存在str
	 */
	public static boolean findInSet(String str, String strList){
		if(strList == null){
			return false;
		}
		if(str == null){
			return false;
		}
		List<String> list = Arrays.asList(strList.split(","));
		for (String s : list) {
			if(str.equals(s)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 首字母大写
	 */
	public static String capitalizeFirstLetter(String name) {
		char[] cs=name.toCharArray();
		cs[0]-=32;
		return String.valueOf(cs);
	}


	public static List<Long> asList(String strList){
		List<Long> result = new ArrayList<>();
		if(strList == null){
			return result;
		}
		List<String> list = Arrays.asList(strList.split(","));
		for (String s : list) {
			if(isEmpty(s)){
				continue;
			}
			try {
				result.add(Long.valueOf(s));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new SystemParamException("传入数据不是数字");
			}
		}
		return result;
	}

	public static boolean isNumber(String str) {
		if (str == null || (str = str.trim()).length() == 0) {
			return false;
		}
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
