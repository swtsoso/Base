package com.voting.web.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.voting.common.exception.SystemException;

public class RequestUtil {

	public static void deleteCookie(String key) {
		HttpServletResponse response = getResponse();
		response.addCookie(new Cookie(key, ""));
	}

	public static String getCookie(String key) {
		HttpServletRequest request = getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (key.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String getRequestIp() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public static String getSessionId() {
		return getRequest().getSession().getId();
	}
	
	/*public static String getSessionIdFromCookie() {
		return getCookie(ConstantVariables.COOKIE_SESSIONID_NAME);
	}*/

	public static Boolean isAjaxRequest() {
		HttpServletRequest request = getRequest();
		Boolean flag = Boolean.FALSE;
		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			if (("x-requested-with").equalsIgnoreCase(headerName) && request.getHeader(headerName) != null) {
				flag = Boolean.TRUE;
				break;
			}
		}
		return flag;
	}

	public static void writeTextToResponse(String message) {
		try {
			ServletResponse response = getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(message);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	private RequestUtil() {}
}
