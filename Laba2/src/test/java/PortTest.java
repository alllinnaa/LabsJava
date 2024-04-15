import org.junit.Test;

import static org.junit.Assert.*;

public class PortTest {
    @Test
    public void testLoadContainers() {
        Port port = new Port(20, 3,0);
        Ship ship = new Ship("TestShip", 10, port, 20,0);
        port.loadContainers(5, ship);
        assertEquals(5, port.getContainers());
    }

    @Test
    public void testUnloadContainers() {
        Port port = new Port(20, 3,0);
        Ship ship = new Ship("TestShip", 10, port, 20,0);
        port.loadContainers(10, ship);
        port.unloadContainers(5, ship);
        assertEquals(5, port.getContainers());
    }

    @Test
    public void testGetFreeBerth() {
        Port port = new Port(20, 2,0);
        Ship ship1 = new Ship("Ship1", 10, port, 20,0);
        Ship ship2 = new Ship("Ship2", 15, port, 20,0);
        assertNotNull(port.getFreeBerth());
        Berth berth = port.getFreeBerth();
        berth.dockShip(ship1);
        assertNotNull(port.getFreeBerth());
        Berth anotherBerth = port.getFreeBerth();
        anotherBerth.dockShip(ship2);
        assertNull(port.getFreeBerth());
    }
}