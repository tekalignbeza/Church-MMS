package com.atl.church.mms.com.atl.church.mms;

import com.atl.church.mms.com.atl.church.mms.dto.ExpenseDTO;
import com.atl.church.mms.com.atl.church.mms.dto.ExpenseTypeDTO;
import com.atl.church.mms.com.atl.church.mms.dto.FamilyDTO;
import com.atl.church.mms.com.atl.church.mms.dto.MeetingDTO;
import com.atl.church.mms.com.atl.church.mms.dto.MemberDTO;
import com.atl.church.mms.com.atl.church.mms.dto.PaymentDTO;
import com.atl.church.mms.com.atl.church.mms.dto.PaymentTypeDTO;
import com.atl.church.mms.com.atl.church.mms.dto.VendorDTO;
import com.atl.church.mms.com.atl.church.mms.exception.ErrorType;
import com.atl.church.mms.com.atl.church.mms.exception.MMSRestException;
import org.springframework.stereotype.Component;

@Component
public class RestValidator {

	//we can use generic but this will help us to add more validation in the future


	public void validateForCreate(MemberDTO dto) throws MMSRestException {
		coreValidator(dto.getId());
	}

	public void validateForCreate(MeetingDTO dto) throws MMSRestException {
		coreValidator(dto.getId());
	}

	public void validateForCreate(PaymentDTO dto) throws MMSRestException {
		coreValidator(dto.getId());
	}

	public void validateForCreate(ExpenseDTO dto) throws MMSRestException {
		coreValidator(dto.getId());
	}

	public void validateForCreate(VendorDTO dto) throws MMSRestException {
		coreValidator(dto.getId());
	}

	public void validateForCreate(PaymentTypeDTO dto) throws MMSRestException {
		coreValidator(dto.getId());
	}

	public void validateForCreate(ExpenseTypeDTO dto) throws MMSRestException {
		coreValidator(dto.getId());
	}

	public void validateForCreate(FamilyDTO dto) throws MMSRestException {
		coreValidator(dto.getId());
	}

	private void coreValidator(Long id) throws MMSRestException {
		if(id != null && id != 0){
			throw new MMSRestException("Create Operation shouldn't have Id", ErrorType.INVALID_INPUT);
		}
	}
}
