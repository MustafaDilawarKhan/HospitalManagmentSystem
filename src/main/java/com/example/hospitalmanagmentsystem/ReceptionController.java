package com.example.hospitalmanagmentsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;


public class ReceptionController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Reception.fxml"));
        primaryStage.setTitle("Hospital Management System - Reception");
        primaryStage.setScene(new Scene(root, 532, 566));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hospitalmanagmentsystem/PatientsInfo.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Patient Information");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePatientDischarge(ActionEvent event) {
        System.out.println("Patient Discharge button clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hospitalmanagmentsystem/PatientDischarge.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Patient Discharge");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleUpdatePatientDetails(ActionEvent event) {
        System.out.println("Update Patient Details button clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hospitalmanagmentsystem/UpdatePatient.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Update Patient Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchRoom(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hospitalmanagmentsystem/SearchRoom.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Search Room");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleLogout(ActionEvent event) {
        System.out.println("Logout button clicked");
        Stage receptionStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        receptionStage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hospitalmanagmentsystem/Login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Hospital Management System - Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

