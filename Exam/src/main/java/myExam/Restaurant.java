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

    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item);
    }


    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (MenuItem item : menuItems) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
    @Override
    public String toString() {
        return name;
    }
}
