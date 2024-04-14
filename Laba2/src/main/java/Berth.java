public class Berth {
    private Ship ship;

    public synchronized void dockShip(Ship ship) {
        while (this.ship != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.ship = ship;
        System.out.println(ship.getShipName() + " docked at the berth.");
        notifyAll();
    }

    public synchronized void unloadShip() {
        System.out.println(ship.getShipName() + " unloaded. Departing from the berth.");
        this.ship = null;
        notifyAll();
    }

    public synchronized Ship getShip() {
        return ship;
    }
}
