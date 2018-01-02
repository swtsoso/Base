package com.voting.web;

import com.alibaba.fastjson.JSONObject;
import com.voting.common.enums.system.ExceptionEnum;
import com.voting.common.exception.AppException;
import com.voting.common.exception.BusinessException;
import com.voting.common.exception.ParamException;
import com.voting.common.exception.SystemException;
import com.voting.common.exception.SystemParamException;
import com.voting.web.result.WebResult;
import com.voting.web.result.RequestBaseResult.ResponseStatus;
import com.voting.web.utils.RequestUtil;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleMappingExceptionResolver extends org.springframework.web.servlet.handler.SimpleMappingExceptionResolver {

	private Logger log = Logger.getLogger(getClass());
	private String jsonPath = null;
	private String pagePath = null;

	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String viewName = determineViewName(ex, request);

		if (viewName == null) {
			if (RequestUtil.isAjaxRequest() && jsonPath != null) {
				viewName = jsonPath;
			} else if (pagePath != null) {
				viewName = pagePath;
			} else {
				return null;
			}
		}

		Integer statusCode = determineStatusCode(request, viewName);
		if (statusCode != null) {
			applyStatusCodeIfPossible(request, response, statusCode);
		}

		return getModelAndView(viewName, ex, request);
	}

	protected ModelAndView getModelAndView(String viewName, Exception ex) {
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject(WebResult.RESULT_NAME, getWebResultJsonString(ex));
		return mv;
	}

	protected String getWebResultJsonString(Exception ex) {
		WebResult result = new WebResult();
		result.setStatus(ResponseStatus.EXCEPTION);

		if (ex instanceof ParamException) {
			log.error("参数异常", ex);
			ParamException bpe = (ParamException) ex;
			result.setStatus(ResponseStatus.PARAMETER_ERROR);
			result.addData("type", ExceptionEnum.BUSINESS_PARAM_EXCEPTION.getValue());
			result.addData("message", bpe.getMessage());
			printBusinessParamException(bpe);
		} else if (ex instanceof SystemParamException) {
			log.error("系统参数异常", ex);
			result.addData("type", ExceptionEnum.SYSTEM_PARAM_EXCEPTION.getValue());
			result.addData("message", ex.getMessage());
		} else if (ex instanceof BusinessException) {
			log.error("业务异常", ex);
			result.addData("type", ExceptionEnum.BUSINESS_EXCEPTION.getValue());
			result.addData("message", ex.getMessage());
			result.addData("errorType" , ((BusinessException) ex).getErrorType());
		} else if (ex instanceof SystemException) {
			log.error("系统异常", ex);
			result.addData("type", ExceptionEnum.SYSTEM_EXCEPTION.getValue());
			result.addData("message", ex.getMessage());
		} else if (ex instanceof AppException) {
			log.error("系统异常", ex);
			result.setStatus(1001);
			result.addData("message", ex.getMessage());
		} else {
			log.error("系统未捕获异常", ex);
			result.addData("type", ExceptionEnum.OTHER_EXCEPTION.getValue());
			result.addData("message", "系统异常");
			result.addData("exceptionMessage", ex.getMessage());
		}

		return JSONObject.toJSONString(result);
	}

	/**
	 * 格式化打印业务参数异常
	 */
	protected void printBusinessParamException(ParamException e) {
		StringBuilder consoleBuilder = new StringBuilder();
		consoleBuilder.append("业务参数异常，参数：[");
		consoleBuilder.append(e.getObjectName());
		consoleBuilder.append("]    字段：[");
		consoleBuilder.append(e.getFieldName());
		consoleBuilder.append("]    验证值：[");
		consoleBuilder.append(e.getFieldValue());
		consoleBuilder.append("]    描述：[");
		consoleBuilder.append(e.getMessage());
		consoleBuilder.append("]");
		log.error(consoleBuilder.toString());
	}

	/**
	 * {@linkplain #jsonPath}
	 */
	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}

	/**
	 * {@linkplain #pagePath}
	 */
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

}
