package com.example.hospitalmanagmentsystem;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Department extends Application {

    @FXML
    private TableView<DepartmentModel> tableView;
    @FXML
    private TableColumn<DepartmentModel, String> departmentColumn;
    @FXML
    private TableColumn<DepartmentModel, String> phoneColumn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Department.fxml")));
        primaryStage.setTitle("Department");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    @FXML
    private void initialize() {
        departmentColumn.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        populateTable();
    }

    private void populateTable() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            String query = "SELECT * FROM department";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String department = resultSet.getString("Department");
                String phone = resultSet.getString("Phone");
                DepartmentModel departmentModel = new DepartmentModel(department, phone);
                tableView.getItems().add(departmentModel);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error fetching department data: " + e.getMessage());
        }
    }

    public static class DepartmentModel {
        private final StringProperty department;
        private final StringProperty phone;

        public DepartmentModel(String department, String phone) {
            this.department = new SimpleStringProperty(department);
            this.phone = new SimpleStringProperty(phone);
        }

        public StringProperty departmentProperty() {
            return department;
        }

        public StringProperty phoneProperty() {
            return phone;
        }
    }

    @FXML
    private void handleBack() {


    }

    public static void main(String[] args) {
        launch(args);
    }
}
