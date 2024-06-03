package com.example.hospitalmanagmentsystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class EmployeeInfoController extends Application {

    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, Integer> ageColumn;

    @FXML
    private TableColumn<Employee, String> phoneNumberColumn;

    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    @FXML
    private TableColumn<Employee, String> emailColumn;

    @FXML
    private TableColumn<Employee, String> idPassportColumn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EmployeeInfo.fxml")));
        primaryStage.setTitle("Employee Information");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        idPassportColumn.setCellValueFactory(new PropertyValueFactory<>("idPassport"));

        loadEmployeeData();
    }

    private void loadEmployeeData() {
        ObservableList<Employee> data = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Employee_Info";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                data.add(new Employee(
                        resultSet.getString("Name"),
                        resultSet.getInt("Age"),
                        resultSet.getString("Phone_Number"),
                        resultSet.getDouble("Salary"),
                        resultSet.getString("Gmail"),
                        resultSet.getString("ID_Passport")
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public static class Employee {
        private String name;
        private int age;
        private String phoneNumber;
        private double salary;
        private String email;
        private String idPassport;

        public Employee(String name, int age, String phoneNumber, double salary, String email, String idPassport) {
            this.name = name;
            this.age = age;
            this.phoneNumber = phoneNumber;
            this.salary = salary;
            this.email = email;
            this.idPassport = idPassport;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public double getSalary() {
            return salary;
        }

        public String getEmail() {
            return email;
        }

        public String getIdPassport() {
            return idPassport;
        }
    }
}
