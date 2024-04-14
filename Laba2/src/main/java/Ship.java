import java.util.Random;

public class Ship extends Thread {
    private String name;
    private int capacity;
    private int containers;
    private Port port;
    private Berth berth;
    private int totalTimeInPort;

    public Ship(String name, int capacity, Port port, int totalTimeInPort) {
        this.name = name;
        this.capacity = capacity;
        this.port = port;
        this.containers = 0;
        this.berth = null;
        this.totalTimeInPort = totalTimeInPort;
    }

    public String getShipName() {
        return name;
    }

    public Berth getBerth() {
        return berth;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis(); // Record the start time of ship's arrival

        while (totalTimeInPort > 0) {
            int action = (int) (Math.random() * 2);
            int amount = (int) (Math.random() * 5) + 1;

            if (action == 0) {
                port.loadContainers(amount);
                containers += amount;
            } else {
                port.unloadContainers(amount);
                containers -= amount;
            }

            try {
                Thread.sleep(2000); // Simulate time spent on loading/unloading
                totalTimeInPort -= 2; // Decrement time in port
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check if ship has spent more time than totalTimeInPort
            if (System.currentTimeMillis() - startTime > totalTimeInPort * 1000) {
                System.out.println(name + " has exceeded its total time in port and is leaving.");
                break;
            }
        }

        System.out.println(name + " has left the port.");
        if (berth != null) {
            berth.unloadShip();
            port.releaseBerth(berth); // Release berth when ship leaves
        }
    }
}
