import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MAX_SHIPS = 10;

    public static void main(String[] args) {
        Port port = new Port(20, 3);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship("Ship1", 10, port, 20));
        ships.add(new Ship("Ship2", 15, port, 16));
        ships.add(new Ship("Ship3", 8, port, 18));
        ships.add(new Ship("Ship4", 12, port, 22));
        ships.add(new Ship("Ship5", 20, port, 28));
        ships.add(new Ship("Ship6", 7, port, 13));
        ships.add(new Ship("Ship7", 14, port, 14));
        ships.add(new Ship("Ship8", 9, port, 12));
        ships.add(new Ship("Ship9", 11, port, 21));
        ships.add(new Ship("Ship10", 18, port, 15));

        // Start ships with a delay to simulate staggered arrivals
        for (Ship ship : ships) {
            ship.start();
            try {
                Thread.sleep(1000); // Introduce a delay between ship starts
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Ship ship : ships) {
            try {
                ship.join(); // Wait for each ship to complete its operation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All ships have successfully left the port. Exiting the program.");
    }
}
