package com.Java;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        ShipGame game = new ShipGame();
        game.printField();      //printField takes a matrix & prints it

        //Five five = new Five();
        //String[][] field_5 = five.getFive();

        //Four four = new Four();
        //String[][] field_4 = four.getFour(field_5);

        //Three three = new Three();
        //String[][] field_3 = three.getThree(field_4);

        Three_Cruiser three_cruiser = new Three_Cruiser();
        String[][] field_3_Cruiser = three_cruiser.getThree(field_3);

        Two two = new Two();
        String[][] field_2 = two.getTwo(field_3_Cruiser);

    }

}
