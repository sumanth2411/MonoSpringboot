package com.techlabs.app.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techlabs.app.entity.Account;
import com.techlabs.app.entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	Page<Transaction> findAllByTransactionDateBetween(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
	
	@Query("select t from Transaction t where (t.senderAccount = :account or t.receiverAccount = :account) and t.transactionDate between :from and :to")
	Page<Transaction> getPassbook(@Param("account") Account account, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to, Pageable pageable);
}
