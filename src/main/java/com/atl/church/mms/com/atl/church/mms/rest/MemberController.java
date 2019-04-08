package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.domain.MemberSearchCriteria;
import com.atl.church.mms.com.atl.church.mms.service.MemberService;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/member")
@Api(value="member", description="Operations related to member services")
public class MemberController {

	@Autowired
	ImageStorage imageStorage;
	@Autowired
	MemberService memberService;
	@Autowired
	private MemberFactory memberFactory;

	@ApiOperation(value = "Get a member by Id or barCode")
	@GetMapping("/{id}")
	 ResponseEntity<MemberDTO> getMember(@PathVariable String id){
		Member member = memberService.getMember(Long.parseLong(id));
		return new ResponseEntity<MemberDTO>(memberFactory.toDto(member),HttpStatus.OK);
	}

	@ApiOperation(value = "Create new member the photo should be uploaded after the member is created")
	@PostMapping("/")
	ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO){
		Member member = memberService.createMember(memberFactory.toDomain(memberDTO));
		return new ResponseEntity<MemberDTO>(memberFactory.toDto(member),HttpStatus.OK);
	}

	@ApiOperation(value = "Update a member info")
	@PutMapping("/")
	ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO memberDTO){
		Member member = memberService.updateMember(memberFactory.toDomain(memberDTO));
		return new ResponseEntity<MemberDTO>(memberFactory.toDto(member),HttpStatus.OK);
	}

	@ApiOperation(value = "Remove a member by Id or barCode")
	@DeleteMapping("/{id}")
	ResponseEntity<Boolean> deleteMember(@PathVariable String id){
		return new ResponseEntity<Boolean>(memberService.deleteMember(Long.parseLong(id)),HttpStatus.OK);
	}

	@ApiOperation(value = "Search members by different criteria")
	@PostMapping("/search")
	ResponseEntity<List<MemberDTO>> searchMember(@RequestBody MemberSearchCriteria searchCriteria){
		List<Member> members = memberService.search(searchCriteria);
		return new ResponseEntity<List<MemberDTO>> (memberFactory.toDtos(members),HttpStatus.OK);
	}

	@ApiOperation(produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,value = "Upload a member photo as Multi part File with Request parameter memberId")
	@PostMapping(value = "/upload", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Resource> singleFileUpload(
			@ApiParam(name = "file", value = "Select the file to Upload", required = true)
			@RequestPart("file") MultipartFile file,
			@RequestParam("memberId") Long memberId) throws Exception {
		if (file.isEmpty()) {
			//return new ResponseEntity<String>("Please select a file to upload", HttpStatus.BAD_REQUEST);
		}
		File tempFile = imageStorage.store(file,memberId+"");
		File uploaded = memberService.upload(tempFile,memberId);
		Resource resource =  new FileSystemResource(uploaded);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
		}
}
