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
        try {
            // Load the reception window FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reception.fxml"));
            Parent root = loader.load();

            // Create a new scene with the reception window content
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any potential errors loading the reception window
        }
    }

    @FXML
    private void handleAddPatient() {
        // Your code to add the patient to the database goes here
        System.out.println("Adding patient to the database...");
    }

}
