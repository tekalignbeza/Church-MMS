package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.RestValidator;
import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.domain.Family;
import com.atl.church.mms.com.atl.church.mms.domain.MemberSearchCriteria;
import com.atl.church.mms.com.atl.church.mms.dto.FamilyDTO;
import com.atl.church.mms.com.atl.church.mms.dto.MemberDTO;
import com.atl.church.mms.com.atl.church.mms.exception.MMSRestException;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import com.atl.church.mms.com.atl.church.mms.factory.AttendaceFactory;
import com.atl.church.mms.com.atl.church.mms.factory.FamilyFactory;
import com.atl.church.mms.com.atl.church.mms.factory.MemberFactory;
import com.atl.church.mms.com.atl.church.mms.factory.PaymentFactory;
import com.atl.church.mms.com.atl.church.mms.service.MeetingService;
import com.atl.church.mms.com.atl.church.mms.service.MemberService;
import com.atl.church.mms.com.atl.church.mms.service.PaymentService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.File;
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
	PaymentService paymentService;
	@Autowired
	PaymentFactory paymentFactory;
	@Autowired
	MeetingService meetingService;
	@Autowired
	AttendaceFactory attendaceFactory;
	@Autowired
	private MemberFactory memberFactory;
    @Autowired
	private FamilyFactory familyFactory;
	@Autowired
	private RestValidator restValidator;

	@ApiOperation(value = "Get a Family by Id or barCode")
	@GetMapping("/family/{id}")
	public ResponseEntity<FamilyDTO> getFamily(@PathVariable @Min(0) Long id) throws MMSServiceException {
		Family family = memberService.getFamily(id);
		FamilyDTO familyDTO = buildFamilyDTO(familyFactory.toDto(family), family.getId());
		return new ResponseEntity<>(familyDTO, HttpStatus.OK);
	}


	@ApiOperation(value = "Create new family")
	@PostMapping("/family/")
	public ResponseEntity<FamilyDTO> createFamily(@RequestBody @Valid FamilyDTO familyDTO) throws MMSRestException {
		restValidator.validateForCreate(familyDTO);
		Family family = memberService.createFamily(familyFactory.toDomain(familyDTO));
		return new ResponseEntity<>(buildFamilyDTO(familyFactory.toDto(family),family.getId()),HttpStatus.OK);
	}

	@ApiOperation(value = "Add Member to family")
	@PutMapping("/addFamilyMember/{familyId}")
	public ResponseEntity<FamilyDTO> addMemberToFamily(@PathVariable @Min(0) Long familyId,@RequestBody @Valid MemberDTO memberDTO) throws MMSServiceException, MMSRestException {
		restValidator.validateForCreate(memberDTO);
		Family family = memberService.addMemberToFamily(familyId,memberFactory.toDomain(memberDTO));
		return new ResponseEntity<>(buildFamilyDTO(familyFactory.toDto(family),family.getId()),HttpStatus.OK);
	}

	@ApiOperation(value = "Remove Member from family")
	@PutMapping("/removeFamilyMember/{familyId}/member/{memberId}")
	public ResponseEntity<FamilyDTO> removeMemberFromFamily(@PathVariable @Min(0) Long familyId,@PathVariable @Min(0) Long memberId) throws MMSServiceException {
		Family family = memberService.deleteMemberFromFamily(familyId,memberId);
		return new ResponseEntity<>(buildFamilyDTO(familyFactory.toDto(family),family.getId()),HttpStatus.OK);
	}

	@ApiOperation(value = "Get a member by Id or barCode")
	@GetMapping("/{id}")
	public ResponseEntity<MemberDTO> getMember(@PathVariable @Min(0) Long id) throws MMSServiceException {
		Member member = memberService.getMember(id);
		return new ResponseEntity<>(memberFactory.toDto(member),HttpStatus.OK);
	}


	@ApiOperation(value = "Update a member info")
	@PutMapping("/")
	public ResponseEntity<MemberDTO> updateMember(@RequestBody @Valid MemberDTO memberDTO) throws MMSServiceException {
		Member member = memberService.updateMember(memberFactory.toDomain(memberDTO));
		return new ResponseEntity<>(memberFactory.toDto(member),HttpStatus.OK);
	}

	@ApiOperation(value = "Search members by different criteria")
	@PostMapping("/search")
	public ResponseEntity<List<MemberDTO>> searchMember(@RequestBody MemberSearchCriteria searchCriteria){
		List<Member> members = memberService.search(searchCriteria);
		return new ResponseEntity<> (memberFactory.toDtos(members),HttpStatus.OK);
	}

	@ApiOperation(produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,value = "Upload a member photo as Multi part File with Request parameter memberId")
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

		private FamilyDTO buildFamilyDTO(FamilyDTO familyDTO, long familyId){
			familyDTO.setMemberDTOList(memberFactory.toDtos(memberService.getMemberByFamilyId(familyId)));
			familyDTO.setPaymentDTOList(paymentFactory.toDtos(paymentService.getPaymentByFamilyId(familyId)));
			familyDTO.setAttendanceDTOList(attendaceFactory.toDtos(meetingService.getAttendanceByFamilyId(familyId)));
			return familyDTO;
		}
}
