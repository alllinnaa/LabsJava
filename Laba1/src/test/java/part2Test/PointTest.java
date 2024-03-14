package part2Test;
import org.junit.Test;
import part2.Point;
import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void testHasMoved() {
        Point point = new Point(0, 0);
        assertFalse(point.hasMoved());
        point.move(5, 5);
        assertTrue(point.hasMoved());
    }

    @Test
    public void testGetSpeed() {
        Point point = new Point(0, 0);
        point.move(3, 4);
        assertEquals(5, point.getSpeed(), 0.001);
    }

    @Test
    public void testIntersects() {
        Point point1 = new Point(-5, 0);
        Point point2 = new Point(-3, 5);
        point1.move(0, 4);
        point2.move(2, 0);
        assertTrue(point1.intersects(point2));
    }
}
