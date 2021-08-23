package com.zbodya.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbodya.Model.Student;
import com.zbodya.Model.Subject;
import com.zbodya.Model.Teacher;
import com.zbodya.Repositories.StudentRepository;
import com.zbodya.Repositories.TeacherRepository;

@Service
public class TeacherService
{

	@Autowired
	TeacherRepository teacherRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	public void addTeachers() 
	{
		List<Teacher> teachers = Arrays.asList(new Teacher("Adam","Traore",Subject.ALGEBRA), 
				new Teacher("Ignat","Rukov",Subject.BIOLOGY),new Teacher("Isaak","Belgard",Subject.LAW));
		teacherRepo.saveAll(teachers);
	}
	
	public List<Teacher> getAllTeachers()
	{
		return teacherRepo.findAll();
	}
	
	public void addTeacher(Teacher teacher) 
	{
		teacherRepo.save(teacher);
	}
	
	public void deleteTeacherById(Long id) 
	{
		teacherRepo.deleteById(id);
	}
	
	public Teacher getTeacherById(Long id) 
	{
		return teacherRepo.findById(id).get();
	}
	
	public List<Student> getStudentsByTeacherId(Long id)
	{
		return studentRepo.findBySubjects(teacherRepo.findById(id).get().getSubject());
	}
	
	public void deleteStudentFromClassByTeacherId(Long student_id, Long teacher_id) 
	{
		Subject subject = teacherRepo.findById(teacher_id).get().getSubject();
		Student student = studentRepo.findBySubjects(subject).stream().filter(s->s.getId().equals(student_id)).findAny().get();
		student.getSubjects().remove(subject);
		studentRepo.save(student);		
	}	
	
}
