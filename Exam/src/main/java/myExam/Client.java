package myExam;

import java.util.Random;

public class Client {
    private final int id;

    public Client() {
        this.id = new Random().nextInt(256);
    }

    public int getId() {
        return id;
    }
}
