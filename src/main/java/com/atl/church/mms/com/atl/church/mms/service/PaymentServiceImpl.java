package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.PaymentRepo;
import com.atl.church.mms.com.atl.church.mms.data.PaymentTypeRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentType;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionStatus;
import com.atl.church.mms.com.atl.church.mms.exception.ErrorType;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private PaymentTypeRepo paymentTypeRepo;
    @Autowired
    private MemberService memberService;

    @Override
    public Payment getPayment(Long id) throws MMSServiceException {
        return paymentRepo.findById(id).orElseThrow(()-> new MMSServiceException("Payment not found", ErrorType.NOT_FOUND));
    }

    @Override
    public List<Payment> getPaymentByFamilyId(long familyId) {
        return paymentRepo.findByFamilyId(familyId);
    }

    @Override
    public Payment createPayment(Long familyId,Long memberId,Long paymentTypeId,Payment payment) throws MMSServiceException {
        payment.setFamily(memberService.getFamily(familyId));
        payment.setPaymentType(paymentTypeRepo.findById(paymentTypeId).orElseThrow(()-> new MMSServiceException("Payment type not found", ErrorType.NOT_FOUND)));
        payment.setMemberId(memberId);
        return paymentRepo.save(payment);
    }

    @Override
    public Payment updatePayment(Long familyId,Long memberId,Long paymentTypeId, Payment payment) throws MMSServiceException {
        getPayment(payment.getId());
        payment.setFamily(memberService.getFamily(familyId));
        payment.setPaymentType(paymentTypeRepo.findById(paymentTypeId).orElseThrow(()-> new MMSServiceException("Payment type not found", ErrorType.NOT_FOUND)));
        payment.setMemberId(memberId);
        return paymentRepo.save(payment);
    }

    @Override
    public boolean deletePayment(Long id) throws MMSServiceException {
        Payment payment = getPayment(id);
        payment.setStatus(TransactionStatus.VOID);
        paymentRepo.save(payment);
        return true;
    }

    @Override
    public PaymentType getPaymentType(Long id) throws MMSServiceException {
        return paymentTypeRepo.findById(id).orElseThrow(()-> new MMSServiceException("Payment type not found", ErrorType.NOT_FOUND));
    }

    @Override
    public PaymentType createPaymentType(PaymentType paymentType) {
        return paymentTypeRepo.save(paymentType);
    }

    @Override
    public PaymentType updatePaymentType(PaymentType paymentType) throws MMSServiceException {
        getPaymentType(paymentType.getId());
        return paymentTypeRepo.save(paymentType);
    }

    @Override
    public boolean deletePaymentType(Long id) throws MMSServiceException {
        PaymentType paymentType = getPaymentType(id);
        paymentTypeRepo.delete(paymentType);
        return true;
    }
}
