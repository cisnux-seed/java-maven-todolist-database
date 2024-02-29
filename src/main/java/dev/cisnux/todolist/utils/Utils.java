package dev.cisnux.todolist.utils;

import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputString(String hint) {
        System.out.print(hint);
        return scanner.nextLine();
    }

    public static int inputNumber(String hint) {
        System.out.print(hint);
        final var input = scanner.nextInt();
        scanner.skip("\n");
        return input;
    }
}
