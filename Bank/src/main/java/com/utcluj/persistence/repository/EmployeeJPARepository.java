package com.utcluj.persistence.repository;

import com.utcluj.persistence.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by todericidan on 3/18/2017.
 */
public interface EmployeeJPARepository extends JpaRepository<Employee,String> {
}
