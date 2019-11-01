package com.atl.church.mms.com.atl.church.mms.exception;

import lombok.Data;

@Data
public class MMSRestException extends Exception {

	private ErrorType errorType;

	public MMSRestException(String message, ErrorType errorType) {
		super(message);
		this.errorType = errorType;
	}
}
