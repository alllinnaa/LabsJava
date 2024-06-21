package myExam;

import javafx.beans.property.*;

public class Order {
    private StringProperty customer;
    private StringProperty foodOrdered;
    private DoubleProperty totalCost;
    private StringProperty courier;
    private StringProperty status;

    public Order(String customer, String foodOrdered, double totalCost, String courier, String status) {
        this.customer = new SimpleStringProperty(customer);
        this.foodOrdered = new SimpleStringProperty(foodOrdered);
        this.totalCost = new SimpleDoubleProperty(totalCost);
        this.courier = new SimpleStringProperty(courier);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty customerProperty() {
        return customer;
    }

    public StringProperty foodOrderedProperty() {
        return foodOrdered;
    }

    public DoubleProperty totalCostProperty() {
        return totalCost;
    }

    public StringProperty courierProperty() {
        return courier;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}

