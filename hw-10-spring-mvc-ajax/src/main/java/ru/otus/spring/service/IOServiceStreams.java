package ru.otus.spring.service;

import org.springframework.beans.factory.DisposableBean;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreams implements IOService, InService, OutService, DisposableBean {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceStreams(PrintStream out,
                            InputStream in) {
        this.output = out;
        this.input = new Scanner(in);
    }

    public void outputString(String s) {
        output.print(s);
    }

    public void outputStringNextLine(String s) {
        output.println(s);
    }

    public int inputInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public void destroy() {
        input.close();
    }
}
