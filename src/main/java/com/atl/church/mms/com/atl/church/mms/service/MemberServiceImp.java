package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.FamilyRepo;
import com.atl.church.mms.com.atl.church.mms.data.MemberRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Member2;
import com.atl.church.mms.com.atl.church.mms.domain.Email;
import com.atl.church.mms.com.atl.church.mms.domain.Family;
import com.atl.church.mms.com.atl.church.mms.domain.MemberSearchCriteria;
import com.atl.church.mms.com.atl.church.mms.utils.EmailService;
import com.atl.church.mms.com.atl.church.mms.utils.ImageStorage;
import com.atl.church.mms.com.atl.church.mms.utils.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberRepo memberRepo;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private ImageStorage imageStorage;
	@Autowired
	private EmailService emailService;
	@Autowired
	FamilyRepo familyRepo;

	@Override
	public Family getFamily(Long id) {
		Optional<Family> family = familyRepo.findById(id);
		return family.get();
	}

	@Override
	public Family createFamily(Family family) {
		return familyRepo.save(family);
	}

	@Override
	public Family addMemberToFamily(Member2 member2) {
		Family family = member2.getFamily();
		family.getMember2List().add(member2);
		memberRepo.save(member2);
		return familyRepo.save(family);
	}

	@Override
	public Family deleteMemberFromFamily(Long memberId) {
		Member2 member2 = getMember(memberId);
		Family family = member2.getFamily();
		family.getMember2List().remove(member2);
		family = familyRepo.save(family);
		deleteMember(memberId);
		return family;
	}

	@Override
	public Member2 getMember(Long id) {
		Optional<Member2> member = memberRepo.findById(id);
		return member.get();
	}

	@Override
	public Member2 createMember(Member2 member2) {
		return memberRepo.save(member2);
	}

	@Override
	public Member2 updateMember(Member2 member2) {
		return memberRepo.save(member2);
	}

	@Override
	public boolean deleteMember(Long id) {
		memberRepo.deleteById(id);
		return true;
	}

	@Override
	public List<Member2> search(MemberSearchCriteria searchCriteria) {
		//keep it simple for now.  just search by one field only
		if (searchCriteria.getFirstName() != null) {
			return memberRepo.findByFirstName(searchCriteria.getFirstName());
		}
		if (searchCriteria.getMiddleName() != null) {
			return memberRepo.findByMiddleName(searchCriteria.getMiddleName());
		}
		if (searchCriteria.getLastName() != null) {
			return memberRepo.findByLastName(searchCriteria.getLastName());
		}
		if (searchCriteria.getEmail() != null) {
			return memberRepo.findByEmail(searchCriteria.getEmail());
		}
		if (searchCriteria.getCellPhone() != null) {
			return memberRepo.findByCellPhone(searchCriteria.getCellPhone());
		}
		return new ArrayList<>();
	}

	@Override
	public List<Member2> getAll(boolean includeInactive) {
		return memberRepo.findAll();
	}

	private void sendRegistrationEmail(long id,File file) throws Exception {
		Member2 member2 = getMember(id);
		Map<String,Object> data = new HashMap<>();
		data.put("fullName", member2.getFirstName()+" "+ member2.getLastName());
		data.put("file",file.getPath());
		Email email = Email.builder().from("debrebisrat.mms@gmail.com").to(member2.getEmail()).subject("Online Account Confirmation").data(data).reason(Email.Reason.registration).build();
		sendEmail(email);
	}

	@Override
	public boolean sendEmail(Email email) throws Exception {
		emailService.sendEmail(email);
		return true;
	}

	@Override
	@Async
	public File upload(File tempFile, Long id) {
		try {
			Member2 member2 = getMember(id);
			BufferedImage bufferedIdCard = uploadService.GenerateIdCard(tempFile, member2);
			File file = imageStorage.save(bufferedIdCard, ImageStorage.RESOURCE_LOCATION_IDCARD + "/" + member2.getId() + ".jpg");
			member2.setIdCard(file.getPath());
			updateMember(member2);
			sendRegistrationEmail(id,file);
			return file;
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public InputStream getMemberIdCard(String id) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Member2 member2 = getMember(Long.parseLong(id));
		BufferedImage idCard = ImageIO.read(imageStorage.get(member2.getIdCard()));
		ImageIO.write(idCard, "jpg", outputStream);
		InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		return inputStream;
	}

}