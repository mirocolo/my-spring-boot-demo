package com.loovee.doll.springboot.demo.serialize;

/**
 * Function:
 *
 * @author HeXin
 * @date 16:55 2019/1/31
 * @since JDK 1.8
 */
public interface Serializer {
	/**
	 * 序列化算法
	 */
	byte getSerializerAlgorithm();

	/**
	 * java 对象转换成二进制
	 */
	byte[] serialize(Object object);

	/**
	 * 二进制转换成 java 对象
	 */
	<T> T deserialize(Class<T> clazz, byte[] bytes);
}
