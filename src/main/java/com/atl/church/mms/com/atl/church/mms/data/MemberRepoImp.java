package com.atl.church.mms.com.atl.church.mms.data;

import com.atl.church.mms.com.atl.church.mms.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepoImp implements MemberRepo {

	@Override
	public Member getMember(Long id){
		return null;
	}

	@Override
	public Member createMember(Member member){
		return member;
	}

	@Override
	public Member updateMember(Member member){
		return member;
	}

	@Override
	public boolean deleteMember(Long id){
		return true;
	}

	@Override
	public boolean sendEmail(Long id, String msg){
		return true;
	}
}
