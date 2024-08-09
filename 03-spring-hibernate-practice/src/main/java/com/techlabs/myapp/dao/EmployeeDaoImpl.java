package com.techlabs.myapp.dao;

import org.springframework.stereotype.Repository;

import com.techlabs.myapp.entity.Employees;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	EntityManager entityManager;
	
	public EmployeeDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	

	@Override
	@Transactional
	public void save(Employees employee) {
		// TODO Auto-generated method stub
		this.entityManager.persist(employee);
	}

}
