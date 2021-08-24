package com.zbodya.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zbodya.Model.Student;
import com.zbodya.Model.Teacher;
import com.zbodya.Service.TeacherService;

@RestController
@RequestMapping("api/teachers")
public class TeacherController 
{
	@Autowired
	TeacherService teacherServ;
	
	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) 
	{
		return new ResponseEntity<Teacher>(teacherServ.addTeacher(teacher),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deeteTeacher(@PathVariable Long id) 
	{
		teacherServ.deleteTeacherById(id);
		return new ResponseEntity<String>("Teacher was deleted succesfully",HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) 
	{
		return new ResponseEntity<Teacher>(teacherServ.getTeacherById(id),HttpStatus.OK);
	}
	
	@GetMapping("/getStudents/{id}")
	public ResponseEntity<List<Student>> getStudentsByTeacherId(@PathVariable Long id)
	{
		return new ResponseEntity<List<Student>>(teacherServ.getStudentsByTeacherId(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteStudent/{student_id},{teacher_id}")
	public ResponseEntity<String> deleteStuddentFromTeacherClass(@PathVariable Long student_id, @PathVariable Long teacher_id) 
	{
		teacherServ.deleteStudentFromClassByTeacherId(student_id, teacher_id);
		return new ResponseEntity<String>("Student was succesfully deletted from Teacher class!",HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Teacher>> getTeachers() 
	{
		return new ResponseEntity<List<Teacher>>(teacherServ.getAllTeachers(),HttpStatus.OK);
	}
}
