package com.zbodya;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zbodya.Service.StudentService;
import com.zbodya.Service.TeacherService;

@SpringBootApplication
public class SpringRestLbdApplication {

	@Autowired
	StudentService studServ;
	
	@Autowired
	TeacherService teacherServ;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestLbdApplication.class, args);
	}
	
	@PostConstruct
	public void postConstruct() 
	{
		studServ.addFiveStudentsToDB();
		studServ.getAllStudents().forEach(s->System.out.println(s.getId() +  " " + s.getFirst_name() + " " + s.getLast_name() + " " + s.getAge() + " " + s.getSubjects()));
		teacherServ.addTeachers();
		teacherServ.getAllTeachers().forEach(t->System.out.println(t.getFirst_name() + " " + t.getLast_name() + " " + t.getId() + " " + t.getSubject()));
		teacherServ.getStudentsByTeacherId(11L).forEach(s->System.out.println(s.getLast_name() + " " + s.getSubjects()));
		teacherServ.deleteStudentFromClassByTeacherId(9L,11L);
		System.out.println(studServ.getStudentById(9L).getSubjects());

	}
	
	

}
