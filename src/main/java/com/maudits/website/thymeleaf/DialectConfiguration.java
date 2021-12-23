package com.maudits.website.thymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DialectConfiguration {

	@Bean
	public TextDialect textDialect() {
		return new TextDialect();
	}

}
