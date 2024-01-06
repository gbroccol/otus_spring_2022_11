package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.NoSuchMessageException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MessageServiceTest {

    public static final String EN_TEST_FINISH_SUCCESS_CODE = "test.finish.success";
    public static final String EN_TEST_FINISH_SUCCESS_VALUE = "Test completed successfully";
    public static final String EN_TEST_FINISH_FAIL_CODE = "test.finish.fail";
    public static final String EN_TEST_FINISH_FAIL_VALUE = "You failed, try again";
    public static final String MESSAGE_CODE_NOT_EXISTS = "message.code.not.exists";

    @Autowired
    MessageService messageService;

    @Test
    @DisplayName("должно выбросить исключение NoSuchMessageException")
    void shouldThrowNoSuchMessageException() {
        Exception exception = assertThrows(NoSuchMessageException.class, () -> {
            messageService.getMessage(MESSAGE_CODE_NOT_EXISTS);
        });
        String expectedMessage = "No message found under code 'message.code.not.exists' for locale 'en'.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("должно вернуть \"" + EN_TEST_FINISH_SUCCESS_VALUE + "\"")
    void shouldReturnTestFinishSuccessValue() {
        String res = messageService.getMessage(EN_TEST_FINISH_SUCCESS_CODE);
        assertThat(res).isEqualTo(EN_TEST_FINISH_SUCCESS_VALUE);
    }

    @Test
    @DisplayName("должно вернуть \"" + EN_TEST_FINISH_FAIL_VALUE + "\"")
    void shouldReturnTestFinishFailValue() {
        String res = messageService.getMessage(EN_TEST_FINISH_FAIL_CODE);
        assertThat(res).isEqualTo(EN_TEST_FINISH_FAIL_VALUE);
    }
}
