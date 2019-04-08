package com.atl.church.mms.com.atl.church.mms.service;
import com.atl.church.mms.com.atl.church.mms.data.MeetingRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Meeting;
import com.atl.church.mms.com.atl.church.mms.utils.EmailService;
import com.atl.church.mms.com.atl.church.mms.utils.ImageStorage;
import com.atl.church.mms.com.atl.church.mms.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeetingServiceImp implements MeetingService {

	@Autowired
	private MeetingRepo meetingRepo;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private ImageStorage imageStorage;
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
	public void inviteMeeting(Meeting meeting) {
		try {
			emailService.sendEmail(meeting,EmailService.Types.meeting);
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
	public boolean sendEmail(Long id,EmailService.Types types) throws Exception {
		emailService.sendEmail(getMeeting(id),types);
		return true;
	}


}
