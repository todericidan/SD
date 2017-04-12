package com.utcluj.persistence.repository;

import com.utcluj.persistence.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by todericidan on 3/23/2017.
 */
public interface TransactionJPARepository extends JpaRepository<Transaction,Long> {
}
