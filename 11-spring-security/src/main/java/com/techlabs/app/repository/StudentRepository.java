package com.techlabs.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.app.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
