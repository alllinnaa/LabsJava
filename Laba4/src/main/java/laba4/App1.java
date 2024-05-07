package laba4;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class App1 extends Application {
    private boolean running = true;
    private List<Integer> dataSet1 = new ArrayList<>();
    private List<Integer> dataSet2 = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        // Initialize UI elements
        Group root = new Group();
        Scene scene = new Scene(root, 900, 700);

        // Handle key combination for starting data transmission
        KeyCombination startCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        scene.setOnKeyPressed(event -> {
            if (startCombination.match(event)) {
                System.out.println("Control + S was pressed");
                startDataTransmission();
            } else if (event.getCode() == KeyCode.I && event.isAltDown()) {
                displayMostFrequentNumbers();
            }
        });

        // Start listening for stop signal
        startStopSignalListener();

        stage.setScene(scene);
        stage.setTitle("App1");
        stage.show();
    }

    private void startStopSignalListener() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(9994); // Listen for stop signal
                serverSocket.accept(); // Block until signal received
                running = false; // Stop data transmission
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startDataTransmission() {
        // Create two threads for generating and transmitting random data
        Thread thread1 = new Thread(() -> sendData(1));
        Thread thread2 = new Thread(() -> sendData(2));

        thread1.start();
        thread2.start();
    }

    private void sendData(int dataSet) {
        try {
            // Connect to localhost on port 12345
            Socket socket = new Socket("127.0.0.1", 9993);

            // Create ObjectOutputStream to send data
            DataOutputStream outputStream = new  DataOutputStream(socket.getOutputStream());

            // Generate and send random data
            Random random = new Random();
            while (running) {
                int data = random.nextInt(256); // Generate random number between 0 and 255
                outputStream.writeInt(dataSet); // Indicate which data set this belongs to
                outputStream.writeInt(data); // Send the random data
                outputStream.flush(); // Flush the stream to ensure data is sent immediately
                System.out.println(dataSet+": "+data);
                Thread.sleep(100); // Wait for 100 milliseconds
            }

            // Close the socket and stream when done
            outputStream.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void displayMostFrequentNumbers() {
        // Calculate frequency of each number in each dataset
        Map<Integer, Long> frequencyMap1 = dataSet1.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        Map<Integer, Long> frequencyMap2 = dataSet2.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        // Sort frequency maps by frequency in descending order
        List<Map.Entry<Integer, Long>> sortedEntries1 = new ArrayList<>(frequencyMap1.entrySet());
        sortedEntries1.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map.Entry<Integer, Long>> sortedEntries2 = new ArrayList<>(frequencyMap2.entrySet());
        sortedEntries2.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Get the three most frequent numbers from each dataset
        List<Integer> mostFrequentNumbers1 = getMostFrequentNumbers(sortedEntries1);
        List<Integer> mostFrequentNumbers2 = getMostFrequentNumbers(sortedEntries2);

        // Display the three most frequent numbers in each dataset
        displayAlert("Most frequent numbers in DataSet 1: " + mostFrequentNumbers1);
        displayAlert("Most frequent numbers in DataSet 2: " + mostFrequentNumbers2);
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
