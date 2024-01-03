package ru.otus.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.IOServiceStreams;

@Configuration
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties({ApplicationProperties.class, ResourcesProperties.class})
public class ApplicationConfig {

    @Bean
    IOService ioServiceConsole() {
        return new IOServiceStreams(System.out, System.in);
    }
}
