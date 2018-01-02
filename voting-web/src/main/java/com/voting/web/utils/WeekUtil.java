package com.voting.web.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author Alan
 * @Data 2017/6/22.
 */
public class WeekUtil {

	private WeekUtil(){};

	private static final String[] WEETZHCN = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

	private static final String[] DATEZHCN = {"一","二","三","四","五","六","七","八","九","十"};

	public static String getWeebZ (Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int i = cal.get(Calendar.DAY_OF_WEEK);
		return WEETZHCN[i-1];

	}


	public static String getDateZ (Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);

		int day = cal.get(Calendar.DAY_OF_MONTH);

		StringBuilder sb = new StringBuilder();
		if (month >= 10) {
			sb.append("十");
			sb.append(DATEZHCN[((month+1)%10) - 1]);
		} else {
			sb.append(DATEZHCN[month]);
		}
		sb.append("月");
		if (day >= 10) {
			if (day >= 20) {
				sb.append(DATEZHCN[(day/10) - 1]);
			}
			sb.append("十");
			if (day%10 != 0) {
				sb.append(DATEZHCN[((day%10) - 1)]);
			}
		} else {
			sb.append(DATEZHCN[day - 1]);
		}
		sb.append("日");

		return sb.toString();
	}

	public static void main(String[] args) {
		getWeebZ(new Date());
	}
}
