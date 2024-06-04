package com.example.hospitalmanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.application.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientsInfoController extends Application {

    @FXML
    private TableView<Patient> table;

    @FXML
    private TableColumn<Patient, String> idColumn;

    @FXML
    private TableColumn<Patient, String> numberColumn;

    @FXML
    private TableColumn<Patient, String> nameColumn;

    @FXML
    private TableColumn<Patient, String> genderColumn;

    @FXML
    private TableColumn<Patient, String> diseaseColumn;

    @FXML
    private TableColumn<Patient, String> roomColumn;

    @FXML
    private TableColumn<Patient, String> depositColumn;

    @FXML
    private TableColumn<Patient, String> dateColumn;

    @FXML
    private Button backButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PatientsInfo.fxml"));
        primaryStage.setTitle("All Patient Information");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        diseaseColumn.setCellValueFactory(new PropertyValueFactory<>("disease"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        depositColumn.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadPatientData();
    }

    private void loadPatientData() {
        ObservableList<Patient> data = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM patient_info";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                data.add(new Patient(
                        resultSet.getString("ID"),
                        resultSet.getString("Number"),
                        resultSet.getString("Name"),
                        resultSet.getString("Gender"),
                        resultSet.getString("Disease"),
                        resultSet.getString("Room_Number"),
                        resultSet.getString("Deposit"),
                        resultSet.getString("Date")
                ));
            }
            table.setItems(data);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public static class Patient {
        private String id;
        private String number;
        private String name;
        private String gender;
        private String disease;
        private String room;
        private String deposit;
        private String date;

        public Patient(String id, String number, String name, String gender, String disease, String room, String deposit, String date) {
            this.id = id;
            this.number = number;
            this.name = name;
            this.gender = gender;
            this.disease = disease;
            this.room = room;
            this.deposit = deposit;
            this.date = date;
        }

        public String getId() {
            return id;
        }

        public String getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getDisease() {
            return disease;
        }

        public String getRoom() {
            return room;
        }

        public String getDeposit() {
            return deposit;
        }

        public String getDate() {
            return date;
        }
    }
}
