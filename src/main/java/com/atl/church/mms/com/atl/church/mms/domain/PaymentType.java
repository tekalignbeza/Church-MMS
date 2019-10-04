package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class PaymentType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    public PaymentType() {
    }

    public PaymentType(Long id,String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
