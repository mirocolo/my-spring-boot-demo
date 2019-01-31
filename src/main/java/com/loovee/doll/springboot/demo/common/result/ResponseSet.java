package com.loovee.doll.springboot.demo.common.result;

import com.loovee.doll.springboot.demo.common.enums.ReturnCodeEnum;
import com.loovee.doll.springboot.demo.common.enums.ReturnCommonTypeEnum;


/**
 * Function:
 *
 * @author HeXin
 * @date 10:02 2019/1/30
 * @since JDK 1.8
 */
public class ResponseSet {

	public int code;

	public String msg;

	public ResponseSet(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseSet() {
		super();
	}

	public ResponseSet(ReturnCodeEnum returnCodeEnum) {
		this.code = returnCodeEnum.code;
		this.msg = returnCodeEnum.msg;
	}

	public ResponseSet(ReturnCommonTypeEnum returnCommonTypeEnum) {
		this.code = returnCommonTypeEnum.code;
		this.msg = returnCommonTypeEnum.msg;
	}

	public ResponseSet set(ReturnCommonTypeEnum ReturnCommonTypeEnum) {
		this.code = ReturnCommonTypeEnum.code;
		this.msg = ReturnCommonTypeEnum.msg;
		return this;
	}

	public ResponseSet set(ReturnCommonTypeEnum ReturnCommonTypeEnum, String msg) {
		this.code = ReturnCommonTypeEnum.code;
		this.msg = msg;
		return this;
	}

	public ResponseSet set(ReturnCodeEnum ReturnCodeEnum) {
		this.code = ReturnCodeEnum.code;
		this.msg = ReturnCodeEnum.msg;
		return this;
	}

	public ResponseSet set(ReturnCodeEnum ReturnCodeEnum, String msg) {
		this.code = ReturnCodeEnum.code;
		this.msg = msg;
		return this;
	}

	public ResponseSet withError(Throwable e) {
		this.code = ReturnCodeEnum._500.code;
		this.msg = e.getMessage();
		return this;
	}

	public ResponseSet withError(ReturnCodeEnum ReturnCodeEnum, Exception e) {
		this.code = ReturnCodeEnum.code;
		this.msg = e.getMessage();
		return this;
	}

	public ResponseSet withError(int errorCode, Exception e) {
		this.code = errorCode;
		this.msg = e.getMessage();
		return this;
	}

	public ResponseSet withSuccess() {
		this.code = ReturnCodeEnum._200.code;
		this.msg = ReturnCodeEnum._200.msg;
		return this;
	}

	public ResponseSet withSuccess(String message) {
		this.code = ReturnCodeEnum._200.code;
		this.msg = message;
		return this;
	}

	public static ResponseSet success() {
		return new ResponseSet(ReturnCodeEnum._200.code, ReturnCodeEnum._200.msg);
	}

	public static ResponseSet success(String msg) {
		return new ResponseSet(ReturnCodeEnum._200.code, msg);
	}

	public static ResponseSet exception() {
		return new ResponseSet(ReturnCodeEnum._500);
	}

	public static ResponseSet error(int code, String msg) {
		return new ResponseSet(code, msg);
	}

	public static ResponseSet error(ReturnCodeEnum rs) {
		return new ResponseSet(rs.code, rs.msg);
	}

	public static ResponseSet error(ReturnCommonTypeEnum rs) {
		return new ResponseSet(rs.code, rs.msg);
	}

	public static ResponseSet paramError() {
		return new ResponseSet(ReturnCommonTypeEnum.PARAM_ERROR.code, ReturnCommonTypeEnum.PARAM_ERROR.msg);
	}

	public static ResponseSet paramError(String msg) {
		return new ResponseSet(ReturnCommonTypeEnum.PARAM_ERROR.code, msg);
	}

	public static ResponseSet failed() {
		ResponseSet rs = new ResponseSet();
		rs.set(ReturnCommonTypeEnum.FAILED);
		return rs;
	}

	@Override
	public String toString() {
		return "[code:" + this.code + ",msg:" + this.msg + "]";
	}

}
