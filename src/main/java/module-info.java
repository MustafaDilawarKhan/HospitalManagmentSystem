module com.example.hospitalmanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.hospitalmanagmentsystem to javafx.fxml;
    exports com.example.hospitalmanagmentsystem;
    exports hospitalmanagmentsystem;
    opens hospitalmanagmentsystem to javafx.fxml;
}