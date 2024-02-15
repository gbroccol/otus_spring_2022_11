package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

// http://localhost:8080/actuator/health

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException {
        Console.main(args);
        SpringApplication.run(Application.class, args);
    }
}