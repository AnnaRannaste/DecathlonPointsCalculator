# Decathlon Points Calculator

This Java application is a Decathlon Points Calculator that allows users to input the results for various track and field events
and calculates the corresponding points based on the Decathlon scoring system.

## Features
User-Friendly GUI: The application provides a graphical user interface (GUI) built with JavaFX,
making it easy and intuitive to input event results and view calculated points.

## Event Selection:
Users can input results for events such as "100M," "Long Jump," "Shot Put," "High Jump," "400M," and more.

## Points Calculation: 
The points for each event are calculated and displayed on the GUI.

## How to Use
Run the Application:
mvn clean install
mvn spring-boot:run

* Execute the DecathlonApplication class to launch the Decathlon Points Calculator.

The GUI window will appear, allowing you to input event results.

* Input Event Results:

For each event, enter the corresponding result in the "Result" column.

* Calculate Points:

Click the "Calculate Points" button to calculate and display the points for each event.

* Total points for the first day, second day, and overall total points are also shown.

## Dependencies
Spring Boot: The application uses the Spring Boot framework for backend functionality.
JavaFX: JavaFX is used for building the graphical user interface.

## Project Structure

* DecathlonApplication: The main class that initializes the Spring Boot application and launches the JavaFX UI.

* DecathlonController: A Spring MVC controller responsible for handling requests related to points calculation.

* DecathlonService: A service class containing the logic for calculating points based on event results.

*DecathlonUI: The JavaFX application class that builds and displays the graphical user interface.

## Build and Run
To build and run the application, ensure you have Maven installed. 

## Testing

The project includes a set of unit tests to ensure the correctness of the Decathlon 
Points Calculator's functionality. The tests are implemented using the JUnit 5 framework 
and cover both the controller and service classes.

* Test CalculatePointsEndpoint:
Verifies that the /calculatePoints endpoint returns the expected results for a given event 
and result.

* Test CalculatePointsForTrackEvent:
Validates the calculation of points for a track event (e.g., "100M") with a specific result.
Compares the calculated points against the expected value.

* Test CalculatePointsForFieldEvent:
Ensures the correct calculation of points for a field event (e.g., "Long Jump") with a given result.
Compares the calculated points against the expected value.

* Test UnknownEvent:
Tests the handling of an unknown event, verifying that an IllegalArgumentException is thrown.
Compares the exception message against the expected error message.

## Running Tests
To run the tests, execute the following command:

mvn test

## Test Results
After running the tests, review the output in the console to ensure that all tests pass successfully. 


