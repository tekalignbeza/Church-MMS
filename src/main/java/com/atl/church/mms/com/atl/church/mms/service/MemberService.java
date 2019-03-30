package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.domain.MemberSearchCriteria;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface MemberService {

	Member getMember(Long id);

	Member createMember(Member member);

	Member updateMember(Member member);

	boolean deleteMember(Long id);

	List<Member> search(MemberSearchCriteria searchCriteria);

	boolean sendEmail(Long id, String msg);

	void upload(File tempFile, String id);

	InputStream getMemberIdCard(String id) throws IOException;
}
