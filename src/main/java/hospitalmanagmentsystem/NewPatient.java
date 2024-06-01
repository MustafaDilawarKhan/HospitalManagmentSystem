package hospitalmanagmentsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class NewPatient {

    @FXML
    private ChoiceBox<String> comboBox;

    @FXML
    private TextField textFieldNumber;

    @FXML
    private TextField textName;

    @FXML
    private RadioButton r1;

    @FXML
    private RadioButton r2;

    @FXML
    private TextField textFieldDisease;

    @FXML
    private ChoiceBox<String> c1;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private TextField textFieldDeposite;

    @FXML
    void handleAddNewPatient(ActionEvent event) {
        // Add action code here
    }

    @FXML
    void handleBack(ActionEvent event) {
        // Add action code here
    }
}
