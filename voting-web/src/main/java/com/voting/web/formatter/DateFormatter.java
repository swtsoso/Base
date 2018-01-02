package com.voting.web.formatter;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import com.voting.common.exception.BusinessException;
import com.voting.common.utils.DateUtils;

public class DateFormatter implements Formatter<Date> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String print(Date object, Locale locale) {
		return null;
	}

	@Override
	public Date parse(String text, Locale locale) {
		try {
			return DateUtils.DATE_FORMAT1.parse(text);
		} catch (Exception e) {
			logger.error("{}使用{}格式格式化失败", text, DateUtils.DATE_FORMAT1.toPattern());
		}
		try {
			return DateUtils.DATE_FORMAT2.parse(text);
		} catch (Exception ex) {
			logger.error("{}使用{}格式格式化失败", text, DateUtils.DATE_FORMAT2.toPattern());
		}
		try {
			return DateUtils.DATE_FORMAT3.parse(text);
		} catch (Exception e) {
			logger.error("{}使用{}格式格式化失败", text, DateUtils.DATE_FORMAT3.toPattern());
		}
		throw new BusinessException("未知的时间格式，系统无法处理");
	}

}
