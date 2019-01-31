package com.loovee.doll.springboot.demo.netty.protocol;

import com.loovee.doll.springboot.demo.serialize.Serializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Function:
 *
 * @author HeXin
 * @date 17:24 2019/1/31
 * @since JDK 1.8
 */
public class PacketCodeC {

	public static PacketCodeC getInstance() {
		return SingleHolder.INSTANCE;
	}

	private static final int MAGIC_NUMBER = 0x12345678;
	private final Map<Byte, Class<? extends Packet>> packetTypeMap;
	private final Map<Byte, Serializer> serializerMap;

	private PacketCodeC(){
		packetTypeMap = new HashMap<>();
		serializerMap = new HashMap<>();
	}

	private static class SingleHolder{
		private static final PacketCodeC INSTANCE;
		static {
			INSTANCE = new PacketCodeC();
		}
	}
}
