package part2Test;
import org.junit.Test;
import static org.junit.Assert.*;
import part2.Point;
import part2.Quadrilateral;
import part2.QuadrilateralAnalyzer;

public class QuadrilateralAnalyzerTest {
    @Test
    public void testAddQuadrilateral() {
        QuadrilateralAnalyzer analyzer = new QuadrilateralAnalyzer();
        Quadrilateral quad = new Quadrilateral(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(0, 1));
        analyzer.addQuadrilateral(quad);
        assertEquals(1, analyzer.countQuadrilaterals());
    }

    @Test
    public void testCountSquares() {
        QuadrilateralAnalyzer analyzer = new QuadrilateralAnalyzer();
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(0, 1))); // Square
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2))); // Square
        assertEquals(2, analyzer.countSquares());
    }

    @Test
    public void testCountRectangles() {
        QuadrilateralAnalyzer analyzer = new QuadrilateralAnalyzer();
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(4, 0), new Point(4, 3), new Point(0, 3))); // Rectangle
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(3, 0), new Point(3, 2), new Point(0, 2))); // Rectangle
        assertEquals(2, analyzer.countRectangles());
    }

    @Test
    public void testCountRhombuses() {
        QuadrilateralAnalyzer analyzer = new QuadrilateralAnalyzer();
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(1, 1), new Point(2, 0), new Point(1, -1))); // Rhombus
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(1, 2), new Point(2, 0), new Point(1, -2))); // Rhombus
        assertEquals(1, analyzer.countRhombuses());
    }

    @Test
    public void testCountArbitraryQuadrilaterals() {
        QuadrilateralAnalyzer analyzer = new QuadrilateralAnalyzer();
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(1, 1), new Point(2, 0), new Point(1, -1))); // Rhombus
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(4, 0), new Point(4, 3), new Point(0, 3))); // Rectangle
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2))); // Square
        analyzer.addQuadrilateral(new Quadrilateral(new Point(0, 0), new Point(1, 2), new Point(2, 0), new Point(1, -2))); // Rhombus
        assertEquals(0, analyzer.countArbitraryQuadrilaterals());
    }

    @Test
    public void testFindLargestByArea() {
        QuadrilateralAnalyzer analyzer = new QuadrilateralAnalyzer();
        Quadrilateral quad1 = new Quadrilateral(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(0, 1)); // Area = 1
        Quadrilateral quad2 = new Quadrilateral(new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2)); // Area = 4
        analyzer.addQuadrilateral(quad1);
        analyzer.addQuadrilateral(quad2);
        assertEquals(quad2, analyzer.findLargestByArea());
    }

    @Test
    public void testFindSmallestByArea() {
        QuadrilateralAnalyzer analyzer = new QuadrilateralAnalyzer();
        Quadrilateral quad1 = new Quadrilateral(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(0, 1)); // Area = 1
        Quadrilateral quad2 = new Quadrilateral(new Point(0, 0), new Point(2, 0), new Point(2, 2), new Point(0, 2)); // Area = 4
        analyzer.addQuadrilateral(quad1);
        analyzer.addQuadrilateral(quad2);
        assertEquals(quad1, analyzer.findSmallestByArea());
    }

}
