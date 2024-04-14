import org.junit.Test;

import static org.junit.Assert.*;

public class ShipTest {

    @Test
    public void testRun_ShipLeavesAfterTimeElapsed() throws InterruptedException {
        Port port = new Port(20, 1);
        Ship ship = new Ship("TestShip", 10, port, 2); // Total time in port: 2 seconds
        ship.start();
        ship.join();
        assertNull(ship.getBerth());
    }


}
