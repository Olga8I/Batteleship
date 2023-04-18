package com.Java;

public class ShipGame {

    public static String a = "ABCDEFGHIJ";
    String[][] bigField = new String[10][10];

    public void printField() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                bigField[i][j] = "~";
            }
        }

        System.out.println();
        System.out.println("  1 2 3 4 5 6 7 8 9 10");

        for (int i = 0; i < 10; i++) {
            System.out.print(a.charAt(i) + " ");
            for (int k = 0; k < 10; k++) {
                System.out.print(bigField[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}