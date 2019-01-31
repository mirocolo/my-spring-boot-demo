package com.loovee.doll.springboot.demo.config.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.loovee.doll.springboot.demo.common.enums.ReturnCodeEnum;
import com.loovee.doll.springboot.demo.common.result.ResponseResult;
import com.loovee.doll.springboot.demo.util.NetAddressUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Function:  请求过滤器 部分请求只能允许内网或者IP白名单访问，在分发器尚未分发请求的时候进行过滤
 *
 * @author HeXin
 * @date 10:02 2019/1/30
 * @since JDK 1.8
 */
@Slf4j
public class SecurityFilter implements Filter {

	@Value("${crm.api.whiteips}")
	private String crmApiWhiteIps;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("SecurityFilter init! 过滤器初始化...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String reqIp = NetAddressUtil.getIpAddress(req);
		boolean internalIp = NetAddressUtil.internalIp(reqIp);
		log.info("请求ip:{};internalIp:{};whiteIps:{}", reqIp, internalIp, crmApiWhiteIps);
		boolean ipOk = internalIp;
		if (!internalIp) {
			if (StringUtils.isNotEmpty(crmApiWhiteIps)) {
				List<String> list = Arrays.asList(crmApiWhiteIps.split(","));
				ipOk = list.contains(reqIp);
			} else {
				ipOk = true;
			}
		}
		if (ipOk) {
			chain.doFilter(request, response);
		} else {
			try (PrintWriter pw = response.getWriter()) {
				log.error("请求ip:{}非法! whiteIps:{},请求:{}", reqIp, crmApiWhiteIps, req.getContextPath() + req.getServletPath());
				ResponseResult result = ResponseResult.error(ReturnCodeEnum._401);
				pw.write(JSONObject.toJSONString(result));
				pw.flush();
			} catch (Exception e) {
				log.error("");
			}
		}
	}

	@Override
	public void destroy() {
		log.info("SecurityFilter destroy! 过滤器销毁...");
	}
}
