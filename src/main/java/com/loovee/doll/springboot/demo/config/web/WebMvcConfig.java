package com.loovee.doll.springboot.demo.config.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.loovee.doll.springboot.demo.config.web.filter.SecurityFilter;
import com.loovee.doll.springboot.demo.config.web.iterator.ParamIterator;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 *
 * @author HeXin
 * @date 16:59 2019/1/30
 * @since JDK 1.8
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	/**
	 * 使用FastJson序列化返回的http消息
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				// 防止循环引用
				SerializerFeature.DisableCircularReferenceDetect,
				// 空集合返回[],不返回null
				SerializerFeature.WriteNullListAsEmpty,
				// 空字符串返回"",不返回null
				SerializerFeature.WriteNullStringAsEmpty,
				// MapValue返回"",不返回null
				SerializerFeature.WriteMapNullValue
		);
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		//处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
		return new HttpMessageConverters(fastJsonHttpMessageConverter);
	}

	/**
	 * 将代理服务器发来的请求包含的IP地址转换成真正的用户IP
	 */
	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}

	@Bean
	public SecurityFilter initSecurityFilter() {
		return new SecurityFilter();
	}

	/**
	 * 注入过滤器链
	 * @return
	 */
	@Bean
	public FilterRegistrationBean securityFilter() {
		FilterRegistrationBean<SecurityFilter> register = new FilterRegistrationBean<>();
		register.setFilter(initSecurityFilter());
		register.addUrlPatterns("/api/crm/*");
		register.addInitParameter("securityKey", "securityVal");
		register.setName("SecurityFilter");
		register.setOrder(1);
		return register;
	}

	@Bean
	public ParamIterator paramIterator() {
		return new ParamIterator();
	}

	/**
	 * 注入系统内实现的拦截器类
	 *
	 * @param registry 拦截器注册类
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(paramIterator())
				.addPathPatterns("/**")
				.excludePathPatterns("/");
	}
}
