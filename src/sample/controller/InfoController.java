package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.model.Contact;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
    private ContactApp contactApp;

    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label patronymic;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    Button okButton;


    public void getContactAppController(ContactApp contactApp) {
        this.contactApp = contactApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void readInfo(Contact selectedItem) {
        firstName.setText(selectedItem.getFirstName());
        lastName.setText(selectedItem.getLastName());
        patronymic.setText(selectedItem.getPatronymic());
        email.setText(selectedItem.getEmail());
        phone.setText(selectedItem.getPhone());
    }

    @FXML
    public void getBack() throws Exception {
        Stage stage = (Stage) (okButton.getScene().getWindow());
        stage.close();
        contactApp.start(new Stage());
    }
}
