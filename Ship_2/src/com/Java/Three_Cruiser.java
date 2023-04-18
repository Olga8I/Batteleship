package com.Java;
import java.util.Scanner;

public class Three_Cruiser {

    public String[][] getThree(String[][] field_4) throws Exception {

        String a = "ABCDEFGHIJ";
        String abcRow = "ABCDEFGHIJ";
        String digitRow = "123456789";
        String coordinates;

        int shipStart = -1;
        int shipStart2 = -1;

        boolean correctThree = false;
        boolean correctThreeVertical = false;
        boolean correctThreeHorizontal = false;
        boolean correctThreeSmallVertical = false;
        boolean correctThreeSmallHorizontal = false;
        boolean first = false;
        boolean second = false;
        System.out.println("Enter the coordinates of the Cruiser (3 cells):, ex: A1 C1: AC|BD|CE|DF|EG|FH|GI|HJ 13|24|35|46|57|68|79|8.10");
        do {

            // System.out.println("Enter the coordinates of the Cruiser (3 cells):, ex: A1 C1: AC|BD|CE|DF|EG|FH|GI|HJ 13|24|35|46|57|68|79|8.10");
            Scanner scanner = new Scanner(System.in);
            coordinates = scanner.nextLine();

            // checking input length:
            if (coordinates.length() < 5) {
                System.out.println("Error: your input is not long enough! Try again: " + coordinates.length());
            }
            if (coordinates.length() > 7) {
                System.out.println("Error: your input is too long! Try again: ");
            }

            // checking first symbol - A..J:
            for (int i = 0; i < 10; i++) {
                if (coordinates.charAt(0) == abcRow.charAt(i)) {
                    first = true;
                    shipStart = i;                                                  //Maybe it's the ship's end!!!!
                    // System.out.println("shipStart: " + shipStart);
                    //  System.out.println("First symbol is OK!");
                    break;
                }
            }
            if (!first) {
                System.out.println("Error: your coordinates should start with a capital letter A...J");
            }

            // checking 2nd symbol - digit 1..9:
            for (int i = 0; i < 9; i++) {
                if (coordinates.charAt(1) == digitRow.charAt(i)) {
                    second = true;
                    shipStart2 = i;
                    //    System.out.println("2nd symbol is OK!");
                    break;
                }
            }
            if (!second) {
                System.out.println("Error: your coordinates 2nd symbol should be a number 1..9");
                shipStart = -1;
            }

            //big vertical 3-check 10 - A10 C10 , BD/CE/DF/EG/FH/GI/HJ
            if ((coordinates.charAt(1) == '1') && (coordinates.charAt(2) == '0') && (coordinates.charAt(3) == ' ')
                    &&            ((coordinates.charAt(0)!=(coordinates.charAt(4))))){
                if (((coordinates.charAt(5)) == '1') && (coordinates.charAt(6) == '0') &&
                        (
                                (coordinates.charAt(0) == 'A' && coordinates.charAt(4) == 'C') ||
                                        (coordinates.charAt(0) == 'B' && coordinates.charAt(4) == 'D') ||
                                        (coordinates.charAt(0) == 'C' && coordinates.charAt(4) == 'E') ||
                                        (coordinates.charAt(0) == 'D' && coordinates.charAt(4) == 'F') ||
                                        (coordinates.charAt(0) == 'E' && coordinates.charAt(4) == 'G') ||
                                        (coordinates.charAt(0) == 'F' && coordinates.charAt(4) == 'H') ||
                                        (coordinates.charAt(0) == 'G' && coordinates.charAt(4) == 'I') ||
                                        (coordinates.charAt(0) == 'H' && coordinates.charAt(4) == 'J') ||

                                        (coordinates.charAt(0) == 'C' && coordinates.charAt(4) == 'A') ||
                                        (coordinates.charAt(0) == 'D' && coordinates.charAt(4) == 'B') ||
                                        (coordinates.charAt(0) == 'E' && coordinates.charAt(4) == 'C') ||
                                        (coordinates.charAt(0) == 'F' && coordinates.charAt(4) == 'D') ||
                                        (coordinates.charAt(0) == 'G' && coordinates.charAt(4) == 'E') ||
                                        (coordinates.charAt(0) == 'H' && coordinates.charAt(4) == 'F') ||
                                        (coordinates.charAt(0) == 'I' && coordinates.charAt(4) == 'G') ||
                                        (coordinates.charAt(0) == 'J' && coordinates.charAt(4) == 'H')

                        )

                ) {
                }
                correctThreeVertical = true;
                //   System.out.println("got big vertical 3");
                if ((coordinates.charAt(0) == 'C' && coordinates.charAt(4) == 'A') ||
                        (coordinates.charAt(0) == 'D' && coordinates.charAt(4) == 'B') ||
                        (coordinates.charAt(0) == 'E' && coordinates.charAt(4) == 'C') ||
                        (coordinates.charAt(0) == 'F' && coordinates.charAt(4) == 'D') ||
                        (coordinates.charAt(0) == 'G' && coordinates.charAt(4) == 'E') ||
                        (coordinates.charAt(0) == 'H' && coordinates.charAt(4) == 'F') ||
                        (coordinates.charAt(0) == 'I' && coordinates.charAt(4) == 'G') ||
                        (coordinates.charAt(0) == 'J' && coordinates.charAt(4) == 'H')){
                    shipStart = shipStart-2;                                      //moving shipStart front 2 squares!!!
                }


                for (int i = shipStart; i < shipStart + 3; i++) {

                    if (field_4[i][9] == "." || field_4[i][9] == "O") { //testing the absence of the 4 & 5-cell-ship
                        System.out.printf("Error: Your 4-cell ship is too close to the 4 & 5-cell ship!");
                        correctThreeVertical = false;
                        first = false;
                        second = false;
                        break;
                    } else if (field_4[i][9] != "." || field_4[i][9] != "O") {
                        correctThreeVertical = true;

                        field_4[i][9] = "O";
                        field_4[i][8] = ".";
                        if (shipStart != 0) {
                            field_4[shipStart - 1][8] = ".";
                            field_4[shipStart - 1][9] = ".";
                        }
                        if (shipStart + 3 != 10) {
                            field_4[shipStart + 3][8] = ".";
                            field_4[shipStart + 3][9] = ".";
                        }
                    }

                }

                System.out.println();
                System.out.println("  1 2 3 4 5 6 7 8 9 10");
                for (int i = 0; i < 10; i++) {
                    System.out.print(a.charAt(i) + " ");
                    for (int k = 0; k < 10; k++) {
                        System.out.print(field_4[i][k] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
                //correctThreeVertical = true;

            }


            //big horizontal 3-check 10 - A8 A10 : DONE?
            if (((coordinates.length() == 6) && (coordinates.charAt(4) == '1') && (coordinates.charAt(5) == '0') &&
                    (coordinates.charAt(2) == ' ')
                    && (((coordinates.charAt(0) == coordinates.charAt(3)) && (coordinates.charAt(1) == '8')) )
            ) ||                                                                           // checking inverted input
                    ((coordinates.length() == 6) && (coordinates.charAt(1) == '1') && (coordinates.charAt(2) == '0') &&
                            (coordinates.charAt(3) == ' ')
                            && (((coordinates.charAt(0) == coordinates.charAt(4)) && (coordinates.charAt(5) == '8')) )
                    )

            )
            {
                {

                    correctThreeHorizontal = true;
                    //     System.out.println("got big horizontal 3");
                }
// checking that place for 3 big horzontal is free from "O" & ".":
                for (int j = 7; j < 10; j++) {

                    if (field_4[shipStart][j] == "O" || field_4[shipStart][j] == ".") {
                        System.out.println("Error: another 4 or 5-cell ship is standing in your way!");
                        correctThreeHorizontal = false;
                        break;
                        // checking upper-line aura for 3 big horizontal - shouldn't be "O", "." are OK - auras can touch
                    }
                    if (shipStart != 0) {
                        if (field_4[shipStart - 1][j] == "O" || field_4[shipStart - 1][5] == "O") {
                            System.out.println("Error: another 4 or 5-cell ship is standing in your upper aura!");
                            correctThreeHorizontal = false;
                            break;
                        }
                    }
                    //checking bottom-line aura for 3 big horizontal: shouldn't be "O", "." are OK - auras can touch
                    if (shipStart != 9) {
                        if (field_4[shipStart + 1][j] == "O" || field_4[shipStart + 1][6] == "O") {
                            System.out.println("Error: another 5-cell ship is standing in your bottom aura!");
                            correctThreeHorizontal = false;
                            break;
                        }
                    }
                    // checking the 3 big horizontal 3-cell head aura-dot for
                    if (field_4[shipStart][6] == "O") {
                        System.out.println("Error: another 5-cell ship is standing in your headway!");
                        correctThreeHorizontal = false;
                        break;
                    }
                }

                if (correctThreeHorizontal) {


// drawing 3 big horizontal with aura

                    for (int j = 7; j < 10; j++) {
                        field_4[shipStart][j] = "O";
                        field_4[shipStart][6] = "."; // head aura dot
                        if (shipStart != 0) {
                            field_4[shipStart - 1][j] = ".";
                            field_4[shipStart - 1][6] = "."; //upper aura head corner dot
                        }
                        if (shipStart != 9) {
                            field_4[shipStart + 1][j] = "."; //bottom aura
                            field_4[shipStart + 1][6] = "."; //bottom aura head corner dot
                        }
                    }

                    System.out.println();
                    System.out.println("  1 2 3 4 5 6 7 8 9 10");
                    for (int i = 0; i < 10; i++) {
                        System.out.print(a.charAt(i) + " ");
                        for (int k = 0; k < 10; k++) {
                            System.out.print(field_4[i][k] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
            }

            //else shipStart = -1; //System.out.println("Error: wrong coordinates of the horizontal Aircraft Carrier! Try again: ");


            // vertical small 3-check A1 C1 (AC BD CE DF EG FH GI HJ)
            if (coordinates.length() == 5 && coordinates.charAt(2) == ' ' && coordinates.charAt(1) == coordinates.charAt(4)) {
                if (
                        coordinates.charAt(0) == 'A' && coordinates.charAt(3) == 'C' ||
                                (coordinates.charAt(0) == 'B' && coordinates.charAt(3) == 'D') ||
                                (coordinates.charAt(0) == 'C' && coordinates.charAt(3) == 'E') ||
                                (coordinates.charAt(0) == 'D' && coordinates.charAt(3) == 'F') ||
                                (coordinates.charAt(0) == 'E' && coordinates.charAt(3) == 'G') ||
                                (coordinates.charAt(0) == 'F' && coordinates.charAt(3) == 'H') ||
                                (coordinates.charAt(0) == 'G' && coordinates.charAt(3) == 'I') ||
                                (coordinates.charAt(0) == 'H' && coordinates.charAt(3) == 'J') ||

                                coordinates.charAt(0) == 'C' && coordinates.charAt(3) == 'A' ||
                                (coordinates.charAt(0) == 'D' && coordinates.charAt(3) == 'B') ||
                                (coordinates.charAt(0) == 'E' && coordinates.charAt(3) == 'C') ||
                                (coordinates.charAt(0) == 'F' && coordinates.charAt(3) == 'D') ||
                                (coordinates.charAt(0) == 'G' && coordinates.charAt(3) == 'E') ||
                                (coordinates.charAt(0) == 'H' && coordinates.charAt(3) == 'F') ||
                                (coordinates.charAt(0) == 'I' && coordinates.charAt(3) == 'G') ||
                                (coordinates.charAt(0) == 'J' && coordinates.charAt(3) == 'H')
                    //inverted output check
                ) {
                    correctThreeSmallVertical = true;
                    //      System.out.println("got small vertical 3 Cruiser");

                    if ( coordinates.charAt(0) == 'C' && coordinates.charAt(3) == 'A' ||
                            (coordinates.charAt(0) == 'D' && coordinates.charAt(3) == 'B') ||
                            (coordinates.charAt(0) == 'E' && coordinates.charAt(3) == 'C') ||
                            (coordinates.charAt(0) == 'F' && coordinates.charAt(3) == 'D') ||
                            (coordinates.charAt(0) == 'G' && coordinates.charAt(3) == 'E') ||
                            (coordinates.charAt(0) == 'H' && coordinates.charAt(3) == 'F') ||
                            (coordinates.charAt(0) == 'I' && coordinates.charAt(3) == 'G') ||
                            (coordinates.charAt(0) == 'J' && coordinates.charAt(3) == 'H')){
                        shipStart = shipStart-2;                                            //moving shipStart to front
                    }

                    for (int i = shipStart; i < shipStart + 3; i++) {

                        if (field_4[i][shipStart2] == "." || field_4[i][shipStart2] == "O") { //testing the absence of the 4 & 5-cell-ship
                            System.out.println("Error: Your 3-cell small vertical Cruiser is too close to the 4 or 5-cell ship!");
                            correctThreeSmallVertical = false;
                            first = false;
                            second = false;
                            break;
                        } else if (field_4[i][shipStart2] != "." || field_4[i][shipStart2] != "O") {
                            correctThreeSmallVertical = true;

                        }
                    }

                    if (correctThreeSmallVertical) {
                        //       System.out.println("Nothing is in the way of your 3-small-vertical Cruiser!");
                        for (int i = shipStart; i < shipStart + 3; i++) {
                            field_4[i][shipStart2] = "O";
                        }

                        // printing aura for 3-small vertical ship
                        for (int i = shipStart; i < shipStart + 3; i++) {//shipStart - letter array position number
                            field_4[i][shipStart2] = "O";                //shipStart2 - digit array position number
                            if (shipStart2 != 0) { //if it's not e.g. A1-A5 & we CAN add dots on the left side
                                field_4[i][shipStart2 - 1] = ".";
                            }//dots on the left
                            if (shipStart2 != 9) { //if it's not e.g. A10-E10 & we CAN add dots on the right side
                                field_4[i][shipStart2 + 1] = ".";//dots on the right
                            }

                            if (shipStart != 7) {
                                if (shipStart2 != 0) {
                                    field_4[i + 1][shipStart2 - 1] = ".";
                                } //dot down left
                                field_4[i + 1][shipStart2 + 1] = ".";//dot down right
                            }
                            if (shipStart != 0 && shipStart2 != 0) {
                                field_4[shipStart - 1][shipStart2 - 1] = ".";
                            }//dot up left
                            if (shipStart != 0 && shipStart2 != 9) {
                                field_4[shipStart - 1][shipStart2 + 1] = "."; //dot up right
                            }
                            if (shipStart != 0) {
                                field_4[shipStart - 1][shipStart2] = "."; //top dot
                            }
                            if (shipStart + 3 != 10) {
                                field_4[shipStart + 3][shipStart2] = "."; //bottom dot
                            }
                        }

                        System.out.println();
                        System.out.println("  1 2 3 4 5 6 7 8 9 10");
                        for (int i = 0; i < 10; i++) {
                            System.out.print(a.charAt(i) + " ");
                            for (int k = 0; k < 10; k++) {
                                System.out.print(field_4[i][k] + " ");
                            }
                            System.out.println();
                        }
                        System.out.println();
                    }

                }

            }


            // horizontal small 3-check A1 A3 (13 24 35 46 57 68 79):
            if (first && coordinates.length() == 5 && (coordinates.charAt(0) == coordinates.charAt(3)) && coordinates.charAt(2) == ' ') {
                if (
                        (coordinates.charAt(1) == '1' && coordinates.charAt(4) == '3') ||
                                (coordinates.charAt(1) == '2' && coordinates.charAt(4) == '4') ||
                                (coordinates.charAt(1) == '3' && coordinates.charAt(4) == '5') ||
                                (coordinates.charAt(1) == '4' && coordinates.charAt(4) == '6') ||
                                (coordinates.charAt(1) == '5' && coordinates.charAt(4) == '7') ||
                                (coordinates.charAt(1) == '6' && coordinates.charAt(4) == '8') ||
                                (coordinates.charAt(1) == '7' && coordinates.charAt(4) == '9') ||

                                (coordinates.charAt(1) == '3' && coordinates.charAt(4) == '1') ||
                                (coordinates.charAt(1) == '4' && coordinates.charAt(4) == '2') ||
                                (coordinates.charAt(1) == '5' && coordinates.charAt(4) == '3') ||
                                (coordinates.charAt(1) == '6' && coordinates.charAt(4) == '4') ||
                                (coordinates.charAt(1) == '7' && coordinates.charAt(4) == '5') ||
                                (coordinates.charAt(1) == '8' && coordinates.charAt(4) == '6') ||
                                (coordinates.charAt(1) == '9' && coordinates.charAt(4) == '7')
                ) {                                                                             //inverted output check
                    correctThreeSmallHorizontal = true;
                    //    System.out.println("got small horizontal 3 Cruiser");

                    if ( (coordinates.charAt(1) == '3' && coordinates.charAt(4) == '1') ||
                            (coordinates.charAt(1) == '4' && coordinates.charAt(4) == '2') ||
                            (coordinates.charAt(1) == '5' && coordinates.charAt(4) == '3') ||
                            (coordinates.charAt(1) == '6' && coordinates.charAt(4) == '4') ||
                            (coordinates.charAt(1) == '7' && coordinates.charAt(4) == '5') ||
                            (coordinates.charAt(1) == '8' && coordinates.charAt(4) == '6') ||
                            (coordinates.charAt(1) == '9' && coordinates.charAt(4) == '7')){
                        shipStart = shipStart;
                        shipStart2 = shipStart2-2;                                          //moving shipStart2
                    }


                    for (int i = shipStart2; i < shipStart2 + 3; i++) {

                        if (field_4[shipStart][i] == "." || field_4[shipStart][i] == "O") { //testing the absence of the 4 & 5-cell-ship
                            System.out.println("Error: Your 3-small-horizontal Cruiser is too close to the 3, 4 or 5-cell ship!");
                            correctThreeSmallHorizontal = false;
                            first = false;
                            second = false;
                            break;
                        } else if (field_4[shipStart][i] != "." || field_4[shipStart][i] != "O") {
                            correctThreeSmallHorizontal = true;

                        }
                    }

                    if (correctThreeSmallHorizontal) {
                        //      System.out.println("Nothing is in the way of your 3-small-horizontal Cruiser!");
                        for (int i = shipStart2; i < shipStart2 + 3; i++) {
                            field_4[shipStart][i] = "O";

                            field_4[shipStart][shipStart2 + 3] = "."; //tail

                            //upper line for small horizontal 3-cell Cruiser
                            if (shipStart != 0) {
                                field_4[shipStart - 1][i] = ".";
                                field_4[shipStart - 1][shipStart2 + 3] = "."; //upper tail (head is start)

                            }
                            //head
                            if (shipStart2 > 0) {
                                if (shipStart != 0) {
                                    field_4[shipStart - 1][shipStart2 - 1] = ".";
                                }
                                field_4[shipStart][shipStart2 - 1] = ".";

                            }
                            if (shipStart < 9) {           //bottom line of aura
                                if (shipStart2 != 0) {
                                    field_4[shipStart + 1][shipStart2 - 1] = ".";
                                } //bottom line head corner
                                field_4[shipStart + 1][shipStart2] = ".";
                                field_4[shipStart + 1][shipStart2 + 1] = ".";
                                field_4[shipStart + 1][shipStart2 + 2] = ".";
                                field_4[shipStart + 1][shipStart2 + 3] = ".";
                            }


                        }
                    }


                    System.out.println();
                    System.out.println("  1 2 3 4 5 6 7 8 9 10");
                    for (int i = 0; i < 10; i++) {
                        System.out.print(a.charAt(i) + " ");
                        for (int k = 0; k < 10; k++) {
                            System.out.print(field_4[i][k] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();

                }


            }


            if ((correctThreeHorizontal) || (correctThreeVertical) || (correctThreeSmallHorizontal) || (correctThreeSmallVertical)) {
                //      System.out.println("Really got 3-cell Cruiser!!!!");
                correctThree = true;
            } else System.out.println("Error: sth is wrong with your 3-cell Cruiser coordinates");


        } while (!correctThree);
        return field_4;
    }
}