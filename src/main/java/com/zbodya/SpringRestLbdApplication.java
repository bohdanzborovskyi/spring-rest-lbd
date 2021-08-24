package com.zbodya;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.zbodya.Service.AuthorizationService;
import com.zbodya.Service.StudentService;
import com.zbodya.Service.TeacherService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ServletComponentScan
@SpringBootApplication
public class SpringRestLbdApplication {

	@Autowired
	StudentService studServ;
	
	@Autowired
	TeacherService teacherServ;
	
	@Autowired
	AuthorizationService authServ;
	
	
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
	//	System.out.println(studServ.getStudentById(9L).getSubjects());
		try 
		{
			System.out.println("FALSE: " + authServ.isAuthorized(new URI("api/teachers").toString(), "STUDENT_ROLE"));
			System.out.println("TRUE: " + authServ.isAuthorized(new URI("api/teachers").toString(), "TEACHER_ROLE"));
			System.out.println("FALSE: " + authServ.isAuthorized(new URI("api/teachers").toString(), "ROLE"));
			System.out.println("TRUE: " + authServ.isAuthorized(new URI("api/students").toString(), "STUDENT_ROLE"));
			System.out.println("TRUE: " + authServ.isAuthorized(new URI("api/students").toString(), "TEACHER_ROLE"));
			System.out.println("FALSE: " + authServ.isAuthorized(new URI("api/students").toString(), "ROLE"));
		} catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}

	}
	
	

}
