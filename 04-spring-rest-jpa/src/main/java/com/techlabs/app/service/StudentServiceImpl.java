package com.techlabs.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techlabs.app.entity.Student;
import com.techlabs.app.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentDao;
	
	public StudentServiceImpl(StudentRepository studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentDao.findAll();
	}

	@Override
	public Student getStudentByRollno(int rollno) {
		// TODO Auto-generated method stub
		return studentDao.findById(rollno).get();
	}

	@Override
	@Transactional
	public Student save(Student student) {
		// TODO Auto-generated method stub
		return studentDao.save(student);
	}

	@Override
	@Transactional
	public Student saveAndUpdate(Student student) {
		// TODO Auto-generated method stub
		return studentDao.save(student);
	}

	@Override
	@Transactional
	public void deleteStudent(int rollno) {
		// TODO Auto-generated method stub
		studentDao.deleteById(rollno);
	}

}
