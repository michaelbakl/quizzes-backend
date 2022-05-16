package it.sevenbits.quiz.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * application class
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@SpringBootApplication
public class Application {

    /**
     * main
     * @param args - String[]
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}