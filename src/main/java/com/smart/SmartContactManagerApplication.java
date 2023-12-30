package com.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SmartContactManagerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SmartContactManagerApplication.class, args);
	
	System.out.println("project running smartContactManager");
	}
	
	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) { return
	 * builder.sources(Application.class); }
	 */
}
