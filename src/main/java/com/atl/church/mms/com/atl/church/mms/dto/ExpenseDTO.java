package com.atl.church.mms.com.atl.church.mms.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ExpenseDTO {

    private Long id;
    private String expenseName;
    private Date expenseDate;
    private String expenseNote;
    private List<ExpenseTypeDTO> expenseTypeDTO;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Long id, String expenseName, Date expenseDate, String expenseNote, List<ExpenseTypeDTO> expenseTypeDTO) {
        this.id = id;
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expenseNote = expenseNote;
        this.expenseTypeDTO = expenseTypeDTO;
    }
}
