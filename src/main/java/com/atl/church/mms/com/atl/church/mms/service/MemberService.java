package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Member;


public interface MemberService {

	Member getMember(Long id);

	Member createMember(Member member);

	Member updateMember(Member member);

	boolean deleteMember(Long id);

	boolean sendEmail(Long id, String msg);
}
