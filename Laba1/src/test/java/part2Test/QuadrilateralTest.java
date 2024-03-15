package part2Test;
import org.junit.Test;
import static org.junit.Assert.*;
import part2.Point;
import part2.Quadrilateral;

public class QuadrilateralTest {
    @Test
    public void testCalculateArea() {
        // Define points for a quadrilateral
        Point point1 = new Point(0, 0);
        Point point2 = new Point(4, 0);
        Point point3 = new Point(4, 3);
        Point point4 = new Point(0, 3);

        // Create a quadrilateral
        Quadrilateral quadrilateral = new Quadrilateral(point1, point2, point3, point4);

        // Calculate the area
        double expectedArea = 12.0; // Area of a rectangle with sides 4 and 3
        double actualArea = quadrilateral.calculateArea();

        // Check if the calculated area matches the expected area
        assertEquals(expectedArea, actualArea, 0.01); // Allowing a small delta for floating point comparison
    }

    @Test
    public void testCalculatePerimeter() {
        // Define points for a quadrilateral
        Point point1 = new Point(0, 0);
        Point point2 = new Point(4, 0);
        Point point3 = new Point(4, 3);
        Point point4 = new Point(0, 3);

        // Create a quadrilateral
        Quadrilateral quadrilateral = new Quadrilateral(point1, point2, point3, point4);

        // Calculate the perimeter
        double expectedPerimeter = 14.0; // Perimeter of a rectangle with sides 4 and 3
        double actualPerimeter = quadrilateral.calculatePerimeter();

        // Check if the calculated perimeter matches the expected perimeter
        assertEquals(expectedPerimeter, actualPerimeter, 0.01); // Allowing a small delta for floating point comparison
    }

    @Test
    public void testIsSquare() {
        // Define points for a square
        Point point1 = new Point(0, 0);
        Point point2 = new Point(1, 0);
        Point point3 = new Point(1, 1);
        Point point4 = new Point(0, 1);

        // Create a quadrilateral
        Quadrilateral quadrilateral = new Quadrilateral(point1, point2, point3, point4);

        // Check if the quadrilateral is a square
        assertTrue(quadrilateral.isSquare());
    }

    @Test
    public void testIsRectangle() {
        // Define points for a rectangle
        Point point1 = new Point(0, 0);
        Point point2 = new Point(4, 0);
        Point point3 = new Point(4, 3);
        Point point4 = new Point(0, 3);

        // Create a quadrilateral
        Quadrilateral quadrilateral = new Quadrilateral(point1, point2, point3, point4);

        // Check if the quadrilateral is a rectangle
        assertTrue(quadrilateral.isRectangle());
    }

    @Test
    public void testIsRhombus() {
        // Define points for a rhombus
        Point point1 = new Point(0, 0);
        Point point2 = new Point(2, 1);
        Point point3 = new Point(0, 2);
        Point point4 = new Point(-2, 1);

        // Create a quadrilateral
        Quadrilateral quadrilateral = new Quadrilateral(point1, point2, point3, point4);

        // Check if the quadrilateral is a rhombus
        assertTrue(quadrilateral.isRhombus());
    }
}
