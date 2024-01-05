package ru.otus.spring.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.model.User;
import ru.otus.spring.service.TestService;
import ru.otus.spring.service.UserService;

@ShellComponent
@RequiredArgsConstructor
public class TestCommands {

    private final UserService userService;
    private final TestService testService;

    private User user;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login() {
        this.user = userService.getUser();
        return String.format("Добро пожаловать: %s %s", user.getFirstName(), user.getLastName());
    }

    @ShellMethod(value = "Start testing command", key = {"t", "test"})
    @ShellMethodAvailability(value = "isStartTestingCommandAvailable")
    public void test() {
        testService.runTest(user);
    }

    private Availability isStartTestingCommandAvailable() {
        return user == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }
}
