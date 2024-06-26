package com.example.hospitalmanagmentsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class NewPatientController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewPatient.fxml")));
        primaryStage.setTitle("New Patient Form");
        primaryStage.setScene(new Scene(root, 850, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private ChoiceBox<String> idComboBox;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private ChoiceBox<String> genderComboBox;
    @FXML
    private TextField diseaseTextField;
    @FXML
    private ChoiceBox<String> roomComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField depositTextField;

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAddPatient() {
        System.out.println("Add button clicked");

        // Get user inputs
        String idType = idComboBox.getValue();
        String number = numberTextField.getText();
        String name = nameTextField.getText();
        String gender = genderComboBox.getValue();
        String disease = diseaseTextField.getText();
        String roomNumber = roomComboBox.getValue();
        LocalDate date = datePicker.getValue();
        String time = date != null ? date.toString() : null;
        String deposit = depositTextField.getText();

        if (roomNumber == null || roomNumber.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Room Not Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a room before adding the patient!");
            alert.showAndWait();
            return;
        }

        System.out.println("ID Type: " + idType);
        System.out.println("Number: " + number);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Disease: " + disease);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Deposit: " + deposit);

        // Check room availability
        boolean roomAvailable = checkRoomAvailability(roomNumber);

        if (roomAvailable) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
                String query = "INSERT INTO patient_info (ID, Number, Name, Gender, Disease, Room_Number, Deposit, Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, idType);
                statement.setString(2, number);
                statement.setString(3, name);
                statement.setString(4, gender);
                statement.setString(5, disease);
                statement.setString(6, roomNumber);
                statement.setString(7, deposit);
                statement.setString(8, LocalDate.now().toString());
                statement.executeUpdate();

                String updateQuery = "UPDATE room SET Availability = 'Occupied' WHERE Room_No = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, roomNumber);
                updateStatement.executeUpdate();

                connection.close();
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Patient record added successfully!");
                successAlert.showAndWait();
                System.out.println("Patient added successfully!");
            } catch (SQLException e) {
                System.out.println("Error adding patient: " + e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Room Occupied");
            alert.setHeaderText(null);
            alert.setContentText("The selected room is already occupied!");
            alert.showAndWait();
        }
    }

    private boolean checkRoomAvailability(String roomNumber) {
        boolean available = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            String query = "SELECT Availability FROM room WHERE Room_No = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, roomNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String availability = resultSet.getString("Availability");
                available = availability.equalsIgnoreCase("Available");
            } else {
                System.out.println("Room " + roomNumber + " not found.");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error checking room availability: " + e.getMessage());
        }
        return available;
    }

    @FXML
    private void initialize() {
        populateRoomComboBox();
    }

    private void populateRoomComboBox() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            String query = "SELECT Room_No FROM room";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            roomComboBox.getItems().clear();

            while (resultSet.next()) {
                roomComboBox.getItems().add(resultSet.getString("Room_No"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error fetching room numbers: " + e.getMessage());
        }
    }
}
