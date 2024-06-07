package part2;

public class Point {
    private final int x;
    private final int y;
    private final long creationTimeSeconds;
    private int moveX;
    private int moveY;
    private long moveTimeSeconds;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.creationTimeSeconds = System.currentTimeMillis() / 1000;
        this.moveX = x;
        this.moveY = y;
        this.moveTimeSeconds = creationTimeSeconds;
    }

    public void move(int newX, int newY) {
        this.moveX = newX;
        this.moveY = newY;
        this.moveTimeSeconds = System.currentTimeMillis() / 1000;
    }

    public boolean hasMoved() {
        return !(x == moveX && y == moveY && creationTimeSeconds == moveTimeSeconds);
    }

    public double getSpeed() {
        if (!hasMoved()) {
            throw new IllegalStateException("Point has not moved.");
        }
        long timeDifferenceSeconds = moveTimeSeconds - creationTimeSeconds;
        double distance = Math.sqrt(Math.pow(moveX - x, 2) + Math.pow(moveY - y, 2));
        return distance / timeDifferenceSeconds;
    }

    public boolean intersects(Point other) {
        if (!hasMoved() || !other.hasMoved()) {
            throw new IllegalStateException("Both points must have moved to check for intersection.");
        }

        double slope1 = (moveY - y) / (double)(moveX - x);
        double slope2 = (other.moveY - other.y) / (double)(other.moveX - other.x);

        if (slope1 == slope2) {
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

    public long getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public int getMoveX() {
        return moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public long getMoveTimeSeconds() {
        return moveTimeSeconds;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
