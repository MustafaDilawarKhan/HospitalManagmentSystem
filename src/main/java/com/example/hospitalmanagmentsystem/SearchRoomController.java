package com.example.hospitalmanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchRoomController {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TableView<Room> tableView;

    @FXML
    private TableColumn<Room, String> Room_NoColumn;

    @FXML
    private TableColumn<Room, String> AvailabilityColumn;

    @FXML
    private TableColumn<Room, String> PriceColumn;

    @FXML
    private TableColumn<Room, String> Room_TypeColumn;

    @FXML
    private Button searchButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        choiceBox.setItems(FXCollections.observableArrayList("Available", "Occupied"));

        Room_NoColumn.setCellValueFactory(new PropertyValueFactory<>("Room_No"));
        AvailabilityColumn.setCellValueFactory(new PropertyValueFactory<>("Availability"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Room_TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Room_Type"));

        loadRoomData();
    }

    private void loadRoomData() {
        ObservableList<Room> data = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM room";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                data.add(new Room(
                        resultSet.getString("Room_No"),
                        resultSet.getString("Availability"),
                        resultSet.getString("Price"),
                        resultSet.getString("Room_Type")
                ));
            }
            tableView.setItems(data);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        String selectedStatus = choiceBox.getValue();
        if (selectedStatus != null) {
            ObservableList<Room> data = FXCollections.observableArrayList();
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM room WHERE Availability = '" + selectedStatus + "'";
                System.out.println("Executing query: " + query);
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    data.add(new Room(
                            resultSet.getString("Room_No"),
                            resultSet.getString("Availability"),
                            resultSet.getString("Price"),
                            resultSet.getString("Room_Type")
                    ));
                }
                System.out.println("Number of rooms found: " + data.size());
                tableView.setItems(data);

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No status selected.");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public static class Room {
        private String Room_No;
        private String Availability;
        private String Price;
        private String Room_Type;

        public Room(String Room_No, String Availability, String Price, String Room_Type) {
            this.Room_No = Room_No;
            this.Availability = Availability;
            this.Price = Price;
            this.Room_Type = Room_Type;
        }

        public String getRoom_No() {
            return Room_No;
        }

        public String getAvailability() {
            return Availability;
        }

        public String getPrice() {
            return Price;
        }

        public String getRoom_Type() {
            return Room_Type;
        }
    }
}
