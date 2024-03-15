package part2;

import java.util.Arrays;

public class Quadrilateral {
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    public Quadrilateral(Point point1, Point point2, Point point3, Point point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    public double calculateArea() {
        // Calculate the area of the quadrilateral by dividing it into two triangles
        double area1 = calculateTriangleArea(point1, point2, point3);
        double area2 = calculateTriangleArea(point1, point3, point4);
        return area1 + area2;
    }

    private double calculateTriangleArea(Point p1, Point p2, Point p3) {
        // Calculate the area of a triangle using the shoelace formula
        return Math.abs((p1.getX() * (p2.getY() - p3.getY())
                + p2.getX() * (p3.getY() - p1.getY())
                + p3.getX() * (p1.getY() - p2.getY())) / 2.0);
    }

    public double calculatePerimeter() {
        // Calculate the perimeter of the quadrilateral
        return point1.distance(point2) + point2.distance(point3) + point3.distance(point4) + point4.distance(point1);
    }

    public boolean isSquare() {
        // Check if the quadrilateral is a square
        double side1 = point1.distance(point2);
        double side2 = point2.distance(point3);
        double side3 = point3.distance(point4);
        double side4 = point4.distance(point1);

        double diagonal1 = point1.distance(point3);
        double diagonal2 = point2.distance(point4);

        return (side1 == side2 && side2 == side3 && side3 == side4) && diagonal1 == diagonal2;
    }

    public boolean isRectangle() {
        // Check if the quadrilateral is a rectangle
        double side1 = point1.distance(point2);
        double side2 = point2.distance(point3);
        double side3 = point3.distance(point4);
        double side4 = point4.distance(point1);

        double diagonal1 = point1.distance(point3);
        double diagonal2 = point2.distance(point4);

        return ((side1 == side3 && side2 == side4) || (side1 == side2 && side3 == side4))
                && diagonal1 == diagonal2;
    }

    public boolean isRhombus() {
        // Check if the quadrilateral is a rhombus
        double side1 = point1.distance(point2);
        double side2 = point2.distance(point3);
        double side3 = point3.distance(point4);
        double side4 = point4.distance(point1);

        double diagonal1 = point1.distance(point3);
        double diagonal2 = point2.distance(point4);

        return ((side1 == side3 && side2 == side4) || (side1 == side2 && side3 == side4)) && diagonal1 != diagonal2;
    }

    @Override
    public String toString() {
        return "Quadrilateral{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", point3=" + point3 +
                ", point4=" + point4 +
                '}';
    }

}
