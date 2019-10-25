package com.atl.church.mms.com.atl.church.mms.dto;

import com.atl.church.mms.com.atl.church.mms.domain.ExpenseType;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionMethod;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionStatus;
import com.atl.church.mms.com.atl.church.mms.domain.Vendor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ExpenseDTO {

    private Long id;
    private String expenseName;
    private Date expenseDate;
    private String expenseNote;
    private String status;
    private String transactionMethod;
    private Vendor vendor;
    private ExpenseTypeDTO expenseType;
    private Double amount;

    public ExpenseDTO(Long id, String expenseName, Date expenseDate, String expenseNote, String status, String transactionMethod, Vendor vendor, ExpenseTypeDTO expenseType, Double amount) {
        this.id = id;
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expenseNote = expenseNote;
        this.status = status;
        this.transactionMethod = transactionMethod;
        this.vendor = vendor;
        this.expenseType = expenseType;
        this.amount = amount;
    }
}
