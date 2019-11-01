package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.domain.Expense;
import com.atl.church.mms.com.atl.church.mms.domain.ExpenseType;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;

public interface ExpenseService {

    Expense getExpense(Long id) throws MMSServiceException;

    Expense createExpense(Long vendorId,Long expenseTypeId,Expense expense) throws MMSServiceException;

    Expense updateExpense(Long vendorId,Long expenseTypeId,Expense expense) throws MMSServiceException;

    boolean deleteExpense(Long id) throws MMSServiceException;

    ExpenseType getExpenseType(Long id) throws MMSServiceException;

    ExpenseType createExpenseType(ExpenseType expenseType);

    ExpenseType updateExpenseType(ExpenseType expenseType) throws MMSServiceException;

    boolean deleteExpenseType(Long id) throws MMSServiceException;

}
