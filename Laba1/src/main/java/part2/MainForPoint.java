package part2;

import java.util.Scanner;

public class MainForPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter x coordinate for Point 1:");
        int x1 = scanner.nextInt();
        System.out.println("Enter y coordinate for Point 1:");
        int y1 = scanner.nextInt();

        Point point1 = new Point(x1, y1);

        System.out.println("Enter x coordinate for Point 2:");
        int x2 = scanner.nextInt();
        System.out.println("Enter y coordinate for Point 2:");
        int y2 = scanner.nextInt();

        Point point2 = new Point(x2, y2);

        System.out.println("Enter new x coordinate for Point 1:");
        int newX1 = scanner.nextInt();
        System.out.println("Enter new y coordinate for Point 1:");
        int newY1 = scanner.nextInt();
        point1.move(newX1, newY1);

        System.out.println("Enter new x coordinate for Point 2:");
        int newX2 = scanner.nextInt();
        System.out.println("Enter new y coordinate for Point 2:");
        int newY2 = scanner.nextInt();
        point2.move(newX2, newY2);

        scanner.close();

        if (point1.hasMoved()) {
            System.out.println("Speed of Point 1: " + point1.getSpeed());
        } else {
            System.out.println("Point 1 has not moved, speed cannot be calculated.");
        }
        if (point2.hasMoved()) {
            System.out.println("Speed of Point 2: " + point2.getSpeed());
        } else {
            System.out.println("Point 2 has not moved, speed cannot be calculated.");
        }

        if (point1.hasMoved() && point2.hasMoved()) {
            System.out.println("Points intersect: " + point1.intersects(point2));
        } else {
            System.out.println("Cannot determine intersection as both points have not moved.");
        }

    }
}
