package com.techlabs.myapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.techlabs.myapp.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository
public class StudentDaoImpl implements StudentDao{

	EntityManager entityManager;
	
	public StudentDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Student student) {
		// TODO Auto-generated method stub
		this.entityManager.persist(student);
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		TypedQuery<Student> query=entityManager.createQuery("select s from Student s",Student.class);
		List<Student> studentList = query.getResultList();
		return studentList;
	}

	@Override
	public Student getStudentByRollno(int i) {
		// TODO Auto-generated method stub
		Student student = entityManager.find(Student.class,i);
		
		return student;
	}

	@Override
	public List<Student> getStudentByName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s where name=?1",Student.class);
		query.setParameter(1, name);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateStudent(Student s) {
		// TODO Auto-generated method stub
		Student student=entityManager.find(Student.class, s.getRollNo());
		if(student!=null) {
			this.entityManager.merge(s);
		}
		else {
			System.out.println("Student rollNo does not exist ,rollNo:"+s.getRollNo());
		}
	}

	@Override
	@Transactional
	public void deleteStudent(int rollNo) {
		// TODO Auto-generated method stub
		Student student = entityManager.find(Student.class, rollNo);
		if(student!=null) {
			this.entityManager.remove(student);
		}
		else {
			System.out.println("Student with rollNO "+rollNo+"does not exist");
		}
	}

	@Override
	@Transactional
	public void updateStudentQuery(Student s) {
		// TODO Auto-generated method stub
		 Student student = entityManager.find(Student.class, s.getRollNo());
		 if(student!=null) {
			 Query query = entityManager.createQuery("update Student s set s.name=?1,s.percentage=?2 where s.rollNo=?3");
			 query.setParameter(1, s.getName());
			 query.setParameter(2, s.getPercentage());
			 query.setParameter(3, s.getRollNo());
			 query.executeUpdate();
			} 
		 else {
				System.out.println("Student with rollNO does not exist");
			}
		
		
	}

	
}
