package part2;
import java.util.Objects;

public class Point {
    private final int x;
    private final int y;
    private final long creationTime;
    private int moveX;
    private int moveY;
    private long moveTime;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.creationTime = System.currentTimeMillis();
        this.moveX = x;
        this.moveY = y;
        this.moveTime = creationTime;
    }

    public void move(int newX, int newY) {
        this.moveX = newX;
        this.moveY = newY;
        this.moveTime = System.currentTimeMillis();
    }

    public boolean hasMoved() {
        return !(x == moveX && y == moveY && creationTime == moveTime);
    }

    public double getSpeed() {
        if (!hasMoved()) {
            throw new IllegalStateException("Point has not moved.");
        }
        long timeDifference = moveTime - creationTime;
        double distance = Math.sqrt(Math.pow(moveX - x, 2) + Math.pow(moveY - y, 2));
        return distance / timeDifference;
    }

    public boolean intersects(Point other) {
        if (!hasMoved() || !other.hasMoved()) {
            throw new IllegalStateException("Both points must have moved to check for intersection.");
        }
        // For simplicity, assuming the points move linearly between their creation and move times
        // Check if the lines formed by the points intersect
        double slope1 = (moveY - y) / (double)(moveTime - creationTime);
        double slope2 = (other.moveY - other.y) / (double)(other.moveTime - other.creationTime);
        if (slope1 == slope2) {
            // Parallel lines, no intersection
            return false;
        }
        // Calculate intersection point
        double intersectionX = (other.y - y + slope1 * creationTime - slope2 * other.creationTime) / (slope1 - slope2);
        double intersectionY = slope1 * (intersectionX - creationTime) + y;
        // Check if intersection point lies on both line segments
        return isBetween(creationTime, moveTime, intersectionX)
                && isBetween(other.creationTime, other.moveTime, intersectionY);
    }

    private boolean isBetween(long start, long end, double value) {
        return value >= start && value <= end;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public int getMoveX() {
        return moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public long getMoveTime() {
        return moveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y && creationTime == point.creationTime && moveX == point.moveX && moveY == point.moveY && moveTime == point.moveTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, creationTime, moveX, moveY, moveTime);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", creationTime=" + creationTime +
                ", moveX=" + moveX +
                ", moveY=" + moveY +
                ", moveTime=" + moveTime +
                '}';
    }
}
