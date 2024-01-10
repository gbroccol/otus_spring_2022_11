package ru.otus.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.IOServiceStreams;
import ru.otus.spring.service.OutService;

@Configuration
@EnableConfigurationProperties
public class ApplicationConfig {

    @Bean
    OutService ioServiceConsole() {
        return new IOServiceStreams(System.out, System.in);
    }
}
