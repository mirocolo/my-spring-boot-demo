package com.loovee.doll.springboot.demo.common.exception;

/**
 * Function:
 *
 * @author HeXin
 * @date 15:00 2019/1/30
 * @since JDK 1.8
 */
public class GenericException extends BasicException {

	GenericException(String message){
		super(message);
	}

	public static GenericException getNullPointException(){
		return new GenericException("空指针异常");
	}
}
