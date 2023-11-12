package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is the main class for the GCU-ClassScheduler. It
 * configures and starts the Spring Boot application.
 * 
 * @author Joel Luthi
 * @version 1.0
 */
// Mark this class as the main Spring Boot application class.
@SpringBootApplication
// Specify the base packages to scan for components (beans) during the application start-up.
@ComponentScan({ "com.gcu" })
public class GcuClassSchedulerApplication {
	/**
	 * The entry point for the application.
	 * 
	 * @param args Command-line arguments passed during application start-up.
	 */
	public static void main(String[] args) {
		// Start the Spring Boot application using the specified class and command-line
		// arguments.
		SpringApplication.run(GcuClassSchedulerApplication.class, args);
		
	
	}

}
