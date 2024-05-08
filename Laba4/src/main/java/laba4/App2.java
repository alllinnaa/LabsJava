package laba4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
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
import java.util.Map;
import java.util.stream.Collectors;

public class App2 extends Application {
    private boolean running = true;
    private boolean generationStopped=false;
    private final List<Integer> dataSet1 = new ArrayList<>();
    private final List<Integer> dataSet2 = new ArrayList<>();
    private ListView<Integer> listView1;
    private ListView<Integer> listView2;

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox();
        Scene scene = new Scene(root, 800, 700);

        listView1 = new ListView<>();
        listView2 = new ListView<>();

        Button stopButton = new Button("Stop transmission and display graphs");
        stopButton.setOnAction(event -> {
            if(!generationStopped )
            {
                stopTransmission();
                displayGraphs();
                generationStopped=true;
            }
            else{
                displayAlert("You have already interrupted generation");
            }

        });

        root.getChildren().addAll(listView1, listView2, stopButton);


        new Thread(this::receiveData).start();

        primaryStage.setScene(scene);
        primaryStage.setTitle("App2");
        primaryStage.show();
    }

    private void receiveData() {
        try {

            ServerSocket serverSocket = new ServerSocket(9993);

            while (running) {

                Socket socket = serverSocket.accept();

                new Thread(() -> {
                    try {

                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                        while (running) {
                            try {
                                int dataSet = inputStream.readInt();
                                int value = inputStream.readInt();

                                if (dataSet == 1) {
                                    dataSet1.add(value);
                                    System.out.println("1: " + value);
                                } else if (dataSet == 2) {
                                    dataSet2.add(value);
                                    System.out.println("2: " + value);
                                }

                                Platform.runLater(this::refreshListView);
                            } catch (IOException e) {

                                if (e instanceof java.io.EOFException) {
                                    break;
                                } else {
                                    e.printStackTrace();
                                }
                            }
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
            displayAlert("Error while receiving data, you may need to run the first program.");
            Platform.exit();
        }
    }


    private void refreshListView() {
        listView1.getItems().setAll(dataSet1);
        listView2.getItems().setAll(dataSet2);
    }

    private void stopTransmission() {
        running = false;
        sendStopSignal();
    }

    private void sendStopSignal() {
        try {
            Socket socket = new Socket("127.0.0.1", 9994);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            displayAlert("Error when stopping data generation, you may not be able to start the first program.");
            Platform.exit();
        }
    }


    private void displayGraphs() {
        Stage stage = new Stage();
        stage.setTitle("Histograms");

        CategoryAxis xAxis1 = new CategoryAxis();
        xAxis1.setLabel("Value");
        NumberAxis yAxis1 = new NumberAxis();
        yAxis1.setLabel("Frequency");
        BarChart<String, Number> barChart1 = new BarChart<>(xAxis1, yAxis1);
        barChart1.setTitle("Dataset 1");

        CategoryAxis xAxis2 = new CategoryAxis();
        xAxis2.setLabel("Value");
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Frequency");
        BarChart<String, Number> barChart2 = new BarChart<>(xAxis2, yAxis2);
        barChart2.setTitle("Dataset 2");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();

        Map<Integer, Long> frequencyMap1 = dataSet1.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        Map<Integer, Long> frequencyMap2 = dataSet2.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        for (Map.Entry<Integer, Long> entry : frequencyMap1.entrySet()) {
            series1.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
        }

        for (Map.Entry<Integer, Long> entry : frequencyMap2.entrySet()) {
            series2.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
        }

        barChart1.getData().add(series1);
        barChart2.getData().add(series2);

        VBox vbox = new VBox(barChart1, barChart2);

        stage.setScene(new Scene(vbox, 900, 600));
        stage.show();
    }

    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("App2");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
