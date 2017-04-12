
package com.utcluj.config.persistence;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;

/**
 * Persistence Configuration.
 *
 * @author todericidan
 */
@Configuration
@EnableJpaRepositories({ "com.utcluj.persistence.repository" })
@EntityScan({ "com.utcluj.persistence.model" })
public class PersistenceConfiguration {

	/**
	 * Configures and returns the {@link JpaTransactionManager}.
	 *
	 * @param entityManagerFactory
	 *            the {@link EntityManagerFactory} to be used.
	 * @return the instance of {@link JpaTransactionManager}.
	 */
	@Bean
	public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}