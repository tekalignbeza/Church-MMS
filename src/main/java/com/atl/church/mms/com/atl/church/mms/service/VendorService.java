package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Vendor;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;

public interface VendorService {

    Vendor getVendor(Long id) throws MMSServiceException;

    Vendor createVendor(Vendor vendor);

    Vendor updateVendor(Vendor vendor) throws MMSServiceException;

    boolean deleteVendor(Long id) throws MMSServiceException;

}
