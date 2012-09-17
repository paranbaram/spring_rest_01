package com.ice.rest;

import com.ice.rest.ErrorMessage;

public class ResourceNotFoundException extends RuntimeException {
	private String msg;
	private ErrorMessage errMsg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	private void setErrorMessage() {
		errMsg.setCategory("system");
		errMsg.setCode("9402");
		errMsg.setId("404");
		errMsg.setMessage("요청한 URI를 찾을 수 없습니다.");
	}
	
	public ErrorMessage getErrorMessage() {
		return errMsg;
	}
	
	public ResourceNotFoundException (String msg) {
		this.msg = msg;
	}
	public ResourceNotFoundException () {
		setErrorMessage();
	}
}
