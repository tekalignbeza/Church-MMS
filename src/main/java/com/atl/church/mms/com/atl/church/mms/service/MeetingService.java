package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Attendance;
import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;

import java.util.List;


public interface MeetingService {

	Meeting getMeeting(Long id) throws MMSServiceException;

	Meeting createMeeting(Meeting meeting);

	void inviteMeeting(Long id) throws MMSServiceException;

	Meeting updateMeeting(Meeting meeting) throws MMSServiceException;

	boolean deleteMeeting(Long id) throws MMSServiceException;

	void addMeetingAttendance(Long meetingId, Long familyId,Long memberId) throws MMSServiceException;

	List<Attendance> getAttendanceByMeetingId(Long id);

	List<Attendance> getAttendanceByFamilyId(Long id);
}
