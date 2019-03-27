package com.atl.church.mms.com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/{id}")
	Member getMember(Long id){
		return null;
	}

	@PostMapping("/")
	Member createMember(Member member){
		return null;
	}

	@PutMapping("/")
	Member updateMember(Member member){
		return null;
	}

	@DeleteMapping("/")
	boolean deleteMember(Long id){
		return true;
	}
}
