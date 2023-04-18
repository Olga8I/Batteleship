package com.Java;

import java.util.Scanner;

public class Two {

    public String[][] getTwo(String[][] field_4) throws Exception {

        String a = "ABCDEFGHIJ";
        String abcRow = "ABCDEFGHIJ";
        String digitRow = "123456789";
        String coordinates;

        int shipStart = -1;
        int shipStart2 = -1;

        boolean correctTwo = false;
        boolean correctTwoVertical = false;
        boolean correctTwoHorizontal = false;
        boolean correctTwoSmallVertical = false;
        boolean correctTwoSmallHorizontal = false;
        boolean first = false;
        boolean second = false;

        System.out.println("Enter the coordinates of the Destroyer (2 cells):, ex: A1 B1: AB|BC|CD|DE|EF|FG|GH|HI|IJ 12|23|34|45|56|67|78|89|9.10");
        do {

            //  System.out.println("Enter the coordinates of the Destroyer (2 cells):, ex: A1 B1: AB|BC|CD|DE|EF|FG|GH|HI|IJ 12|23|34|45|56|67|78|89|9.10");
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
                    shipStart = i;                                              //Maybe it's the ship's end!!!!!!!!!
                    //  System.out.println("shipStart: " + shipStart);
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
                    //  System.out.println("2nd symbol is OK!");
                    break;
                }
            }
            if (!second) {
                System.out.println("Error: your coordinates 2nd symbol should be a number 1..9");
                shipStart = -1;
            }

            //big vertical 2-check 10 - A10 B10 , BC/CD/DE/EF/FG/GH/HI/IJ
            if ((coordinates.charAt(1) == '1') && (coordinates.charAt(2) == '0') && (coordinates.charAt(3) == ' ')
                    &&            ((coordinates.charAt(0)!=(coordinates.charAt(4))))){
                if (((coordinates.charAt(5)) == '1') && (coordinates.charAt(6) == '0') &&
                        (
                                (coordinates.charAt(0) == 'A' && coordinates.charAt(4) == 'B') ||
                                        (coordinates.charAt(0) == 'B' && coordinates.charAt(4) == 'C') ||
                                        (coordinates.charAt(0) == 'C' && coordinates.charAt(4) == 'D') ||
                                        (coordinates.charAt(0) == 'D' && coordinates.charAt(4) == 'E') ||
                                        (coordinates.charAt(0) == 'E' && coordinates.charAt(4) == 'F') ||
                                        (coordinates.charAt(0) == 'F' && coordinates.charAt(4) == 'G') ||
                                        (coordinates.charAt(0) == 'G' && coordinates.charAt(4) == 'H') ||
                                        (coordinates.charAt(0) == 'H' && coordinates.charAt(4) == 'I') ||
                                        (coordinates.charAt(0) == 'I' && coordinates.charAt(4) == 'J') ||

                                        (coordinates.charAt(0) == 'B' && coordinates.charAt(4) == 'A') ||
                                        (coordinates.charAt(0) == 'C' && coordinates.charAt(4) == 'B') ||
                                        (coordinates.charAt(0) == 'D' && coordinates.charAt(4) == 'C') ||
                                        (coordinates.charAt(0) == 'E' && coordinates.charAt(4) == 'D') ||
                                        (coordinates.charAt(0) == 'F' && coordinates.charAt(4) == 'E') ||
                                        (coordinates.charAt(0) == 'G' && coordinates.charAt(4) == 'F') ||
                                        (coordinates.charAt(0) == 'H' && coordinates.charAt(4) == 'G') ||
                                        (coordinates.charAt(0) == 'I' && coordinates.charAt(4) == 'H') ||
                                        (coordinates.charAt(0) == 'J' && coordinates.charAt(4) == 'I')

                        )

                ) {
                }
                correctTwoVertical = true;
                //   System.out.println("got big vertical 2");
                if ((coordinates.charAt(0) == 'B' && coordinates.charAt(4) == 'A') ||
                        (coordinates.charAt(0) == 'C' && coordinates.charAt(4) == 'B') ||
                        (coordinates.charAt(0) == 'D' && coordinates.charAt(4) == 'C') ||
                        (coordinates.charAt(0) == 'E' && coordinates.charAt(4) == 'D') ||
                        (coordinates.charAt(0) == 'F' && coordinates.charAt(4) == 'E') ||
                        (coordinates.charAt(0) == 'G' && coordinates.charAt(4) == 'F') ||
                        (coordinates.charAt(0) == 'H' && coordinates.charAt(4) == 'G') ||
                        (coordinates.charAt(0) == 'I' && coordinates.charAt(4) == 'H') ||
                        (coordinates.charAt(0) == 'J' && coordinates.charAt(4) == 'I')){
                    shipStart = shipStart-1;                                       //moving shipStart front 1 square!!!
                }


                for (int i = shipStart; i < shipStart + 2; i++) {

                    if (field_4[i][9] == "." || field_4[i][9] == "O") { //testing the absence of the 3,4 or 5-cell-ship
                        System.out.println("Error: Your 2-cell Destroyer is too close to the 3,4 or 5-cell ship!");
                        correctTwoVertical = false;
                        first = false;
                        second = false;
                        break;
                    } else if (field_4[i][9] != "." || field_4[i][9] != "O") {
                        correctTwoVertical = true;

                        field_4[i][9] = "O";
                        field_4[i][8] = ".";
                        if (shipStart != 0) {
                            field_4[shipStart - 1][8] = ".";
                            field_4[shipStart - 1][9] = ".";
                        }
                        if (shipStart + 2 != 10) {
                            field_4[shipStart + 2][8] = ".";
                            field_4[shipStart + 2][9] = ".";
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


            //big horizontal 2-check 10 - A9 A10 : DONE?

            if (((coordinates.length() == 6) && (coordinates.charAt(4) == '1') && (coordinates.charAt(5) == '0') &&
                    (coordinates.charAt(2) == ' ')
                    && (((coordinates.charAt(0) == coordinates.charAt(3)) && (coordinates.charAt(1) == '9')) )
            ) ||                                                                           // checking inverted input
                    ((coordinates.length() == 6) && (coordinates.charAt(1) == '1') && (coordinates.charAt(2) == '0') &&
                            (coordinates.charAt(3) == ' ')
                            && (((coordinates.charAt(0) == coordinates.charAt(4)) && (coordinates.charAt(5) == '9')) )
                    )

            )
            {
                {

                    correctTwoHorizontal = true;
                    //     System.out.println("got big horizontal 2");
                }
// checking that place for 2 big horzontal is free from "O" & ".":
                for (int j = 8; j < 10; j++) {
                    if (field_4[shipStart][j] == "O" || field_4[shipStart][j] == ".") {
                        System.out.println("Error: another 3, 4 or 5-cell ship is standing in your way!");
                        correctTwoHorizontal = false;
                        break;
                        // checking upper-line aura for 2 big horizontal - shouldn't be "O", "." are OK - auras can touch
                    }
                    if (shipStart != 0) {
                        if (field_4[shipStart - 1][j] == "O" || field_4[shipStart - 1][7] == "O") {
                            System.out.println("Error: another 3,4 or 5-cell ship is standing in your upper aura!");
                            correctTwoHorizontal = false;
                            break;
                        }
                    }
                    //checking bottom-line aura for 3 big horizontal: shouldn't be "O", "." are OK - auras can touch
                    if (shipStart != 9) {
                        if (field_4[shipStart + 1][j] == "O" || field_4[shipStart + 1][7] == "O") {
                            System.out.println("Error: another 3,4 or 5-cell ship is standing in your bottom aura!");
                            correctTwoHorizontal = false;
                            break;
                        }
                    }
                    // checking the 2 big horizontal 2-cell head aura-dot for
                    if (field_4[shipStart][7] == "O") {
                        System.out.println("Error: another 5-cell ship is standing in your headway!");
                        correctTwoHorizontal = false;
                        break;
                    }
                }

                if (correctTwoHorizontal) {


// drawing 2 big horizontal with aura

                    for (int j = 8; j < 10; j++) {
                        field_4[shipStart][j] = "O";
                        field_4[shipStart][7] = "."; // head aura dot
                        if (shipStart != 0) {
                            field_4[shipStart - 1][j] = ".";
                            field_4[shipStart - 1][7] = "."; //upper aura head corner dot
                        }
                        if (shipStart != 9) {
                            field_4[shipStart + 1][j] = "."; //bottom aura
                            field_4[shipStart + 1][7] = "."; //bottom aura head corner dot
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


            // vertical small 2-check A1 B1 (AB BC CD DE EF FG GH HI IJ)
            if (coordinates.length() == 5 && coordinates.charAt(2) == ' ' && coordinates.charAt(1) == coordinates.charAt(4)) {
                if (
                        coordinates.charAt(0) == 'A' && coordinates.charAt(3) == 'B' ||
                                (coordinates.charAt(0) == 'B' && coordinates.charAt(3) == 'C') ||
                                (coordinates.charAt(0) == 'C' && coordinates.charAt(3) == 'D') ||
                                (coordinates.charAt(0) == 'D' && coordinates.charAt(3) == 'E') ||
                                (coordinates.charAt(0) == 'E' && coordinates.charAt(3) == 'F') ||
                                (coordinates.charAt(0) == 'F' && coordinates.charAt(3) == 'G') ||
                                (coordinates.charAt(0) == 'G' && coordinates.charAt(3) == 'H') ||
                                (coordinates.charAt(0) == 'H' && coordinates.charAt(3) == 'I') ||
                                (coordinates.charAt(0) == 'I' && coordinates.charAt(3) == 'J') ||

                                coordinates.charAt(0) == 'B' && coordinates.charAt(3) == 'A' ||
                                (coordinates.charAt(0) == 'C' && coordinates.charAt(3) == 'B') ||
                                (coordinates.charAt(0) == 'D' && coordinates.charAt(3) == 'C') ||
                                (coordinates.charAt(0) == 'E' && coordinates.charAt(3) == 'D') ||
                                (coordinates.charAt(0) == 'F' && coordinates.charAt(3) == 'E') ||
                                (coordinates.charAt(0) == 'G' && coordinates.charAt(3) == 'F') ||
                                (coordinates.charAt(0) == 'H' && coordinates.charAt(3) == 'G') ||
                                (coordinates.charAt(0) == 'I' && coordinates.charAt(3) == 'H') ||
                                (coordinates.charAt(0) == 'J' && coordinates.charAt(3) == 'I')
                    //inverted output check
                ) {
                    correctTwoSmallVertical = true;
                    // System.out.println("got small vertical 2 Destroyer");

                    if (coordinates.charAt(0) == 'B' && coordinates.charAt(3) == 'A' ||
                            (coordinates.charAt(0) == 'C' && coordinates.charAt(3) == 'B') ||
                            (coordinates.charAt(0) == 'D' && coordinates.charAt(3) == 'C') ||
                            (coordinates.charAt(0) == 'E' && coordinates.charAt(3) == 'D') ||
                            (coordinates.charAt(0) == 'F' && coordinates.charAt(3) == 'E') ||
                            (coordinates.charAt(0) == 'G' && coordinates.charAt(3) == 'F') ||
                            (coordinates.charAt(0) == 'H' && coordinates.charAt(3) == 'G') ||
                            (coordinates.charAt(0) == 'I' && coordinates.charAt(3) == 'H') ||
                            (coordinates.charAt(0) == 'J' && coordinates.charAt(3) == 'I')){
                        shipStart = shipStart-1;                                            //moving shipStart to front
                    }

                    for (int i = shipStart; i < shipStart + 2; i++) {

                        if (field_4[i][shipStart2] == "." || field_4[i][shipStart2] == "O") { //testing the absence of the 4 & 5-cell-ship
                            System.out.println("Error: Your 2-cell small vertical Destroyer is too close to the 3, 4 or 5-cell ship!");
                            correctTwoSmallVertical = false;
                            first = false;
                            second = false;
                            break;
                        } else if (field_4[i][shipStart2] != "." || field_4[i][shipStart2] != "O") {
                            correctTwoSmallVertical = true;

                        }
                    }

                    if (correctTwoSmallVertical) {
                        //System.out.println("Nothing is in the way of your 2-small-vertical Destroyer!");
                        for (int i = shipStart; i < shipStart + 2; i++) {
                            field_4[i][shipStart2] = "O";
                        }

                        // printing aura for 2-small vertical Destroyer
                        for (int i = shipStart; i < shipStart+2; i++) {//shipStart - letter array position number
                            field_4[i][shipStart2] = "O";                //shipStart2 - digit array position number
                            if (shipStart2 !=0){ //if it's not e.g. A1-A5 & we CAN add dots on the left side
                                field_4[i][shipStart2-1] = "."; }//dots on the left
                            if (shipStart2 !=9){ //if it's not e.g. A10-E10 & we CAN add dots on the right side
                                field_4[i][shipStart2+1] = ".";//dots on the right
                            }

                            if (shipStart !=8){
                                if(shipStart2 !=0) {field_4[i+1][shipStart2-1] = ".";} //dot down left
                                field_4[i+1][shipStart2+1] = ".";//dot down right
                            }
                            if (shipStart !=0 && shipStart2 !=0){
                                field_4[shipStart-1][shipStart2-1] = ".";}//dot up left
                            if (shipStart !=0 && shipStart2 !=9) {
                                field_4[shipStart-1][shipStart2+1] = "."; //dot up right
                            }
                            if (shipStart != 0){
                                field_4[shipStart-1][shipStart2] = "."; //top dot
                            }
                            if (shipStart+2 != 10){
                                field_4[shipStart+2][shipStart2] = "."; //bottom dot
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


            // horizontal small 2-check A1 A2 (12 23 34 45 56 67 78 89):
            if (first && coordinates.length() == 5 && (coordinates.charAt(0) == coordinates.charAt(3)) && coordinates.charAt(2) == ' ') {
                if (
                        (coordinates.charAt(1) == '1' && coordinates.charAt(4) == '2') ||
                                (coordinates.charAt(1) == '2' && coordinates.charAt(4) == '3') ||
                                (coordinates.charAt(1) == '3' && coordinates.charAt(4) == '4') ||
                                (coordinates.charAt(1) == '4' && coordinates.charAt(4) == '5') ||
                                (coordinates.charAt(1) == '5' && coordinates.charAt(4) == '6') ||
                                (coordinates.charAt(1) == '6' && coordinates.charAt(4) == '7') ||
                                (coordinates.charAt(1) == '7' && coordinates.charAt(4) == '8') ||
                                (coordinates.charAt(1) == '8' && coordinates.charAt(4) == '9') ||

                                (coordinates.charAt(1) == '2' && coordinates.charAt(4) == '1') ||
                                (coordinates.charAt(1) == '3' && coordinates.charAt(4) == '2') ||
                                (coordinates.charAt(1) == '4' && coordinates.charAt(4) == '3') ||
                                (coordinates.charAt(1) == '5' && coordinates.charAt(4) == '4') ||
                                (coordinates.charAt(1) == '6' && coordinates.charAt(4) == '5') ||
                                (coordinates.charAt(1) == '7' && coordinates.charAt(4) == '6') ||
                                (coordinates.charAt(1) == '8' && coordinates.charAt(4) == '7') ||
                                (coordinates.charAt(1) == '9' && coordinates.charAt(4) == '8')
                    //inverted output check
                ) {
                    correctTwoSmallHorizontal = true;
                    //  System.out.println("got small horizontal 2 Destroyer");

                    if ( (coordinates.charAt(1) == '2' && coordinates.charAt(4) == '1') ||
                            (coordinates.charAt(1) == '3' && coordinates.charAt(4) == '2') ||
                            (coordinates.charAt(1) == '4' && coordinates.charAt(4) == '3') ||
                            (coordinates.charAt(1) == '5' && coordinates.charAt(4) == '4') ||
                            (coordinates.charAt(1) == '6' && coordinates.charAt(4) == '5') ||
                            (coordinates.charAt(1) == '7' && coordinates.charAt(4) == '6') ||
                            (coordinates.charAt(1) == '8' && coordinates.charAt(4) == '7') ||
                            (coordinates.charAt(1) == '9' && coordinates.charAt(4) == '8')){
                        shipStart = shipStart;
                        shipStart2 = shipStart2-1;                                          //moving shipStart2
                    }


                    for (int i = shipStart2; i < shipStart2 + 2; i++) {

                        if (field_4[shipStart][i] == "." || field_4[shipStart][i] == "O") { //testing the absence of the 4 & 5-cell-ship
                            System.out.println("Error: Your 2-small-horizontal Destroyer is too close to the 3, 4 or 5-cell ship!");
                            correctTwoSmallHorizontal = false;
                            first = false;
                            second = false;
                            break;
                        } else if (field_4[shipStart][i] != "." || field_4[shipStart][i] != "O") {
                            correctTwoSmallHorizontal = true;

                        }
                    }

                    if (correctTwoSmallHorizontal) {
                        //    System.out.println("Nothing is in the way of your 2-small-horizontal Destroyer!");
                        for (int i = shipStart2; i < shipStart2 + 2; i++) {
                            field_4[shipStart][i] = "O";

                            field_4[shipStart][shipStart2 + 2] = "."; //tail

                            //upper line for small horizontal 2-cell Destroyer
                            if (shipStart != 0) {
                                field_4[shipStart - 1][i] = ".";
                                field_4[shipStart - 1][shipStart2 + 2] = "."; //upper tail (head is start)

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


            if ((correctTwoHorizontal) || (correctTwoVertical) || (correctTwoSmallHorizontal) || (correctTwoSmallVertical)) {
                //    System.out.println("Really got 2-cell Destroyer!!!!");
                correctTwo = true;
            } else System.out.println("Error: sth is wrong with your 2-cell Destroyer coordinates");


        } while (!correctTwo);
        return field_4;
    }
}