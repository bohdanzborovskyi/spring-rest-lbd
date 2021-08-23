package com.zbodya.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbodya.Model.Student;
import com.zbodya.Model.Subject;
import com.zbodya.Repositories.StudentRepository;

@Service
public class StudentService 
{
	
	@Autowired
	StudentRepository studentRepo;

	public void addFiveStudentsToDB() 
	{
		List<Student> students = Arrays.asList(new Student("Piotr","Jacek",22).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY), 
				new Student("Jack","Wordks",33).addSubject(Subject.LAW),
				new Student("Andre", "Uoaa", 27).addSubject(Subject.LAW).addSubject(Subject.BIOLOGY),
				new Student("Pako","Alcazer", 26).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY),
				new Student("Dima","Kovalev", 23).addSubject(Subject.BIOLOGY).addSubject(Subject.LAW),
				new Student("Ad","Sparrow", 35).addSubject(Subject.LAW).addSubject(Subject.BIOLOGY),
				new Student("Adam","Taylor",44).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY),
				new Student("Udo","Gbenu",41).addSubject(Subject.LAW).addSubject(Subject.ALGEBRA), 
				new Student("Wers","Wahen",61).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY));
		
		studentRepo.saveAll(students);
	}
	
	public List<Student> getAllStudents()
	{
		return studentRepo.findAll();
	}
	
	public Student addStudent(Student student) 
	{
		return studentRepo.save(student);
	}
	
	public Student editStudent(String last_name, int age, Long id) 
	{
		Student editable = studentRepo.findById(id).get();
		editable.setAge(age);
		editable.setLast_name(last_name);
		studentRepo.save(editable);
		return editable;
	}
	
	public void deleteStudent(Long id) 
	{
		studentRepo.deleteById(id);
	}
	
	public Student getStudentById(Long id) 
	{
		return studentRepo.findById(id).get();
	}
	
}
