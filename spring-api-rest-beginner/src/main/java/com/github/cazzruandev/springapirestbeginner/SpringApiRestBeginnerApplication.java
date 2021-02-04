package com.github.cazzruandev.springapirestbeginner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.github.cazzruandev.springapirestbeginner.domain.model")
@ComponentScan(basePackages = "com.github.cazzruandev.springapirestbeginner.*" )
@EnableJpaRepositories(basePackages = "com.github.cazzruandev.springapirestbeginner.domain.repository")
@EnableAutoConfiguration
@SpringBootApplication
public class SpringApiRestBeginnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiRestBeginnerApplication.class, args);
	}

}
