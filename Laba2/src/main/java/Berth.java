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
        System.out.println(ship.getName() + " docked at the berth.");
    }

    public synchronized void undockShip() {
        System.out.println(ship.getName() + " undocked from the berth.");
        this.ship = null;
        notifyAll();
    }
}
