package myExam;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppForDeliveryFood extends Application {
    private List<Restaurant> restaurants;
    private Restaurant selectedRestaurant;
    private ListView<MenuItem> menuListView;
    private TableView<Order> orderTable;
    private ObservableList<Order> orders;
    private ObservableList<Courier> couriers;
    private ListView<Courier> courierListView;

    @Override
    public void start(Stage stage) {
        restaurants = new ArrayList<>();
        orders = FXCollections.observableArrayList();
        couriers = FXCollections.observableArrayList();

        // Sample data
        Restaurant r1 = new Restaurant("Restaurant 1");
        r1.addMenuItem(new MenuItem("Pizza", 8.99));
        r1.addMenuItem(new MenuItem("Pasta", 7.99));
        restaurants.add(r1);

        Restaurant r2 = new Restaurant("Restaurant 2");
        r2.addMenuItem(new MenuItem("Sushi", 12.99));
        r2.addMenuItem(new MenuItem("Ramen", 9.99));
        restaurants.add(r2);

        Restaurant r3 = new Restaurant("Restaurant 3");
        r3.addMenuItem(new MenuItem("Burger", 5.99));
        r3.addMenuItem(new MenuItem("Fries", 2.99));
        restaurants.add(r3);

        ComboBox<Restaurant> restaurantComboBox = new ComboBox<>();
        restaurantComboBox.getItems().addAll(restaurants);
        restaurantComboBox.setOnAction(e -> showMenu(restaurantComboBox.getValue()));

        menuListView = new ListView<>();

        TextField itemNameField = new TextField();
        itemNameField.setPromptText("Item Name");

        TextField itemPriceField = new TextField();
        itemPriceField.setPromptText("Item Price");

        Button addButton = new Button("Add Item");
        addButton.setOnAction(e -> addItem(itemNameField.getText(), Double.parseDouble(itemPriceField.getText())));

        Button saveButton = new Button("Save Changes");
        saveButton.setOnAction(e -> saveChanges());

        // Courier management
        courierListView = new ListView<>(couriers);
        TextField courierNameField = new TextField();
        courierNameField.setPromptText("Courier Name");

        Button addCourierButton = new Button("Add Courier");
        addCourierButton.setOnAction(e -> addCourier(courierNameField.getText()));

        Button removeCourierButton = new Button("Remove Courier");
        removeCourierButton.setOnAction(e -> removeCourier(courierListView.getSelectionModel().getSelectedItem()));

        // Order Table setup
        orderTable = new TableView<>();
        TableColumn<Order, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setCellValueFactory(data -> data.getValue().customerProperty());

        TableColumn<Order, String> foodColumn = new TableColumn<>("Food Ordered");
        foodColumn.setCellValueFactory(data -> data.getValue().foodOrderedProperty());

        TableColumn<Order, Double> totalColumn = new TableColumn<>("Total Cost");
        totalColumn.setCellValueFactory(data -> data.getValue().totalCostProperty().asObject());

        TableColumn<Order, String> courierColumn = new TableColumn<>("Courier");
        courierColumn.setCellValueFactory(data -> data.getValue().courierProperty());

        TableColumn<Order, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data -> data.getValue().statusProperty());

        orderTable.getColumns().addAll(customerColumn, foodColumn, totalColumn, courierColumn, statusColumn);
        orderTable.setItems(orders);

        Button deleteOrderButton = new Button("Delete Order");
        deleteOrderButton.setOnAction(e -> deleteOrder(orderTable.getSelectionModel().getSelectedItem()));

        // Start server to listen for client orders
        new Thread(this::startOrderServer).start();

        VBox root = new VBox(10, restaurantComboBox, menuListView, itemNameField, itemPriceField, addButton, saveButton, new Label("Couriers"), courierNameField, addCourierButton, removeCourierButton, courierListView, orderTable, deleteOrderButton);
        Scene scene = new Scene(root, 600, 800);
        stage.setScene(scene);
        stage.setTitle("Menu Management and Order Processing");
        stage.show();
    }

    private void showMenu(Restaurant restaurant) {
        selectedRestaurant = restaurant;
        menuListView.getItems().setAll(restaurant.getMenuItems());
    }

    private void addItem(String name, double price) {
        if (selectedRestaurant != null) {
            MenuItem newItem = new MenuItem(name, price);
            selectedRestaurant.addMenuItem(newItem);
            menuListView.getItems().add(newItem);
        }
    }

    private void saveChanges() {
        try (Socket socket = new Socket("localhost", 9993);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
            for (Restaurant restaurant : restaurants) {
                dos.writeUTF(restaurant.getName());
                for (MenuItem item : restaurant.getMenuItems()) {
                    dos.writeUTF(item.getName());
                    dos.writeDouble(item.getPrice());
                }
                dos.writeUTF("END_OF_MENU");
            }
            dos.writeUTF("END_OF_DATA");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startOrderServer() {
        try (ServerSocket serverSocket = new ServerSocket(9994)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream dis = new DataInputStream(socket.getInputStream())) {
                    int clientId = dis.readInt();
                    StringBuilder foodOrdered = new StringBuilder();
                    double totalCost = 0;
                    String itemName;
                    while (!(itemName = dis.readUTF()).equals("END_OF_ORDER")) {
                        double price = dis.readDouble();
                        foodOrdered.append(itemName).append(", ");
                        totalCost += price;
                    }
                    String customer = "Client " + clientId;
                    String food = foodOrdered.toString();
                    Courier courier = assignCourier();
                    Order order = new Order(customer, food, totalCost, courier.getName(), "Pending");
                    orders.add(order);
                    new Thread(() -> processOrder(order, courier)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCourier(String name) {
        if (name != null && !name.isEmpty()) {
            couriers.add(new Courier(name));
        }
    }

    private void removeCourier(Courier courier) {
        if (courier != null) {
            couriers.remove(courier);
        }
    }

    private void deleteOrder(Order order) {
        if (order != null) {
            orders.remove(order);
        }
    }

    private Courier assignCourier() {
        for (Courier courier : couriers) {
            if (courier.isAvailable()) {
                courier.setAvailable(false);
                return courier;
            }
        }
        return null; // In case all couriers are busy
    }

    private void processOrder(Order order, Courier courier) {
        try {
            int deliveryTime = new Random().nextInt(5) + 1; // Random time between 1 and 5 minutes
            Thread.sleep(deliveryTime * 60000);
            order.setStatus("Completed");
            courier.setAvailable(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
