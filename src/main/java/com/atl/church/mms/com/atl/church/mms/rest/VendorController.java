package com.atl.church.mms.com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.RestValidator;
import com.atl.church.mms.com.atl.church.mms.domain.Vendor;
import com.atl.church.mms.com.atl.church.mms.dto.VendorDTO;
import com.atl.church.mms.com.atl.church.mms.exception.MMSRestException;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import com.atl.church.mms.com.atl.church.mms.factory.VendorFactory;
import com.atl.church.mms.com.atl.church.mms.service.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;

@Controller
@RequestMapping("/api/vendor")
@Api(value = "vendor", description = "Expense management")
public class VendorController {

    @Autowired
    private VendorService vendorService;
    @Autowired
    private VendorFactory vendorFactory;
    @Autowired
    private RestValidator restValidator;


    @ApiOperation(value = "Get Vendor by Id")
    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> get(@PathVariable @Min(0) Long id) throws MMSServiceException {
        Vendor vendor = vendorService.getVendor(id);
        return new ResponseEntity<>(vendorFactory.toDto(vendor),HttpStatus.OK);
    }

    @ApiOperation(value = "Create Vendor")
    @PostMapping("/")
    public ResponseEntity<VendorDTO> create(@RequestBody @Valid VendorDTO vendorDTO) throws MMSRestException {
        restValidator.validateForCreate(vendorDTO);
        Vendor vendor = vendorService.createVendor(vendorFactory.toDomain(vendorDTO));
        return new ResponseEntity<>(vendorFactory.toDto(vendor),HttpStatus.OK);
    }

    @ApiOperation(value = "Update Vendor")
    @PutMapping("/")
    public ResponseEntity<VendorDTO> update(@RequestBody @Valid VendorDTO vendorDTO) throws MMSServiceException {
        Vendor vendor = vendorService.updateVendor(vendorFactory.toDomain(vendorDTO));
        return new ResponseEntity<>(vendorFactory.toDto(vendor),HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Vendor by id")
    @DeleteMapping("/")
    public ResponseEntity<Boolean> update(@PathVariable @Min(0) Long id) throws MMSServiceException {
        return new ResponseEntity(vendorService.deleteVendor(id),HttpStatus.OK);
    }

}
