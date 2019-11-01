package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.RestValidator;
import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.dto.AttendanceDTO;
import com.atl.church.mms.com.atl.church.mms.dto.MeetingDTO;
import com.atl.church.mms.com.atl.church.mms.exception.MMSRestException;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import com.atl.church.mms.com.atl.church.mms.factory.AttendaceFactory;
import com.atl.church.mms.com.atl.church.mms.factory.MeetingFactory;
import com.atl.church.mms.com.atl.church.mms.service.MeetingService;
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

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RequestMapping("/meeting")
@Api(value="meeting", description="Operations related to meeting services")
public class MeetingController {

	@Autowired
	private MeetingService meetingService;
	@Autowired
	private AttendaceFactory attendaceFactory;
	@Autowired
	private MeetingFactory meetingFactory;
	@Autowired
	private RestValidator restValidator;

	@ApiOperation(value = "Get a meeting by Id")
	@GetMapping("/{id}")
	 public ResponseEntity<MeetingDTO> getMeeting(@PathVariable @Min(0) Long id) throws MMSServiceException {
		Meeting meeting = meetingService.getMeeting(id);
		return new ResponseEntity<>(meetingFactory.toDto(meeting),HttpStatus.OK);
	}

	@ApiOperation(value = "Create new meeting")
	@PostMapping("/")
	public ResponseEntity<MeetingDTO> createMeeting(@RequestBody @Valid MeetingDTO meetingDTO) throws MMSRestException {
		restValidator.validateForCreate(meetingDTO);
		Meeting meeting = meetingService.createMeeting(meetingFactory.toDomain(meetingDTO));
		return new ResponseEntity<>(meetingFactory.toDto(meeting),HttpStatus.OK);
	}

	@ApiOperation(value = "Send invite to all members")
	@PostMapping("/invite")
	public ResponseEntity<String> inviteMeeting(@RequestBody @Min(0) Long id) throws MMSServiceException {
		meetingService.inviteMeeting(id);
		return new ResponseEntity<>("Meeting invitation sent ",HttpStatus.OK);
	}

	@ApiOperation(value = "Update a meeting info")
	@PutMapping("/")
	public ResponseEntity<MeetingDTO> updateMeeting(@RequestBody @Valid MeetingDTO meetingDTO) throws MMSServiceException {
		Meeting meeting = meetingService.updateMeeting(meetingFactory.toDomain(meetingDTO));
		return new ResponseEntity<>(meetingFactory.toDto(meeting),HttpStatus.OK);
	}

	@ApiOperation(value = "Remove a meeting by Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteMeeting(@PathVariable @Min(0) Long id) throws MMSServiceException {
		return new ResponseEntity<>(meetingService.deleteMeeting(id),HttpStatus.OK);
	}

	@ApiOperation(value = "Add meeting attendance")
	@PostMapping("/attendance/meeting/{meetingId}/family/{familyId}/member/{memberId}")
	public ResponseEntity<String> meetingAttendance(@PathVariable @Min(0) Long meetingId, @PathVariable @Min(0) Long familyId, @PathVariable @Min(0) Long memberId ) throws MMSServiceException {
		meetingService.addMeetingAttendance(meetingId,familyId,memberId);
		return new ResponseEntity<>("Attendance Added ",HttpStatus.OK);
	}

	@ApiOperation(value = "get meeting attendance")
	@GetMapping("/attendanceByMeeting/{id}")
	public ResponseEntity<List<AttendanceDTO>> meetingAttendance(@PathVariable @Min(0) Long id){
		List attendanceList = meetingService.getAttendanceByMeetingId(id);
		return new ResponseEntity<List<AttendanceDTO>>(attendaceFactory.toDtos(attendanceList),HttpStatus.OK);
	}

	@ApiOperation(value = "get Family's attendance")
	@GetMapping("/attendanceByFamily/{id}")
	public ResponseEntity<List<AttendanceDTO>> familyAttendance(@PathVariable Long id){
		List attendanceList = meetingService.getAttendanceByFamilyId(id);
		return new ResponseEntity<List<AttendanceDTO>>(attendaceFactory.toDtos(attendanceList),HttpStatus.OK);
	}
}
