package ru.otus.spring.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.User;

import java.util.Scanner;

@Service
public class UserService implements DisposableBean {

    private final Scanner scanner = new Scanner(System.in);

    public User getUser() {

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println();
        return new User(firstName, lastName);
    }

    @Override
    public void destroy() {
        scanner.close();
    }
}
