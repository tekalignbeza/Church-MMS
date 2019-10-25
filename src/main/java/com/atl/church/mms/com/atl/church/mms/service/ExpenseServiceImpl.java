package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.ExpenseRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Expense;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;

    @Override
    public Expense getExpense(Long id) {
        Optional<Expense> expense = expenseRepo.findById(id);
        return expense.isPresent() ? expense.get() : null ;
    }

    @Override
    public Expense createExpense(Expense expense) {
        if( expenseRepo.findById(expense.getId()).isPresent())
            return null;
        return expenseRepo.save(expense);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        if( ! expenseRepo.findById(expense.getId()).isPresent())
            return null;
        return expenseRepo.save(expense);
    }

    @Override
    public boolean deleteExpense(Long id) {
        Optional<Expense> expense =  expenseRepo.findById(id);
        if( !expense.isPresent())
            return false;
        expense.get().setStatus(TransactionStatus.VOID);
        expenseRepo.save(expense.get());
        return true;
    }
}
