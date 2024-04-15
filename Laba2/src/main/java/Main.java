import java.util.ArrayList;
import java.util.List;
    public class Main {
        public static void main(String[] args) {
            Port port = new Port(100, 5, 10);
            List<Ship> ships = new ArrayList<>();
            ships.add(new Ship("Ship1", 10, port, 20));
            ships.add(new Ship("Ship2", 15, port, 16));
            ships.add(new Ship("Ship3", 8, port, 18));
            ships.add(new Ship("Ship4", 12, port, 22));
            ships.add(new Ship("Ship5", 20, port, 23));

            for (Ship ship : ships) {
                ship.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (Ship ship : ships) {
                try {
                    ship.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("All ships have successfully left the port. Exiting the program.");
        }
    }
