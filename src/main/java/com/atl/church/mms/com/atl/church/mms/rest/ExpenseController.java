package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.RestValidator;
import com.atl.church.mms.com.atl.church.mms.domain.Expense;
import com.atl.church.mms.com.atl.church.mms.domain.ExpenseType;
import com.atl.church.mms.com.atl.church.mms.dto.ExpenseDTO;
import com.atl.church.mms.com.atl.church.mms.dto.ExpenseTypeDTO;
import com.atl.church.mms.com.atl.church.mms.exception.MMSRestException;
import com.atl.church.mms.com.atl.church.mms.exception.MMSServiceException;
import com.atl.church.mms.com.atl.church.mms.factory.ExpenseFactory;
import com.atl.church.mms.com.atl.church.mms.factory.ExpenseTypeFactory;
import com.atl.church.mms.com.atl.church.mms.service.ExpenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/expense")
@Api(value="expense", description="Operations expense registering")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private ExpenseFactory expenseFactory;
    @Autowired
    private ExpenseTypeFactory expenseTypeFactory;
    @Autowired
    private RestValidator restValidator;

    @ApiOperation(value = "Get a Expense by Id")
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> get(@PathVariable @Min(0) Long id) throws MMSServiceException {
        Expense expense = expenseService.getExpense(id);
        return new ResponseEntity<>(expenseFactory.toDto(expense),HttpStatus.OK);
    }
    @ApiOperation(value = "Create a expense entry")
    @PostMapping("/vendor/{vendorId}/expenseType/{expenseTypeId}/")
    public ResponseEntity<ExpenseDTO> create(@PathVariable @Min(0) Long vendorId, @PathVariable @Min(0) Long expenseTypeId, @Valid @RequestBody ExpenseDTO expenseDTO) throws URISyntaxException, MMSServiceException, MMSRestException {
        restValidator.validateForCreate(expenseDTO);
        Expense expense = expenseService.createExpense(vendorId,expenseTypeId,expenseFactory.toDomain(expenseDTO));
        MultiValueMap<String,String> headers = new HttpHeaders();
        ((HttpHeaders) headers).setLocation(new URI("/expense/"+ expense.getId()));
        ResponseEntity responseEntity = new ResponseEntity(expenseFactory.toDto(expense),headers,HttpStatus.CREATED);
       return responseEntity;

    }

    @ApiOperation(value = "Update a expense")
    @PutMapping("/vendor/{vendorId}/expenseType/{expenseTypeId}/")
    public ResponseEntity<ExpenseDTO> updatePayment(@PathVariable @Min(0) Long vendorId, @PathVariable @Min(0) Long expenseTypeId,@RequestBody @Valid ExpenseDTO expenseDTO) throws MMSServiceException {
        expenseService.updateExpense(vendorId,expenseTypeId,expenseFactory.toDomain(expenseDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Void a expense")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteExpense(@PathVariable @Min(0) Long id) throws MMSServiceException {
        return new ResponseEntity<>(expenseService.deleteExpense(id),HttpStatus.OK);
    }


    @ApiOperation(value = "Get a ExpenseType by Id")
    @GetMapping("/ExpenseType/{id}")
    public ResponseEntity<ExpenseTypeDTO> getExpenseType(@PathVariable @Min(0) Long id) throws MMSServiceException {
        ExpenseType expenseType = expenseService.getExpenseType(id);
        return new ResponseEntity<>(expenseTypeFactory.toDto(expenseType),HttpStatus.OK);
    }
    @ApiOperation(value = "Create a expenseType entry")
    @PostMapping("/ExpenseType/")
    public ResponseEntity<ExpenseTypeDTO> createExpenseType(@RequestBody @Valid ExpenseTypeDTO expenseTypeDTO) throws URISyntaxException, MMSRestException {
        restValidator.validateForCreate(expenseTypeDTO);
        ExpenseType expenseType = expenseService.createExpenseType(expenseTypeFactory.toDomain(expenseTypeDTO));
        return new ResponseEntity<>(expenseTypeFactory.toDto(expenseType),HttpStatus.OK);
    }

    @ApiOperation(value = "Update a expenseType")
    @PutMapping("/ExpenseType/")
    public ResponseEntity<ExpenseTypeDTO> updatePayment(@RequestBody @Valid ExpenseTypeDTO expenseTypeDTO) throws MMSServiceException {
        ExpenseType expenseType = expenseService.updateExpenseType(expenseTypeFactory.toDomain(expenseTypeDTO));
        return new ResponseEntity<>(expenseTypeFactory.toDto(expenseType),HttpStatus.OK);
    }

    @ApiOperation(value = "delete a expenseType")
    @DeleteMapping("/ExpenseType/{id}")
    public ResponseEntity<Boolean> deleteExpenseType(@PathVariable @Min(0) Long id) throws MMSServiceException {
        return new ResponseEntity<>(expenseService.deleteExpenseType(id),HttpStatus.OK);
    }
}
