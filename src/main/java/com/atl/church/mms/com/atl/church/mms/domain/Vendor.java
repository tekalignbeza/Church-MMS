package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
public class Vendor {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    private Address address;


    public Vendor() {
    }

    public Vendor(Long id, String name,Address address) {
        this.id = id;
        this.name = name;
        this.address=address;
    }
}
