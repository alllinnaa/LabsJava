public class Berth {
    private Ship ship;
    private static final int MAX_WAIT_TIME = 10000;

    public synchronized void dockShip(Ship ship) {
        long startTime = System.currentTimeMillis();
        while (this.ship != null) {
            try {
                wait(MAX_WAIT_TIME); // Wait with a maximum timeout
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() - startTime > MAX_WAIT_TIME) {
                System.out.println("Timeout: Could not dock " + ship.getShipName() + " at the berth.");
                notifyAll();
                return;
            }
        }
        this.ship = ship;
        System.out.println(ship.getShipName() + " docked at the berth.");
        notifyAll();
    }

    public synchronized void unloadShip() {
        long startTime = System.currentTimeMillis();
        while (this.ship == null) {
            try {
                wait(MAX_WAIT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() - startTime > MAX_WAIT_TIME) {
                System.out.println("Timeout: Could not unload " + ship.getShipName() + " from the berth.");
                notifyAll();
                return;
            }
        }
        System.out.println(ship.getShipName() + " unloaded. Departing from the berth.");
        this.ship = null;
        notifyAll();
    }

    public synchronized boolean isOccupied() {
        return ship != null;
    }

    public Ship getShip() {
        return ship;
    }
}