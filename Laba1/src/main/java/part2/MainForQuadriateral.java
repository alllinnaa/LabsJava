package part2;

public class MainForQuadriateral {
    public static void main(String[] args) {
        Quadrilateral quad1 = new Quadrilateral(new Point(0, 0), new Point(0, 5), new Point(5, 5), new Point(5, 0));
        Quadrilateral quad2 = new Quadrilateral(new Point(0, 0), new Point(0, 8), new Point(8, 8), new Point(8, 0));
        Quadrilateral quad3 = new Quadrilateral(new Point(0, 0), new Point(3, 3), new Point(0, 6), new Point(-3, 3));
        Quadrilateral quad4 = new Quadrilateral(new Point(0, 0), new Point(0, 4), new Point(6, 4), new Point(6, 0));
        Quadrilateral quad5 = new Quadrilateral(new Point(-2, 0), new Point(-1, 4), new Point(2, 5), new Point(3, 0));
        Quadrilateral quad6 = new Quadrilateral(new Point(1, 1), new Point(4, 2), new Point(6, -1), new Point(3, -2));
        Quadrilateral quad7 = new Quadrilateral(new Point(-3, 3), new Point(-2, 6), new Point(-5, 7), new Point(-6, 4));
        Quadrilateral quad8 = new Quadrilateral(new Point(0, 0), new Point(1, -2), new Point(2, 0), new Point(1, 2));


        QuadrilateralAnalyzer analyzer = new QuadrilateralAnalyzer();
        analyzer.addQuadrilateral(quad1);
        analyzer.addQuadrilateral(quad2);
        analyzer.addQuadrilateral(quad3);
        analyzer.addQuadrilateral(quad4);
        analyzer.addQuadrilateral(quad5);
        analyzer.addQuadrilateral(quad6);
        analyzer.addQuadrilateral(quad7);
        analyzer.addQuadrilateral(quad8);


        System.out.println("Quadrilateral 1: Type - " + analyzer.getType(quad1) + ", Area: " + quad1.calculateArea() + ", Perimeter: " + quad1.calculatePerimeter());
        System.out.println("Quadrilateral 2: Type - " + analyzer.getType(quad2) + ", Area: " + quad2.calculateArea() + ", Perimeter: " + quad2.calculatePerimeter());
        System.out.println("Quadrilateral 3: Type - " + analyzer.getType(quad3) + ", Area: " + quad3.calculateArea() + ", Perimeter: " + quad3.calculatePerimeter());
        System.out.println("Quadrilateral 4: Type - " + analyzer.getType(quad4) + ", Area: " + quad4.calculateArea() + ", Perimeter: " + quad4.calculatePerimeter());
        System.out.println("Quadrilateral 5: Type - " + analyzer.getType(quad5) + ", Area: " + quad5.calculateArea() + ", Perimeter: " + quad5.calculatePerimeter());
        System.out.println("Quadrilateral 6: Type - " + analyzer.getType(quad6) + ", Area: " + quad6.calculateArea() + ", Perimeter: " + quad6.calculatePerimeter());
        System.out.println("Quadrilateral 7: Type - " + analyzer.getType(quad7) + ", Area: " + quad7.calculateArea() + ", Perimeter: " + quad7.calculatePerimeter());
        System.out.println("Quadrilateral 8: Type - " + analyzer.getType(quad8) + ", Area: " + quad8.calculateArea() + ", Perimeter: " + quad8.calculatePerimeter());


        System.out.println("Number of quadrilaterals: " + analyzer.countQuadrilaterals());
        System.out.println("Number of squares: " + analyzer.countSquares());
        System.out.println("Number of rectangles: " + analyzer.countRectangles());
        System.out.println("Number of rhombuses: " + analyzer.countRhombuses());
        System.out.println("Number of arbitrary quadrilaterals: " + analyzer.countArbitraryQuadrilaterals());


        Quadrilateral largestByArea = analyzer.findLargestByArea();
        Quadrilateral smallestByArea = analyzer.findSmallestByArea();
        System.out.println("Largest quadrilateral by area: Type - " + analyzer.getType(largestByArea) + ", Area: " + largestByArea.calculateArea() + ", Perimeter: " + largestByArea.calculatePerimeter());
        System.out.println("Smallest quadrilateral by area: Type - " + analyzer.getType(smallestByArea) + ", Area: " + smallestByArea.calculateArea() + ", Perimeter: " + smallestByArea.calculatePerimeter());


        Quadrilateral largestByPerimeter = analyzer.findLargestByPerimeter();
        Quadrilateral smallestByPerimeter = analyzer.findSmallestByPerimeter();
        System.out.println("Largest quadrilateral by perimeter: Type - " + analyzer.getType(largestByPerimeter) + ", Area: " + largestByPerimeter.calculateArea() + ", Perimeter: " + largestByPerimeter.calculatePerimeter());
        System.out.println("Smallest quadrilateral by perimeter: Type - " + analyzer.getType(smallestByPerimeter) + ", Area: " + smallestByPerimeter.calculateArea() + ", Perimeter: " + smallestByPerimeter.calculatePerimeter());
    }
}
