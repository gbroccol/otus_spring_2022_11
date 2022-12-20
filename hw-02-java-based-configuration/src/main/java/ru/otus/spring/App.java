package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.model.User;
import ru.otus.spring.service.TestService;
import ru.otus.spring.service.UserService;

@ComponentScan
public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        User user = context.getBean(UserService.class).getUser();
        context.getBean(TestService.class).runTestReturnSuccess(user);
        context.close();
    }
}
