package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.service.MemberService;
import com.atl.church.mms.com.atl.church.mms.utils.ImageStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	ImageStorage imageStorage;
	@Autowired
	MemberService memberService;
	@Autowired
	private MemberFactory memberFactory;

	@GetMapping("/{id}")
	MemberDTO getMember(Long id){
		return memberFactory.toDto(memberService.getMember(id));
	}

	@PostMapping("/")
	MemberDTO createMember(MemberDTO member){
		return null;
	}

	@PutMapping("/")
	MemberDTO updateMember(MemberDTO member){
		return null;
	}

	@DeleteMapping("/")
	boolean deleteMember(Long id){
		return true;
	}

	@PostMapping("/upload") // //new annotation since 4.3
	public String singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("memberId") String memberId) throws Exception {

		if (file.isEmpty()) {
			return "Please select a file to upload";
		}
		byte[] bytes = file.getBytes();
		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage bufferedImage = ImageIO.read(in);
		String extension = file.getOriginalFilename().split("\\.")[1];
		File tempFile = imageStorage.save(bufferedImage,ImageStorage.RESOURCE_LOCATION_TEMP+"/"+memberId+"."+extension);
		memberService.upload(tempFile,memberId);
		return "You successfully uploaded '" + file.getOriginalFilename()+" '";
	}
}
