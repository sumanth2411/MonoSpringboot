package com.techlabs.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.techlabs.app.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao{

	private EntityManager entityManager;
	
	public StudentDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}



	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		TypedQuery<Student> query=entityManager.createQuery("select s from Student s",Student.class);
		List<Student> studentList = query.getResultList();
		return studentList;
	}



	@Override
	public Student getStudentByRollno(int rollno) {
		// TODO Auto-generated method stub
		Student student = entityManager.find(Student.class, rollno);
		return student;
	}



	@Override
	@Transactional
	public Student save(Student student) {
		// TODO Auto-generated method stub
		return entityManager.merge(student);
	}



	@Override
	@Transactional
	public Student saveAndUpdate(Student student) {
		// TODO Auto-generated method stub
		return entityManager.merge(student);
	}



	@Override
	@Transactional
	public void deleteStudent(int rollno) {
		// TODO Auto-generated method stub
		 Student student=entityManager.find(Student.class, rollno);
		 entityManager.remove(student);
	}

}
