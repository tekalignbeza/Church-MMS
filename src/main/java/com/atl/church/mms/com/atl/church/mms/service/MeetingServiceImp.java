package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.AttendanceRepo;
import com.atl.church.mms.com.atl.church.mms.data.MeetingRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Attendance;
import com.atl.church.mms.com.atl.church.mms.domain.Family;
import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.domain.Email;
import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.exception.ErrorType;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import com.atl.church.mms.com.atl.church.mms.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MeetingServiceImp implements MeetingService {

	@Autowired
	private MeetingRepo meetingRepo;
	@Autowired
	private MemberService memberService;
	@Autowired
	private AttendanceRepo attendanceRepo;
	@Autowired
	private EmailService emailService;
	@Override
	public Meeting getMeeting(Long id) throws MMSServiceException {
		return meetingRepo.findById(id).orElseThrow(()->new MMSServiceException("Meeting not found", ErrorType.NOT_FOUND));
	}

	@Override
	public Meeting createMeeting(Meeting meeting){
		return meetingRepo.save(meeting);
	}

	@Override
	public void inviteMeeting(Long id) throws MMSServiceException {
		try {
			Meeting meeting = getMeeting(id);
			List<Member> members = memberService.getAll(false);
			for(Member member : members){
				Map<String,Object> data = new HashMap<>();
				data.put("fullName", member.getFirstName()+" "+ member.getLastName());
				data.put("file", member.getIdCard());
				data.put("date",meeting.getDateTime().toString());
				data.put("title",meeting.getTitle());
				data.put("address",meeting.getAddress1()+" "+(meeting.getAddress2()==null?"":meeting.getAddress2())+", "+meeting.getCity()+", "+meeting.getState()+", "+meeting.getZipCode());
				data.put("agenda",meeting.getAgenda());
				data.put("cellPhone",meeting.getCellPhone());
				Email email = Email.builder().from("debrebisrat.mms@gmail.com").to(member.getEmail()).subject(meeting.getTitle()).reason(Email.Reason.meeting).data(data).build();
				emailService.sendEmail(email);
			}
		} catch (Exception e) {
			throw new MMSServiceException("Something went wrong "+e.getMessage(),ErrorType.UNKNOW_ISSUE);
		}
	}

	@Override
	public Meeting updateMeeting(Meeting meeting) throws MMSServiceException {
		getMeeting(meeting.getId());
		return meetingRepo.save(meeting);
	}

	@Override
	public boolean deleteMeeting(Long id) throws MMSServiceException {
		 Meeting meeting = getMeeting(id);
		 meetingRepo.delete(meeting);
		 return true;
	}

	@Override
	public void addMeetingAttendance(Long meetingId, Long familyId,Long memberId) throws MMSServiceException {
		Meeting meeting = getMeeting(meetingId);
		Family family = memberService.getFamily(familyId);
		Attendance attendance = Attendance.builder().
				meeting(meeting)
				.family(family)
				.memberId(memberId)
				.build();
		attendanceRepo.save(attendance);
	}

	@Override
	public List<Attendance> getAttendanceByMeetingId(Long id) {
		return attendanceRepo.findByMeetingId(id);
	}

	@Override
	public List<Attendance> getAttendanceByFamilyId(Long id) {
		return attendanceRepo.findByFamilyId(id);
	}
}
