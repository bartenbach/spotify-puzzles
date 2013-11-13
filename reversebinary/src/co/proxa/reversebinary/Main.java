package co.proxa.reversebinary;

import java.util.Scanner;

public class Main {

    final static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        String input = getInput();
        while (input == null) {
            input = getInput();
        }

        final String backwards = reverseString(input);
        final Integer i = Integer.parseInt(backwards, 2);
        System.out.print(i);
    }

    public static String getInput() {
        String x = scn.next();

        try {
            return Integer.toBinaryString(Integer.parseInt(x));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static String reverseString(String x) {
        StringBuilder sb = new StringBuilder(x);
        return sb.reverse().toString();
    }

}
