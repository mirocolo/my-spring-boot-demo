package com.loovee.doll.springboot.demo.common.enums;

/**
 * Function:
 *
 * @author HeXin
 * @date 10:02 2019/1/30
 * @since JDK 1.8
 */
public enum ReturnCommonTypeEnum {

	PARAM_ERROR(-8,"params error"),
	ERROR(-9,"error"),
	WARN(-1,"warn"),
	FAILED(0,"failed"),
	SUCCESS(200,"success"),
	;
	public int  code;
	
	public String msg;
	
	ReturnCommonTypeEnum(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	 public static ReturnCommonTypeEnum find(int controller){
	        for(ReturnCommonTypeEnum enumObj : ReturnCommonTypeEnum.values()){
	            if(enumObj.code == controller){
	                return enumObj;
	            }
	        }
	        return null;
	 }
	public static void main(String[] args)
    {
	    String s = ReturnCommonTypeEnum.find(-1).msg;
	    System.out.println(s);
    }
}
