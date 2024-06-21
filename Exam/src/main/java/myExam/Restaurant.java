package myExam;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<MenuItem> menuItems;

    public Restaurant(String name) {
        this.name = name;
        this.menuItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }
    @Override
    public String toString() {
        return name;
    }
}
