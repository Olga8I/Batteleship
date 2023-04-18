import java.util.Arrays;
import java.util.Scanner;

class Battleship {
    private final String[][] field;
    private final int fieldColumnCount;
    private final int fieldRowCount;
    private Scanner scanner = new Scanner(System.in);
    private char row1, row2;
    private int col1, col2;
    private int x, y;
    private ShipType shipType;

    private enum ShipType {
        AIRCRAFTCARRIER("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        SUBMARINE("Submarine", 3),
        CRUISER("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        final String name;
        final Integer size;

        ShipType(String name, Integer size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public Integer getSize() {
            return size;
        }
    }

    Battleship() {
        this(10, 10);
    }

    Battleship(Integer row, Integer column) {
        fieldRowCount = ++row;
        fieldColumnCount = ++column;
        field = new String[row][column];
        createGrid();
    }

    private void createGrid() {
        for (int i = 1; i < fieldColumnCount; i++) {
            String[] item = new String[11];
            char ch = (char) ('@' + i);
            item[0] = String.valueOf(ch);
            Arrays.fill(item, 1, 11,"~");
            field[i] = item;
        }

        String[] item = new String[11];
        for (int i = 1; i < fieldColumnCount; i++) {
            item[i] =  Integer.toString(i);
        }
        item[0] = " ";
        field[0] = item;
    }

    private void arrangeShips() {
        for (ShipType ship : ShipType.values()) {
            putShip(ship);
        }
    }

    private void putShip(ShipType ship) {
        shipType = ship;
        System.out.printf("Enter the coordinates of the %s (%d cells): %n", shipType.getName(), shipType.getSize());
        while (true) {
            String re = scanner.nextLine();
            String[] loc = re.trim().split("\\s+");
            try {
                check(loc);
                insert();
                System.out.println();
                print();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void insert() {
        if (row1 == row2) {
            if (col1 > col2) {
                for (int i = 0; i < shipType.getSize(); i++) {
                    field[+row1 - '@'][col2 + i] = "O";
                }
            } else {
                for (int i = 0; i < shipType.getSize(); i++) {
                    field[+row1 - '@'][col1 + i] = "O";
                }
            }
        } else if ( col1 == col2) {
            if (row1 > row2){
                for (int i = 0; i < shipType.getSize(); i++) {
                    field[+row2 - '@' + i][col1] = "O";
                }
            } else {
                for (int i = 0; i < shipType.getSize(); i++) {
                    field[+row1 - '@' + i][col1] = "O";
                }
            }
        }
    }

    private void check( String[] loc) {
        row1 = Character.toUpperCase(loc[0].charAt(0));
        col1 = Integer.parseInt(loc[0].substring(1));
        row2 = Character.toUpperCase(loc[1].charAt(0));
        col2 = Integer.parseInt(loc[1].substring(1));

        checkLocation();
        checkSize();
        checkGrid();
    }

    private void checkSize() {
        if (row1 == row2) {
            if (Math.abs(col1 - col2) + 1 != shipType.getSize()) {
                throw new IllegalArgumentException("Error! Wrong length of the " + shipType.getName() +"! Try again: ");
            }
        } else if ( col1 == col2) {
            if (Math.abs(row1 - row2) + 1 != shipType.getSize()){
                throw new IllegalArgumentException("Error! Wrong length of the " + shipType.getName() +"! Try again: ");
            }
        }
    }

    private void checkLocation() {
        if (row1 != row2 && col1 != col2 || col1 < 1 || col1 > 10  || col2 < 1 || col2 > 10 || +row1 - '@' < 1 || +row1 - '@' > 10 || +row2 - '@' < 1 || +row2 - '@' > 10) {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again: ");
        }
    }

    private void checkGrid() {
        for (int j = -1; j < 2; j++) {
            if (row1 == row2) {
                for (int i = 0; i < shipType.getSize(); i++) {
                    boolean rh = (col1 + i + j) < fieldRowCount;
                    boolean ch = (+row1 - '@' + i + j) < fieldColumnCount;
                    if(ch && rh && (field[+row1 - '@'][col1 + i + j] == "O" || field[+row1 - '@' + j][col1 + i + j] == "O" )) {
                        throw new IllegalArgumentException("Error! You placed it too close to another one. Try again: ");
                    }
                }
            } else if ( col1 == col2) {
                for (int i = 0; i < shipType.getSize(); i++) {
                    boolean rh = (col1 + i + j) < fieldRowCount;
                    boolean ch = (+row1 - '@' + i + j) < fieldColumnCount;
                    if(rh && ch && (field[+row1 - '@' + i + j][col1] == "O" || field[+row1 - '@'][col1 + i + j] == "O")) {
                        throw new IllegalArgumentException("Error! You placed it too close to another one. Try again: ");
                    }
                }
            }
        }
    }

    protected void start() {
        print();
        arrangeShips();
        shooting();
    }

    private void shooting() {
        System.out.println("The game starts!");
        print();
        System.out.println("Take a shot!");
        while (true) {
            try {
                getCoordinates();
                checkCoordinate();
                shoot();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void getCoordinates() {
        String re = scanner.next();
        x = +Character.toUpperCase(re.charAt(0)) - '@';
        y = Integer.parseInt(re.substring(1));
    }

    private void checkCoordinate() {
        if (y < 1 || y > 10  ||  x < 1 || x > 10) {
            throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
        }
    }

    private void shoot(){
        if (field[x][y] == "O") {
            field[x][y] = "X";
            print();
            System.out.println("You hit a ship!");
        } else {
            field[x][y] = "M";
            print();
            System.out.println("You missed!");
        }
    }

    protected void print() {
        for (int i = 0; i < fieldRowCount; i++) {
            for (int j = 0; j < fieldRowCount; j++) {
                System.out.printf("%s ", field[i][j]);
            }
            System.out.println();
        }
    }
}