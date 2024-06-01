package com.example.hospitalmanagmentsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginController extends Application {

    @FXML
    private TextField textField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView imageView;

    private DatabaseConnection databaseConnection;

    public LoginController() {
        databaseConnection = new DatabaseConnection();
    }

    @FXML
    void handleLoginButton(ActionEvent event) {
        String user = textField.getText();
        String pass = passwordField.getText();

        // Validate user credentials using the database
        if (databaseConnection.validateLogin(user, pass)) {
            // Navigate to the Reception scene or functionality
            try {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Reception.fxml")));
                stage.setScene(new Scene(root, 800, 600));
                stage.setTitle("Hospital Management System - Reception");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Please enter a valid username and password.");
            alert.showAndWait();
        }
    }

    @FXML
    void handleCancelButton(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        primaryStage.setTitle("Hospital Management System - Login");
        primaryStage.setScene(new Scene(root, 750, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
