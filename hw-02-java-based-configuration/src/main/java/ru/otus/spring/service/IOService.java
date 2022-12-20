package ru.otus.spring.service;

import java.util.Scanner;

public class IOService {

    public static void outputString(String s) {
        System.out.println(s);
    }

    public static int readInt(Scanner input) {
        return Integer.parseInt(input.nextLine());
    }
}
