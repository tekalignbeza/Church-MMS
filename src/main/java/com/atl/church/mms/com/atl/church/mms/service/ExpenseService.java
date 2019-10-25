package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Expense;

public interface ExpenseService {

    Expense getExpense(Long id);

    Expense createExpense(Expense expense);

    Expense updateExpense(Expense payment);

    boolean deleteExpense(Long id);

}
