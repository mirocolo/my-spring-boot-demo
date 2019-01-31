package com.loovee.doll.springboot.demo.serialize;

/**
 * Function:
 *
 * @author HeXin
 * @date 16:53 2019/1/31
 * @since JDK 1.8
 */
public interface SerializerAlgorithm {

	/**
	 * json 序列化
	 */
	byte JSON = 1;

	/**
	 * PROTO_STUFF 根据PROTO_BUFFER改进而来
	 */
	byte PROTO_STUFF = 2;
}
