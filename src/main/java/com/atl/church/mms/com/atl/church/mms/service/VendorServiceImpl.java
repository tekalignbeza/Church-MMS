package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.VendorRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorServiceImpl  implements VendorService{

    @Autowired
    private VendorRepo vendorRepo;

    @Override
    public Vendor getVendor(Long id) {
        Optional<Vendor> vendor = vendorRepo.findById(id);
        return vendor.isPresent() ? vendor.get() : null;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if(vendorRepo.findById(vendor.getId()).isPresent()){
            return null;
        }
        return vendorRepo.save(vendor);
    }

    @Override
    public Vendor updateVendor(Vendor vendor) {
        if(!vendorRepo.findById(vendor.getId()).isPresent()){
            return null;
        }
        return vendorRepo.save(vendor);
    }

    @Override
    public boolean deleteVendor(Long id) {
        if(vendorRepo.getOne(id) != null) {
            vendorRepo.delete(vendorRepo.getOne(id));
            return true;
        }
        return false;

    }
}
