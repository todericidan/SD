package com.utcluj.persistence.repository;

import com.utcluj.persistence.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by todericidan on 3/21/2017.
 */
public interface AccountJPARepository  extends JpaRepository<Account,Long> {
}
