package com.utcluj.persistence.repository;

import com.utcluj.persistence.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Created by todericidan on 3/21/2017.
 */
public interface ClientJPARepository extends JpaRepository<Client,String>{
}
