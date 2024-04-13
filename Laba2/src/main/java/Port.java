public class Port {
    private int capacity;
    private int containers;

    public Port(int capacity) {
        this.capacity = capacity;
        this.containers = 0;
    }

    public synchronized void loadContainers(int amount) {
        while (containers + amount > capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        containers += amount;
        System.out.println(amount + " containers loaded. Total containers in port: " + containers);
        notifyAll();
    }

    public synchronized void unloadContainers(int amount) {
        while (containers - amount < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        containers -= amount;
        System.out.println(amount + " containers unloaded. Total containers in port: " + containers);
        notifyAll();
    }
}
