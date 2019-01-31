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
public class ResponseResult extends ResponseSet {

	public Object data;

	public ResponseResult() {

	}

	public ResponseResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResponseResult(ResponseSet responseSet) {
		this.code = responseSet.code;
		this.msg = responseSet.msg;
	}

	public ResponseResult(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public static ResponseResult success() {
		return new ResponseResult(ReturnCodeEnum._200.code, ReturnCodeEnum._200.msg);
	}

	public static ResponseResult success(Object data) {
		return new ResponseResult(ReturnCodeEnum._200.code, ReturnCodeEnum._200.msg, data);
	}

	public static ResponseResult success(String msg) {
		return new ResponseResult(ReturnCodeEnum._200.code, msg);
	}

	public static ResponseResult success(Object data, String msg) {
		return new ResponseResult(ReturnCodeEnum._200.code, msg, data);
	}

	public static ResponseResult error(int code, String msg) {
		return new ResponseResult(code, msg);
	}

	public static ResponseResult paramError(String msg) {
		return new ResponseResult(ReturnCommonTypeEnum.PARAM_ERROR.code, msg);
	}

	public void setByResponseSet(ResponseSet rs) {
		this.code = rs.code;
		this.msg = rs.msg;
	}

	public static ResponseResult error(ReturnCodeEnum rs) {
		return new ResponseResult(rs.code, rs.msg);
	}

	public static ResponseResult error(ReturnCommonTypeEnum rs) {
		return new ResponseResult(rs.code, rs.msg);
	}

	public static ResponseResult failed() {
		ResponseResult rs = new ResponseResult();
		rs.set(ReturnCommonTypeEnum.FAILED);
		return rs;
	}

	public static ResponseResult failed(String msg) {
		ResponseResult rs = new ResponseResult();
		rs.set(ReturnCommonTypeEnum.FAILED);
		rs.msg = msg;
		return rs;
	}


	public static ResponseResult exception() {
		return new ResponseResult(ReturnCodeEnum._500.code, ReturnCodeEnum._500.msg);
	}

	public static ResponseResult exception(String msg) {
		return new ResponseResult(ReturnCodeEnum._500.code, msg);
	}

	@Override
	public String toString() {
		return "[code:" + this.code + ",msg:" + this.msg + ",data:[" + (this.data != null ? this.data.toString() : null) + "]" + "]";
	}
}
