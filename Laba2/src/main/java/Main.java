import java.util.ArrayList;
import java.util.List;
    public class Main {
        public static void main(String[] args) {
            Port port = new Port(100, 5, 10);
            List<Ship> ships = new ArrayList<>();
            ships.add(new Ship("Ship1", 10, port, 20, 5));
            ships.add(new Ship("Ship2", 15, port, 16, 8));
            ships.add(new Ship("Ship3", 8, port, 18, 3));
            ships.add(new Ship("Ship4", 12, port, 22, 7));
            ships.add(new Ship("Ship5", 20, port, 23, 10));

            if (ships.size() != port.getNumberOfBerths()) {
                System.out.println("Number of ships does not match number of berths in the port.");
                return;
            }

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
