package myExam;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AppForClient extends Application {
    private Client client;
    private List<Restaurant> restaurants;
    private ComboBox<Restaurant> restaurantComboBox;
    private ListView<MenuItem> menuListView;
    private ListView<MenuItem> orderListView;
    private Label totalLabel;

    @Override
    public void start(Stage stage) throws Exception {
        client = new Client();
        restaurants = new ArrayList<>();

        new Thread(() -> getRestaurants()).start();

        restaurantComboBox = new ComboBox<>();
        restaurantComboBox.setOnAction(e -> showMenu(restaurantComboBox.getValue()));

        menuListView = new ListView<>();
        menuListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        orderListView = new ListView<>();
        totalLabel = new Label("Total: $0.00");

        Button addButton = new Button("Add to Order");
        addButton.setOnAction(e -> addToOrder());

        Button makeOrderButton = new Button("Make Order");
        makeOrderButton.setOnAction(e -> placeOrder());

        Label clientIdLabel = new Label("Client ID: " + client.getId());

        VBox root = new VBox(10, clientIdLabel, restaurantComboBox, menuListView, addButton, orderListView, totalLabel, makeOrderButton);
        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Food Ordering App");
        stage.show();
    }

    private void getRestaurants() {
        try (ServerSocket serverSocket = new ServerSocket(9993)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream dis = new DataInputStream(socket.getInputStream())) {

                    restaurants.clear();
                    menuListView.getItems().clear();
                    orderListView.getItems().clear();
                    totalLabel.setText("Total: $0.00");

                    String restaurantName;
                    while (!(restaurantName = dis.readUTF()).equals("END_OF_DATA")) {
                        Restaurant restaurant = new Restaurant(restaurantName);
                        String itemName;
                        while (!(itemName = dis.readUTF()).equals("END_OF_MENU")) {
                            double price = dis.readDouble();
                            restaurant.addMenuItem(new MenuItem(itemName, price));
                        }
                        restaurants.add(restaurant);
                    }
                    updateRestaurantComboBox();
                } catch (IOException e) {
                    displayAlert("Connection error with food delivery app during restaurant transfers");
                }
            }
        } catch (IOException e) {
            displayAlert("Error creating server while transferring restaurants");
        }
    }


    private void updateRestaurantComboBox() {
        restaurantComboBox.getItems().setAll(restaurants);
    }

    private void showMenu(Restaurant restaurant) {
        menuListView.getItems().setAll(restaurant.getMenuItems());
    }

    private void addToOrder() {
        orderListView.getItems().addAll(menuListView.getSelectionModel().getSelectedItems());
        updateTotal();
    }

    private void updateTotal() {
        double total = 0.0;
        for (MenuItem item : orderListView.getItems()) {
            total += item.getPrice();
        }
        totalLabel.setText("Total: $" + total);
    }

    private boolean isFreeCourier() {
        boolean freeCourier=false;
        try (Socket socket = new Socket("localhost", 9995);
             DataInputStream dis = new DataInputStream(socket.getInputStream())){
            freeCourier = dis.readBoolean();
        } catch (IOException e) {
            displayAlert("Error when checking available couriers");
        }
        return freeCourier;
    }

    private void placeOrder() {
        if (orderListView.getItems().isEmpty()) {
            displayAlert("Cannot send an empty order. Please add items to your order.");
            return;
        }
        if(!isFreeCourier()){
            displayAlert("We cannot fulfill your order because there are no available couriers.");
            return;
        }

        try (Socket socket = new Socket("localhost", 9994);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
            dos.writeInt(client.getId());
            for (MenuItem item : orderListView.getItems()) {
                dos.writeUTF(item.getName());
                dos.writeDouble(item.getPrice());
            }
            dos.writeUTF("END_OF_ORDER");
        } catch (IOException e) {
            displayAlert("Connection error when creating an order");
        }
        orderListView.getItems().clear();
        totalLabel.setText("Total: $0.00");
    }

    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AppForClient");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
