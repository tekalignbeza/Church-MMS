package com.atl.church.mms.com.atl.church.mms.utils;

import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.domain.Member;

public interface EmailService {
	void sendEmail(Member member ,Types types) throws Exception;

	void sendEmail(Meeting member , Types types) throws Exception;

	enum Types {
		registration,meeting
	}
}
