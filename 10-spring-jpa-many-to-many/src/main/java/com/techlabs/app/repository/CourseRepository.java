package com.techlabs.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.app.entity.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{

}
