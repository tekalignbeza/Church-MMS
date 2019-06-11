package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Email;
import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.domain.MemberSearchCriteria;
import com.atl.church.mms.com.atl.church.mms.utils.EmailService;

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

	List<Member> getAll(boolean includeInactive);

	boolean sendEmail(Email email) throws Exception;

	File upload(File tempFile, Long id);

	InputStream getMemberIdCard(String id) throws IOException;
}
