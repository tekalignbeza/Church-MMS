package com.atl.church.mms.com.atl.church.mms.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@Builder
public class Family {
	@Id
	@GeneratedValue
	long id;
	String name;
	@OneToOne
	Address address;
	@OneToMany(mappedBy="family")
	List<Member2> member2List;

	public Family(){}

	public Family(Long id,String name, Address address, List<Member2> member2List) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.member2List = member2List;
	}
}
