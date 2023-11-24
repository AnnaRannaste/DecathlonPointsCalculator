package com.decathlonapplication.services;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DecathlonService {

    private static final Map<String, double[]> SCORE_TABLE = new HashMap<>();

    static {
        SCORE_TABLE.put("100M", new double[]{25.4347, 18, 1.81});
        SCORE_TABLE.put("LONG JUMP", new double[]{0.14354, 220, 1.4});
        SCORE_TABLE.put("SHOT PUT", new double[]{51.39, 1.5, 1.05});
        SCORE_TABLE.put("HIGH JUMP", new double[]{0.8465, 75, 1.42});
        SCORE_TABLE.put("400M", new double[]{1.53775, 82, 1.81});
        SCORE_TABLE.put("110M HURDLES", new double[]{5.74352, 28.5, 1.92});
        SCORE_TABLE.put("DISCUS THROW", new double[]{12.91, 4, 1.1});
        SCORE_TABLE.put("POLE VAULT", new double[]{0.2797, 100, 1.35});
        SCORE_TABLE.put("JAVELIN THROW", new double[]{10.14, 7, 1.08});
        SCORE_TABLE.put("1500M", new double[]{0.03768, 480, 1.85});

        SCORE_TABLE.put("1ST DAY POINTS", new double[]{0, 0, 1});
        SCORE_TABLE.put("2ND DAY POINTS", new double[]{0, 0, 1});
        SCORE_TABLE.put("TOTAL POINTS", new double[]{0, 0, 2});
    }

    public double calculatePoints(String event, double result) {
        if (!SCORE_TABLE.containsKey(event)) {
            throw new IllegalArgumentException("Unknown event: " + event);
        }

        double[] coefficients = SCORE_TABLE.get(event);

        double points;
        if (isTrackEvent(event)) {
            points = calculateTrackEventPoints(coefficients, result);
        } else {
            points = calculateFieldEventPoints(coefficients, result);
        }
        return points;
    }


    private boolean isTrackEvent(String event) {

        return !event.equals("JAVELIN THROW") && !event.equals("LONG JUMP") && !event.equals("SHOT PUT") && !event.equals("HIGH JUMP") && !event.equals("DISCUS THROW")
                && !event.equals("POLE VAULT") && !event.equals("HIGH JUMP");
    }

    private double calculateTrackEventPoints(double[] coefficients, double result) {
        return Math.round(coefficients[0] * Math.pow(coefficients[1] - result, coefficients[2]));
    }

    private double calculateFieldEventPoints(double[] coefficients, double result) {
        return Math.round(coefficients[0] * Math.pow(result - coefficients[1], coefficients[2]));
    }

    public double calculateDayPoints(String[] events, double[] results) {
        if (events.length != results.length) {
            throw new IllegalArgumentException("Events and results arrays must have the same length");
        }

        double day1Points = 0;
        double day2Points = 0;
        boolean secondDay = false;

        for (int i = 0; i < events.length; i++) {
            double points = calculatePoints(events[i], results[i]);

            if (events[i].equals("110M HURDLES")) {
                secondDay = true;
            }

            if (!secondDay) {
                day1Points += points;
            } else {
                day2Points += points;
            }
        }

        double totalPoints = day1Points + day2Points;
        return totalPoints;
    }
}
