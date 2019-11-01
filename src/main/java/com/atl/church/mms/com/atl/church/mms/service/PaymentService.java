package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentType;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;

import java.util.List;

public interface  PaymentService {

    Payment getPayment(Long id) throws MMSServiceException;

    List<Payment> getPaymentByFamilyId(long familyId);

    Payment createPayment(Long familyId,Long memberId,Long paymentTypeId,Payment payment) throws MMSServiceException;

    Payment updatePayment(Long familyId,Long memberId,Long paymentTypeId,Payment payment) throws MMSServiceException;

    boolean deletePayment(Long id) throws MMSServiceException;

    PaymentType getPaymentType(Long id) throws MMSServiceException;

    PaymentType createPaymentType(PaymentType paymentType);

    PaymentType updatePaymentType(PaymentType paymentType) throws MMSServiceException;

    boolean deletePaymentType(Long id) throws MMSServiceException;

}
