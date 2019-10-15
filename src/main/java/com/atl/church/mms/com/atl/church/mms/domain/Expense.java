package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
public class Expense {

    @Id
    @GeneratedValue
    private Long id;
    private String expenseName;
    private Date expenseDate;
    private String expenseNote;
    @OneToMany
    private List<ExpenseType> expenseType;


    public Expense() {
    }

    public Expense(Long id,String expenseName, Date expenseDate, String expenseNote, List<ExpenseType> expenseType) {
        this.id = id;
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expenseNote = expenseNote;
        this.expenseType = expenseType;
    }
}
