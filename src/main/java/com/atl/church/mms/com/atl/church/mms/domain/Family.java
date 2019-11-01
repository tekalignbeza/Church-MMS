package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Data
@Entity
@Builder
public class Family {
	@Id
	@GeneratedValue
	Long id;
	String name;
	@OneToOne
	Address address;

	public Family(){}

	public Family(long id,String name, Address address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
}
