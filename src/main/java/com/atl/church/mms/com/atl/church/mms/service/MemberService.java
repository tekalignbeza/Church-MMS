package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.domain.Email;
import com.atl.church.mms.com.atl.church.mms.domain.Family;
import com.atl.church.mms.com.atl.church.mms.domain.MemberSearchCriteria;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface MemberService {

	Family getFamily(Long id) throws MMSServiceException;

	Family createFamily(Family family);

	Family addMemberToFamily(Long familyId, Member member) throws MMSServiceException;

	Family deleteMemberFromFamily(Long familyId,Long memberId) throws MMSServiceException;

	List<Member> getMemberByFamilyId(long familyId);

	Member getMember(Long id) throws MMSServiceException;

	Member updateMember(Member member) throws MMSServiceException;

	boolean deleteMember(Long id) throws MMSServiceException;

	List<Member> search(MemberSearchCriteria searchCriteria);

	List<Member> getAll(boolean includeInactive);

	boolean sendEmail(Email email) throws Exception;

	File upload(File tempFile, Long id);

	InputStream getMemberIdCard(String id) throws IOException, MMSServiceException;
}
