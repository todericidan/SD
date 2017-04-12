
package com.utcluj.config.jackson;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Faster XML's Object Mapper Configuration.
 * @author todericidan
 */

@Configuration
public class JacksonConfiguration {

	@Bean
	public ObjectMapper objectMapper() {
		final ObjectMapper mapper = new ObjectMapper();

		mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DEFAULT_VIEW_INCLUSION, true);
		mapper.setSerializationInclusion(NON_EMPTY);

		return mapper;
	}
}