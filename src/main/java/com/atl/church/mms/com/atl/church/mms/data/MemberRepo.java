package com.atl.church.mms.com.atl.church.mms.data;

import com.atl.church.mms.com.atl.church.mms.domain.Member2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepo extends JpaRepository<Member2, Long>  {

	List<Member2> findByFirstName(String firstName);
	List<Member2> findByMiddleName(String firstName);
	List<Member2> findByLastName(String firstName);
	List<Member2> findByEmail(String firstName);
	List<Member2> findByCellPhone(String firstName);



}
