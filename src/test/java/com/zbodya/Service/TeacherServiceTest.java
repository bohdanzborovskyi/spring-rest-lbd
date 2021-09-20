package com.zbodya.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zbodya.Model.Student;
import com.zbodya.Model.Subject;
import com.zbodya.Model.Teacher;
import com.zbodya.Repositories.StudentRepository;
import com.zbodya.Repositories.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TeacherService.class})
@ExtendWith(SpringExtension.class)
class TeacherServiceTest {
    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @Test
    void testAddTeachers() {
        when(this.teacherRepository.saveAll((Iterable<Teacher>) any()))
                .thenReturn((Iterable<Teacher>) mock(Iterable.class));
        this.teacherService.addTeachers();
        verify(this.teacherRepository).saveAll((Iterable<Teacher>) any());
        assertTrue(this.teacherService.getAllTeachers().isEmpty());
    }

    @Test
    void testGetAllTeachers() {
        ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
        when(this.teacherRepository.findAll()).thenReturn(teacherList);
        List<Teacher> actualAllTeachers = this.teacherService.getAllTeachers();
        assertSame(teacherList, actualAllTeachers);
        assertTrue(actualAllTeachers.isEmpty());
        verify(this.teacherRepository).findAll();
    }

    @Test
    void testAddTeacher() {
        Teacher teacher = new Teacher();
        teacher.setLast_name("Doe");
        teacher.setFirst_name("Jane");
        teacher.setSubject(Subject.ALGEBRA);
        when(this.teacherRepository.save((Teacher) any())).thenReturn(teacher);

        Teacher teacher1 = new Teacher();
        teacher1.setLast_name("Doe");
        teacher1.setFirst_name("Jane");
        teacher1.setSubject(Subject.ALGEBRA);
        assertSame(teacher, this.teacherService.addTeacher(teacher1));
        verify(this.teacherRepository).save((Teacher) any());
        assertTrue(this.teacherService.getAllTeachers().isEmpty());
    }

    @Test
    void testDeleteTeacherById() {
        doNothing().when(this.teacherRepository).deleteById((Long) any());
        this.teacherService.deleteTeacherById(123L);
        verify(this.teacherRepository).deleteById((Long) any());
        assertTrue(this.teacherService.getAllTeachers().isEmpty());
    }

    @Test
    void testGetTeacherById() {
        Teacher teacher = new Teacher();
        teacher.setLast_name("Doe");
        teacher.setFirst_name("Jane");
        teacher.setSubject(Subject.ALGEBRA);
        Optional<Teacher> ofResult = Optional.<Teacher>of(teacher);
        when(this.teacherRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(teacher, this.teacherService.getTeacherById(123L));
        verify(this.teacherRepository).findById((Long) any());
        assertTrue(this.teacherService.getAllTeachers().isEmpty());
    }

    @Test
    void testGetTeacherById2() {
        Teacher teacher = new Teacher();
        teacher.setLast_name("Doe");
        teacher.setFirst_name("Jane");
        teacher.setSubject(Subject.ALGEBRA);
        Optional<Teacher> ofResult = Optional.<Teacher>of(teacher);
        when(this.teacherRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(teacher, this.teacherService.getTeacherById(0L));
        verify(this.teacherRepository).findById((Long) any());
        assertTrue(this.teacherService.getAllTeachers().isEmpty());
    }

    @Test
    void testGetStudentsByTeacherId() {
        Teacher teacher = new Teacher();
        teacher.setLast_name("Doe");
        teacher.setFirst_name("Jane");
        teacher.setSubject(Subject.ALGEBRA);
        Optional<Teacher> ofResult = Optional.<Teacher>of(teacher);
        when(this.teacherRepository.findById((Long) any())).thenReturn(ofResult);
        ArrayList<Student> studentList = new ArrayList<Student>();
        when(this.studentRepository.findBySubjects((Subject) any())).thenReturn(studentList);
        List<Student> actualStudentsByTeacherId = this.teacherService.getStudentsByTeacherId(123L);
        assertSame(studentList, actualStudentsByTeacherId);
        assertTrue(actualStudentsByTeacherId.isEmpty());
        verify(this.teacherRepository).findById((Long) any());
        verify(this.studentRepository).findBySubjects((Subject) any());
        assertTrue(this.teacherService.getAllTeachers().isEmpty());
    }

    @Test
    void testGetStudentsByTeacherId2() {
        Teacher teacher = new Teacher();
        teacher.setLast_name("Doe");
        teacher.setFirst_name("Jane");
        teacher.setSubject(Subject.ALGEBRA);
        Optional<Teacher> ofResult = Optional.<Teacher>of(teacher);
        when(this.teacherRepository.findById((Long) any())).thenReturn(ofResult);

        Student student = new Student();
        student.setSubjects(new ArrayList<Subject>());
        student.setAge(0);
        student.setLast_name("Doe");
        student.setFirst_name("Jane");

        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(student);
        when(this.studentRepository.findBySubjects((Subject) any())).thenReturn(studentList);
        List<Student> actualStudentsByTeacherId = this.teacherService.getStudentsByTeacherId(123L);
        assertSame(studentList, actualStudentsByTeacherId);
        assertEquals(1, actualStudentsByTeacherId.size());
        verify(this.teacherRepository).findById((Long) any());
        verify(this.studentRepository).findBySubjects((Subject) any());
        assertTrue(this.teacherService.getAllTeachers().isEmpty());
    }
}

