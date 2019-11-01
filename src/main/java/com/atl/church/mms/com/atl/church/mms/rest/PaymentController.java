package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.RestValidator;
import com.atl.church.mms.com.atl.church.mms.domain.Payment;
import com.atl.church.mms.com.atl.church.mms.domain.PaymentType;
import com.atl.church.mms.com.atl.church.mms.dto.PaymentDTO;
import com.atl.church.mms.com.atl.church.mms.dto.PaymentTypeDTO;
import com.atl.church.mms.com.atl.church.mms.exception.MMSRestException;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import com.atl.church.mms.com.atl.church.mms.factory.PaymentFactory;
import com.atl.church.mms.com.atl.church.mms.factory.PaymentTypeFactory;
import com.atl.church.mms.com.atl.church.mms.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/payment")
@Api(value="member", description="Operations payment registering")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentFactory paymentFactory;
    @Autowired
    private PaymentTypeFactory paymentTypeFactory;
    @Autowired
    private RestValidator restValidator;

    @ApiOperation(value = "Get a Payment by Id")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> get(@PathVariable @Min(0) Long id) throws MMSServiceException {
        Payment payment = paymentService.getPayment(id);
        return new ResponseEntity<>(paymentFactory.toDto(payment),HttpStatus.OK);
    }
    @ApiOperation(value = "Create a payment entry")
    @PostMapping("/family/{familyId}/member/{memberId}/paymentType/{paymentTypeId}")
    public ResponseEntity<PaymentDTO> create(@PathVariable @Min(0) Long familyId, @PathVariable @Min(0) Long memberId,@PathVariable @Min(0) Long paymentTypeId, @RequestBody @Valid PaymentDTO paymentDTO) throws URISyntaxException, MMSServiceException, MMSRestException {
        restValidator.validateForCreate(paymentDTO);
        Payment payment=paymentService.createPayment(familyId,memberId,paymentTypeId,paymentFactory.toDomain(paymentDTO));
        MultiValueMap<String,String> headers = new HttpHeaders();
        ((HttpHeaders) headers).setLocation(new URI("/payment/"+ payment.getId()));
        ResponseEntity responseEntity = new ResponseEntity(paymentFactory.toDto(payment),headers,HttpStatus.CREATED);
       return responseEntity;

    }

    @ApiOperation(value = "Update a payment")
    @PutMapping("/family/{familyId}/member/{memberId}/paymentType/{paymentTypeId}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable @Min(0) Long familyId, @PathVariable @Min(0) Long memberId,@PathVariable @Min(0) Long paymentTypeId,@RequestBody @Valid PaymentDTO paymentDTO) throws MMSServiceException {
        Payment payment = paymentService.updatePayment(familyId,memberId,paymentTypeId,paymentFactory.toDomain(paymentDTO));
        return new ResponseEntity<>(paymentFactory.toDto(payment),HttpStatus.OK);
    }

    @ApiOperation(value = "Void a payment")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePayment(@PathVariable @Min(0) Long id) throws MMSServiceException {
        return new ResponseEntity<>(paymentService.deletePayment(id),HttpStatus.OK);
    }

    @ApiOperation(value = "Get a PaymentType by Id")
    @GetMapping("/PaymentType/{id}")
    public ResponseEntity<PaymentTypeDTO> getPaymentType(@PathVariable @Min(0) Long id) throws MMSServiceException {
        PaymentType paymentType = paymentService.getPaymentType(id);
        return new ResponseEntity<>(paymentTypeFactory.toDto(paymentType),HttpStatus.OK);
    }
    @ApiOperation(value = "Create a paymentType entry")
    @PostMapping("/PaymentType/")
    public ResponseEntity<PaymentTypeDTO> createPaymentType(@RequestBody @Valid PaymentTypeDTO paymentTypeDTO) throws MMSRestException {
        restValidator.validateForCreate(paymentTypeDTO);
        PaymentType paymentType = paymentService.createPaymentType(paymentTypeFactory.toDomain(paymentTypeDTO));
        return new ResponseEntity<>(paymentTypeFactory.toDto(paymentType),HttpStatus.OK);
    }

    @ApiOperation(value = "Update a paymentType entry")
    @PutMapping("/PaymentType/")
    public ResponseEntity<PaymentTypeDTO> updatePaymentType(@RequestBody @Valid PaymentTypeDTO paymentTypeDTO) throws MMSServiceException {
        PaymentType paymentType = paymentService.updatePaymentType(paymentTypeFactory.toDomain(paymentTypeDTO));
        return new ResponseEntity<>(paymentTypeFactory.toDto(paymentType),HttpStatus.OK);
    }

    @ApiOperation(value = "delete a paymentType")
    @DeleteMapping("/PaymentType/{id}")
    public ResponseEntity<Boolean> deletePaymentType(@PathVariable @Min(0) Long id) throws MMSServiceException {
        return new ResponseEntity<>(paymentService.deletePaymentType(id),HttpStatus.OK);
    }
}
