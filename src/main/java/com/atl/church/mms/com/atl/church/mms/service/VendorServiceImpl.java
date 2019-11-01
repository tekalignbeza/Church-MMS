package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.AddressRepo;
import com.atl.church.mms.com.atl.church.mms.data.UserRepo;
import com.atl.church.mms.com.atl.church.mms.data.VendorRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Vendor;
import com.atl.church.mms.com.atl.church.mms.exception.ErrorType;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class VendorServiceImpl  implements VendorService{

    @Autowired
    private VendorRepo vendorRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public Vendor getVendor(Long id) throws MMSServiceException {
        return vendorRepo.findById(id).orElseThrow(()-> new MMSServiceException("Vendor not found", ErrorType.NOT_FOUND));
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        userRepo.save(vendor.getUser());
        addressRepo.save(vendor.getAddress());
        return vendorRepo.save(vendor);
    }

    @Override
    public Vendor updateVendor(Vendor vendor) throws MMSServiceException {
        getVendor(vendor.getId());
        userRepo.save(vendor.getUser());
        addressRepo.save(vendor.getAddress());
        return vendorRepo.save(vendor);
    }

    @Override
    public boolean deleteVendor(Long id) throws MMSServiceException {
        Vendor vendor = getVendor(id);
        vendorRepo.delete(vendor);
        return true;
    }
}
