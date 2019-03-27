package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
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
}
