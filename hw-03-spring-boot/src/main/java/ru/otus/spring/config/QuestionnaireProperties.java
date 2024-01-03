package ru.otus.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "questionnaire")
public class QuestionnaireProperties {

    private String csvFileName;
    private Integer minRightAnswers;

}