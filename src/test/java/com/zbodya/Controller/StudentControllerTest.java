package com.zbodya.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zbodya.Model.Student;
import com.zbodya.Model.Subject;
import com.zbodya.Service.StudentService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    @Test
    void testGetAllStudents() throws Exception {
        when(this.studentService.getAllStudents()).thenReturn(new ArrayList<Student>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students/");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllStudents2() throws Exception {
        when(this.studentService.getAllStudents()).thenReturn(new ArrayList<Student>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/students/");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetStudentById() throws Exception {
        Student student = new Student();
        student.setSubjects(new ArrayList<Subject>());
        student.setAge(1);
        student.setLast_name("Doe");
        student.setFirst_name("Jane");
        when(this.studentService.getStudentById((Long) any())).thenReturn(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"first_name\":\"Jane\",\"last_name\":\"Doe\",\"age\":1,\"subjects\":[]}"));
    }

    @Test
    void testEditStudent() throws Exception {
        Student student = new Student();
        student.setSubjects(new ArrayList<Subject>());
        student.setAge(1);
        student.setLast_name("Doe");
        student.setFirst_name("Jane");
        when(this.studentService.editStudent((String) any(), anyInt(), (Long) any())).thenReturn(student);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/students/edit");
        MockHttpServletRequestBuilder paramResult = putResult.param("age", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("id", String.valueOf(1L))
                .param("last_name", "foo");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"first_name\":\"Jane\",\"last_name\":\"Doe\",\"age\":1,\"subjects\":[]}"));
    }

    @Test
    void testAddNewStudent() throws Exception {
        Student student = new Student();
        student.setSubjects(new ArrayList<Subject>());
        student.setAge(1);
        student.setLast_name("Doe");
        student.setFirst_name("Jane");
        when(this.studentService.addStudent((Student) any())).thenReturn(student);

        Student student1 = new Student();
        student1.setSubjects(new ArrayList<Subject>());
        student1.setAge(1);
        student1.setLast_name("Doe");
        student1.setFirst_name("Jane");
        String content = (new ObjectMapper()).writeValueAsString(student1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/students/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"first_name\":\"Jane\",\"last_name\":\"Doe\",\"age\":1,\"subjects\":[]}"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(this.studentService).deleteStudent((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/students/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Student was deleted"));
    }

    @Test
    void testDeleteStudent2() throws Exception {
        doNothing().when(this.studentService).deleteStudent((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/students/delete/{id}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Student was deleted"));
    }
}

