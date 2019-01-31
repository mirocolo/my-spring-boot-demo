package com.loovee.doll.springboot.demo.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Function:
 *
 * @author HeXin
 * @date 10:02 2019/1/30
 * @since JDK 1.8
 */
@Slf4j
@Configuration
public class RedisConfig {

	@Autowired
	private RedisProperties properties;

	@Bean
	public JedisPool getJedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(properties.getJedis().getPool().getMaxIdle());
		config.setMaxTotal(properties.getJedis().getPool().getMaxActive());
		config.setMaxWaitMillis(properties.getJedis().getPool().getMaxWait().toMillis());
		JedisPool pool = new JedisPool(config, properties.getHost(), properties.getPort(), (int) properties.getTimeout().getSeconds());
		log.info("redis node [{},{}] ", config, properties.getHost(), properties.getPort());
		return pool;
	}


}
