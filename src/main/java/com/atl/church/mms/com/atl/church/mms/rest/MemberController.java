package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.domain.Member2;
import com.atl.church.mms.com.atl.church.mms.domain.Family;
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

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/member")
@Api(value="member2", description="Operations related to member2 services")
public class MemberController {

	@Autowired
	ImageStorage imageStorage;
	@Autowired
	MemberService memberService;
	@Autowired
	private MemberFactory memberFactory;
    @Autowired
	private FamilyFactory familyFactory;

	@ApiOperation(value = "Get a Family by Id or barCode")
	@GetMapping("/family/{id}")
	ResponseEntity<FamilyDTO> getFamily(@PathVariable String id){
		Family family = memberService.getFamily(Long.parseLong(id));
		return new ResponseEntity<FamilyDTO>(familyFactory.toDto(family),HttpStatus.OK);
	}


	@ApiOperation(value = "Create new family")
	@PostMapping("/family/")
	ResponseEntity<FamilyDTO> createFamily(@RequestBody FamilyDTO familyDTO){
		Family family = memberService.createFamily(familyFactory.toDomain(familyDTO));
		return new ResponseEntity<FamilyDTO>(familyFactory.toDto(family),HttpStatus.OK);
	}

	@ApiOperation(value = "Add Member2 to family")
	@PutMapping("/family/add")
	ResponseEntity<FamilyDTO> addMemberToFamily(@RequestBody MemberDTO memberDTO){
		Family family = memberService.addMemberToFamily(memberFactory.toDomain(memberDTO));
		return new ResponseEntity<FamilyDTO>(familyFactory.toDto(family),HttpStatus.OK);
	}

	@ApiOperation(value = "Remove Member2 from family")
	@PutMapping("/family/remove")
	ResponseEntity<FamilyDTO> removeMemberFromFamily(@PathVariable String id){
		Family family = memberService.deleteMemberFromFamily(Long.parseLong(id));
		return new ResponseEntity<FamilyDTO>(familyFactory.toDto(family),HttpStatus.OK);
	}

	@ApiOperation(value = "Get a member2 by Id or barCode")
	@GetMapping("/{id}")
	 ResponseEntity<MemberDTO> getMember(@PathVariable String id){
		Member2 member2 = memberService.getMember(Long.parseLong(id));
		return new ResponseEntity<MemberDTO>(memberFactory.toDto(member2),HttpStatus.OK);
	}

	@ApiOperation(value = "Create new member2 the photo should be uploaded after the member2 is created")
	@PostMapping("/")
	ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO){
		Member2 member2 = memberService.createMember(memberFactory.toDomain(memberDTO));
		return new ResponseEntity<MemberDTO>(memberFactory.toDto(member2),HttpStatus.OK);
	}

	@ApiOperation(value = "Update a member2 info")
	@PutMapping("/")
	ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO memberDTO){
		Member2 member2 = memberService.updateMember(memberFactory.toDomain(memberDTO));
		return new ResponseEntity<MemberDTO>(memberFactory.toDto(member2),HttpStatus.OK);
	}

	@ApiOperation(value = "Remove a member2 by Id or barCode")
	@DeleteMapping("/{id}")
	ResponseEntity<Boolean> deleteMember(@PathVariable String id){
		return new ResponseEntity<Boolean>(memberService.deleteMember(Long.parseLong(id)),HttpStatus.OK);
	}

	@ApiOperation(value = "Search members by different criteria")
	@PostMapping("/search")
	ResponseEntity<List<MemberDTO>> searchMember(@RequestBody MemberSearchCriteria searchCriteria){
		List<Member2> member2s = memberService.search(searchCriteria);
		return new ResponseEntity<List<MemberDTO>> (memberFactory.toDtos(member2s),HttpStatus.OK);
	}

	@ApiOperation(produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,value = "Upload a member2 photo as Multi part File with Request parameter memberId")
	@PostMapping(value = "/upload", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Resource> singleFileUpload(
			@ApiParam(name = "file", value = "Select the file to Upload", required = true)
			@RequestPart("file") MultipartFile file,
			@RequestParam("memberId") Long memberId) throws Exception {
		if (file.isEmpty()) {
			throw new Exception("Please select a file to upload");
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
