package it.sevenbits.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



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