package laba4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class App2 extends Application {
    private boolean running = true;
    private final List<Integer> dataSet1 = new ArrayList<>();
    private final List<Integer> dataSet2 = new ArrayList<>();
    private ListView<Integer> listView1;
    private ListView<Integer> listView2;

    @Override
    public void start(Stage primaryStage) {
        // UI elements
        VBox root = new VBox();
        Scene scene = new Scene(root, 800, 700);

        // ListViews to display data
        listView1 = new ListView<>();
        listView2 = new ListView<>();

        // Button to stop data transmission and display graphs
        Button stopButton = new Button("Stop Transmission and Display Graphs");
        stopButton.setOnAction(event -> {
            stopTransmission();
            displayGraphs();
        });

        root.getChildren().addAll(listView1, listView2, stopButton);


        new Thread(this::receiveData).start();

        primaryStage.setScene(scene);
        primaryStage.setTitle("App2");
        primaryStage.show();
    }

    private void receiveData() {
        try {
            // Create server socket on port 9993
            ServerSocket serverSocket = new ServerSocket(9993);

            while (running) {
                // Listen for incoming connections
                Socket socket = serverSocket.accept();

                // Start a new thread to handle the connection
                new Thread(() -> {
                    try {
                        // Create DataInputStream to read data
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                        while (running) {
                            // Read data set and value
                            int dataSet = inputStream.readInt();
                            int value = inputStream.readInt();

                            // Add value to corresponding list
                            if (dataSet == 1) {
                                dataSet1.add(value);
                                System.out.println("1: " + value);
                            } else if (dataSet == 2) {
                                dataSet2.add(value);
                                System.out.println("2: " + value);
                            }

                            // Update UI on JavaFX application thread
                            Platform.runLater(this::refreshListView);
                        }

                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void refreshListView() {
        // Clear and update ListView with new data
        listView1.getItems().setAll(dataSet1);
        listView2.getItems().setAll(dataSet2);
    }

    private void stopTransmission() {
        running = false;
        sendStopSignal();
    }

    private void sendStopSignal() {
        try {
            // Send stop signal to App1
            Socket socket = new Socket("127.0.0.1", 9994);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayGraphs() {
        // Create new stage for graphs
        Stage stage = new Stage();
        stage.setTitle("Graphs");

        // Create line charts for each dataset
        NumberAxis xAxis1 = new NumberAxis();
        NumberAxis yAxis1 = new NumberAxis();
        LineChart<Number, Number> lineChart1 = new LineChart<>(xAxis1, yAxis1);
        lineChart1.setTitle("Dataset 1");

        NumberAxis xAxis2 = new NumberAxis();
        NumberAxis yAxis2 = new NumberAxis();
        LineChart<Number, Number> lineChart2 = new LineChart<>(xAxis2, yAxis2);
        lineChart2.setTitle("Dataset 2");

        // Create data series for each dataset
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();

        // Add data to series
        for (int i = 0; i < dataSet1.size(); i++) {
            series1.getData().add(new XYChart.Data<>(i, dataSet1.get(i)));
        }
        for (int i = 0; i < dataSet2.size(); i++) {
            series2.getData().add(new XYChart.Data<>(i, dataSet2.get(i)));
        }

        // Add series to charts
        lineChart1.getData().add(series1);
        lineChart2.getData().add(series2);

        // Create VBox to hold charts
        VBox vbox = new VBox(lineChart1, lineChart2);

        // Show the stage
        stage.setScene(new Scene(vbox, 900, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
