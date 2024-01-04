package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.ApplicationProperties;

@Component
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messageSource;
    private final ApplicationProperties props;

    public String getMessageHelloUser() {
        return messageSource.getMessage("hello.user", null, props.getLocale());
    }

    public String getMessageEnterFirstName() {
        return messageSource.getMessage("enter.first.name", null, props.getLocale());
    }

    public String getMessageEnterLastName() {
        return messageSource.getMessage("enter.last.name", null, props.getLocale());
    }

    public String getMessageTestQuestionsAmount(Integer[] param) {
        return messageSource.getMessage("test.questions.amount", param, props.getLocale());
    }

    public String getMessageTestQuestionsMinCorrectAmount(Integer[] param) {
        return messageSource.getMessage("test.questions.min.correct.amount", param, props.getLocale());
    }

    public String getMessageEnterAnswerNumber() {
        return messageSource.getMessage("enter.answer.number", null, props.getLocale());
    }

    public String getMessageTestQuestionsAnsweredAmount(Integer[] param) {
        return messageSource.getMessage("test.questions.answered.amount", param, props.getLocale());
    }

    public String getMessageTestFinishSuccess() {
        return messageSource.getMessage("test.finish.success", null, props.getLocale());
    }

    public String getMessageTestFinishFail() {
        return messageSource.getMessage("test.finish.fail", null, props.getLocale());
    }
}
