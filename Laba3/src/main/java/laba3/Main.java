package laba3;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("x");
        yAxis.setLabel("y");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Function Plotter");
        lineChart.setPrefSize(1200, 700);
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("sin(x)", "cos(x)", "x^2");
        choiceBox.setValue("sin(x)");
        updateChart(lineChart, "sin(x)");
        choiceBox.setOnAction(event -> updateChart(lineChart, choiceBox.getValue()));
        Group root = new Group();
        root.getChildren().addAll(lineChart, choiceBox);
        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("Function Plotter");
        stage.show();
    }


    private void updateChart(LineChart<Number, Number> chart, String functionName) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(functionName);

        switch (functionName) {
            case "sin(x)":
                for (double x = -20; x <= 20; x += 0.1) {
                    series.getData().add(new XYChart.Data<>(x, Math.sin(x)));
                }
                break;
            case "cos(x)":
                for (double x = -20; x <= 20; x += 0.1) {
                    series.getData().add(new XYChart.Data<>(x, Math.cos(x)));
                }
                break;
            case "x^2":
                for (double x = -20; x <= 20; x += 0.1) {
                    series.getData().add(new XYChart.Data<>(x, x * x));
                }
                break;
            default:
                break;
        }

        chart.getData().clear();
        chart.getData().add(series);

        for (XYChart.Data<Number, Number> data : series.getData()) {
            data.getNode().setVisible(false);
        }
    }

    public static void main(String[] args) {launch(args);}
}



