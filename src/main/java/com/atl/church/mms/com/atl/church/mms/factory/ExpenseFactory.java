package com.atl.church.mms.com.atl.church.mms.factory;

import com.atl.church.mms.com.atl.church.mms.domain.Expense;
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
                .expenseType(dto.getExpenseTypeDTO().stream().map(eType -> this.expenseTypeFactory.toDomain(eType)).collect(Collectors.toList()))
                .build();
    }


    public ExpenseDTO toDto(Expense expense){
        return ExpenseDTO.builder()
                .id(expense.getId())
                .expenseDate(expense.getExpenseDate())
                .expenseName(expense.getExpenseName())
                .expenseNote(expense.getExpenseNote())
                .expenseTypeDTO(expense.getExpenseType().stream().map(eType -> this.expenseTypeFactory.toDto(eType)).collect(Collectors.toList()))
                .build();
    }
}
