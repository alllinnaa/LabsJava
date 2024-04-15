import java.util.ArrayList;
import java.util.List;

public class Port {
    private int capacity;
    private int containers;
    private List<Berth> berths;

    public Port(int capacity, int numberOfBerths, int initialContainers) {
        this.capacity = capacity;
        this.containers = Math.min(initialContainers, capacity);
        this.berths = new ArrayList<>(numberOfBerths);
        for (int i = 0; i < numberOfBerths; i++) {
            berths.add(new Berth());
        }
    }
    public synchronized Berth getFreeBerth() {
        for (Berth berth : berths) {
            if (!berth.isOccupied()) {
                return berth;
            }
        }
        return null;
    }

    public synchronized void loadContainers(int amount, Ship ship) {
        while (containers + amount > capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        containers += amount;
        System.out.println(amount + " containers loaded from " + ship.getShipName() + ". Total containers in port: " + containers);
        notifyAll();
    }

    public synchronized void unloadContainers(int amount, Ship ship) {
        while (containers - amount < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        containers -= amount;
        System.out.println(amount + " containers unloaded to " + ship.getShipName() + ". Total containers in port: " + containers);
        notifyAll();
    }

    public synchronized int getContainers() {
        return containers;
    }
}