package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.domain.Member;
import com.atl.church.mms.com.atl.church.mms.domain.Payment;
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

    @ApiOperation(value = "Get a Payment by Id")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> get(@PathVariable String id){
        Payment payment = paymentService.getPayment(Long.parseLong(id));
        if( null == payment)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(paymentFactory.toDto(payment),HttpStatus.OK);
    }
    @ApiOperation(value = "Create a payment entry")
    @PostMapping("/")
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO paymentDTO) throws URISyntaxException {
        Payment payment = paymentService.getPayment(paymentDTO.getId());
        if( null == payment)
            return new ResponseEntity<>(HttpStatus.IM_USED);




        paymentService.createPayment(paymentFactory.toDomain(paymentDTO));
        MultiValueMap<String,String> headers = new HttpHeaders();
        ((HttpHeaders) headers).setLocation(new URI("/payment/"+ payment.getId()));
        ResponseEntity responseEntity = new ResponseEntity(null,headers,HttpStatus.CREATED);

       return responseEntity;

    }

    @ApiOperation(value = "Update a payment")
    @PutMapping("/")
    public ResponseEntity<PaymentDTO> updatePayemnt(@RequestBody PaymentDTO paymentDTO){

        if( null == paymentService.getPayment(paymentDTO.getId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        paymentService.updatePayment(paymentFactory.toDomain(paymentDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Void a payment")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePayment(@PathVariable String id){
        if( null == paymentService.getPayment(Long.parseLong(id)))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Boolean>(paymentService.deletePayment(Long.parseLong(id)),HttpStatus.OK);
    }
}
