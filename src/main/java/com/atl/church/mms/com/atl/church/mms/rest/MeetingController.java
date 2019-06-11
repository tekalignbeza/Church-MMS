package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.domain.Attendance;
import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.service.MeetingService;
import com.atl.church.mms.com.atl.church.mms.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/meeting")
@Api(value="meeting", description="Operations related to meeting services")
public class MeetingController {

	@Autowired
	private MeetingService meetingService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MeetingFactory meetingFactory;
	@Autowired
	private MemberFactory memberFactory;

	@ApiOperation(value = "Get a meeting by Id")
	@GetMapping("/{id}")
	 ResponseEntity<MeetingDTO> getMeeting(@PathVariable String id){
		Meeting meeting = meetingService.getMeeting(Long.parseLong(id));
		return new ResponseEntity<MeetingDTO>(meetingFactory.toDto(meeting),HttpStatus.OK);
	}

	@ApiOperation(value = "Create new meeting")
	@PostMapping("/")
	ResponseEntity<MeetingDTO> createMeeting(@RequestBody MeetingDTO meetingDTO){
		Meeting meeting = meetingService.createMeeting(meetingFactory.toDomain(meetingDTO));
		return new ResponseEntity<MeetingDTO>(meetingFactory.toDto(meeting),HttpStatus.OK);
	}

	@ApiOperation(value = "Send invite to all members")
	@PostMapping("/invite")
	ResponseEntity<String> inviteMeeting(@RequestBody long id){
		meetingService.inviteMeeting(id);
		return new ResponseEntity<String>("Meeting invitation sent ",HttpStatus.OK);
	}

	@ApiOperation(value = "Update a meeting info")
	@PutMapping("/")
	ResponseEntity<MeetingDTO> updateMeeting(@RequestBody MeetingDTO meetingDTO){
		Meeting meeting = meetingService.updateMeeting(meetingFactory.toDomain(meetingDTO));
		return new ResponseEntity<MeetingDTO>(meetingFactory.toDto(meeting),HttpStatus.OK);
	}

	@ApiOperation(value = "Remove a meeting by Id")
	@DeleteMapping("/{id}")
	ResponseEntity<Boolean> deleteMeeting(@PathVariable String id){
		return new ResponseEntity<Boolean>(meetingService.deleteMeeting(Long.parseLong(id)),HttpStatus.OK);
	}

	@ApiOperation(value = "Add meeting attendance")
	@PostMapping("/attendance")
	ResponseEntity<String> meetingAttendance(@RequestBody AttendanceDTO attendanceDTO){
		Member member = memberService.getMember(attendanceDTO.getMemberId());
		Meeting meeting = meetingService.getMeeting(attendanceDTO.getMeetingId());
		Attendance attendance = Attendance.builder().meeting(meeting).member(member).build();
		meetingService.meetingAttendance(attendance);
		return new ResponseEntity<String>("Meeting invitation sent ",HttpStatus.OK);
	}

	@ApiOperation(value = "get meeting attendance")
	@GetMapping("/attendance/{id}")
	ResponseEntity<List<MemberDTO>> meetingAttendance(@PathVariable Long id){
		List members = meetingService.getAttendanceByMeetingId(id);
		return new ResponseEntity<List<MemberDTO>>(memberFactory.toDtos(members),HttpStatus.OK);
	}
}
