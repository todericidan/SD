
package com.utcluj.config.webclient;

		import java.util.ArrayList;
		import java.util.List;

		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.http.converter.HttpMessageConverter;
		import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
		import org.springframework.web.client.RestTemplate;
		import org.springframework.web.servlet.config.annotation.CorsRegistry;
		import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring RESTTemplate Configuration.
 *
 * @author todericidan
 */
@Configuration
public class WebClientConfig extends WebMvcConfigurerAdapter{

	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();

		final List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(converters);

		return restTemplate;
	}

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry){
		corsRegistry.addMapping("/**");
	}




}