package com.zbodya.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<Student>>getAllStudents()
	{
		return new ResponseEntity<List<Student>>(studentServ.getAllStudents(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) 
	{
		return new ResponseEntity<Student>(studentServ.getStudentById(id),HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Student> editStudent(@RequestParam("last_name") String last_name, @RequestParam("age") int age, @RequestParam("id") Long id) 
	{
		return new ResponseEntity<Student>(studentServ.editStudent(last_name, age, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) 
	{
		studentServ.deleteStudent(id);
		return new ResponseEntity<String>("Student was deleted",HttpStatus.OK);
	}
	
	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<Student> addNewStudent(@RequestBody Student student) 
	{
		return new ResponseEntity<Student>(studentServ.addStudent(student),HttpStatus.OK);
	}
	
	

}
