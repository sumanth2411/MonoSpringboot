package com.techlabs.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.app.entity.Contact;
import com.techlabs.app.entity.User;

public interface ContactRepository extends JpaRepository<Contact, Long>{

	Page<Contact> findByUser(User user, Pageable pageable);
}
