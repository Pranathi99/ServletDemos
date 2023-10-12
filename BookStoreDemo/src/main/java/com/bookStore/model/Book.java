package com.bookStore.model;

import javax.persistence.Entity;

@Entity
public class Book {
	
	private Long id;
	
	private String name;
	
	private Author author;
}
