package com.atl.church.mms.com.atl.church.mms.factory;

import com.atl.church.mms.com.atl.church.mms.domain.Expense;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionMethod;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionStatus;
import com.atl.church.mms.com.atl.church.mms.dto.ExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ExpenseFactory {

    @Autowired
    private ExpenseTypeFactory expenseTypeFactory;

    public Expense toDomain(ExpenseDTO dto){
        return Expense.builder()
                .id(dto.getId())
                .expenseDate(dto.getExpenseDate())
                .expenseName(dto.getExpenseName())
                .expenseNote(dto.getExpenseNote())
                .amount(dto.getAmount())
                .expenseType(expenseTypeFactory.toDomain(dto.getExpenseType()))
                .status(dto.getStatus() != null ? TransactionStatus.valueOf(dto.getStatus()): null)
                .transactionMethod(dto.getTransactionMethod() != null ? TransactionMethod.valueOf(dto.getTransactionMethod()) : null)
                .vendor(dto.getVendor())
                .build();
    }


    public ExpenseDTO toDto(Expense expense){
        return ExpenseDTO.builder()
                .id(expense.getId())
                .expenseDate(expense.getExpenseDate())
                .expenseName(expense.getExpenseName())
                .expenseNote(expense.getExpenseNote())
                .amount(expense.getAmount())
                .expenseType(expenseTypeFactory.toDto(expense.getExpenseType()))
                .status(expense.getStatus() != null ? expense.getStatus().name() : null)
                .transactionMethod(expense.getTransactionMethod() != null ? expense.getTransactionMethod().name() : null)
                .vendor(expense.getVendor())
                .build();
    }
}
