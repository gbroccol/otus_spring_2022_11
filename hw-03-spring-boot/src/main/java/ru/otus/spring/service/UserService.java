package ru.otus.spring.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.User;

import java.util.Scanner;

@Service
public class UserService implements DisposableBean {

    private final Scanner scanner = new Scanner(System.in);
    private final MessageService messageService;
    private final IOService ioService;

    public UserService(MessageService messageService,
                       @Qualifier("ioServiceConsole") IOService ioService) {
        this.messageService = messageService;
        this.ioService = ioService;
    }

    public User getUser() {

        ioService.outputString(messageService.getMessageEnterFirstName());
        String firstName = scanner.nextLine();

        ioService.outputString(messageService.getMessageEnterLastName());
        String lastName = scanner.nextLine();

        return new User(firstName, lastName);
    }

    @Override
    public void destroy() {
        scanner.close();
    }
}
