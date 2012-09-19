package com.ice.rest;

import com.ice.rest.ErrorMessage;

public class RequestInvalidException extends RuntimeException {
	private String msg;
	private ErrorMessage errMsg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	private void setErrorMessage(String msg, String code, String category) {
		errMsg.setCategory(category);
		errMsg.setCode(code);
		errMsg.setId("400");
		errMsg.setMessage(msg);
	}
		
	private void setErrorMessage() {
		errMsg.setCategory("system");
		errMsg.setCode("9406");
		errMsg.setId("400");
		errMsg.setMessage("파라미터가 잘못 되었습니다.");
	}
	
	public ErrorMessage getErrorMessage() {
		return errMsg;
	}
	
	public RequestInvalidException (String msg, String code, String category) {
		setErrorMessage(msg,code,category);
	}
	public RequestInvalidException () {
		setErrorMessage();
	}
}
