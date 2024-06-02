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
    private RadioButton r1;
    @FXML
    private RadioButton r2;
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
        System.out.println("Add button clicked"); // Debug print statement

        // Get user inputs
        String idType = idComboBox.getValue();
        String number = numberTextField.getText();
        String name = nameTextField.getText();
        String gender = r1.isSelected() ? "Male" : "Female";
        String disease = diseaseTextField.getText();
        String roomNumber = roomComboBox.getValue();
        LocalDate date = datePicker.getValue();
        String time = date != null ? date.toString() : null; // Assuming time is represented by the date
        String deposit = depositTextField.getText();

        // Print user inputs for debugging
        System.out.println("ID Type: " + idType);
        System.out.println("Number: " + number);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Disease: " + disease);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Time: " + time);
        System.out.println("Deposit: " + deposit);

        // Insert into database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            String query = "INSERT INTO patient_info (ID, Number, Name, Gender, Disease, Room_Number, Time, Deposit, Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idType);
            statement.setString(2, number);
            statement.setString(3, name);
            statement.setString(4, gender);
            statement.setString(5, disease);
            statement.setString(6, roomNumber);
            statement.setString(7, time);
            statement.setString(8, deposit);
            statement.setString(9, LocalDate.now().toString()); // Current date
            statement.executeUpdate();
            connection.close();
            System.out.println("Patient added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }
}
