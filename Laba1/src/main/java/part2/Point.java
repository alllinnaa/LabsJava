package part2;

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

        double slope1 = (moveY - y) / (double)(moveX - x);
        double slope2 = (other.moveY - other.y) / (double)(other.moveX - other.x);

        if (slope1 == slope2) {
            // Parallel lines, no intersection
            return false;
        }
        return true;
    }

    public double distance(Point other) {
        int dx = other.x - this.x;
        int dy = other.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
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
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
