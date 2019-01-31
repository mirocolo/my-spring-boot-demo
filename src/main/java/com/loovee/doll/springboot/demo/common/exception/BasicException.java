package com.loovee.doll.springboot.demo.common.exception;

import java.io.Serializable;

/**
 * Function:
 *
 * @author HeXin
 * @date 10:02 2019/1/30
 * @since JDK 1.8
 */
public class BasicException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;
	String errorCode;
	String errorMessage;

	public BasicException() {
	}

	public BasicException(String message) {
		super(message);
	}

	public BasicException(Exception oriEx) {
		super(oriEx);
	}

	public BasicException(Exception oriEx, String message) {
		super(message, oriEx);
	}

	public BasicException(Throwable oriEx) {
		super(oriEx);
	}

	public BasicException(String message, Exception oriEx) {
		super(message, oriEx);
	}

	public BasicException(String message, Throwable oriEx) {
		super(message, oriEx);
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
