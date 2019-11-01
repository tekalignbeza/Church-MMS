package com.atl.church.mms.com.atl.church.mms.domain;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@Builder
public class Expense {

    @Id
    @GeneratedValue
    private Long id;
    private String expenseName;
    private Date expenseDate;
    private String expenseNote;
    private TransactionStatus status;
    private TransactionMethod transactionMethod;
    @ManyToOne
    private Vendor vendor;
    @ManyToOne
    private ExpenseType expenseType;
    private Double amount;

    public Expense(){}

    public Expense(Long id, String expenseName, Date expenseDate, String expenseNote, TransactionStatus status, TransactionMethod transactionMethod, Vendor vendor, ExpenseType expenseType, Double amount) {
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
