package ru.otus.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Setter
@Getter
@ConfigurationProperties(prefix = "application")
public class ApplicationProp implements ApplicationPropLocale {

    private Locale locale;

}