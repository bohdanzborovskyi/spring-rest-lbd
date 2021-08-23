package com.zbodya.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zbodya.Model.Student;
import com.zbodya.Model.Subject;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>
{
	
	List<Student> findAll();
	List<Student> findBySubjects(Subject subject);

}
