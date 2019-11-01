package com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Address;
import com.atl.church.mms.com.atl.church.mms.domain.Family;
import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionMethod;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionStatus;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentType;
import com.atl.church.mms.com.atl.church.mms.dto.FamilyDTO;
import com.atl.church.mms.com.atl.church.mms.dto.PaymentDTO;
import com.atl.church.mms.com.atl.church.mms.factory.FamilyFactory;
import com.atl.church.mms.com.atl.church.mms.factory.PaymentFactory;
import com.atl.church.mms.com.atl.church.mms.dto.PaymentTypeDTO;
import com.atl.church.mms.com.atl.church.mms.factory.PaymentTypeFactory;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class PaymentFactoryTest {
    @InjectMocks
    private PaymentFactory paymentFactory;
    @Spy
    private PaymentTypeFactory paymentTypeFactory;
    @Mock
    private FamilyFactory familyFactory;

    private PaymentDTO paymentDTO;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        paymentDTO = buildPaymentDTO();
    }

    @Test
    public void toDTOTest() {
        Payment payment = buildPayment();
        Mockito.when(familyFactory.toDomain(Mockito.any())).thenReturn(new Family());
        Mockito.when(familyFactory.toDto(Mockito.any())).thenReturn(new FamilyDTO());
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
                .family(Family.builder().address(new Address()).build())
                .transactionMethod(TransactionMethod.CASH)
                .note("this is not a member2")
                .status(TransactionStatus.PAID)
                .paymentType(PaymentType.builder().id(1l).name("Monthly Payment").build())
                .amount(20.00)
                .build();
    }


    private PaymentDTO buildPaymentDTO(){
        return PaymentDTO.builder()
                .id(new Long(3423432))
                .memberId(new Long(98989))
                .paymentMethod(TransactionMethod.CASH.name())
                .note("this is not a member2")
                .status(TransactionStatus.PAID.name())
                .type(PaymentTypeDTO.builder().id(1l).name("Monthly Payment").build())
                .amount(20.00)
                .build();
    }
}
