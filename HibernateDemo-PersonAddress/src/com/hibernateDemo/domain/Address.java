package com.hibernateDemo.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String street,city,pincode;

	public Address() {
		super();
	}

	public Address(String street, String city, String pincode) {
		super();
		this.street = street;
		this.city = city;
		this.pincode = pincode;
	}
	
	

}
