package com.example.hospitalmanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {

    Connection connection;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateLogin(String user, String pass) {
        String query = "SELECT * FROM login WHERE ID = ? AND Pass = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // returns true if a match is found
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
