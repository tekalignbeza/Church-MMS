package com.atl.church.mms.com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.exception.MMSRestException;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// error handle for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers,
																  HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		return new ResponseEntity<>(buildResponseBody(errors), headers, status);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public void constraintViolationException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(MMSRestException.class)
	public void mmsRestException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(MMSServiceException.class)
	public ResponseEntity<Object> mmsServiceException(MMSServiceException ex) {
		HttpStatus status;
		switch (ex.getErrorType()){
			case NOT_FOUND:
				status = HttpStatus.NOT_FOUND;
				break;
			case UNKNOW_ISSUE:
				status = HttpStatus.NO_CONTENT;
				break;
			default:
				status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(ex.getMessage(),status);
	}


	private Map<String, Object> buildResponseBody(List<String> errors){
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("errors", errors);
		return body;
	}
}