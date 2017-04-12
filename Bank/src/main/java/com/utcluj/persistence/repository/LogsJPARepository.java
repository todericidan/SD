package com.utcluj.persistence.repository;

import com.utcluj.persistence.model.Log;
import com.utcluj.persistence.model.primarykey.LogPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by todericidan on 3/19/2017.
 */
public interface LogsJPARepository extends JpaRepository<Log,LogPrimaryKey> {
}
