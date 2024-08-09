package com.techlabs.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techlabs.app.entity.Student;
import com.techlabs.app.exception.StudentApiException;
import com.techlabs.app.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository repository;

	@Override
	public List<Student> findAll() {
		return repository.findAll();
	}

	@Override
	public Student findById(Long id) {
		Optional<Student> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new StudentApiException(HttpStatus.NOT_FOUND, "Student with id was not found"+id);
		
	}

	@Transactional
	@Override
	public Student save(Student student) {
		return repository.save(student);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);

	}

	@Override
	public List<Student> saveAll(List<Student> studentList) {
		return repository.saveAll(studentList);
	}
}
