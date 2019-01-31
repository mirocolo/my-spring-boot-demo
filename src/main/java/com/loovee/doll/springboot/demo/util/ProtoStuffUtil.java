package com.loovee.doll.springboot.demo.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Function: ProtoStuff序列化工具类
 *
 * @author HeXin
 * @date 17:40 2019/1/31
 * @since JDK 1.8
 */
public class ProtoStuffUtil {

	/**
	 * RuntimeSchema.getSchema 自带缓存功能，无需再次处理
	 * 序列化
	 */
	public static <T> byte[] serializer(T obj) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			Schema<T> schema = RuntimeSchema.getSchema(clazz);
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}

	/**
	 * 反序列化
	 */
	public static <T> T deserializer(byte[] data, Class<T> clazz) {
		try {
			Schema<T> schema = RuntimeSchema.getSchema(clazz);
			T obj = schema.newMessage();
			ProtostuffIOUtil.mergeFrom(data, obj, schema);
			return obj;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
}
