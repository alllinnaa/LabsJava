package laba4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class App1 extends Application {
    private boolean running = true;
    private boolean startGeneration = true;
    private List<Integer> dataSet1 = new ArrayList<>();
    private List<Integer> dataSet2 = new ArrayList<>();

    @Override
    public void start(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root, 400, 200);


        Label thread1Label = new Label();
        Label thread2Label = new Label();


        VBox vbox = new VBox(10, thread1Label, thread2Label);
        root.getChildren().add(vbox);


        KeyCombination startCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        scene.setOnKeyPressed(event -> {
            if (startCombination.match(event)) {
                if (startGeneration) {
                    startDataTransmission();
                    startGeneration=false;
                } else {
                    displayAlert("You have already started the number generator.");
                }
            } else if (event.getCode() == KeyCode.I && event.isAltDown()) {
                if (!running) {
                    displayMostFrequentNumbers(thread1Label, thread2Label);
                } else {
                    displayAlert("Threads were not started or did not complete their work.");
                }
            }
        });

        startStopSignalListener();

        stage.setScene(scene);
        stage.setTitle("App1");
        stage.show();
    }

    private void startStopSignalListener() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(9994); // Listen for stop signal
                serverSocket.accept();
                running = false;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startDataTransmission() {
        try {
            Socket socket = new Socket("127.0.0.1", 9993);
            if (socket.isConnected()) {
                socket.close();
                // Create threads within the try block to catch any exceptions during thread creation
                Thread thread1 = new Thread(() -> sendData(1));
                Thread thread2 = new Thread(() -> sendData(2));
                thread1.start();
                thread2.start();
            } else {
                displayAlert("The second program has not been started. Please start the second program before proceeding.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            displayAlert("Error connecting to the second program. Please make sure it is running.");
            Platform.exit();
        }
    }

    private void sendData(int dataSet) {
        try {
            Socket socket = new Socket("127.0.0.1", 9993);

                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                Random random = new Random();
                while (running) {
                    int data = random.nextInt(256);

                    if (dataSet == 1) {
                        dataSet1.add(data);
                    } else if (dataSet == 2) {
                        dataSet2.add(data);
                    }

                    outputStream.writeInt(dataSet);
                    outputStream.writeInt(data);
                    outputStream.flush();
                    Thread.sleep(100);
                }

                outputStream.close();
                socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            displayAlert("Error creating socket.");
        }
    }
    private void displayMostFrequentNumbers(Label thread1Label, Label thread2Label) {

        List<Integer> thread1Data = new ArrayList<>(dataSet1);
        List<Integer> thread2Data = new ArrayList<>(dataSet2);

        Map<Integer, Long> frequencyMapThread1 = thread1Data.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        Map<Integer, Long> frequencyMapThread2 = thread2Data.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));


        List<Map.Entry<Integer, Long>> sortedEntriesThread1 = new ArrayList<>(frequencyMapThread1.entrySet());
        sortedEntriesThread1.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map.Entry<Integer, Long>> sortedEntriesThread2 = new ArrayList<>(frequencyMapThread2.entrySet());
        sortedEntriesThread2.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));


        List<Integer> mostFrequentNumbersThread1 = getMostFrequentNumbers(sortedEntriesThread1);
        List<Integer> mostFrequentNumbersThread2 = getMostFrequentNumbers(sortedEntriesThread2);


        StringBuilder thread1Output = new StringBuilder("Most frequent numbers generated by Thread 1: ");
        for (int number : mostFrequentNumbersThread1) {
            thread1Output.append(number).append(", ");
        }
        thread1Output.delete(thread1Output.length() - 2, thread1Output.length()); // Remove the last ", "

        StringBuilder thread2Output = new StringBuilder("Most frequent numbers generated by Thread 2: ");
        for (int number : mostFrequentNumbersThread2) {
            thread2Output.append(number).append(", ");
        }
        thread2Output.delete(thread2Output.length() - 2, thread2Output.length()); // Remove the last ", "

        thread1Label.setText(thread1Output.toString());
        thread2Label.setText(thread2Output.toString());
    }

    private List<Integer> getMostFrequentNumbers(List<Map.Entry<Integer, Long>> sortedEntries) {
        List<Integer> mostFrequentNumbers = new ArrayList<>();
        int count = 0;
        for (Map.Entry<Integer, Long> entry : sortedEntries) {
            mostFrequentNumbers.add(entry.getKey());
            count++;
            if (count == 3) {
                break;
            }
        }
        return mostFrequentNumbers;
    }
    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Most Frequent Numbers");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}