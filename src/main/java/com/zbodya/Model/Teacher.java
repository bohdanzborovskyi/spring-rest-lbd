package com.zbodya.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Teacher 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String first_name;
	
	@Column
	private String last_name;
	
	@Enumerated
	private Subject subject;

	public Long getId() {
		return id;
	}
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher(String first_name, String last_name, Subject subject) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.subject = subject;
	}

	public Teacher() {
		super();
	}
	
	
	
}
