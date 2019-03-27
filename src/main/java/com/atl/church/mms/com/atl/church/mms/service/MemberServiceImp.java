package com.atl.church.mms.com.atl.church.mms.service;
import com.atl.church.mms.com.atl.church.mms.data.MemberRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberRepo memberRepo;

	@Override
	public Member getMember(Long id){
		return memberRepo.getMember(id);
	}

	@Override
	public Member createMember(Member member){
		return memberRepo.createMember(member);
	}

	@Override
	public Member updateMember(Member member){
		return memberRepo.updateMember(member);
	}

	@Override
	public boolean deleteMember(Long id){
		return memberRepo.deleteMember(id);
	}

	@Override
	public boolean sendEmail(Long id, String msg){
		return true;
	}
}
