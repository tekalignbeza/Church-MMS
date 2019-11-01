package com.atl.church.mms.com.atl.church.mms.exception;

public enum  ErrorType {

	NOT_FOUND(1,"Not found"),
	UNKNOW_ISSUE(2,"Unknown"),
	INVALID_INPUT(3,"Invalid Input");
	private int errorCode;
	private String errorString;
	ErrorType(int errorCode, String errorString) {

		this.errorCode = errorCode;
		this.errorString = errorString;
	}
}
