package com.hibernateDemo.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Guide {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="staff_id")
	private String staffId;
	
	private String name;
	
	private Integer salary;
	
	@OneToMany(mappedBy="guide")
	private Set<Student>students=new HashSet<Student>();

	public Guide() {
		super();
	}
	
	public Guide(String staffId, String name, Integer salary) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
	}
	
	public Set<Student> getStudents(){
		return students;
	}
	
}
