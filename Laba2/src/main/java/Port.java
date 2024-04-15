import java.util.ArrayList;
import java.util.List;

public class Port {
    private int capacity;
    private int containers;
    private List<Berth> berths;
    private static final int MAX_WAIT_TIME = 10000;

    public Port(int capacity, int numberOfBerths, int initialContainers) {
        if (capacity <= 0 || numberOfBerths <= 0 || initialContainers < 0) {
            throw new IllegalArgumentException("Capacity, number of berths, and initial containers must be non-negative values.");
        }
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
        long startTime = System.currentTimeMillis();
        while (containers + amount > capacity) {
            try {
                wait(MAX_WAIT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() - startTime > MAX_WAIT_TIME) {
                System.out.println("Timeout: Could not service " + ship.getShipName() + " for loading containers.");
                notifyAll();
                return;
            }
        }
        containers += amount;
        System.out.println(amount + " containers loaded from " + ship.getShipName() + ". Total containers in port: " + containers);
        notifyAll();
    }

    public synchronized void unloadContainers(int amount, Ship ship) {
        long startTime = System.currentTimeMillis();
        while (containers - amount < 0) {
            try {
                wait(MAX_WAIT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() - startTime > MAX_WAIT_TIME) {
                System.out.println("Timeout: Could not service " + ship.getShipName() + " for unloading containers.");
                notifyAll();
                return;
            }
        }
        containers -= amount;
        System.out.println(amount + " containers unloaded to " + ship.getShipName() + ". Total containers in port: " + containers);
        notifyAll();
    }

    public synchronized int getContainers() {
        return containers;
    }
    public int getNumberOfBerths() {
        return berths.size();
    }
}