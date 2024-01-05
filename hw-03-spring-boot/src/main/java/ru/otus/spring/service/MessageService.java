package ru.otus.spring.service;

import io.micrometer.core.lang.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.ApplicationPropLocale;

@Component
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messageSource;
    private final ApplicationPropLocale applicationPropLocale;

    public String getMessage(String msgCode, @Nullable Object... args) {
        return messageSource.getMessage(msgCode, args, applicationPropLocale.getLocale());
    }
}
