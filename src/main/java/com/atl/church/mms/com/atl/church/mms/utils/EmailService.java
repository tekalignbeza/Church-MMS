package com.atl.church.mms.com.atl.church.mms.utils;

import com.atl.church.mms.com.atl.church.mms.domain.Email;
import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.domain.Member;

public interface EmailService {

	void sendEmail(Email email) throws Exception;
}
