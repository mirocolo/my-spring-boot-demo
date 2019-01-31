package com.loovee.doll.springboot.demo.netty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Function:
 *
 * @author HeXin
 * @date 10:41 2019/1/31
 * @since JDK 1.8
 */
@Configuration
public class NettyConfig {
	/**
	 * 端口
	 */
	@Value("${netty.config.port}")
	private int tcpPort;

	@Value("${netty.config.boss.thread.count}")
	private int bossCount;

	@Value("${netty.config.worker.thread.count}")
	private int workerCount;

	@Value("${netty.config.so.keepalive}")
	private boolean keepAlive;

	@Value("${netty.config.so.backlog}")
	private int backlog;

	@Value("${netty.config.so.nodelay}")
	private boolean noDelay;

	@Autowired
	@Qualifier("springProtocolInitializer")
	private StringProtocolInitializer protocolInitializer;

	/**
	 * bootstrap配置
	 */
	@SuppressWarnings("unchecked")
	@Bean(name = "serverBootstrap")
	public ServerBootstrap bootstrap() {
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup(), workerGroup())
				.channel(NioServerSocketChannel.class)
				.handler(loggingHandler())
				.childHandler(protocolInitializer);
		Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();
		Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
		for (@SuppressWarnings("rawtypes")
				ChannelOption option : keySet) {
			b.option(option, tcpChannelOptions.get(option));
		}
		return b;
	}

	@Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup bossGroup() {
		return new NioEventLoopGroup(bossCount);
	}

	@Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup workerGroup() {
		return new NioEventLoopGroup(workerCount);
	}

	@Bean(name = "tcpSocketAddress")
	public InetSocketAddress tcpSocketAddress() {
		return new InetSocketAddress(tcpPort);
	}

	@Bean(name = "tcpChannelOptions")
	public Map<ChannelOption<?>, Object> tcpChannelOptions() {
		Map<ChannelOption<?>, Object> options = new HashMap<>(16);
		options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
		options.put(ChannelOption.SO_BACKLOG, backlog);
		options.put(ChannelOption.TCP_NODELAY, noDelay);
		return options;
	}

	@Bean(name = "loggingHandler")
	public LoggingHandler loggingHandler(){
		return new LoggingHandler(LogLevel.INFO);
	}

	@Bean(name = "stringEncoder")
	public StringEncoder stringEncoder() {
		return new StringEncoder();
	}

	@Bean(name = "stringDecoder")
	public StringDecoder stringDecoder() {
		return new StringDecoder();
	}

}
