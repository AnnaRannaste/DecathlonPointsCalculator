package com.decathlonapplication;

import com.decathlonapplication.services.DecathlonService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class DecathlonUI extends Application {
    private Label[] eventLabels;
    private final DecathlonService decathlonService = new DecathlonService();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        eventLabels = new Label[]{
                new Label("100M"), new Label("LONG JUMP"), new Label("SHOT PUT"), new Label("HIGH JUMP"), new Label("400M"),
                new Label("1ST DAY POINTS"), new Label("110M HURDLES"), new Label("DISCUS THROW"), new Label("POLE VAULT"),
                new Label("JAVELIN THROW"), new Label("1500M"), new Label("2ND DAY POINTS"), new Label("TOTAL POINTS")
        };

        primaryStage.setTitle("Decathlon Points Calculator");


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.add(new Label(""), 0, 1);
        grid.add(new Label("Event"), 0, 0);
        grid.add(new Label("Result"), 1, 0);
        grid.add(new Label("Points"), 2, 0);


        String[] events = {"100M", "LONG JUMP", "SHOT PUT", "HIGH JUMP", "400M",
                "1ST DAY POINTS", "110M HURDLES", "DISCUS THROW", "POLE VAULT", "JAVELIN THROW", "1500M",
                "2ND DAY POINTS", "TOTAL POINTS"};

        TextField[] resultFields = new TextField[events.length];
        TextField[] pointsFields = new TextField[events.length];

        for (int i = 0; i < events.length; i++) {
            grid.add(eventLabels[i], 0, i + 1);

            if (i == 5 || i == 11 || i == 12) {

                pointsFields[i] = new TextField();
                pointsFields[i].setEditable(false);
                grid.add(pointsFields[i], 2, i + 1);

            } else {

                resultFields[i] = new TextField();
                pointsFields[i] = new TextField();
                grid.add(resultFields[i], 1, i + 1);
                grid.add(pointsFields[i], 2, i + 1);
            }
        }


        Button calculateButton = new Button("Calculate Points");

        calculateButton.setOnAction(e -> updatePointsFields(resultFields, pointsFields));

        grid.add(calculateButton, 1, events.length + 1, 2, 1);


        Scene scene = new Scene(grid, 550, 550);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void updatePointsFields(TextField[] resultFields, TextField[] pointsFields) {
        double totalDay1Points = 0;
        double totalDay2Points = 0;

        String[] events = {"100M", "LONG JUMP", "SHOT PUT", "HIGH JUMP", "400M",
                "1ST DAY POINTS", "110M HURDLES", "DISCUS THROW", "POLE VAULT", "JAVELIN THROW", "1500M",
                "2ND DAY POINTS", "TOTAL POINTS"};

        double[] results = new double[resultFields.length];
        boolean secondDay = false;

        for (int i = 0; i < resultFields.length; i++) {
            if (resultFields[i] != null && pointsFields[i] != null) {
                try {
                    String event = eventLabels[i].getText();
                    double result = Double.parseDouble(resultFields[i].getText());
                    double points = decathlonService.calculatePoints(event, result);

                    if (event.equals("110M HURDLES")) {
                        secondDay = true;
                    }

                    if (!secondDay) {
                        totalDay1Points += points;
                    } else {
                        totalDay2Points += points;
                    }

                    pointsFields[i].setText(String.valueOf(points));
                    results[i] = result;
                } catch (NumberFormatException ex) {
                    pointsFields[i].setText("Invalid input");
                }
            }
        }

        totalDay2Points = decathlonService.calculateDayPoints(Arrays.copyOfRange(events, 6, events.length), Arrays.copyOfRange(results, 6, results.length));

        pointsFields[5].setText(String.valueOf(totalDay1Points));
        pointsFields[11].setText(String.valueOf(totalDay2Points));
        pointsFields[12].setText(String.valueOf(totalDay1Points + totalDay2Points));
    }
}




