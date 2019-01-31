package com.loovee.doll.springboot.demo.netty.protocol.command;

/**
 * Function:
 *
 * @author HeXin
 * @date 17:02 2019/1/31
 * @since JDK 1.8
 */
public interface Command {

	Byte PING = 1;
	Byte PONG = 2;
}
