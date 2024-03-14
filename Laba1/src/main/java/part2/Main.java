package part2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Quadrilateral quad1 = new Quadrilateral(new Point(0, 0), new Point(0, 5), new Point(5, 5), new Point(5, 0));
        Quadrilateral quad2 = new Quadrilateral(new Point(0, 0), new Point(0, 8), new Point(8, 8), new Point(8, 0));
        Quadrilateral quad3 = new Quadrilateral(new Point(0, 0), new Point(3, 3), new Point(0, 6), new Point(-3, 3));
        Quadrilateral quad4 = new Quadrilateral(new Point(0, 0), new Point(0, 4), new Point(6, 4), new Point(6, 0));
        Quadrilateral quad5 = new Quadrilateral(new Point(-2, 0), new Point(-1, 4), new Point(2, 5), new Point(3, 0));
        Quadrilateral quad6 = new Quadrilateral(new Point(1, 1), new Point(4, 2), new Point(6, -1), new Point(3, -2));
        Quadrilateral quad7 = new Quadrilateral(new Point(-3, 3), new Point(-2, 6), new Point(-5, 7), new Point(-6, 4));

        QuadrilateralAnalyzer analyzer = new QuadrilateralAnalyzer();
        analyzer.addQuadrilateral(quad1);
        analyzer.addQuadrilateral(quad2);
        analyzer.addQuadrilateral(quad3);
        analyzer.addQuadrilateral(quad4);
        analyzer.addQuadrilateral(quad5);
        analyzer.addQuadrilateral(quad6);
        analyzer.addQuadrilateral(quad7);

        // Print the counts of different types of quadrilaterals
        System.out.println("Number of quadrilaterals: " + analyzer.countQuadrilaterals());
        System.out.println("Number of squares: " + analyzer.countSquares());
        System.out.println("Number of rectangles: " + analyzer.countRectangles());
        System.out.println("Number of rhombuses: " + analyzer.countRhombuses());
        System.out.println("Number of arbitrary quadrilaterals: " + analyzer.countArbitraryQuadrilaterals());

        // Find and print the largest and smallest quadrilaterals by area
        Quadrilateral largestByArea = analyzer.findLargestByArea();
        Quadrilateral smallestByArea = analyzer.findSmallestByArea();
        System.out.println("Largest quadrilateral by area: " + largestByArea);
        System.out.println("Smallest quadrilateral by area: " + smallestByArea);

        // Find and print the largest and smallest quadrilaterals by perimeter
        Quadrilateral largestByPerimeter = analyzer.findLargestByPerimeter();
        Quadrilateral smallestByPerimeter = analyzer.findSmallestByPerimeter();
        System.out.println("Largest quadrilateral by perimeter: " + largestByPerimeter);
        System.out.println("Smallest quadrilateral by perimeter: " + smallestByPerimeter);
    }

}
