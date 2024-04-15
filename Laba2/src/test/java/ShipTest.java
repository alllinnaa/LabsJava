import org.junit.Test;

import static org.junit.Assert.*;

public class ShipTest {

    @Test
    public void testRun_ShipLeavesAfterTimeElapsed() throws InterruptedException {
        Port port = new Port(20, 1,0);
        Ship ship = new Ship("TestShip", 10, port, 2,0); // Total time in port: 2 seconds
        ship.start();
        ship.join();
        assertNull(ship.getBerth());
    }

}
