package com.atl.church.mms.com.atl.church.mms.data;

import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

	List<Payment> findByFamilyId(long familyId);
}
