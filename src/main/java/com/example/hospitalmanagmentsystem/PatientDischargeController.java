package com.example.hospitalmanagmentsystem;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class PatientDischargeController extends Application {

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label roomLabel;
    @FXML
    private Label inDateLabel;
    @FXML
    private Label outTimeLabel;
    @FXML
    private Button dischargeButton;
    @FXML
    private Button checkButton;
    @FXML
    private Button backButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/hospitalmanagmentsystem/PatientDischarge.fxml"));
        primaryStage.setTitle("Patient Discharge");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void initialize() {
        loadPatientData();
        outTimeLabel.setText(new Date().toString());
    }

    private void loadPatientData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM patient_info";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                choiceBox.getItems().add(resultSet.getString("number"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCheck(ActionEvent event) {
        String selectedNumber = choiceBox.getValue();
        if (selectedNumber != null) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM patient_info WHERE number = '" + selectedNumber + "'";
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    roomLabel.setText(resultSet.getString("Room_Number"));
                    inDateLabel.setText(resultSet.getString("Date"));
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleDischarge(ActionEvent event) {
        String selectedNumber = choiceBox.getValue();
        if (selectedNumber != null) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
                Statement statement = connection.createStatement();
                String deleteQuery = "DELETE FROM patient_info WHERE number = '" + selectedNumber + "'";
                String updateQuery = "UPDATE room SET Availability = 'Available' WHERE room_no = '" + roomLabel.getText() + "'";
                statement.executeUpdate(deleteQuery);
                statement.executeUpdate(updateQuery);

                statement.close();
                connection.close();

                System.out.println("Patient discharged successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
