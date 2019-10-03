package com.atl.church.mms.com.atl.church.mms.utils;

import com.atl.church.mms.com.atl.church.mms.domain.Email;

public interface EmailService {

	void sendEmail(Email email) throws Exception;
}
