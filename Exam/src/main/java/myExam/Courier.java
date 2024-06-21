package myExam;

public class Courier {
    private final String name;
    private boolean isAvailable;

    public Courier(String name) {
        this.name = name;
        this.isAvailable = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return name;
    }
}
