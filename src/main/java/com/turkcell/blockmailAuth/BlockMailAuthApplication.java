package com.turkcell.blockmailAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.turkcell.blockmailAuth")
public class BlockMailAuthApplication extends SpringBootServletInitializer {
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BlockMailAuthApplication.class);
	}
	
	
	public static void main (String [] args) {
		SpringApplication.run(BlockMailAuthApplication.class, args);
	}

}
