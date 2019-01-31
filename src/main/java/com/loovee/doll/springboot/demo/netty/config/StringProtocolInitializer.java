package com.loovee.doll.springboot.demo.netty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Function:
 *
 * @author HeXin
 * @date 15:44 2019/1/31
 * @since JDK 1.8
 */
@Component
@Qualifier("springProtocolInitializer")
public class StringProtocolInitializer extends ChannelInitializer<SocketChannel> {

	@Autowired
	StringDecoder stringDecoder;

	@Autowired
	StringEncoder stringEncoder;



	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("decoder", stringDecoder);
		pipeline.addLast("encoder", stringEncoder);

	}

	public StringDecoder getStringDecoder() {
		return stringDecoder;
	}

	public void setStringDecoder(StringDecoder stringDecoder) {
		this.stringDecoder = stringDecoder;
	}

	public StringEncoder getStringEncoder() {
		return stringEncoder;
	}

	public void setStringEncoder(StringEncoder stringEncoder) {
		this.stringEncoder = stringEncoder;
	}

}
