package com.techlabs.myapp;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.techlabs.myapp.dao.StudentDao;
import com.techlabs.myapp.entity.Student;

@SpringBootApplication
public class Application implements CommandLineRunner{

	private StudentDao studentDao;
	
	
	
	public Application(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//addStudent();
		//getAllStudents();
		//getStudentByRollno();
		//getStudentByName();
		//updateStudent();
		//deleteStudent();
		updateStudentQuery();
	}
	
	private void updateStudentQuery() {
		// TODO Auto-generated method stub
		Student s =new Student(39,"akshay",58);
		studentDao.updateStudentQuery(s);
	}

	private void deleteStudent() {
		// TODO Auto-generated method stub
		studentDao.deleteStudent(37);
	}

	private void updateStudent() {
		// TODO Auto-generated method stub
		Student s =new Student(35,"Jagan",60);
		studentDao.updateStudent(s);
	}

	private void getStudentByName() {
		// TODO Auto-generated method stub
		List<Student> students = studentDao.getStudentByName("piyush");
		for(Student s:students) {
			System.out.println(s);
		}
	}

	private void getStudentByRollno() {
		// TODO Auto-generated method stub
		Student student = studentDao.getStudentByRollno(24);
		System.out.println(student);
	}

	private void getAllStudents() {
		List<Student> studentList = studentDao.getAllStudents();
		for(Student s:studentList) {
			System.out.println(s);
		}
	}

	private void addStudent() {
		// TODO Auto-generated method stub
		Student student=new Student("piyush",60);
		studentDao.save(student);
	}

}
