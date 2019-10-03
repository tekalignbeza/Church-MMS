package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.AttendanceRepo;
import com.atl.church.mms.com.atl.church.mms.data.MeetingRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Attendance;
import com.atl.church.mms.com.atl.church.mms.domain.Member2;
import com.atl.church.mms.com.atl.church.mms.domain.Email;
import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public Meeting getMeeting(Long id){
		Optional<Meeting> meeting = meetingRepo.findById(id);
		return meeting.get();
	}

	@Override
	public Meeting createMeeting(Meeting meeting){
		return meetingRepo.save(meeting);
	}

	@Override
	public void inviteMeeting(Long id) {
		try {
			Meeting meeting = getMeeting(id);
			List<Member2> member2s = memberService.getAll(false);
			for(Member2 member2 : member2s){
				Map<String,Object> data = new HashMap<>();
				data.put("fullName", member2.getFirstName()+" "+ member2.getLastName());
				data.put("file", member2.getIdCard());
				data.put("date",meeting.getDateTime().toString());
				data.put("title",meeting.getTitle());
				data.put("address",meeting.getAddress1()+" "+(meeting.getAddress2()==null?"":meeting.getAddress2())+", "+meeting.getCity()+", "+meeting.getState()+", "+meeting.getZipCode());
				data.put("agenda",meeting.getAgenda());
				data.put("cellPhone",meeting.getCellPhone());
				Email email = Email.builder().from("debrebisrat.mms@gmail.com").to(member2.getEmail()).subject(meeting.getTitle()).reason(Email.Reason.meeting).data(data).build();
				emailService.sendEmail(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Meeting updateMeeting(Meeting meeting){
		return meetingRepo.save(meeting);
	}

	@Override
	public boolean deleteMeeting(Long id){
		 meetingRepo.deleteById(id);
		 return true;
	}

	@Override
	public void meetingAttendance(Attendance attendance) {
		attendanceRepo.save(attendance);
	}

	@Override
	public List<Member2> getAttendanceByMeetingId(Long id) {
		return attendanceRepo.findByMeetingId(id).stream().map(r->r.getMember2()).collect(Collectors.toList());
	}

}
