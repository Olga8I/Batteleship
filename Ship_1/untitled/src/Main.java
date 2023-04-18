public class Main {

    public static void main(String[] args) {
        // Write your code here
        Battleship battleShip = new Battleship();
        battleShip.display();
        try {
            battleShip.placeBattleShip();
            Battleship.scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred : " + e.getMessage());
        }
    }
}