package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Vendor;

public interface VendorService {

    Vendor getVendor(Long id);

    Vendor createVendor(Vendor vendor);

    Vendor updateVendor(Vendor vendor);

    boolean deleteVendor(Long id);

}
