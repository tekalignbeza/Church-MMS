package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Payment;

import java.util.List;

public interface  PaymentService {

    Payment getPayment(Long id);

    Payment createPayment(Payment payment);

    Payment updatePayment(Payment payment);

    boolean deletePayment(Long id);

}
