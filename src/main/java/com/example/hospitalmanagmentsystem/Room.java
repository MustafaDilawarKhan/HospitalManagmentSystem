package com.example.hospitalmanagmentsystem;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Room extends Application {

    @FXML
    private TableView<RoomModel> tableView;
    @FXML
    private TableColumn<RoomModel, String> roomNoColumn;
    @FXML
    private TableColumn<RoomModel, String> availabilityColumn;
    @FXML
    private TableColumn<RoomModel, String> priceColumn;
    @FXML
    private TableColumn<RoomModel, String> roomTypeColumn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Room.fxml")));
        primaryStage.setTitle("Room");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    @FXML
    private void initialize() {
        roomNoColumn.setCellValueFactory(cellData -> cellData.getValue().roomNoProperty());
        availabilityColumn.setCellValueFactory(cellData -> cellData.getValue().availabilityProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        roomTypeColumn.setCellValueFactory(cellData -> cellData.getValue().roomTypeProperty());

        populateTable();
    }

    private void populateTable() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            String query = "SELECT * FROM room";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String roomNo = resultSet.getString("Room_no");
                String availability = resultSet.getString("Availability");
                String price = resultSet.getString("Price");
                String roomType = resultSet.getString("Room_type");
                RoomModel roomModel = new RoomModel(roomNo, availability, price, roomType);
                tableView.getItems().add(roomModel);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error fetching room data: " + e.getMessage());
        }
    }

    public static class RoomModel {
        private final StringProperty roomNo;
        private final StringProperty availability;
        private final StringProperty price;
        private final StringProperty roomType;

        public RoomModel(String roomNo, String availability, String price, String roomType) {
            this.roomNo = new SimpleStringProperty(roomNo);
            this.availability = new SimpleStringProperty(availability);
            this.price = new SimpleStringProperty(price);
            this.roomType = new SimpleStringProperty(roomType);
        }

        public StringProperty roomNoProperty() {
            return roomNo;
        }

        public StringProperty availabilityProperty() {
            return availability;
        }

        public StringProperty priceProperty() {
            return price;
        }

        public StringProperty roomTypeProperty() {
            return roomType;
        }
    }

    @FXML
    private void handleBack() {
        // Add your back button action here
    }

    public static void main(String[] args) {
        launch(args);
    }
}
