module com.example.hospitalmanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hospitalmanagmentsystem to javafx.fxml;
    exports com.example.hospitalmanagmentsystem;
}