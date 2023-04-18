import java.util.Scanner;

class Battleship {

    private String[][] battleField;
    private final StringBuilder existingLocation = new StringBuilder();
    protected static final Scanner scanner = new Scanner(System.in);
    private boolean isIterated = false;
    enum VERTICAL_POS { A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8), I(9), J(10);
        private final int value;
        VERTICAL_POS(int value) {
            this.value = value;
        }
    }

    enum SHIPS { AIRCRAFT_CARRIER(5, "Aircraft Carrier"), BATTLESHIP(4, "Battleship"), SUBMARINE(3, "Submarine"), CRUISER(3, "Cruiser"), DESTROYER(2, "Destroyer");
        private final int value;
        private final String model;

        SHIPS(int value, String model) {
            this.value = value;
            this.model = model;
        }

        private int getCells() {
            return this.value;
        }

        private String getModel() {
            return this.model;
        }
    }
    enum LOCATION { WRONG_LENGTH, WRONG_LOCATION, TOO_CLOSE, RIGHT_LOCATION }
    public Battleship() {
        createBattleField();
    }
    private void createBattleField() {
        battleField = new String[11][11];
        int character = 65;
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField[0].length; j++) {
                if (i == 0) {
                    if (j != 0) {
                        battleField[i][j] = String.valueOf(j);
                    }
                } else {
                    if (j == 0) {
                        battleField[i][j] = String.valueOf((char) character);
                        ++character;
                    } else {
                        battleField[i][j] = "~";
                    }

                }
            }
        }
    }
    protected void placeBattleShip() {
        boolean allShipsPlaced = false;
        SHIPS currentShip = SHIPS.AIRCRAFT_CARRIER;

        do {
            switch(currentShip) {
                case AIRCRAFT_CARRIER:
                    if (mapCoordinates(currentShip)) {
                        display();
                        currentShip = SHIPS.BATTLESHIP;
                    }
                    break;
                case BATTLESHIP:
                    if (mapCoordinates(currentShip)) {
                        display();
                        currentShip = SHIPS.SUBMARINE;
                    }
                    break;
                case SUBMARINE:
                    if (mapCoordinates(currentShip)) {
                        display();
                        currentShip = SHIPS.CRUISER;
                    }
                    break;
                case CRUISER:
                    if (mapCoordinates(currentShip)) {
                        display();
                        currentShip = SHIPS.DESTROYER;
                    }
                    break;
                case DESTROYER:
                    if (mapCoordinates(currentShip)) {
                        display();
                        allShipsPlaced = true;
                    }
                    break;

                default:
                    break;
            }
        } while (!allShipsPlaced);
    }
    private boolean mapCoordinates(SHIPS currentShip) {
        if (!isIterated) {
            System.out.println("Enter the coordinates of the " + currentShip.getModel() + " (" + currentShip.getCells() +
                    " cells):\n");
        }

        try {
            String inputStr = scanner.nextLine();
            String[] coordinates = inputStr.split("\\s");

            System.out.println();

            LOCATION currentLocation = check_and_map(coordinates[0], coordinates[1], currentShip);
            if (currentLocation.equals(LOCATION.WRONG_LOCATION)){
                System.out.println("Error! Wrong ship location! Try again:\n");
                isIterated = true;
                return false;
            } else if (currentLocation.equals(LOCATION.WRONG_LENGTH)) {
                System.out.println("Error! Wrong length of the Submarine! Try again:\n");
                isIterated = true;
                return false;
            } else if (currentLocation.equals(LOCATION.TOO_CLOSE)) {
                System.out.println("Error! You placed it too close to another one. Try again.\n");
                isIterated = true;
                return false;
            } else if (currentLocation.equals(LOCATION.RIGHT_LOCATION)){
                isIterated = false;
                return true;
            }
        } catch(Exception e) {
            System.out.println("An exception occurred : " + e.getCause());
            return false;
        }

        return false;
    }

    private LOCATION check_and_map(String startPos, String endPos, SHIPS currentShip) {

        int xSPos = Integer.parseInt(String.valueOf(startPos.charAt(1)));;
        if (startPos.length() == 3) {
            String temp = String.valueOf(startPos.charAt(1)) + String.valueOf(startPos.charAt(2));
            xSPos = Integer.parseInt(temp);
        }
        int ySPos = (VERTICAL_POS.valueOf(String.valueOf(startPos.charAt(0)))).value;


        int xEPos = Integer.parseInt(String.valueOf(endPos.charAt(1)));
        if (endPos.length() == 3) {
            String temp = String.valueOf(endPos.charAt(1)) + String.valueOf(endPos.charAt(2));
            xEPos = Integer.parseInt(temp);
        }

        int yEPos = (VERTICAL_POS.valueOf(String.valueOf(endPos.charAt(0)))).value;

        if (xSPos != xEPos && ySPos == yEPos) {
            if (xSPos > xEPos) {
                int temp = xEPos;
                xEPos = xSPos;
                xSPos = temp;
            }

            if (currentShip.getCells() == Math.abs(xEPos - xSPos) + 1) {
                if (this.existingLocation.length() > 0) {
                    if (xEPos != 10 && ("O".equals(this.battleField[ySPos][xSPos - 1]) ||
                            "O".equals(this.battleField[ySPos][xEPos + 1]))) {
                        return LOCATION.TOO_CLOSE;
                    } else if (xEPos == 10 && "O".equals(this.battleField[ySPos][xSPos - 1])) {
                        return LOCATION.TOO_CLOSE;
                    }
                    for (int i = xSPos; i <= xEPos; i++) {
                        if (ySPos != 10 && ("O".equals(this.battleField[ySPos - 1][i]) ||
                                "O".equals(this.battleField[ySPos + 1][i]))) {
                            String verticalShip1 = "V" + (ySPos - 1) + i;
                            String verticalShip2 = "V" + (ySPos + 1) + i;

                            if (this.existingLocation.toString().contains(verticalShip1) ||
                                    this.existingLocation.toString().contains(verticalShip2)) {
                                for (;i >= xSPos; i--){
                                    this.battleField[ySPos][i] = "~";
                                    this.existingLocation.delete(this.existingLocation.length() - 4,
                                            this.existingLocation.length());
                                }
                                return LOCATION.TOO_CLOSE;
                            } else {
                                this.battleField[ySPos][i] = "O";
                                this.existingLocation.append("H").append(ySPos).append(i).append(" ");
                            }
                        } else if (ySPos == 10 && "O".equals(this.battleField[ySPos - 1][i])) {
                            String verticalShip1 = "V" + (ySPos - 1) + i;

                            if (this.existingLocation.toString().contains(verticalShip1)) {
                                for (;i >= xSPos; i--){
                                    this.battleField[ySPos][i] = "~";
                                    this.existingLocation.delete(this.existingLocation.length() - 4,
                                            this.existingLocation.length());
                                }
                                return LOCATION.TOO_CLOSE;
                            } else {
                                this.battleField[ySPos][i] = "O";
                                this.existingLocation.append("H").append(ySPos).append(i).append(" ");
                            }

                        } else {
                            this.battleField[ySPos][i] = "O";
                            this.existingLocation.append("H").append(ySPos).append(i).append(" ");
                        }
                    }
                } else {
                    for (int i = xSPos; i <= xEPos; i++) {
                        this.battleField[ySPos][i] = "O";
                        this.existingLocation.append("H").append(ySPos).append(i).append(" ");
                    }
                }
            } else {
                return LOCATION.WRONG_LENGTH;
            }

            return LOCATION.RIGHT_LOCATION;

        } else if (xSPos == xEPos && ySPos != yEPos) {
            if (ySPos > yEPos) {
                int temp = yEPos;
                yEPos = ySPos;
                ySPos = temp;
            }

            if (currentShip.getCells() == Math.abs(yEPos - ySPos) + 1) {
                if (this.existingLocation.length() > 0) {
                    /*
                     *  check if there is any ship before the starting position or
                     *  after the ending position that is going to touch your ship.
                     */
                    if (yEPos != 10 && ("O".equals(this.battleField[xSPos][ySPos - 1]) ||
                            "O".equals(this.battleField[xSPos][yEPos + 1]))) {
                        return LOCATION.TOO_CLOSE;
                    } else if (yEPos == 10 && "O".equals(this.battleField[xSPos][ySPos - 1])) {
                        return LOCATION.TOO_CLOSE;
                    }

                    /*
                     *  check if there is any ship in horizonal position touching
                     *  this ship.
                     */
                    for (int i = ySPos; i <= yEPos; i++) {
                        if (xSPos != 10 && ("O".equals(this.battleField[i][xSPos - 1]) ||
                                "O".equals(this.battleField[i][xSPos + 1]))) {
                            String horizontalShip1 = "H" + i + (xSPos - 1);
                            String horizontalShip2 = "H" + i + (xSPos + 1);

                            if (this.existingLocation.toString().contains(horizontalShip1) ||
                                    this.existingLocation.toString().contains(horizontalShip2)) {
                                for (;i >= ySPos; i--){
                                    this.battleField[i][xSPos] = "~";
                                    this.existingLocation.delete(this.existingLocation.length() - 4,
                                            this.existingLocation.length());
                                }
                                return LOCATION.TOO_CLOSE;
                            } else {
                                this.battleField[i][xSPos] = "O";
                                this.existingLocation.append("V").append(i).append(xSPos).append(" ");
                            }
                        } else if (xSPos == 10 && "O".equals(this.battleField[i][xSPos - 1])) {
                            String horizontalShip1 = "H" + i + (xSPos - 1);
                            if (this.existingLocation.toString().contains(horizontalShip1)) {
                                for (;i >= ySPos; i--){
                                    this.battleField[i][xSPos] = "~";
                                    this.existingLocation.delete(this.existingLocation.length() - 4,
                                            this.existingLocation.length());
                                }
                                return LOCATION.TOO_CLOSE;
                            } else {
                                this.battleField[i][xSPos] = "O";
                                this.existingLocation.append("V").append(i).append(xSPos).append(" ");
                            }

                        }else {
                            this.battleField[i][xSPos] = "O";
                            this.existingLocation.append("V").append(i).append(xSPos).append(" ");
                        }
                    }
                } else {
                    for (int i = ySPos; i <= yEPos; i++) {
                        this.battleField[i][xSPos] = "O";
                        this.existingLocation.append("V").append(i).append(xSPos).append(" ");
                    }
                }

                return LOCATION.RIGHT_LOCATION;
            } else {
                return LOCATION.WRONG_LENGTH;
            }
        } else {
            System.out.println();
            return LOCATION.WRONG_LOCATION;
        }
    }

    protected void display(){
        for(int i = 0; i < battleField.length; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(battleField[i][0]);
            }

            for (int j = 1; j < battleField[0].length; j++) {
                System.out.print(" " + battleField[i][j]);
            }

            System.out.println();
        }
        System.out.println();
    }
}
