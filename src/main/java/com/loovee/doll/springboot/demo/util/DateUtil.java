package com.loovee.doll.springboot.demo.util;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Function:
 *
 * @author HeXin
 * @date 17:38 2019/1/30
 * @since JDK 1.8
 */
public class DateUtil {
	/**
	 * 得到当前时间的秒数
	 */
	public static long nowToSeconds() {
		Instant instant = Instant.now();
		return TimeUnit.MILLISECONDS.toSeconds(instant.toEpochMilli());
	}

	/**
	 * 得到当前时间的毫秒数
	 */
	public static long nowToMillisecond() {
		Instant instant = Instant.now();
		return instant.toEpochMilli();
	}
}
