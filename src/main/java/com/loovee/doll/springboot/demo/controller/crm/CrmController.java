package com.loovee.doll.springboot.demo.controller.crm;

import com.loovee.doll.springboot.demo.bean.po.User;
import com.loovee.doll.springboot.demo.common.exception.GenericException;
import com.loovee.doll.springboot.demo.dao.TestMapper;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;

/**
 * Function:
 *
 * @author HeXin
 * @date 10:02 2019/1/30
 * @since JDK 1.8
 */
@Slf4j
@RestController
@RequestMapping("/api/crm")
public class CrmController {

	@Autowired
	TestMapper testMapper;

	@Autowired
	private JedisPool jedisPool;

	@Value("${crm.request.secret}")
	String key;

	@RequestMapping(value = "/findUserById", method = RequestMethod.POST)
	public User helloWorld(@RequestParam long id) {
		User user = testMapper.findUserById(id);
		if (null == user) {
			throw GenericException.getNullPointException();
		}
		user.setNick(new String(Base64.decodeBase64(user.getNick()), Charset.forName("UTF-8")));
		return user;
	}
}
