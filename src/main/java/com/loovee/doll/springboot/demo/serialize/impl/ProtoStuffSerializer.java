package com.loovee.doll.springboot.demo.serialize.impl;

import com.loovee.doll.springboot.demo.serialize.Serializer;
import com.loovee.doll.springboot.demo.serialize.SerializerAlgorithm;
import com.loovee.doll.springboot.demo.util.ProtoStuffUtil;

/**
 * Created on 2018/10/26. 借助ProtoStuff
 *
 * @author He Xin
 */
public class ProtoStuffSerializer implements Serializer {

	@Override
	public byte getSerializerAlgorithm() {
		return SerializerAlgorithm.PROTO_STUFF;
	}

	@Override
	public byte[] serialize(Object object) {
		return ProtoStuffUtil.serializer(object);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, byte[] data) {
		return ProtoStuffUtil.deserializer(data, clazz);
	}
}
