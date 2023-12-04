package com.edgrantJBusRD;

import com.edgrantJBusRD.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The JBus class is the entry point of the JBus application.
 * It initializes the JsonDBEngine, starts the Spring Boot application, and adds a shutdown hook for proper cleanup.
 */
@SpringBootApplication
public class JBus {

    /**
     * The main method is the entry point of the JBus application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Initialize the JsonDBEngine
        JsonDBEngine.Run(JBus.class);

        // Start the Spring Boot application
        SpringApplication.run(JBus.class, args);

        // Add a shutdown hook for proper cleanup
        Runtime.getRuntime().addShutdownHook(new Thread(JsonDBEngine::join));
    }
}
