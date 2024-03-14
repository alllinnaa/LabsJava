package part2;
import java.util.ArrayList;
import java.util.List;

public class QuadrilateralAnalyzer {
    private List<Quadrilateral> quadrilaterals;

    public QuadrilateralAnalyzer() {
        quadrilaterals = new ArrayList<>();
    }

    public void addQuadrilateral(Quadrilateral quadrilateral) {
        quadrilaterals.add(quadrilateral);
    }

    public int countQuadrilaterals() {
        return quadrilaterals.size();
    }

    public int countSquares() {
        int count = 0;
        for (Quadrilateral quad : quadrilaterals) {
            if (quad.isSquare()) {
                count++;
            }
        }
        return count
    }

    public int countRectangles() {
        int count = 0;
        for (Quadrilateral quad : quadrilaterals) {
            if (quad.isRectangle() && !quad.isSquare()) {
                count++;
            }
        }
        return count;
    }

    public int countRhombuses() {
        int count = 0;
        for (Quadrilateral quad : quadrilaterals) {
            if (quad.isRhombus() && !quad.isRectangle()) {
                count++;
            }
        }
        return count;
    }

    public int countArbitraryQuadrilaterals() {
        return countQuadrilaterals() - countSquares() - countRectangles() - countRhombuses();
    }

    public Quadrilateral findLargestByArea() {
        Quadrilateral largest = null;
        double maxArea = Double.MIN_VALUE;
        for (Quadrilateral quad : quadrilaterals) {
            double area = quad.calculateArea();
            if (area > maxArea) {
                maxArea = area;
                largest = quad;
            }
        }
        return largest;
    }

    public Quadrilateral findSmallestByArea() {
        Quadrilateral smallest = null;
        double minArea = Double.MAX_VALUE;
        for (Quadrilateral quad : quadrilaterals) {
            double area = quad.calculateArea();
            if (area < minArea) {
                minArea = area;
                smallest = quad;
            }
        }
        return smallest;
    }

    public Quadrilateral findLargestByPerimeter() {
        Quadrilateral largest = null;
        double maxPerimeter = Double.MIN_VALUE;
        for (Quadrilateral quad : quadrilaterals) {
            double perimeter = quad.calculatePerimeter();
            if (perimeter > maxPerimeter) {
                maxPerimeter = perimeter;
                largest = quad;
            }
        }
        return largest;
    }

    public Quadrilateral findSmallestByPerimeter() {
        Quadrilateral smallest = null;
        double minPerimeter = Double.MAX_VALUE;
        for (Quadrilateral quad : quadrilaterals) {
            double perimeter = quad.calculatePerimeter();
            if (perimeter < minPerimeter) {
                minPerimeter = perimeter;
                smallest = quad;
            }
        }
        return smallest;
    }
}