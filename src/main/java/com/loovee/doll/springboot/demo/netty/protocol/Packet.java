package com.loovee.doll.springboot.demo.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;

import io.protostuff.Exclude;
import lombok.Data;

/**
 * Function:
 *
 * @author HeXin
 * @date 17:01 2019/1/31
 * @since JDK 1.8
 */
@Data
public abstract class Packet {
	/**
	 * 协议版本
	 */
	@Exclude
	@JSONField(deserialize = false, serialize = false)
	private Byte version = 1;

	/**
	 * 指令
	 */
	@JSONField(serialize = false)
	public abstract Byte getCommand();
}
