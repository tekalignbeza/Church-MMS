package com.atl.church.mms.com.atl.church.mms.rest;


import com.atl.church.mms.com.atl.church.mms.domain.Expense;
import com.atl.church.mms.com.atl.church.mms.dto.ExpenseDTO;
import com.atl.church.mms.com.atl.church.mms.factory.ExpenseFactory;
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

    @ApiOperation(value = "Get a Expense by Id")
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> get(@PathVariable String id){
        Expense expense = expenseService.getExpense(Long.parseLong(id));
        if( null == expense)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(expenseFactory.toDto(expense),HttpStatus.OK);
    }
    @ApiOperation(value = "Create a expense entry")
    @PostMapping("/")
    public ResponseEntity<ExpenseDTO> create(@RequestBody ExpenseDTO expenseDTO) throws URISyntaxException {
        Expense expense = expenseService.getExpense(expenseDTO.getId());
        if( null == expense)
            return new ResponseEntity<>(HttpStatus.IM_USED);




        expenseService.createExpense(expenseFactory.toDomain(expenseDTO));
        MultiValueMap<String,String> headers = new HttpHeaders();
        ((HttpHeaders) headers).setLocation(new URI("/expense/"+ expense.getId()));
        ResponseEntity responseEntity = new ResponseEntity(null,headers,HttpStatus.CREATED);

       return responseEntity;

    }

    @ApiOperation(value = "Update a expense")
    @PutMapping("/")
    public ResponseEntity<ExpenseDTO> updatePayemnt(@RequestBody ExpenseDTO expenseDTO){

        if( null == expenseService.getExpense(expenseDTO.getId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        expenseService.updateExpense(expenseFactory.toDomain(expenseDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Void a expense")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteExpense(@PathVariable String id){
        if( null == expenseService.getExpense(Long.parseLong(id)))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Boolean>(expenseService.deleteExpense(Long.parseLong(id)),HttpStatus.OK);
    }
}
