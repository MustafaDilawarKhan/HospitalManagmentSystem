package com.example.hospitalmanagmentsystem;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Room extends Application {

    private TableView<RoomData> tableView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Room.fxml"));
        Scene scene = new Scene(root, 600, 600);

        initializeTableView(scene);
        initializeBackButton(scene, primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();

        populateTableView();
    }

    private void initializeTableView(Scene scene) {
        tableView = (TableView<RoomData>) scene.lookup("#tableView");

        TableColumn<RoomData, String> roomNoCol = new TableColumn<>("Room No");
        roomNoCol.setCellValueFactory(cellData -> cellData.getValue().roomNoProperty());

        TableColumn<RoomData, String> availabilityCol = new TableColumn<>("Availability");
        availabilityCol.setCellValueFactory(cellData -> cellData.getValue().availabilityProperty());

        TableColumn<RoomData, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        TableColumn<RoomData, String> roomTypeCol = new TableColumn<>("Room Type");
        roomTypeCol.setCellValueFactory(cellData -> cellData.getValue().roomTypeProperty());

        tableView.getColumns().addAll(roomNoCol, availabilityCol, priceCol, roomTypeCol);
    }

    private void initializeBackButton(Scene scene, Stage primaryStage) {
        Button backButton = (Button) scene.lookup("#backButton");
        backButton.setOnAction(event -> primaryStage.close());
    }

    private void populateTableView() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM room");
            while (rs.next()) {
                String roomNo = rs.getString("room_no");
                String availability = rs.getString("Availability");
                double price = rs.getDouble("Price");
                String roomType = rs.getString("Room_type");
                tableView.getItems().add(new RoomData(roomNo, availability, price, roomType));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class RoomData {
        private final SimpleStringProperty roomNo;
        private final SimpleStringProperty availability;
        private final SimpleDoubleProperty price;
        private final SimpleStringProperty roomType;

        public RoomData(String roomNo, String availability, double price, String roomType) {
            this.roomNo = new SimpleStringProperty(roomNo);
            this.availability = new SimpleStringProperty(availability);
            this.price = new SimpleDoubleProperty(price);
            this.roomType = new SimpleStringProperty(roomType);
        }

        public String getRoomNo() {
            return roomNo.get();
        }

        public SimpleStringProperty roomNoProperty() {
            return roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo.set(roomNo);
        }

        public String getAvailability() {
            return availability.get();
        }

        public SimpleStringProperty availabilityProperty() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability.set(availability);
        }

        public double getPrice() {
            return price.get();
        }

        public SimpleDoubleProperty priceProperty() {
            return price;
        }

        public void setPrice(double price) {
            this.price.set(price);
        }

        public String getRoomType() {
            return roomType.get();
        }

        public SimpleStringProperty roomTypeProperty() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType.set(roomType);
        }
    }
}
