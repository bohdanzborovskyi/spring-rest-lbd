package com.zbodya;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbodya.Controller.StudentController;
import com.zbodya.Model.Student;
import com.zbodya.Model.Subject;
import com.zbodya.Service.StudentService;
import com.zbodya.Service.TeacherService;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest
{
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	StudentService studServ;
	
	@MockBean 
	TeacherService teacherServ;
	
	@Test
	public void getAllStudents() 
	{
		List<Student> students = Lists.newArrayList(new Student("Piotr","Jacek",22).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY), 
				new Student("Jack","Wordks",33).addSubject(Subject.LAW),
				new Student("Andre", "Uoaa", 27).addSubject(Subject.LAW).addSubject(Subject.BIOLOGY),
				new Student("Pako","Alcazer", 26).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY),
				new Student("Dima","Kovalev", 23).addSubject(Subject.BIOLOGY).addSubject(Subject.LAW),
				new Student("Ad","Sparrow", 35).addSubject(Subject.LAW).addSubject(Subject.BIOLOGY),
				new Student("Adam","Taylor",44).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY),
				new Student("Udo","Gbenu",41).addSubject(Subject.LAW).addSubject(Subject.ALGEBRA), 
				new Student("Wers","Wahen",61).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY));
		when(studServ.getAllStudents()).thenReturn(students);
		try {
			mvc.perform(MockMvcRequestBuilders
					.get("http://localhost:8080/api/students/")
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$[*]").isArray())
					.andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(22))
					.andExpect(MockMvcResultMatchers.jsonPath("$[0].first_name").value("Piotr"))
					.andExpect(MockMvcResultMatchers.jsonPath("$[0].last_name").value("Jacek"));
		} catch (Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	@Test 
	public void getStudentById() 
	{
		when(studServ.getStudentById(1L)).thenReturn(new Student("Job","Nastack",25));
		try {
			mvc.perform(MockMvcRequestBuilders
					.get("http://localhost:8080/api/students/1")
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())					
					.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(25))
					.andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("Job"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.last_name").value("Nastack"));
		} catch (Exception e)
		{			
			e.printStackTrace();
		}
	}
	
	@Test 
	public void createStudent() throws Exception 
	{
		when(studServ.addStudent(Mockito.any())).thenReturn(new Student("Jack","Sparrow",33));
		mvc.perform(MockMvcRequestBuilders
				.post("http://localhost:8080/api/students/add")	
				.accept(MediaType.APPLICATION_JSON)
				.content(objectToString(new Student("Jack","Sparrow",33)))
				.contentType(MediaType.APPLICATION_JSON))	
				.andDo(print())
	//			.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(33));				
	}
	
	@Test
	public void deleteStudent() throws Exception 
	{
		mvc.perform(MockMvcRequestBuilders
				.delete("http://localhost:8080/api/students/delete/1"))
				.andDo(print())
				.andExpect(status().isOk());
				
	}
	
	@Test
	public void editStudent() throws Exception 
	{
		when(studServ.editStudent(Mockito.anyString(), Mockito.anyInt(), Mockito.anyLong())).thenReturn(new Student("Jack", "Sparrow", 33));
		mvc.perform(MockMvcRequestBuilders
				.put("http://localhost:8080/api/students/edit?last_name=Jack&age=44&id=2"))
//				.param("last_name", "Jack")
//				.param("age", "33")
//				.param("id","1"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	public static String objectToString(Object obj) 
	{
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	

}
