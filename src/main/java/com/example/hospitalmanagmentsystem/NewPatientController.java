package com.example.hospitalmanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewPatientController {

    @FXML
    private void handleBack(ActionEvent event) {
        // Get the current stage (window) of the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close the current stage (window)
        stage.close();
    }

    @FXML
    private void handleAddPatient() {
        // Your code to add the patient to the database goes here
        System.out.println("Adding patient to the database...");
    }

}
