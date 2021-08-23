package com.zbodya;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbodya.Controller.TeacherController;
import com.zbodya.Model.Student;
import com.zbodya.Model.Subject;
import com.zbodya.Model.Teacher;
import com.zbodya.Service.StudentService;
import com.zbodya.Service.TeacherService;

@RunWith(SpringRunner.class)
@WebMvcTest(TeacherController.class)
public class TeacherControllerTest
{
	
	@Autowired
	private MockMvc mvc;	
	
	@MockBean 
	TeacherService teacherServ;
	
	@MockBean
	StudentService studServ;
	
	@Test
	public void getTeacherById() throws Exception 
	{
		when(teacherServ.getTeacherById(Mockito.any())).thenReturn(new Teacher("Korney","Tompsom",Subject.ALGEBRA));
		
		mvc.perform(MockMvcRequestBuilders
				.get("/api/teachers/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("Korney"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.last_name").value("Tompsom"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.subject").exists());					
	}
	
	@Test 
	public void getStudentsByTeacherId() throws Exception 
	{
		when(teacherServ.getStudentsByTeacherId(Mockito.any())).thenReturn(Lists.list(
				new Student("Piotr","Jacek",22).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY), 
				new Student("Jack","Wordks",33).addSubject(Subject.LAW),
				new Student("Andre", "Uoaa", 27).addSubject(Subject.LAW).addSubject(Subject.BIOLOGY),
				new Student("Pako","Alcazer", 26).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY),
				new Student("Dima","Kovalev", 23).addSubject(Subject.BIOLOGY).addSubject(Subject.LAW),
				new Student("Ad","Sparrow", 35).addSubject(Subject.LAW).addSubject(Subject.BIOLOGY),
				new Student("Adam","Taylor",44).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY),
				new Student("Udo","Gbenu",41).addSubject(Subject.LAW).addSubject(Subject.ALGEBRA), 
				new Student("Wers","Wahen",61).addSubject(Subject.ALGEBRA).addSubject(Subject.BIOLOGY)));
		
		mvc.perform(MockMvcRequestBuilders
				.get("/api/teachers/getStudents/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].first_name").value("Piotr"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].last_name").value("Jacek"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(22));				
	}
	
	@Test 
	public void getAllTeachers() throws Exception 
	{
		when(teacherServ.getAllTeachers()).thenReturn(Lists.list(
				new Teacher("Adam","Traore",Subject.ALGEBRA), 
				new Teacher("Ignat","Rukov",Subject.BIOLOGY),
				new Teacher("Isaak","Belgard",Subject.LAW)));
		
		mvc.perform(MockMvcRequestBuilders
				.get("/api/teachers/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].first_name").value("Adam"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].last_name").value("Traore"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].subject").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*]").isArray());				
	}
	
	@Test
	public void addTeacher() throws Exception 
	{
		when(teacherServ.addTeacher(Mockito.any())).thenReturn(new Teacher("Jacek","Jacek",Subject.ALGEBRA));
		
		mvc.perform(MockMvcRequestBuilders
				.post("/api/teachers/add")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectToString(new Teacher("Jacek","Jacek",Subject.ALGEBRA)))
				.contentType(MediaType.APPLICATION_JSON))	
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.first_name").value("Jacek"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.last_name").value("Jacek"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.subject").exists());				
	}
	
	@Test
	public void deleteTeacher() throws Exception 
	{
		mvc.perform(MockMvcRequestBuilders
				.delete("/api/teachers/delete/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteStudentFromTeacherClass() throws Exception 
	{
		mvc.perform(MockMvcRequestBuilders
				.delete("/api/teachers/deleteStudent/1,5"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	
	public static String objectToString(Object obj) 
	{
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) 
		{		
			throw new RuntimeException(e);
		}
	}

}
