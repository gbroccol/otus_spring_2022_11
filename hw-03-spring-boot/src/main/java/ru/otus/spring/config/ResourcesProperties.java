package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "resources")
public class ResourcesProperties {
    private String csvFileName;

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public String getCsvFileName() {
        return csvFileName;
    }
}