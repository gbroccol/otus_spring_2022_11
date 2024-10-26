package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

// http://localhost:8080/actuator/health

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application.class, args);

        System.out.println("Для тестирования:");
        System.out.println("http://localhost:8080");
    }
}