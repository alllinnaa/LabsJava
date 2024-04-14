import org.junit.Test;

import static org.junit.Assert.*;

public class PortTest {
    @Test
    public void testLoadContainers() {
        Port port = new Port(20, 3);
        Ship ship = new Ship("TestShip", 10, port, 20);

        // Load containers into the port
        port.loadContainers(5, ship);

        // Check if containers are loaded correctly
        assertEquals(5, port.getContainers());
    }

    @Test
    public void testUnloadContainers() {
        Port port = new Port(20, 3);
        Ship ship = new Ship("TestShip", 10, port, 20);

        // Load containers into the port
        port.loadContainers(10, ship);

        // Unload containers from the port
        port.unloadContainers(5, ship);

        // Check if containers are unloaded correctly
        assertEquals(5, port.getContainers());
    }

    @Test
    public void testGetFreeBerth() {
        Port port = new Port(20, 2);
        Ship ship1 = new Ship("Ship1", 10, port, 20);
        Ship ship2 = new Ship("Ship2", 15, port, 20);

        assertNotNull(port.getFreeBerth());

        Berth berth = port.getFreeBerth();
        berth.dockShip(ship1);

        assertNotNull(port.getFreeBerth());

        Berth anotherBerth = port.getFreeBerth();
        anotherBerth.dockShip(ship2);

        assertNull(port.getFreeBerth());
    }
}