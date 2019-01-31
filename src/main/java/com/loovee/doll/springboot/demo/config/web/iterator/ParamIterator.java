package com.loovee.doll.springboot.demo.config.web.iterator;

import com.alibaba.fastjson.JSONObject;
import com.loovee.doll.springboot.demo.util.DateUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Function: 日志参数和返回请求耗时拦截器
 *
 * @author HeXin
 * @date 16:20 2019/1/30
 * @since JDK 1.8
 */
@Slf4j
public class ParamIterator implements HandlerInterceptor {

	private static final String METHOD_GET = "GET";

	private static final String METHOD_POST = "POST";

	private final static String REQUEST_TIME_KEY = "REQUEST_TIME_KEY";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute(REQUEST_TIME_KEY, DateUtil.nowToMillisecond());
		String requestUrl = request.getContextPath() + request.getServletPath();
		String method = request.getMethod();
		Map map = getRequestMap(method, request);
		log.info("当前请求：{} | {} |参数为{}", method, requestUrl, JSONObject.toJSONString(map));
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
	                            @Nullable Exception ex) throws Exception {
		long start = (long) request.getAttribute(REQUEST_TIME_KEY);
		request.removeAttribute(REQUEST_TIME_KEY);
		long end = DateUtil.nowToMillisecond();
		log.info("返回请求,耗时：{} ms", (end - start));
	}

	private Map getRequestMap(String method, HttpServletRequest request) {
		if (METHOD_POST.equals(method)) {
			return request.getParameterMap();
		}
		if (METHOD_GET.equals(method)) {
			return getQueryStrings(request);
		}
		return Collections.EMPTY_MAP;
	}

	private Map<String, String> getQueryStrings(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<String, String>(16);
		String queryString = request.getQueryString();
		if (StringUtils.isNotEmpty(queryString)) {
			String[] paramArray = queryString.split("&");
			if (paramArray.length > 0) {
				for (String paramStr : paramArray) {
					String[] paramTemp = paramStr.split("=");
					if (paramTemp.length != 2) {
						continue;
					}
					paramMap.put(paramTemp[0], paramTemp[1]);
				}
			}
		}
		return paramMap;
	}
}
