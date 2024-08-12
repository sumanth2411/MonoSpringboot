package com.techlabs.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.app.entity.Contact;
import com.techlabs.app.entity.ContactDetails;

public interface ContactDetailRepository extends JpaRepository<ContactDetails, Long>{

	Page<ContactDetails> findByContact(Contact contact, PageRequest pageRequest);
}
