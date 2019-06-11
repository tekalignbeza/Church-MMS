package com.atl.church.mms.com.atl.church.mms.data;

import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.domain.MemberSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepo extends JpaRepository<Member, Long>  {

	List<Member> findByFirstName(String firstName);
	List<Member> findByMiddleName(String firstName);
	List<Member> findByLastName(String firstName);
	List<Member> findByEmail(String firstName);
	List<Member> findByCellPhone(String firstName);



}
