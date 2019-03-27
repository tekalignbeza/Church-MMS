package com.atl.church.mms.com.atl.church.mms.data;

import com.atl.church.mms.com.atl.church.mms.domain.Member;

public interface MemberRepo {
	Member getMember(Long id);

	Member createMember(Member member);

	Member updateMember(Member member);

	boolean deleteMember(Long id);

	boolean sendEmail(Long id, String msg);
}
