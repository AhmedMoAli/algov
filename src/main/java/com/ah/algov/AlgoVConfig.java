package com.ah.algov;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Algov configuration.
 */
@Configuration
public class AlgoVConfig {

	/**
	 * Configures the model mapper.
	 * 
	 * @return configured object model bean.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
