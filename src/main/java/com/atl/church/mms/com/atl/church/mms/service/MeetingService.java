package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.utils.EmailService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface MeetingService {

	Meeting getMeeting(Long id);

	Meeting createMeeting(Meeting meeting);

	void inviteMeeting(Meeting meeting);

	Meeting updateMeeting(Meeting meeting);

	boolean deleteMeeting(Long id);

	boolean sendEmail(Long id, EmailService.Types types) throws Exception;

}
