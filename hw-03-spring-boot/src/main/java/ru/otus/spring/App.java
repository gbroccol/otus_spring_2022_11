package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.model.User;
import ru.otus.spring.service.TestService;
import ru.otus.spring.service.UserService;

// http://localhost:8080/actuator/health

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        var context = SpringApplication.run(App.class);

        User user = context.getBean(UserService.class).getUser();
        context.getBean(TestService.class).runTest(user);
    }
}