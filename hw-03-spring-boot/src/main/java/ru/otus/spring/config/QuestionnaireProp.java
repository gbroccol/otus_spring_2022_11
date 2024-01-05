package ru.otus.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "questionnaire")
public class QuestionnaireProp implements QuestionnairePropFile, QuestionnairePropRules {

    private String csvFileName;
    private Integer minRightAnswers;

}