package com.atl.church.mms.com.atl.church.mms.service;

import com.atl.church.mms.com.atl.church.mms.data.ExpenseRepo;
import com.atl.church.mms.com.atl.church.mms.data.ExpenseTypeRepo;
import com.atl.church.mms.com.atl.church.mms.domain.Expense;
import com.atl.church.mms.com.atl.church.mms.domain.ExpenseType;
import com.atl.church.mms.com.atl.church.mms.domain.TransactionStatus;
import com.atl.church.mms.com.atl.church.mms.exception.ErrorType;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;
    @Autowired
    private ExpenseTypeRepo expenseTypeRepo;
    @Autowired
    private VendorService vendorService;

    @Override
    public Expense getExpense(Long id) throws MMSServiceException {
        return expenseRepo.findById(id).orElseThrow(()-> new MMSServiceException("Expense not found", ErrorType.NOT_FOUND));
    }

    @Override
    public Expense createExpense(Long vendorId,Long expenseTypeId, Expense expense) throws MMSServiceException {
        expense.setVendor(vendorService.getVendor(vendorId));
        expense.setExpenseType(expenseTypeRepo.findById(expenseTypeId).orElseThrow(()-> new MMSServiceException("Expense type not found", ErrorType.NOT_FOUND)));
        return expenseRepo.save(expense);
    }

    @Override
    public Expense updateExpense(Long vendorId,Long expenseTypeId,Expense expense) throws MMSServiceException {
        getExpense(expense.getId());
        expense.setVendor(vendorService.getVendor(vendorId));
        expense.setExpenseType(expenseTypeRepo.findById(expenseTypeId).orElseThrow(()-> new MMSServiceException("Expense Type not found", ErrorType.NOT_FOUND)));
        return expenseRepo.save(expense);
    }

    @Override
    public boolean deleteExpense(Long id) throws MMSServiceException {
        Expense expense =  getExpense(id);
        expense.setStatus(TransactionStatus.VOID);
        expenseRepo.save(expense);
        return true;
    }

    @Override
    public ExpenseType getExpenseType(Long id) throws MMSServiceException {
        return expenseTypeRepo.findById(id).orElseThrow(()-> new MMSServiceException("Expense type not found", ErrorType.NOT_FOUND));
    }

    @Override
    public ExpenseType createExpenseType(ExpenseType expenseType) {
        return expenseTypeRepo.save(expenseType);
    }

    @Override
    public ExpenseType updateExpenseType(ExpenseType expenseType) throws MMSServiceException {
        getExpenseType(expenseType.getId());
        return expenseTypeRepo.save(expenseType);
    }

    @Override
    public boolean deleteExpenseType(Long id) throws MMSServiceException {
        ExpenseType expenseType = getExpenseType(id);
        expenseTypeRepo.delete(expenseType);
        return true;
    }
}
