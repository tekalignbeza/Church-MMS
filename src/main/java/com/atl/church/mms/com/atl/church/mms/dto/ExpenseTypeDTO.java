package com.atl.church.mms.com.atl.church.mms.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
public class ExpenseTypeDTO {

    private Long id;
    @NotEmpty(message = "Please provide a name")
    private String name;
    private String description;


    public ExpenseTypeDTO() {
    }

    public ExpenseTypeDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
