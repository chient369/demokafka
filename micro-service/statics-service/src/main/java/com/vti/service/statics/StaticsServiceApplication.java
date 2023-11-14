package com.vti.service.statics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@SpringBootApplication
@EntityScan(basePackages = {"com.vti.common.*"})
public class StaticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaticsServiceApplication.class, args);
	}
	
	@Bean
	JsonMessageConverter converter() {
		return new JsonMessageConverter();
	}
}
