module com.example.hospitalmanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hospitalmanagmentsystem to javafx.fxml;
    exports com.example.hospitalmanagmentsystem;
}