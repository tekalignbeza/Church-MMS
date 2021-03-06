package com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.PaymentRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionMethod;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionStatus;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentType;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import com.atl.church.mms.com.atl.church.mms.service.PaymentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(JUnit4.class)
public class PaymentServiceTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;
    @Mock
    private PaymentRepo paymentRepo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getPayment() throws MMSServiceException {
        Payment payment = buildPayment();
        when(paymentRepo.findById(payment.getId())).thenReturn(Optional.of(payment));
        paymentService.getPayment(payment.getId());
        verify(paymentRepo, times(1)).findById(payment.getId());
        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(paymentRepo).findById(argument.capture());
        assertEquals(payment.getId(), argument.getValue());

    }
    @Test
    public void getPaymentNotFound() throws MMSServiceException {
        Payment payment = buildPayment();
        when(paymentRepo.findById(payment.getId())).thenReturn(Optional.empty());
        assertNull(paymentService.getPayment(payment.getId()));
        verify(paymentRepo, times(1)).findById(payment.getId());
        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(paymentRepo).findById(argument.capture());
        assertEquals(payment.getId(), argument.getValue());

    }

    @Test
    public void createPayment() throws MMSServiceException {
        Payment payment = buildPayment();
        paymentService.createPayment(1l,2l,3l,payment);
        verify(paymentRepo, times(1)).save(payment);
        ArgumentCaptor<Payment> argument = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepo).save(argument.capture());
        assertEquals(payment, argument.getValue());

    }
    @Test
    public void createPaymentExists() throws MMSServiceException {
        Payment payment = buildPayment();
        when(paymentRepo.findById(new Long(payment.getId()))).thenReturn(Optional.of(payment));
        assertNull(paymentService.createPayment(1l,2l,3l,payment));
        verify(paymentRepo, times(0)).save(payment);

    }

    @Test
    public void updatePayment() throws MMSServiceException {
        Payment payment = buildPayment();
        when(paymentRepo.findById(payment.getId())).thenReturn(Optional.of(payment));
        Payment result = paymentService.updatePayment(1l,2l,3l,payment);
        verify(paymentRepo, times(1)).save(payment);
        verify(paymentRepo,times(1)).findById(payment.getId());

    }

    @Test
    public void updatePaymentNotFound() throws MMSServiceException {
        Payment payment = buildPayment();
        when(paymentRepo.findById(payment.getId())).thenReturn(Optional.empty());
        paymentService.updatePayment(1l,2l,3l,payment);
        verify(paymentRepo, times(0)).save(payment);
        verify(paymentRepo,times(1)).findById(payment.getId());

    }

    @Test
    public void deletePayment() throws MMSServiceException {
        Payment payment = buildPayment();
        when(paymentRepo.findById(payment.getId())).thenReturn(Optional.of(payment));
        assertTrue(paymentService.deletePayment(payment.getId()));
        ArgumentCaptor<Payment> argument = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepo).save(argument.capture());
        assertEquals("Void", argument.getValue().getStatus().toString());
        verify(paymentRepo,times(1)).findById(payment.getId());
        verify(paymentRepo,times(1)).save(payment);

    }

    @Test
    public void deletePaymentNotFound() throws MMSServiceException {
        Payment payment = buildPayment();
        when(paymentRepo.findById(payment.getId())).thenReturn(Optional.empty());
        assertFalse(paymentService.deletePayment(payment.getId()));
        verify(paymentRepo,times(1)).findById(payment.getId());
        verify(paymentRepo,times(0)).save(payment);

    }

    private Payment buildPayment(){
        return Payment.builder()
                .id(new Long(3423432))
                .memberId(new Long(98989))
                .transactionMethod(TransactionMethod.CASH)
                .note("this is not a member2")
                .status(TransactionStatus.PAID)
                .paymentType(PaymentType.builder().id(1l).name("Monthly Payment").build())
                .reason("")
                .amount(20.00)
                .build();
    }
}
