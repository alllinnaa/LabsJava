import org.junit.Test;

import static org.junit.Assert.*;

public class BerthTest {

    @Test
    public void testDockShip_ShipDocksSuccessfully() {
        Berth berth = new Berth();
        Ship ship = new Ship("TestShip", 10, null, 5, 0); // Providing a non-negative initial container value
        berth.dockShip(ship);
        assertEquals(ship, berth.getShip());
    }

    @Test
    public void testUnloadShip_ShipUnloadedSuccessfully() {
        Berth berth = new Berth();
        Ship ship = new Ship("TestShip", 10, null, 5, 0); // Providing a non-negative initial container value
        berth.dockShip(ship);
        berth.unloadShip();
        assertNull(berth.getShip());
    }
}
