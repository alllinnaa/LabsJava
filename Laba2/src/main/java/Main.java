import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Port port = new Port(20);
        Berth berth = new Berth();

        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship("Ship1", 10, port));
        ships.add(new Ship("Ship2", 15, port));
        ships.add(new Ship("Ship3", 8, port));

        for (Ship ship : ships) {
            ship.start();
        }

        for (Ship ship : ships) {
            berth.dockShip(ship);
            try {
                Thread.sleep(1000); // Wait for 1 second between docking each ship
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            berth.undockShip();
        }
    }
}
