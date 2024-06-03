package com.example.hospitalmanagmentsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class ReceptionController {

    @FXML
    private void handleAddNewPatient(ActionEvent event) {
        try {
            // Load the NewPatient.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPatient.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("New Patient Form");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRoom(ActionEvent event) {
        try {
            // Load the Room.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Room.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Room Information");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDepartment(ActionEvent event) {
        try {
            // Load the Department.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Department.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Department Information");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAllEmployeeInfo(ActionEvent event) {
        // Implement the action for showing all employee information
        System.out.println("All Employee Info button clicked");
    }

    @FXML
    private void handlePatientInfo(ActionEvent event) {
        // Implement the action for showing patient information
        System.out.println("Patient Info button clicked");
    }

    @FXML
    private void handlePatientDischarge(ActionEvent event) {
        // Implement the action for discharging a patient
        System.out.println("Patient Discharge button clicked");
    }

    @FXML
    private void handleUpdatePatientDetails(ActionEvent event) {
        // Implement the action for updating patient details
        System.out.println("Update Patient Details button clicked");
    }

    @FXML
    private void handleHospitalAmbulance(ActionEvent event) {
        // Implement the action for handling Hospital Ambulance
        System.out.println("Hospital Ambulance button clicked");
    }

    @FXML
    private void handleSearchRoom(ActionEvent event) {
        // Implement the action for searching a room
        System.out.println("Search Room button clicked");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        // Implement the action for logging out
        System.out.println("Logout button clicked");
    }
}
