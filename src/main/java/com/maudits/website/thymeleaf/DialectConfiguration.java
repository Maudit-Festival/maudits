package com.maudits.website.thymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;

@Configuration
public class DialectConfiguration {

	@Bean
	public IDialect textDialect() {
		return new TextDialect();
	}

}
