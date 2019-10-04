package com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentMethod;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentStatus;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentType;
import com.atl.church.mms.com.atl.church.mms.rest.PaymentDTO;
import com.atl.church.mms.com.atl.church.mms.rest.PaymentFactory;
import com.atl.church.mms.com.atl.church.mms.rest.PaymentTypeDTO;
import com.atl.church.mms.com.atl.church.mms.rest.PaymentTypeFactory;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class PaymentFactoryTest {
    @InjectMocks
    private PaymentFactory paymentFactory;
    @Spy
    private PaymentTypeFactory paymentTypeFactory;

    private PaymentDTO paymentDTO;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        paymentDTO = buildPaymentDTO();
    }

    @Test
    public void toDTOTest() {
        Payment payment = buildPayment();
        paymentFactory.toDto(paymentFactory.toDomain(paymentDTO));

    }

    @Test
    public void toDtoListTest() {
        List<Payment> payments = Lists.newArrayList();
        Payment payment1 = buildPayment();
        Payment payment2 = buildPayment();
        payments.add(payment1);
        payments.add(payment2);

        List<PaymentDTO> dots = paymentFactory.toDtos(payments);
        assertNotNull(dots);
        assertTrue(dots.size() == 2);


    }

    @Test
    public void toDomainTest(){
        Payment payment = buildPayment();
        paymentFactory.toDomain(paymentFactory.toDto(payment));

    }


    private Payment buildPayment(){
        return Payment.builder()
                .id(new Long(3423432))
                .memberId(new Long(98989))
                .paymentMethod(PaymentMethod.CASH)
                .note("this is not a member2")
                .status(PaymentStatus.PAID)
                .type(PaymentType.builder().id(1l).name("Monthly Payment").build())
                .amount(20.00)
                .build();
    }


    private PaymentDTO buildPaymentDTO(){
        return PaymentDTO.builder()
                .id(new Long(3423432))
                .memberId(new Long(98989))
                .paymentMethod(PaymentMethod.CASH.name())
                .note("this is not a member2")
                .status(PaymentStatus.PAID.name())
                .type(PaymentTypeDTO.builder().id(1l).name("Monthly Payment").build())
                .amount(20.00)
                .build();
    }
}
