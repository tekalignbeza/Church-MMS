package com.atl.church.mms.com.atl.church.mms.rest;

import com.atl.church.mms.com.atl.church.mms.domain.Vendor;
import com.atl.church.mms.com.atl.church.mms.dto.VendorDTO;
import com.atl.church.mms.com.atl.church.mms.factory.VendorFactory;
import com.atl.church.mms.com.atl.church.mms.service.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/api/vendor")
@Api(value = "vendor", description = "Expense management")
public class VendorController {

    @Autowired
    private VendorService vendorService;
    @Autowired
    private VendorFactory vendorFactory;


    @ApiOperation(value = "Get Vendor by Id")
    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> get(@PathVariable String id){
        Vendor vendor = vendorService.getVendor(Long.parseLong(id));
        if(!Objects.isNull(vendor)){
            return ResponseEntity.ok(vendorFactory.toDto(vendor));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);

    }

    @ApiOperation(value = "Create Vendor")
    @PostMapping("/")
    public ResponseEntity<VendorDTO> create(@RequestBody VendorDTO vendorDTO){
        Vendor vendor = vendorService.createVendor(vendorFactory.toDomain(vendorDTO));
        return ResponseEntity.ok(vendorFactory.toDto(vendor));


    }

    @ApiOperation(value = "Update Vendor")
    @PutMapping("/")
    public ResponseEntity<VendorDTO> update(@RequestBody VendorDTO vendorDTO){
        Vendor vendor = vendorService.updateVendor(vendorFactory.toDomain(vendorDTO));
        return ResponseEntity.ok(vendorFactory.toDto(vendor));


    }

    @ApiOperation(value = "Delete Vendor by id")
    @DeleteMapping("/")
    public ResponseEntity<Boolean> update(@PathVariable String id){
        return ResponseEntity.ok(vendorService.deleteVendor(Long.parseLong(id)));


    }

}
