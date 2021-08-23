package com.zbodya.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void addTeacher(@RequestBody Teacher teacher) 
	{
		teacherServ.addTeacher(teacher);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deeteTeacher(@PathVariable Long id) 
	{
		teacherServ.deleteTeacherById(id);
	}
	
	@GetMapping("/{id}")
	public Teacher getTeacher(@PathVariable Long id) 
	{
		return teacherServ.getTeacherById(id);
	}
	
	@GetMapping("/getStudents/{id}")
	public List<Student> getStudentsByTeacherId(@PathVariable Long id)
	{
		return teacherServ.getStudentsByTeacherId(id);
	}
	
	@DeleteMapping("/deleteStudent/{student_id},{teacher_id}")
	public void deleteStuddentFromTeacherClass(@PathVariable Long student_id, @PathVariable Long teacher_id) 
	{
		teacherServ.deleteStudentFromClassByTeacherId(student_id, teacher_id);
	}
	
	@GetMapping("/")
	public List<Teacher> getTeacher() 
	{
		return teacherServ.getAllTeachers();
	}
}
