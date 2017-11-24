package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.cognizant.services.Empinfoservice;
import com.cognizant.services.Empinfoserviceimpl;

@SpringBootApplication(scanBasePackages = { "com.cognizant" })
public class Application extends SpringBootServletInitializer {
	public static void main(String args[]) throws Exception {
		SpringApplication.run(Application.class, args);

	}

	@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
