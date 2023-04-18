package com.Java;
import java.util.LinkedHashMap;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Main {

    public static void main(String[] args) {
        BattleField battleField = new BattleField();
        battleField.startGame();
    }
}

class BattleField {
    private final char[][] battleField = new char[10][10];
    private final LinkedHashMap<String, Integer> ships = new LinkedHashMap<>();
    private final char aChar = 'A';
    private final char fogOfWarChar = '~';
    private final char shipChar = 'O';
    private final char hitChar = 'X';
    private final char missChar = 'M';
    private int shipsLeft;

    public BattleField() {
        ships.put("Aircraft Carrier", 5);
        ships.put("Battleship", 4);
        ships.put("Submarine", 3);
        ships.put("Cruiser", 3);
        ships.put("Destroyer", 2);
        shipsLeft = 5;

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                battleField[row][col] = fogOfWarChar;
            }
        }

        printBattleField();
    }

    private void printBattleField() {
        printBattleField(false);
    }

    private void printBattleField(boolean isFogEnabled) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int row = 0; row < 10; row++) {
            System.out.print((char) (aChar + row) + " ");
            for (int col = 0; col < 10; col++) {
                char currChar = battleField[row][col];
                if (isFogEnabled) {
                    currChar = currChar == shipChar ? fogOfWarChar : currChar;
                }
                System.out.print(currChar + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void startGame() {
        fillBattleField();
        System.out.println("The game starts!\n");
        printBattleField(true);
        System.out.println("Take a shot!");
        while (shipsLeft != 0) {
            takeShot();
        }

    }

    public void takeShot() {
        boolean isDone = false;
        Scanner scanner = new Scanner(System.in);

        while (!isDone) {
            String coords = scanner.next();

            try {
                String message = "";
                int row = coords.charAt(0) - aChar;
                int col = Integer.parseInt(coords.substring(1)) - 1;

                char aimChar = battleField[row][col];
                if (aimChar == shipChar) {
                    battleField[row][col] = hitChar;
                    if (isShipSank(row, col)) {
                        shipsLeft -= 1;
                        if (shipsLeft != 0) {
                            message = "You sank a ship! Specify a new target:";
                        } else {
                            message = "You sank the last ship. You won. Congratulations!";
                        }
                    } else {
                        message = "You hit a ship! Try again:";
                    }
                } else if (aimChar == fogOfWarChar) {
                    battleField[row][col] = missChar;
                    message = "You missed! Try again:";
                } else {
                    message = "You've already shoot this cell!";
                }
                isDone = true;

                System.out.println();
                printBattleField(true);
                System.out.println(message);
                System.out.println();

            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        }
    }

    public void fillBattleField() {
        ships.forEach((key, value) -> {
            boolean isDone = false;
            while (!isDone) {
                try {
                    int[] coords = getAndParseCoords(String.format("Enter the coordinates of the %s (%d cells):", key, value));
                    if (!isCoordsPlaneMatch(coords)) {
                        throw new CustomException("Wrong ship location");
                    }
                    if (!isLengthMatch(coords, value)) {
                        throw new CustomException(String.format("Wrong length of the %s", key));
                    }

                    if (!isNoIntersections(coords)) {
                        throw new CustomException("You placed it too close to another one");
                    }

                    placeShip(coords);
                    isDone = true;

                    printBattleField();

                } catch (Exception exc) {
                    System.out.printf("Error! %s! Try again:\n", exc.getMessage());
                }
            }

        });
    }

    private int[] getAndParseCoords(String invocationMsg) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(invocationMsg);
        String beginCoords = scanner.next();
        String endCoords = scanner.next();

        int firstRow = beginCoords.charAt(0) - aChar;
        int secondRow = endCoords.charAt(0) - aChar;

        int firstCol = Integer.parseInt(beginCoords.substring(1));
        int secondCol = Integer.parseInt(endCoords.substring(1));


        int beginRow = min(firstRow, secondRow);
        int endRow = max(firstRow, secondRow);

        int beginCol = min(firstCol, secondCol) - 1;
        int endCol = max(firstCol, secondCol) - 1;

        return new int[]{beginRow, endRow, beginCol, endCol};
    }

    private void placeShip(int[] coords) {
        for (int row = coords[0]; row <= coords[1]; row++) {
            for (int col = coords[2]; col <= coords[3]; col++) {
                battleField[row][col] = shipChar;
            }

        }
    }

    private boolean isCoordsPlaneMatch(int[] coords) {
        return coords[0] == coords[1] || coords[2] == coords[3];
    }

    private boolean isLengthMatch(int[] coords, int targetLength) {
        int computedLength;
        if (coords[0] == coords[1]) {
            computedLength = coords[3] - coords[2] + 1;
        } else {
            computedLength = coords[1] - coords[0] + 1;
        }
        return computedLength == targetLength;
    }

    private boolean isNoIntersections(int[] coords) {
        int beginRowForCheck = max(0, coords[0] - 1);
        int endRowForCheck = min(9, coords[1] + 1);
        int beginColForCheck = max(0, coords[2] - 1);
        int endColForCheck = min(9, coords[3] + 1);

        return isNoIntersectionWithShip(beginRowForCheck, endRowForCheck, beginColForCheck, endColForCheck);
    }

    private boolean isShipSank(int row, int col) {
        int beginRowForCheck = max(0, row - 1);
        int endRowForCheck = min(9, row + 1);
        int beginColForCheck = max(0, col - 1);
        int endColForCheck = min(9, col + 1);

        return isNoIntersectionWithShip(beginRowForCheck, endRowForCheck, beginColForCheck, endColForCheck);
    }

    private boolean isNoIntersectionWithShip(int beginRowForCheck, int endRowForCheck,
                                             int beginColForCheck, int endColForCheck) {
        boolean hasIntersections = false;
        for (int checkRow = beginRowForCheck; checkRow <= endRowForCheck; checkRow++) {
            if (hasIntersections) {
                break;
            }
            for (int checkCol = beginColForCheck; checkCol <= endColForCheck; checkCol++) {
                if (battleField[checkRow][checkCol] == shipChar) {
                    hasIntersections = true;
                    break;
                }
            }
        }
        return !hasIntersections;
    }

}

class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }

}