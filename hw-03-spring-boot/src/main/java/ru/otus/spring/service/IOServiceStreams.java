package ru.otus.spring.service;

import org.springframework.beans.factory.DisposableBean;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreams implements IOService, DisposableBean {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceStreams(PrintStream output, InputStream inputStream) {
        this.output = output;
        input = new Scanner(inputStream);
    }

    public void outputString(String s) {
        output.println(s);
    }

    public int readInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public void destroy() {
        input.close();
    }
}
