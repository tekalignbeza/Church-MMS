package com.atl.church.mms.com.atl.church.mms.service;
import com.atl.church.mms.com.atl.church.mms.data.MemberRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.domain.MemberSearchCriteria;
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
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberRepo memberRepo;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private ImageStorage imageStorage;
	@Autowired
	private EmailService emailService;

	@Override
	public Member getMember(Long id){
		Optional<Member> member = memberRepo.findById(id);
		return member.get();
	}

	@Override
	public Member createMember(Member member){
		return memberRepo.save(member);
	}

	@Override
	public Member updateMember(Member member){
		return memberRepo.save(member);
	}

	@Override
	public boolean deleteMember(Long id){
		 memberRepo.deleteById(id);
		 return true;
	}

	@Override
	public List<Member> search(MemberSearchCriteria searchCriteria) {
		return memberRepo.findByFirstName(searchCriteria.getFirstName());
	}

	@Override
	public boolean sendEmail(Long id,EmailService.Types types) throws Exception {
		emailService.sendEmail(getMember(id),types);
		return true;
	}

	@Override
	public File upload(File tempFile, Long id) {
		try {
			Member member =  getMember(id);
			BufferedImage bufferedIdCard = uploadService.GenerateIdCard(tempFile,member);
			File file = imageStorage.save(bufferedIdCard,ImageStorage.RESOURCE_LOCATION_IDCARD+"/"+member.getId()+".jpg");
			member.setIdCard(file.getAbsolutePath());
			updateMember(member);
			return file;
			//todo asyc call send email.
		}catch (Exception e){

		}
		return null;
	}

	@Override
	public InputStream getMemberIdCard(String id) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Member member = getMember(Long.parseLong(id));
		BufferedImage idCard  = ImageIO.read(imageStorage.get(member.getIdCard()));
		ImageIO.write(idCard, "jpg", outputStream);
		InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		return inputStream;
	}

}
