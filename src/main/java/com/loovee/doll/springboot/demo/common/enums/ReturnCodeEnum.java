package com.loovee.doll.springboot.demo.common.enums;

/**
 * Function:
 *
 * @author HeXin
 * @date 10:02 2019/1/30
 * @since JDK 1.8
 */
public enum ReturnCodeEnum {
	
	_200(200,"成功"),
	_400(400,"业务簽名錯誤"),
	_403(403,"操作过于频繁"),
	/**
	 * 非法ip
	 */
	_401(401, "非法ip"),
	_500(500,"业务代码错误"),
	_501(501,"业务代码异常"),
	;

	
	public String msg;
	
	public int code;
	
	ReturnCodeEnum(int code,String msg){
		this.msg=msg;
		this.code=code;
	}
}
