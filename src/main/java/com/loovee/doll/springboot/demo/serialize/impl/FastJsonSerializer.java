package com.loovee.doll.springboot.demo.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.loovee.doll.springboot.demo.serialize.Serializer;
import com.loovee.doll.springboot.demo.serialize.SerializerAlgorithm;

/**
 * Function:
 *
 * @author HeXin
 * @date 16:56 2019/1/31
 * @since JDK 1.8
 */
public class FastJsonSerializer implements Serializer {

	@Override
	public byte getSerializerAlgorithm() {
		return SerializerAlgorithm.JSON;
	}

	@Override
	public byte[] serialize(Object object) {
		return JSON.toJSONBytes(object);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, byte[] bytes) {
		return JSON.parseObject(bytes, clazz);
	}
}
