package com.loovee.doll.springboot.demo.bean.po;

import lombok.Data;

/**
 * Function:
 *
 * @author HeXin
 * @date 10:02 2019/1/30
 * @since JDK 1.8
 */
@Data
public class User {
	private Long id;
	private String avatar;
	private String nick;
	private String amount;
	private Long inviteCode;
	private Long createTime;
}
