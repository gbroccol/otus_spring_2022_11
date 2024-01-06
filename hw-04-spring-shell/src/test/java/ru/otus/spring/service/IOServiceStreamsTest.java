package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Тест OpenedConsoleIOService")
class IOServiceStreamsTest {

    private static final String TEXT_TO_PRINT1 = "Ничто не истинно";
    private static final String TEXT_TO_PRINT2 = "Все дозволено";

    private ByteArrayOutputStream bos;
    private IOService ioService;

    @BeforeEach
    void setUp() {
        System.out.println(Thread.currentThread().getName());

        bos = new ByteArrayOutputStream();
        ioService = new IOServiceStreams(new PrintStream(bos), System.in);
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT1 + "\"")
    @Test
    void shouldPrintOnlyFirstCreedLine() throws InterruptedException {
        ioService.outputString(TEXT_TO_PRINT1);
        Thread.sleep(1000);
        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT1);
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT2 + "\"")
    @Test
    void shouldPrintOnlySecondCreedLine() {
        ioService.outputString(TEXT_TO_PRINT2);
        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT2);
    }
}