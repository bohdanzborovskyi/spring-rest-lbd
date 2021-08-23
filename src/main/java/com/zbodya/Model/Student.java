package com.zbodya.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student
{	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String first_name;
	@Column
	private String last_name;
	@Column
	private int age;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Subject> subjects;
	
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}	
	
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public Student(String first_name, String last_name, int age) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.subjects = new ArrayList<Subject>();
	}
	public Student() {
		super();
		this.subjects = new ArrayList<Subject>();
	}
	
	public Student addSubject(Subject subject) 
	{
		this.subjects.add(subject);
		return this;
	}
	
	
	
	
	
}
