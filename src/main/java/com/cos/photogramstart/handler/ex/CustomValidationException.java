package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException{

	//  객체를 구분할 경우에!
	private static final long serialVersionUID = 1L;

	private Map<String,String> errorMap;
	
	public CustomValidationException(String message, Map<String,String> errorMap) {
		super(message); // 내가 들고 있는게 아니라 부모에게 throws
		this.errorMap = errorMap;
	}
	
	public Map<String,String>getErrorMap(){
		return errorMap;
	}
}
