package com.zbodya.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zbodya.Model.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> 
{
	List<Teacher> findAll();
}
