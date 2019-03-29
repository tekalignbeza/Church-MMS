package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Member;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public interface MemberService {

	Member getMember(Long id);

	Member createMember(Member member);

	Member updateMember(Member member);

	boolean deleteMember(Long id);

	boolean sendEmail(Long id, String msg);

	void upload(File tempFile, String id);

	InputStream getMemberIdCard(String id) throws IOException;
}
