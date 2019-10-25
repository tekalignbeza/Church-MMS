package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.PaymentRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public Payment getPayment(Long id) {
        Optional<Payment> payment = paymentRepo.findById(id);
        return payment.isPresent() ? payment.get() : null ;
    }

    @Override
    public Payment createPayment(Payment payment) {
        if( paymentRepo.findById(payment.getId()).isPresent())
            return null;
        return paymentRepo.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        if( ! paymentRepo.findById(payment.getId()).isPresent())
            return null;
        return paymentRepo.save(payment);
    }

    @Override
    public boolean deletePayment(Long id) {
        Optional<Payment> payment =  paymentRepo.findById(id);
        if( !payment.isPresent())
            return false;
        payment.get().setStatus(TransactionStatus.VOID);
        paymentRepo.save(payment.get());
        return true;
    }
}
