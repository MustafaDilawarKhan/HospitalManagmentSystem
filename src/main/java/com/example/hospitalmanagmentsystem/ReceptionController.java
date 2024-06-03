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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPatient.fxml"));
            Parent root = loader.load();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Room.fxml"));
            Parent root = loader.load();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Department.fxml"));
            Parent root = loader.load();
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeInfo.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Employee Information");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePatientInfo(ActionEvent event) {
        System.out.println("Patient Info button clicked");
    }

    @FXML
    private void handlePatientDischarge(ActionEvent event) {
        System.out.println("Patient Discharge button clicked");
    }

    @FXML
    private void handleUpdatePatientDetails(ActionEvent event) {
        System.out.println("Update Patient Details button clicked");
    }

    @FXML
    private void handleHospitalAmbulance(ActionEvent event) {
        System.out.println("Hospital Ambulance button clicked");
    }

    @FXML
    private void handleSearchRoom(ActionEvent event) {
        System.out.println("Search Room button clicked");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        System.out.println("Logout button clicked");
    }
}
