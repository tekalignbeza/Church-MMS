package com.atl.church.mms.com.atl.church.mms.domain;

import com.atl.church.mms.com.atl.church.mms.utils.EmailService;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Email {

	Reason reason;
	private String from;
	private String to;
	private String subject;
	private Map<String,Object> data;

	public enum Reason {
		registration,meeting
	}
}

