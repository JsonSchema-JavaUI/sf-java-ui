package io.asfjava.ui.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
/*
 * The annotation below used to scan the servlet context loader added to the sf-java-ui library
 * in order to initialize the library @ server startup
 * 
 * */
@ServletComponentScan(basePackages = { "io.asfjava.ui.core" })
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
