package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Attendance;
import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.domain.Member2;

import java.util.List;


public interface MeetingService {

	Meeting getMeeting(Long id);

	Meeting createMeeting(Meeting meeting);

	void inviteMeeting(Long id);

	Meeting updateMeeting(Meeting meeting);

	boolean deleteMeeting(Long id);

	void meetingAttendance(Attendance attendance);

	List<Member2> getAttendanceByMeetingId(Long id);
}
