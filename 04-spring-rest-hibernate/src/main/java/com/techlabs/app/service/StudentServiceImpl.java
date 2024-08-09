package com.techlabs.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techlabs.app.dao.StudentDao;
import com.techlabs.app.entity.Student;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentDao studentDao;
	
	public StudentServiceImpl(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentDao.getAllStudents();
	}

	@Override
	public Student getStudentByRollno(int rollno) {
		// TODO Auto-generated method stub
		return studentDao.getStudentByRollno(rollno);
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
		return studentDao.saveAndUpdate(student);
	}

	@Override
	@Transactional
	public void deleteStudent(int rollno) {
		// TODO Auto-generated method stub
		
	}

}
