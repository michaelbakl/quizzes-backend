package it.sevenbits.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * application class
 */
@SuppressWarnings("checkstyle:FinalClass")
@SpringBootApplication
public class Application {
    private Application() {}

    /**
     * main
     * @param args - String[]
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}