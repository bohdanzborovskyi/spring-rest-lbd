package com.zbodya.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zbodya.Model.Student;
import com.zbodya.Service.StudentService;

@RestController
@RequestMapping("api/students")
public class StudentController
{
	
	@Autowired
	StudentService studentServ;
	
	@GetMapping("/")
	public List<Student>getAllStudents()
	{
		return studentServ.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) 
	{
		return studentServ.getStudentById(id);
	}
	
	@PutMapping("/edit")
	public Student editStudent(@RequestParam("last_name") String last_name, @RequestParam("age") int age, @RequestParam("id") Long id) 
	{
		return studentServ.editStudent(last_name, age, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id) 
	{
		studentServ.deleteStudent(id);
		return "Student was deleted";
	}
	
	@PostMapping(value = "/add", consumes = "application/json")
	public @ResponseBody Student addNewStudent(@RequestBody Student student) 
	{
		return studentServ.addStudent(student);
	}
	
	

}
