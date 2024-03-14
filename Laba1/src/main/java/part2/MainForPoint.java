package part2;

import java.util.Scanner;

public class MainForPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter coordinates for point 1 (x y):");
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();

        System.out.println("Enter coordinates for point 2 (x y):");
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();

        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);

        System.out.println("Points created:");
        System.out.println("Point 1: " + point1);
        System.out.println("Point 2: " + point2);

        System.out.println("Moving point 1 to (3, 3)...");
        point1.move(3, 3);
        System.out.println("Point 1: " + point1);

        System.out.println("Moving point 2 to (4, 4)...");
        point2.move(4, 4);
        System.out.println("Point 2: " + point2);

        System.out.println("Intersection check:");
        System.out.println("Do the trajectories of point 1 and point 2 intersect? " + point1.intersects(point2));

        scanner.close();
    }
}
