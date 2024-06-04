package com.example.hospitalmanagmentsystem;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UpdatePatientController extends Application {

    @FXML
    private ComboBox<String> nameComboBox;

    @FXML
    private TextField textFieldRoom;

    @FXML
    private TextField textFieldAmount;

    @FXML
    private TextField textFieldDisease;

    @FXML
    private Button update;

    @FXML
    private Button back;

    private String selectedPatientName;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePatient.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 940, 490);
        primaryStage.setTitle("Update Patient Details");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void initialize() {
        // Populate the ComboBox with patient names fetched from the database
        populateComboBox();
    }

    private void populateComboBox() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            String query = "SELECT Name FROM patient_info";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                nameComboBox.getItems().add(resultSet.getString("Name"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCheck() {
        selectedPatientName = nameComboBox.getValue();
        if (selectedPatientName != null) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
                String query = "SELECT Room_Number, Deposit, Disease FROM patient_info WHERE Name=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, selectedPatientName);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    textFieldRoom.setText(resultSet.getString("Room_Number"));
                    textFieldAmount.setText(resultSet.getString("Deposit"));
                    textFieldDisease.setText(resultSet.getString("Disease"));
                } else {
                    showAlert("Patient not found in the database.");
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Please select a patient name.");
        }
    }

    @FXML
    private void handleUpdate() {
        if (selectedPatientName != null) {
            String room = textFieldRoom.getText();
            String amountPaid = textFieldAmount.getText();
            String disease = textFieldDisease.getText();
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
                String query = "UPDATE patient_info SET Room_Number=?, Deposit=?, Disease=? WHERE Name=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, room);
                statement.setString(2, amountPaid);
                statement.setString(3, disease);
                statement.setString(4, selectedPatientName);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    showAlert("Patient record updated successfully.");
                    clearFields();
                } else {
                    showAlert("Failed to update patient record.");
                }
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Please select a patient name.");
        }
    }

    @FXML
    private void handleBack() {
        // Handle back button action, e.g., navigate to another scene
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        textFieldRoom.clear();
        textFieldAmount.clear();
        textFieldDisease.clear();
    }
}
