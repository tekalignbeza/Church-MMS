package com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionMethod;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionStatus;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentType;
import com.atl.church.mms.com.atl.church.mms.exception.MMSRestException;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import com.atl.church.mms.com.atl.church.mms.rest.PaymentController;
import com.atl.church.mms.com.atl.church.mms.dto.PaymentDTO;
import com.atl.church.mms.com.atl.church.mms.factory.PaymentFactory;
import com.atl.church.mms.com.atl.church.mms.dto.PaymentTypeDTO;
import com.atl.church.mms.com.atl.church.mms.service.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(JUnit4.class)
public class PaymentControllerTest {
    @Mock
    private PaymentService paymentService;
    @Mock
    private PaymentFactory paymentFactory;
    @InjectMocks
    private PaymentController paymentController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNotFountTest() throws MMSServiceException {
        when(paymentService.getPayment(new Long("12233"))).thenReturn(null);
        ResponseEntity responseEntity = paymentController.get(12233l);
        assertTrue("content not found", responseEntity.getStatusCode().value() == 404);
        verify(paymentService, times(1)).getPayment(new Long("12233"));

    }

    @Test
    public void getFountTest() throws MMSServiceException {
        Payment payment = buildPayment();
        PaymentDTO paymentDTO = buildPaymentDTO();
        when(paymentService.getPayment(new Long("12233"))).thenReturn(payment);
        when(paymentFactory.toDto(payment)).thenReturn(paymentDTO);
        ResponseEntity responseEntity = paymentController.get(12233l);
        PaymentDTO result = (PaymentDTO) responseEntity.getBody();
        assertTrue("content found", responseEntity.getStatusCode().value() == 200);
        assertTrue("content comparison", result.getId().equals(payment.getId()));
        verify(paymentService, times(1)).getPayment(new Long("12233"));
        verify(paymentFactory, times(1)).toDto(payment);

    }

    @Test
    public void updateNotFountTest() throws MMSServiceException {
        PaymentDTO paymentDTO = buildPaymentDTO();
        when(paymentService.getPayment(new Long("3423432"))).thenReturn(null);
        ResponseEntity responseEntity = paymentController.updatePayment(1l,2l,3l,paymentDTO);
        assertTrue("content not found", responseEntity.getStatusCode().value() == 404);
        verify(paymentService, times(1)).getPayment(new Long("3423432"));

    }

    @Test
    public void updateWhenFoundFountTest() throws MMSServiceException {
        Payment payment = buildPayment();
        PaymentDTO paymentDTO = buildPaymentDTO();
        when(paymentService.getPayment(new Long("3423432"))).thenReturn(payment);
        when(paymentFactory.toDto(payment)).thenReturn(paymentDTO);
        when(paymentFactory.toDomain(paymentDTO)).thenReturn(payment);
        ResponseEntity responseEntity = paymentController.updatePayment(1l,2l,3l,paymentDTO);

        assertTrue("content found", responseEntity.getStatusCode().value() == 200);
        verify(paymentService, times(1)).getPayment(new Long("3423432"));
        verify(paymentFactory, times(1)).toDomain(paymentDTO);

    }

    @Test
    public void createExistingTest() throws URISyntaxException, MMSServiceException, MMSRestException {
        Payment payment = buildPayment();
        PaymentDTO paymentDTO = buildPaymentDTO();
        when(paymentService.getPayment(new Long("3423432"))).thenReturn(null);
        when(paymentFactory.toDto(payment)).thenReturn(paymentDTO);
        when(paymentFactory.toDomain(paymentDTO)).thenReturn(payment);
        ResponseEntity responseEntity = paymentController.create(1l,2l,3l,paymentDTO);

        assertTrue("content found", responseEntity.getStatusCode().value() == 226);
        verify(paymentService, times(1)).getPayment(new Long("3423432"));

    }

    @Test
    public void createTest() throws URISyntaxException, MMSServiceException, MMSRestException {
        Payment payment = buildPayment();
        PaymentDTO paymentDTO = buildPaymentDTO();
        when(paymentService.getPayment(new Long("3423432"))).thenReturn(payment);
        when(paymentFactory.toDto(payment)).thenReturn(paymentDTO);
        when(paymentFactory.toDomain(paymentDTO)).thenReturn(payment);
        ResponseEntity responseEntity = paymentController.create(1l,2l,3l,paymentDTO);

        assertTrue("content found", responseEntity.getStatusCode().value() == 201);
        assertTrue("check location uri", responseEntity.getHeaders().get("Location").contains("/payment/" + payment.getId()));
        verify(paymentService, times(1)).getPayment(new Long("3423432"));


    }

    @Test
    public void deleteNotFoundTest() throws MMSServiceException {
        Payment payment = buildPayment();
        PaymentDTO paymentDTO = buildPaymentDTO();
        when(paymentService.getPayment(new Long("3423432"))).thenReturn(null);
        when(paymentFactory.toDto(payment)).thenReturn(paymentDTO);
        when(paymentFactory.toDomain(paymentDTO)).thenReturn(payment);
        ResponseEntity responseEntity = paymentController.deletePayment(3423432l);

        assertTrue("content found", responseEntity.getStatusCode().value() == 404);
        verify(paymentService, times(1)).getPayment(new Long("3423432"));


    }

    @Test
    public void deleteFoundTest() throws MMSServiceException {
        Payment payment = buildPayment();
        PaymentDTO paymentDTO = buildPaymentDTO();
        when(paymentService.getPayment(new Long("3423432"))).thenReturn(payment);
        when(paymentFactory.toDto(payment)).thenReturn(paymentDTO);
        when(paymentFactory.toDomain(paymentDTO)).thenReturn(payment);
        ResponseEntity responseEntity = paymentController.deletePayment(3423432l);

        assertTrue("content found", responseEntity.getStatusCode().value() == 200);
        verify(paymentService, times(1)).getPayment(new Long("3423432"));


    }

    private Payment buildPayment() {
        return Payment.builder()
                .id(new Long(3423432))
                .memberId(new Long(98989))
                .transactionMethod(TransactionMethod.CASH)
                .note("this is not a member2")
                .status(TransactionStatus.PAID)
                .paymentType(PaymentType.builder().id(1l).name("Monthly Payment").build())
                .amount(20.00)
                .build();
    }

    private PaymentDTO buildPaymentDTO() {
        return PaymentDTO.builder()
                .id(new Long(3423432))
                .memberId(new Long(98989))
                .paymentMethod(TransactionMethod.CASH.toString())
                .note("this is not a member2")
                .status(TransactionStatus.PAID.toString())
                .type(PaymentTypeDTO.builder().id(1l).name("Monthly Payment").build())
                .amount(20.00)
                .build();
    }
}
