public class Ship extends Thread{
    private String name;
    private int capacity;
    private int containers;
    private Port port;

    public Ship(String name, int capacity, Port port) {
        this.name = name;
        this.capacity = capacity;
        this.port = port;
        this.containers = 0;
    }

    @Override
    public void run() {
        while (true) {
            int action = (int) (Math.random() * 2); // 0 for loading, 1 for unloading
            int amount = (int) (Math.random() * 5) + 1; // Random amount of containers between 1 and 5

            if (action == 0) {
                port.loadContainers(amount);
                containers += amount;
            } else {
                port.unloadContainers(amount);
                containers -= amount;
            }

            try {
                Thread.sleep(2000); // Wait for 2 seconds before next action
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
