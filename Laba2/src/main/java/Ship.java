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
        long startTime = System.currentTimeMillis();

        Berth freeBerth = port.getFreeBerth();
        while (freeBerth == null) {
            System.out.println(name + " is waiting for a free berth.");
            try {
                port.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        freeBerth.dockShip(this);

        while (totalTimeInPort > 0) {
            int action = (int) (Math.random() * 2);
            int amount = (int) (Math.random() * 5) + 1;

            synchronized (port) {
                if (action == 0) {
                    if (containers >= amount) {
                        port.unloadContainers(amount, this);
                        containers -= amount;
                    } else {
                        System.out.println(name + " cannot unload " + amount + " containers. Ship does not have enough containers.");
                    }
                } else {
                    if (containers + amount <= capacity) {
                        port.loadContainers(amount, this);
                        containers += amount;
                    } else {
                        System.out.println(name + " cannot load " + amount + " containers. Ship's capacity is exceeded.");
                    }
                }
            }

            try {
                Thread.sleep(2000);
                totalTimeInPort -= 2;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (System.currentTimeMillis() - startTime > totalTimeInPort * 1000) {
                System.out.println(name + " has exceeded its total time in port and is leaving.");
                break;
            }
        }

        System.out.println(name + " has left the port.");
        if (berth != null) {
            berth.unloadShip();
        }
    }
}