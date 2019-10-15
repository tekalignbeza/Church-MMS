package com.atl.church.mms.com.atl.church.mms.domain;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class   ExpenseType {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    public ExpenseType() {
    }

    public ExpenseType(Long id,String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
