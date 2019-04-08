package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.service.MeetingService;
import com.atl.church.mms.com.atl.church.mms.utils.ImageStorage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/meeting")
@Api(value="meeting", description="Operations related to meeting services")
public class MeetingController {

	@Autowired
	ImageStorage imageStorage;
	@Autowired
	MeetingService meetingService;
	@Autowired
	private MeetingFactory meetingFactory;

	@ApiOperation(value = "Get a meeting by Id or barCode")
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

	@ApiOperation(value = "Create new meeting")
	@PostMapping("/invite")
	ResponseEntity<String> inviteMeeting(@RequestBody MeetingDTO meetingDTO){
		meetingService.createMeeting(meetingFactory.toDomain(meetingDTO));
		return new ResponseEntity<String>("Meeting invitation sent ",HttpStatus.OK);
	}

	@ApiOperation(value = "Update a meeting info")
	@PutMapping("/")
	ResponseEntity<MeetingDTO> updateMeeting(@RequestBody MeetingDTO meetingDTO){
		Meeting meeting = meetingService.updateMeeting(meetingFactory.toDomain(meetingDTO));
		return new ResponseEntity<MeetingDTO>(meetingFactory.toDto(meeting),HttpStatus.OK);
	}

	@ApiOperation(value = "Remove a meeting by Id or barCode")
	@DeleteMapping("/{id}")
	ResponseEntity<Boolean> deleteMeeting(@PathVariable String id){
		return new ResponseEntity<Boolean>(meetingService.deleteMeeting(Long.parseLong(id)),HttpStatus.OK);
	}

}
