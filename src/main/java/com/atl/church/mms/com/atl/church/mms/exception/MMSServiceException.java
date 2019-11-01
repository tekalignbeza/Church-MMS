package com.atl.church.mms.com.atl.church.mms.exception;

import lombok.Data;

@Data
public class MMSServiceException extends Exception {

	private ErrorType errorType;

	public MMSServiceException(String message, ErrorType errorType) {
		super(message);
		this.errorType = errorType;
	}
}
