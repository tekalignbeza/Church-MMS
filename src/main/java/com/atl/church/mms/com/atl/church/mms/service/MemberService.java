package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Member2;
import com.atl.church.mms.com.atl.church.mms.domain.Email;
import com.atl.church.mms.com.atl.church.mms.domain.Family;
import com.atl.church.mms.com.atl.church.mms.domain.MemberSearchCriteria;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface MemberService {

	Family getFamily(Long id);

	Family createFamily(Family family);

	Family addMemberToFamily(Member2 member2);

	Family deleteMemberFromFamily(Long memberId);

	Member2 getMember(Long id);

	Member2 createMember(Member2 member2);

	Member2 updateMember(Member2 member2);

	boolean deleteMember(Long id);

	List<Member2> search(MemberSearchCriteria searchCriteria);

	List<Member2> getAll(boolean includeInactive);

	boolean sendEmail(Email email) throws Exception;

	File upload(File tempFile, Long id);

	InputStream getMemberIdCard(String id) throws IOException;
}
